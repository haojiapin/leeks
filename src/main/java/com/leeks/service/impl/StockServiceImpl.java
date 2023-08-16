package com.leeks.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leeks.dao.StockDao;
import com.leeks.entity.Stock;
import com.leeks.service.StockService;
import org.springframework.stereotype.Service;

/**
 * (Stock)表服务实现类
 *
 * @author makejava
 * @since 2023-08-01 11:43:33
 */
@Service("stockService")
public class StockServiceImpl extends ServiceImpl<StockDao, Stock> implements StockService {

}

