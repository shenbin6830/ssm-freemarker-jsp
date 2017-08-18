package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.deal.service.DealCategoryService;
import com.deal.service.DealService;
import com.entity.Deal;
import com.entity.DealCategory;
import com.site.web.base.controller.BaseSiteController;
import com.site.web.site.dto.IndexCategoryDealDTO;



@Controller
public class IndexController extends BaseSiteController{
	@Autowired private DealService dealService;
	//@Autowired private DealCategoryService categoryService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest request) {
		
		return "index";
	}
}
