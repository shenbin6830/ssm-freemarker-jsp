package com.deal.cache;

import java.util.List;

import org.springframework.stereotype.Component;

import com.entity.DealCategory;
import com.framework.cache.CacheOperator;


/**
 * @author yourname
 * @date 2017年8月13日 下午2:32:57
 * 
 */
@Component
public class DealCategoryCacheOperator extends CacheOperator {
	  /**
     * 获取全部DealCategory
     * @return
     */
    public List<DealCategory> getAllDealCategories() {
        return super.getEntitiesByKeyPrefix("DealCategory", DealCategory.class);
    }
    /**
     * 向缓存中增加DealCategory
     * @param dealCategory
     */
    public void putDealCategory(DealCategory dealCategory) {
        super.putEntity("DealCategory." + dealCategory.getId(), dealCategory);

        // 维护其父类别的子类别缓存
        List<DealCategory> subCategories = getSubCategories(dealCategory.getParentId());
        if (subCategories != null && subCategories.size() > 0) {
            if (subCategories.contains(dealCategory)) {
                int index = 0;
                for (int i = 0; i < subCategories.size(); i++) {
                    if (subCategories.get(i).getId() == dealCategory.getId()) {
                        index = i;
                    }
                }
                subCategories.remove(index);
            }
            subCategories.add(dealCategory);

            putSubCategories(dealCategory.getParentId(), subCategories);
        }
    }
    /**
     * 获取下级分类
     * @param parentId
     * @return
     */
    public List<DealCategory> getSubCategories(Long parentId) {
        return super.getEntities("SubDealCategories." + parentId, DealCategory.class);
    }
    /**
     * 增加下级分类
     * @param parentId
     * @param dealCategories
     */
    public void putSubCategories(Long parentId, List<DealCategory> dealCategories) {
        super.putEntities("SubDealCategories." + parentId, dealCategories);
    }
}
