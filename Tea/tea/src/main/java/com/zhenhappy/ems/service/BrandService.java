package com.zhenhappy.ems.service;

import com.alibaba.fastjson.JSONObject;
import com.zhenhappy.ems.dto.ProductDto;
import com.zhenhappy.ems.entity.TBrand;
import com.zhenhappy.ems.entity.TExhibitor;
import com.zhenhappy.ems.entity.TExhibitorInfo;
import com.zhenhappy.ems.entity.TProduct;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by wujianbin on 2014-08-04.
 */
@Service
public class BrandService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Autowired
    private ExhibitorService exhibitorService;

    @Autowired
    private ProductService productService;

    public List<TBrand> loadBrandsByEid(Integer eid) {
        List<TBrand> brands = hibernateTemplate.find("from TBrand where eid = ? and isDefault=0", eid);
        return brands;
    }

    public List<TBrand> loadAllBrandsByEid(Integer eid) {
        List<TBrand> brands = hibernateTemplate.find("from TBrand where eid = ?", eid);
        return brands;
    }

    @Transactional
    public void saveExhibitorAndBrands(TExhibitorInfo exhibitorInfo) {
        List<TBrand> brands = null;
        if (StringUtils.isNotEmpty(exhibitorInfo.getBrandsData())) {
            brands = JSONObject.parseArray(exhibitorInfo.getBrandsData(), TBrand.class);
        }

        if (brands != null && brands.size() > 0) {
            for (TBrand brand : brands) {
                brand.setCreateTime(new Date());
                brand.setEid(exhibitorInfo.getEid());
                brand.setIsDefault(0);
                hibernateTemplate.save(brand);
            }
        }
        TBrand brand = new TBrand();
        brand.setIsDefault(1);
        brand.setEid(exhibitorInfo.getEid());
        brand.setCreateTime(new Date());
        brand.setBrandName(exhibitorInfo.getCompany());
        hibernateTemplate.save(brand);
        exhibitorService.insert(exhibitorInfo);
    }

    @Transactional
    public void updateExhibitorAndBrands(TExhibitorInfo exhibitorInfo) {
        List<TBrand> brands = loadBrandsByEid(exhibitorInfo.getEid());
        List<TBrand> nowBrands = null;
        if (StringUtils.isNotEmpty(exhibitorInfo.getBrandsData())) {
            nowBrands = JSONObject.parseArray(exhibitorInfo.getBrandsData(), TBrand.class);
        }
        for (TBrand brand : nowBrands) {
            if (brand.getId() != null) {
                TBrand temp = brands.remove(brands.indexOf(brand));
                temp.setBrandName(brand.getBrandName());
                hibernateTemplate.update(temp);
            } else {
                brand.setIsDefault(0);
                brand.setEid(exhibitorInfo.getEid());
                brand.setCreateTime(new Date());
                hibernateTemplate.save(brand);
            }
        }

        for (TBrand brand : brands) {
            hibernateTemplate.delete(brand);
        }

        exhibitorService.update(exhibitorInfo);
    }

    @Transactional
    public void addProductWithBrand(ProductDto productDto, Integer einfoId, List<String> filenames, Integer brandId,TExhibitor exhibitor) {
        TProduct product = productService.addProduct(productDto, einfoId, filenames);
        if(brandId==null){
            TBrand brand = new TBrand();
            brand.setBrandName(exhibitor.getCompany());
            brand.setCreateTime(new Date());
            brand.setEid(exhibitor.getEid());
            brand.setIsDefault(1);
            hibernateTemplate.save(brand);
            brandId = brand.getId();
        }
        //save product to brandId
        jdbcTemplate.execute("insert into t_brand_product values(" + product.getId() + "," + brandId + ")");
    }

}
