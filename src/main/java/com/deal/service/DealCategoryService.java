package com.deal.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author yourname
 * @date 2017年8月12日 下午9:15:12
 * 
 */
public class DealCategoryService {
	private static final Logger logger = LoggerFactory.getLogger(DealCategoryService.class);

    @Autowired private DealCategoryDAO dealCategoryDAO;

    @Autowired private DealCategoryCacheOperator dealCategoryCacheOperator;
}
