package com.leeks.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.leeks.dao.MoneyDao;
import com.leeks.entity.Money;
import com.leeks.entity.Ranking;
import com.leeks.entity.User;
import com.leeks.service.MoneyService;
import com.leeks.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2023-08-01 11:41:02
 */
@Slf4j
@RestController
@RequestMapping
public class UserController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;
    @Resource
    private MoneyService moneyService;
    @Resource
    private MoneyDao moneyDao;

    private final TemplateEngine templateEngine;

    public UserController(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @GetMapping("/ranking")
    public String list(Model model) {
        List<User> userList = userService.list(new QueryWrapper<User>().orderByAsc("id"));
        List<Ranking> rankingList = new ArrayList<>();
        for (User user : userList) {
            //初始收益
            double d = 0.00;
            Ranking ranking = new Ranking();
            ranking.setUserName(user.getName());

            List<Money> moneyStartList = moneyService.list(new QueryWrapper<Money>().eq("user_id", user.getId()).orderByAsc("id"));
            if (moneyStartList == null) {
                ranking.setIncome(d);
                rankingList.add(ranking);
            } else {
                int number = moneyStartList.size() - 1;
                for (int i = 0; i < moneyStartList.size(); i++) {
                    //确定不是最后一次
                    if (i + 1 <= number) {

                        //最新
                        Money moneyNew = moneyStartList.get(i);
                        Double nowMoneyNew = moneyNew.getNowMoney();

                        //下一次
                        Money money = moneyStartList.get(i + 1);
                        Double nowMoney = money.getNowMoney();
                        Double beforeMoney = money.getBeforeMoney();

                        //资金变动逻辑
                        nowMoneyNew = nowMoneyNew + beforeMoney;

                        //每次收益率
                        //收益率 = (结束金额 - 起始金额) / 起始金额
                        d += (nowMoney - nowMoneyNew) / nowMoneyNew * 100;
                    }

                }

                DecimalFormat df = new DecimalFormat("#.00");
                String formattedValue = df.format(d);
                double income = Double.parseDouble(formattedValue);

                ranking.setIncome(income);
                rankingList.add(ranking);
            }
        }
        List<Ranking> rankings = rankingList.stream().sorted(Comparator.comparing(Ranking::getIncome).reversed()).collect(Collectors.toList());
        for (int i = 0; i < rankings.size(); i++) {
            rankings.get(i).setRanking(i + 1);
        }
        model.addAttribute("rankings", rankings);

        // 使用Thymeleaf模板引擎渲染视图模板
        Context context = new Context();
        context.setVariables(model.asMap());
        String renderedHtml = templateEngine.process("ranking.html", context);

        // 返回渲染后的结果
        return renderedHtml;
    }
}

