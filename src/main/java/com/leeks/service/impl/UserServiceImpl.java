package com.leeks.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leeks.dao.UserDao;
import com.leeks.entity.User;
import com.leeks.service.UserService;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2023-08-01 11:41:02
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

}

