package com.zhenhappy.ems.service;

import com.sun.org.apache.xpath.internal.SourceTree;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.zhenhappy.ems.dao.imp.ProductDaoImp;
import com.zhenhappy.ems.dto.*;
import com.zhenhappy.ems.entity.*;
import com.zhenhappy.util.QueryFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by wujianbin on 2014-04-13.
 */
@Service
public class ProductService {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Autowired
    private ProductDaoImp productDaoImp;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public ProductDaoImp getProductDaoImp() {
        return productDaoImp;
    }

    public void setProductDaoImp(ProductDaoImp productDaoImp) {
        this.productDaoImp = productDaoImp;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ProductInfoDto> loadAllProducts(Integer einfoId) {
        List<Map<String, Object>> datas = jdbcTemplate.queryForList("select tp.id, tpt2.class_name as subTypeName,tpt2.class_name_en as subTypeNameEn,tpt1.class_name as parentTypeName,tpt1.class_name_en as parentTypeNameEn,tp.product_name,tp.product_model,tp.product_brand,tp.create_time from (select * from t_product where einfo_id = ?) as tp,t_product_class_relation tpcr,t_product_type tpt1,t_product_type tpt2 where " +
                "tp.id = tpcr.product_id and tpt1.id = tpcr.class_id and tpt2.id = tpcr.parent_class_id", new Object[]{einfoId});
        List<ProductInfoDto> products = new ArrayList<ProductInfoDto>(datas.size());
        for (Map<String, Object> data : datas) {
            ProductInfoDto productInfo = new ProductInfoDto();
            productInfo.setId((Integer) data.get("id"));
            productInfo.setParentTypeName((String) data.get("parentTypeName"));
            productInfo.setParentTypeNameEn((String) data.get("parentTypeNameEn"));
            productInfo.setTypeName((String) data.get("subTypeName"));
            productInfo.setTypeNameEn((String) data.get("subTypeNameEn"));
            productInfo.setProductName((String) data.get("product_name"));
            productInfo.setProductModel((String) data.get("product_model"));
            productInfo.setProductBrand((String) data.get("product_brand"));
            productInfo.setCreateTime((Date) data.get("create_time"));
            products.add(productInfo);
        }
        return products;
    }

    public ProductQueryDto queryProducts(ProductQueryDto queryDto, Integer einfoId) {
        List<String> conditions = new ArrayList<String>();

        conditions.add(" einfoId=" + einfoId);

        if(queryDto.getLocale()!=null&&queryDto.getLocale().equals(Locale.US)){
            if (StringUtils.isNotEmpty(queryDto.getProductName())) {
                conditions.add(" productNameEn like '%" + queryDto.getProductName() + "%' ");
            }
            if (StringUtils.isNotEmpty(queryDto.getProductModel())) {
                conditions.add(" productModelEn like '%" + queryDto.getProductModel() + "%' ");
            }
            if (StringUtils.isNotEmpty(queryDto.getProductBrand())) {
                conditions.add(" productBrandEn like '%" + queryDto.getProductBrand() + "%' ");
            }
            if (StringUtils.isNotEmpty(queryDto.getProductSpec())) {
                conditions.add(" productSpecEn like '%" + queryDto.getProductSpec() + "%' ");
            }
        }else{
            if (StringUtils.isNotEmpty(queryDto.getProductName())) {
                conditions.add(" productName like '%" + queryDto.getProductName() + "%' ");
            }
            if (StringUtils.isNotEmpty(queryDto.getProductModel())) {
                conditions.add(" productModel like '%" + queryDto.getProductModel() + "%' ");
            }
            if (StringUtils.isNotEmpty(queryDto.getProductBrand())) {
                conditions.add(" productBrand like '%" + queryDto.getProductBrand() + "%' ");
            }
            if (StringUtils.isNotEmpty(queryDto.getProductSpec())) {
                conditions.add(" productSpec like '%" + queryDto.getProductSpec() + "%' ");
            }
        }

        String conditionsSql = StringUtils.join(conditions, " and ");
        List<TProduct> products = productDaoImp.queryPageByHQL("select count(*) from TProduct where " + conditionsSql, "from TProduct where " + conditionsSql, new Object[]{}, queryDto);
        queryDto.setDatas(products);
        return queryDto;
    }

    @Transactional
    public TProduct addProduct(ProductDto productDto, Integer einfo_id, List<String> fileNames) {
        TProduct product = new TProduct();
        BeanUtils.copyProperties(productDto, product);
        product.setCreateTime(new Date());
        product.setEinfoId(einfo_id);
        hibernateTemplate.save(product);
        for (String fileName : fileNames) {
            TProductImage image = new TProductImage();
            image.setCreateTime(new Date());
            image.setProductId(product.getId());
            image.setImage(fileName);
            hibernateTemplate.save(image);
        }
        //save product in types
        for (ProductTypeCheck productTypeCheck : productDto.getSelected()) {
            TProductClassRelation productClassRelation = new TProductClassRelation();
            productClassRelation.setCreateTime(new Date());
            productClassRelation.setProductId(product.getId());
            productClassRelation.setClassId(productTypeCheck.getSubTypeId());
            productClassRelation.setParentClassId(productTypeCheck.getParentTypeId());
            hibernateTemplate.save(productClassRelation);


            if (productTypeCheck.getIsOther().intValue() == 1) {
                TProductClassRelationOther productClassRelationOther = new TProductClassRelationOther();
                productClassRelationOther.setCreateTime(new Date());
                productClassRelationOther.setName(productTypeCheck.getOtherDescription());
                productClassRelationOther.setProductClassId(productClassRelation.getId());
                hibernateTemplate.save(productClassRelationOther);
            }

            //如果展商没有为自己选择所属该类产品，自动为该展商挂上属于该类产品
            List items = hibernateTemplate.find("from TExhibitorClass where einfoId= ? and parentClassId = ? and classId = ?",einfo_id,productTypeCheck.getParentTypeId(),productTypeCheck.getSubTypeId());
            if(items.size()==0){
                TExhibitorClass exhibitorClass = new TExhibitorClass();
                exhibitorClass.setCreateTime(new Date());
                exhibitorClass.setClassId(productTypeCheck.getSubTypeId());
                exhibitorClass.setParentClassId(productTypeCheck.getParentTypeId());
                exhibitorClass.setEinfoId(einfo_id);
                hibernateTemplate.save(exhibitorClass);
                //if other
                if (productTypeCheck.getIsOther().intValue() == 1) {
                    TExhibitorClassOther exhibitorClassOther = new TExhibitorClassOther();
                    exhibitorClassOther.setCreateTime(new Date());
                    exhibitorClassOther.setExhibitorClassId(exhibitorClass.getId());
                    exhibitorClassOther.setName(productTypeCheck.getOtherDescription());
                    hibernateTemplate.save(exhibitorClassOther);
                }
            }
        }
        return product;
    }

    public List<ProductTypeCheck> loadAllProductTypesWithCheck(Integer productid) {
        List<ProductTypeCheck> productTypeChecks = new ArrayList<ProductTypeCheck>();
        List<Map<String, Object>> items = jdbcTemplate.queryForList("select tec.id,tec.parent_class_id,tec.class_id,tpt.is_other from t_product_class_relation tec,t_product_type tpt  where tec.product_id = ? and tec.class_id = tpt.id", new Object[]{productid});
        for (Map<String, Object> item : items) {
            ProductTypeCheck productTypeCheck = new ProductTypeCheck();
            productTypeCheck.setIsOther((Integer) item.get("is_other"));
            productTypeCheck.setParentTypeId((Integer) item.get("parent_class_id"));
            productTypeCheck.setSubTypeId((Integer) item.get("class_id"));
            if (productTypeCheck.getIsOther() != null && ((Integer) item.get("is_other")).intValue() == 1) {
                //load other description
                productTypeCheck.setOtherDescription(jdbcTemplate.queryForObject("select name from t_product_class_relation_other where product_class_id = " + ((Integer) item.get("id")), String.class));
            }
            productTypeChecks.add(productTypeCheck);
        }
        return productTypeChecks;
    }

    public TProductImage getProductLogo(Integer id) {
        List<TProductImage> images = hibernateTemplate.find("from TProductImage where id = ?", new Object[]{id});
        return images.size() > 0 ? images.get(0) : null;
    }

    @Transactional
    public void deleteProduct(Integer pid, Integer einfoId) {
        productDaoImp.update("delete from TProduct where id = ? and einfoId=?", new Object[]{pid, einfoId}, QueryFactory.HQL);
    }

    @Transactional
    public void modifyProductTypes(ProductDto productDto, Integer einfoid) {
        jdbcTemplate.execute("delete from t_product_class_relation_other where product_class_id in (select id from t_product_class_relation where product_id =" + productDto.getId() + " )");
        jdbcTemplate.execute("delete from t_product_class_relation where product_id = " + productDto.getId());
        for (ProductTypeCheck productTypeCheck : productDto.getSelected()) {
            TProductClassRelation productClassRelation = new TProductClassRelation();
            productClassRelation.setCreateTime(new Date());
            productClassRelation.setProductId(productDto.getId());
            productClassRelation.setClassId(productTypeCheck.getSubTypeId());
            productClassRelation.setParentClassId(productTypeCheck.getParentTypeId());
            hibernateTemplate.save(productClassRelation);
            if (productTypeCheck.getIsOther().intValue() == 1) {
                TProductClassRelationOther productClassRelationOther = new TProductClassRelationOther();
                productClassRelationOther.setCreateTime(new Date());
                productClassRelationOther.setName(productTypeCheck.getOtherDescription());
                productClassRelationOther.setProductClassId(productClassRelation.getId());
                hibernateTemplate.save(productClassRelationOther);
            }

            //如果展商没有为自己选择所属该类产品，自动为该展商挂上属于该类产品
            List items = hibernateTemplate.find("from TExhibitorClass where einfoId= ? and parentClassId = ? and classId = ?",einfoid,productTypeCheck.getParentTypeId(),productTypeCheck.getSubTypeId());
            if(items.size()==0){
                TExhibitorClass exhibitorClass = new TExhibitorClass();
                exhibitorClass.setCreateTime(new Date());
                exhibitorClass.setClassId(productTypeCheck.getSubTypeId());
                exhibitorClass.setParentClassId(productTypeCheck.getParentTypeId());
                exhibitorClass.setEinfoId(einfoid);
                hibernateTemplate.save(exhibitorClass);
                //if other
                if (productTypeCheck.getIsOther().intValue() == 1) {
                    TExhibitorClassOther exhibitorClassOther = new TExhibitorClassOther();
                    exhibitorClassOther.setCreateTime(new Date());
                    exhibitorClassOther.setExhibitorClassId(exhibitorClass.getId());
                    exhibitorClassOther.setName(productTypeCheck.getOtherDescription());
                    hibernateTemplate.save(exhibitorClassOther);
                }
            }
        }
    }

    @Transactional
    public void modifyProduct(ProductDto productDto, List<Integer> picids, List<String> fileNames, Integer einfoid) {
        TProduct product = new TProduct();
        BeanUtils.copyProperties(productDto, product);
        hibernateTemplate.saveOrUpdate(product);
        if (picids != null && picids.size() > 0) {
            jdbcTemplate.update("delete t_product_image where product_id = ? and id not in (" + StringUtils.join(picids, ",") + ")", new Object[]{productDto.getId()});
        } else {
            jdbcTemplate.update("delete t_product_image where product_id = ?", new Object[]{productDto.getId()});
        }
        for (String fileName : fileNames) {
            TProductImage image = new TProductImage();
            image.setCreateTime(new Date());
            image.setProductId(product.getId());
            image.setImage(fileName);
            hibernateTemplate.save(image);
        }
    }

    /**
     * Get all images's id by product id.
     *
     * @param productId
     * @return
     */
    public List<Integer> queryProductPics(Integer productId) {
        try {
            List<Integer> images = jdbcTemplate.queryForList("select id from t_product_image where product_id =?", new Object[]{productId}, Integer.class);
            return images;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Collection<TypeProductsDto> previewProducts(Integer eid) {
        Map<Integer, TypeProductsDto> type_products = new HashMap<Integer, TypeProductsDto>();
        String queryString = "select t.id,t.product_name,tpt.class_name,tpt.class_name_en,tpt.id as tid,tpt.parent_id as pid from (select * from t_product where einfo_id = ?)t,t_product_class_relation tpcr,t_product_type tpt where t.id = tpcr.product_id and tpt.id = tpcr.class_id";
        List<Map<String, Object>> datas = jdbcTemplate.queryForList(queryString, new Object[]{eid});
        for (Map<String, Object> data : datas) {
            Integer tid = (Integer) data.get("tid");
            TypeProductsDto products = type_products.get(tid);
            if (products == null) {
                products = new TypeProductsDto();
                products.setTypeId(tid);
                products.setTypeName((String) data.get("class_name"));
                products.setTypeNameEn((String) data.get("class_name_en"));
                type_products.put(tid,products);
            }
            products.new Product((String)data.get("product_name"),null,(Integer)data.get("id"));
        }
        return type_products.values();
    }

}
