package com.leeks.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leeks.dao.MoneyDao;
import com.leeks.entity.Money;
import com.leeks.service.MoneyService;
import org.springframework.stereotype.Service;

/**
 * (Money)表服务实现类
 *
 * @author makejava
 * @since 2023-08-01 11:41:53
 */
@Service("moneyService")
public class MoneyServiceImpl extends ServiceImpl<MoneyDao, Money> implements MoneyService {

}

