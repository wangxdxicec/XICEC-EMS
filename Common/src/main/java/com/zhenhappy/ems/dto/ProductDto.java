package com.zhenhappy.ems.dto;

import java.util.Date;
import java.util.List;

/**
 * Created by wujianbin on 2014-04-13.
 */
public class ProductDto {

    private Integer id;
    private Integer einfoId;
    private String productName;
    private String productModel;
    private String origin;
    private String keyWords;
    private String description;
    private String productBrand;
    private String productSpec;
    private String productCount;
    private String packageDescription;
    private String priceDescription;
    //English information
    private String productNameEn;
    private String productNameT;
    private String productModelEn;
    private String originEn;
    private String keyWordsEn;
    private String descriptionEn;
    private String productBrandEn;
    private String productSpecEn;
    private String productCountEn;
    private String packageDescriptionEn;
    private String priceDescriptionEn;

    private Integer flag;
    private Date createTime;

    private String selectString;

    private String imageIds;

    public String getImageIds() {
        return imageIds;
    }

    public void setImageIds(String imageIds) {
        this.imageIds = imageIds;
    }

    public String getSelectString() {
        return selectString;
    }

    public void setSelectString(String selectString) {
        this.selectString = selectString;
    }

    private List<ProductTypeCheck> selected;

    public List<ProductTypeCheck> getSelected() {
        return selected;
    }

    public void setSelected(List<ProductTypeCheck> selected) {
        this.selected = selected;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEinfoId() {
        return einfoId;
    }

    public void setEinfoId(Integer einfoId) {
        this.einfoId = einfoId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductSpec() {
        return productSpec;
    }

    public void setProductSpec(String productSpec) {
        this.productSpec = productSpec;
    }

    public String getProductCount() {
        return productCount;
    }

    public void setProductCount(String productCount) {
        this.productCount = productCount;
    }

    public String getPackageDescription() {
        return packageDescription;
    }

    public void setPackageDescription(String packageDescription) {
        this.packageDescription = packageDescription;
    }

    public String getPriceDescription() {
        return priceDescription;
    }

    public void setPriceDescription(String priceDescription) {
        this.priceDescription = priceDescription;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public String getProductNameEn() {
        return productNameEn;
    }

    public void setProductNameEn(String productNameEn) {
        this.productNameEn = productNameEn;
    }



    public String getProductModelEn() {
        return productModelEn;
    }

    public void setProductModelEn(String productModelEn) {
        this.productModelEn = productModelEn;
    }

    public String getOriginEn() {
        return originEn;
    }

    public void setOriginEn(String originEn) {
        this.originEn = originEn;
    }

    public String getKeyWordsEn() {
        return keyWordsEn;
    }

    public void setKeyWordsEn(String keyWordsEn) {
        this.keyWordsEn = keyWordsEn;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public String getProductBrandEn() {
        return productBrandEn;
    }

    public void setProductBrandEn(String productBrandEn) {
        this.productBrandEn = productBrandEn;
    }

    public String getProductSpecEn() {
        return productSpecEn;
    }

    public void setProductSpecEn(String productSpecEn) {
        this.productSpecEn = productSpecEn;
    }

    public String getProductCountEn() {
        return productCountEn;
    }

    public void setProductCountEn(String productCountEn) {
        this.productCountEn = productCountEn;
    }

    public String getPackageDescriptionEn() {
        return packageDescriptionEn;
    }

    public void setPackageDescriptionEn(String packageDescriptionEn) {
        this.packageDescriptionEn = packageDescriptionEn;
    }

    public String getProductNameT() {
        return productNameT;
    }

    public void setProductNameT(String productNameT) {
        this.productNameT = productNameT;
    }

    public String getPriceDescriptionEn() {
        return priceDescriptionEn;
    }

    public void setPriceDescriptionEn(String priceDescriptionEn) {
        this.priceDescriptionEn = priceDescriptionEn;
    }
}
