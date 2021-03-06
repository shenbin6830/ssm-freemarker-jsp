package com.support.area.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

import com.entity.BaseEntity;

/**
 * 地区
 */
public class Area extends BaseEntity {

    @Getter @Setter private String name;
    @Getter @Setter private Long parentId;
    @Getter @Setter private Integer common;//常用
    @Getter @Setter private AreaType type;//类型:省,市
    @Getter @Setter private Date createTime;
    @Getter @Setter private Date updateTime;

    @Getter @Setter private List<Area> children;

}
