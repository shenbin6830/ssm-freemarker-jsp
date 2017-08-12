package com.deal.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.deal.constant.DealConstant;
import com.deal.dao.DealDAO;
import com.entity.Deal;

/**
 * @author yourname
 * @date 2017年8月12日 下午8:46:19
 * 
 */
@Service
public class DealService {
	private static final Logger logger=LoggerFactory.getLogger(DealService.class);
	
	private DealDAO dealDAO;
//	private DealDetailDAO dealDetailDAO;
//	private ImageService imageService;
//	private DealCategoryService dealCategoryService;

	/**
     * 查询首页显示的商品
     * @param areaId
     * @param categoryIds
     * @return
     */
    public List<Deal> getDealsForIndex(Long areaId, List<Long> categoryIds) {
        return dealDAO.getDealsForIndex(areaId, categoryIds, DealConstant.DEAL_PUBLISH_STATUS_PUBLISH);
    }
}
