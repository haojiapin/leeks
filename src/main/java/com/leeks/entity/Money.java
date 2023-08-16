package com.leeks.entity;

import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * (Money)表实体类
 *
 * @author makejava
 * @since 2023-08-01 11:41:53
 */
@SuppressWarnings("serial")
public class Money extends Model<Money> {
    //主键
    private Integer id;
    //用户id
    private Integer userId;
    //加入资金
    private Double beforeMoney;
    //本期的资金
    private Double nowMoney;
    //创建时间
    private Date createTime;
    //修改时间
    private Date updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getBeforeMoney() {
        return beforeMoney;
    }

    public void setBeforeMoney(Double beforeMoney) {
        this.beforeMoney = beforeMoney;
    }

    public Double getNowMoney() {
        return nowMoney;
    }

    public void setNowMoney(Double nowMoney) {
        this.nowMoney = nowMoney;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
    }

