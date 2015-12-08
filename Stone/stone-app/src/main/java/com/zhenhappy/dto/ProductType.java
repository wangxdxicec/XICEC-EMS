package com.zhenhappy.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wujianbin on 2014-04-12.
 */
public class ProductType implements Comparable<ProductType> {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private ProductType parentType;

    private List<ProductType> childrenTypes = new ArrayList<ProductType>();

    private String typeName;
    private String typeNameEN;
    private String typeNameTw;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeNameEN() {
        return typeNameEN;
    }

    public void setTypeNameEN(String typeNameEN) {
        this.typeNameEN = typeNameEN;
    }

    public String getTypeNameTw() {
        return typeNameTw;
    }

    public void setTypeNameTw(String typeNameTw) {
        this.typeNameTw = typeNameTw;
    }

    private Integer level;

    private Integer isOther;

    private Integer order;

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getIsOther() {
        return isOther;
    }

    public void setIsOther(Integer isOther) {
        this.isOther = isOther;
    }

    public ProductType getParentType() {
        return parentType;
    }

    public void setParentType(ProductType parentType) {
        this.parentType = parentType;
    }

    public List<ProductType> getChildrenTypes() {
        return childrenTypes;
    }

    public void setChildrenTypes(List<ProductType> childrenTypes) {
        this.childrenTypes = childrenTypes;
    }



    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public int compareTo(ProductType o) {
        return getOrder().intValue() - o.getOrder();
    }
}
