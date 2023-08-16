package com.leeks.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (Ranking)表实体类
 *
 * @author makejava
 * @since 2023-08-01 11:41:53
 */
@SuppressWarnings("serial")
@Data
public class Ranking extends Model<Ranking> {
    private String userName;
    private int ranking;
    private double income;

}

