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
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
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
            Ranking ranking = new Ranking();
            ranking.setUserName(user.getName());
            Money moneyStart = moneyService.getOne(new QueryWrapper<Money>().eq("user_id", user.getId()).orderByAsc("id").last("limit 1"));
            if (moneyStart == null) {
                ranking.setIncome(0.00);
                rankingList.add(ranking);
            } else {
                Money moneyEnd = moneyService.getOne(new QueryWrapper<Money>().eq("user_id", user.getId()).orderByDesc("id").last("limit 1"));
                Double nowMoneyStart = moneyStart.getNowMoney();
                Double nowMoneyEnd = moneyEnd.getNowMoney();
                //加钱逻辑
                double beforeMoney = moneyDao.selectBeforeMoneyCount(user.getId());
                nowMoneyStart = nowMoneyStart + beforeMoney;


                //收益率 = (结束金额 - 起始金额) / 起始金额
                double d = (nowMoneyEnd - nowMoneyStart) / nowMoneyStart * 100;

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

