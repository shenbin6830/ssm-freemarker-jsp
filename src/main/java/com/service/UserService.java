package com.service;

import com.entity.User;

public interface UserService {
	public abstract User findByKey(int userId) throws Exception;
}
