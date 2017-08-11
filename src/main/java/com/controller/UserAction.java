package com.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.User;
import com.service.UserService;

@Controller
@RequestMapping(value="user")
public class UserAction {
	   @Autowired
	    private UserService userService;
	    
	    @RequestMapping(value = "" ,method = RequestMethod.POST)
	    @ResponseBody
	    public Map<String,Object> findUser(String userId) throws Exception {
	        User user = userService.findByKey(Integer.parseInt(userId));
	        Map<String,Object> map = new HashMap<String,Object>();
	        map.put("userName", user.getUserName());
	        
	        return map;
	    }
}	
