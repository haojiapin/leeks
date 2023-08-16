package com.leeks.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leeks.entity.Money;
import com.leeks.service.MoneyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Money)表控制层
 *
 * @author makejava
 * @since 2023-08-01 11:41:53
 */
@Slf4j
@RestController
@RequestMapping("money")
public class MoneyController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private MoneyService moneyService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param money 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<Money> page, Money money) {
        return success(this.moneyService.page(page, new QueryWrapper<>(money)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.moneyService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param money 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody Money money) {
        return success(this.moneyService.save(money));
    }

    /**
     * 修改数据
     *
     * @param money 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody Money money) {
        return success(this.moneyService.updateById(money));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.moneyService.removeByIds(idList));
    }
}

