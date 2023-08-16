package com.leeks.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leeks.entity.Stock;
import com.leeks.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Stock)表控制层
 *
 * @author makejava
 * @since 2023-08-01 11:43:33
 */
@Slf4j
@RestController
@RequestMapping("stock")
public class StockController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private StockService stockService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param stock 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<Stock> page, Stock stock) {
        return success(this.stockService.page(page, new QueryWrapper<>(stock)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.stockService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param stock 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody Stock stock) {
        return success(this.stockService.save(stock));
    }

    /**
     * 修改数据
     *
     * @param stock 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody Stock stock) {
        return success(this.stockService.updateById(stock));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.stockService.removeByIds(idList));
    }
}

