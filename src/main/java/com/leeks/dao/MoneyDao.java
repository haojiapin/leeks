package com.leeks.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leeks.entity.Money;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * (Money)表数据库访问层
 *
 * @author makejava
 * @since 2023-08-01 11:41:53
 */
public interface MoneyDao extends BaseMapper<Money> {

    @Select("select SUM(before_money) from money where user_id = #{userId}")
    double selectBeforeMoneyCount(@Param("userId") Integer userId);
}

