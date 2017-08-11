package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller //控制器（注入服务）
@RequestMapping(value="") //访问地址，value="index" 则访问地址为：XXXX/index
public class IndexAction {
	  /**
     * @param value ：值同上用于访问命名
     */
    @RequestMapping(value = "" ,method = RequestMethod.GET)
    public ModelAndView initLoad() throws Exception {
        //值为视图文件所在位置。
        ModelAndView mav = new ModelAndView("index");
        
        return mav;
    }
    @RequestMapping(value = "index" ,method = RequestMethod.GET)
    public String index() {
      
        return "index";
    }
}
