package com.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.orm.mapper.UserMapper;
import com.entity.User;
import com.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
    private UserMapper usermapper;

    public User findByKey(int userId) {
        
        return usermapper.selectByPrimaryKey(userId);
    }


}
