package com.deal.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.deal.cache.DealCategoryCacheOperator;
import com.deal.dao.DealCategoryDAO;
import com.entity.DealCategory;



/**
 * @author yourname
 * @date 2017年8月12日 下午9:15:12
 * 
 */
@Service
public class DealCategoryService {
	private static final Logger logger = LoggerFactory.getLogger(DealCategoryService.class);

    @Autowired private DealCategoryDAO dealCategoryDAO;

    @Autowired private DealCategoryCacheOperator dealCategoryCacheOperator;
    
    /**
     * 查询所有分类,按顺序显示
     * @return
     */
    public List<DealCategory> getCategories() {
        List<DealCategory> dealCategories = getAllWithoutDeleted();//从缓存或数据库中查询全部

        //JDK8的stream处理,把根分类区分出来
        List<DealCategory> roots = dealCategories.stream().filter(dealCategory -> (dealCategory.getParentId() == 0)).collect(Collectors.toList());

        //对跟分类进行排序
        roots.sort(new Comparator<DealCategory>() {
            @Override
            public int compare(DealCategory o1, DealCategory o2) {
                return o1.getOrderNum() > o2.getOrderNum() ? 1 : -1;
            }
        });

        //把非根分类区分出来
        List<DealCategory> subs = dealCategories.stream().filter(dealCategory -> (dealCategory.getParentId() != 0)).collect(Collectors.toList());

        //递归构建结构化的分类信息
        roots.forEach(root -> buildSubs(root, subs));
        return roots;
    }
    /**
     * 查询所有未删除的商品类别
     * @return
     */
    private List<DealCategory> getAllWithoutDeleted() {
        List<DealCategory> allCategories = dealCategoryCacheOperator.getAllDealCategories();
//        CacheUtil.getAllEntities(DealCategory.class);
        if (allCategories == null || allCategories.size() == 0) {
            allCategories = dealCategoryDAO.getAllWithoutDeleted();
            for (DealCategory dealCategory : allCategories) {
                dealCategoryCacheOperator.putDealCategory(dealCategory);
//                CacheUtil.putEntity("DealCategory." + dealCategory.getId(), dealCategory);
//                CacheUtil.putEntity(dealCategory);
            }
        }
        return allCategories;
    }
    /**
     * 递归构建
     * @param parent
     * @param subs
     */
    private void buildSubs(DealCategory parent, List<DealCategory> subs) {
        List<DealCategory> children = subs.stream().filter(sub -> (sub.getParentId() == parent.getId())).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(children)) {//有子分类的情况
            parent.setChildren(children);
            children.forEach(child -> buildSubs(child, subs));//再次递归构建
        }
    }


}
