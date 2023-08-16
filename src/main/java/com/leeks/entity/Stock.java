package com.leeks.entity;

import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * (Stock)表实体类
 *
 * @author makejava
 * @since 2023-08-01 11:43:33
 */
@SuppressWarnings("serial")
public class Stock extends Model<Stock> {
    
    private Integer id;
    
    private Integer moneyId;
    
    private String stockCode;
    
    private String stockName;
    //买入价格
    private Double buyingPrice;
    //买入时间
    private Date buyingTime;
    //卖出价格
    private Double sellingPrice;
    //卖出时间
    private Date sellingTime;
    //图片
    private String picture;
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

    public Integer getMoneyId() {
        return moneyId;
    }

    public void setMoneyId(Integer moneyId) {
        this.moneyId = moneyId;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(Double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public Date getBuyingTime() {
        return buyingTime;
    }

    public void setBuyingTime(Date buyingTime) {
        this.buyingTime = buyingTime;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Date getSellingTime() {
        return sellingTime;
    }

    public void setSellingTime(Date sellingTime) {
        this.sellingTime = sellingTime;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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

