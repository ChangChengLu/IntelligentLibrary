package com.cclu.intellignetlibrary.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cclu.intellignetlibrary.mapper.UserMapper;
import com.cclu.intellignetlibrary.model.entity.User;
import com.cclu.intellignetlibrary.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author ChangCheng Lu
 * @date 2023/9/9 13:46
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
}
