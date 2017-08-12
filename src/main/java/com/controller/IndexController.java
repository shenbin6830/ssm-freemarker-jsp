package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.deal.service.DealService;



@Controller
public class IndexController {
	@Autowired private DealService dealService;
	@Autowired private DealCategoryService categoryService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest request) {
		//1.分类
		//2.首页商品
		//3.每个大分类下显示8个商品

		List<DealCategory> categories = categoryService.getCategories();
		model.addAttribute("categories", categories);

		//根据IP确定
		Long areaId = getAreaId(request);


		//1.构造一个结构体存放每个分类的8个商品
		//2.页面循环结构体的集合
		//3.结构体包含8个商品,1各分类
		//4.8个商品分两组

		List<IndexCategoryDealDTO> indexCategoryDealDTOs = new ArrayList<>();
		categories.forEach(category -> {
			List<Deal> deals = dealService.getDealsForIndex(areaId, category.getSelfAndChildrenIds());
			indexCategoryDealDTOs.add(new IndexCategoryDealDTO(category, deals));
		});

		model.addAttribute("indexCategoryDealDTOs", indexCategoryDealDTOs);
		return "index";
	}
}
