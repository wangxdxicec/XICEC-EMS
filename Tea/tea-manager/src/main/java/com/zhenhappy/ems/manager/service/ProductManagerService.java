package com.zhenhappy.ems.manager.service;

import com.zhenhappy.ems.dao.ProductDao;
import com.zhenhappy.ems.dao.ProductImageDao;
import com.zhenhappy.ems.dao.ProductTypeDao;
import com.zhenhappy.ems.entity.TExhibitorInfo;
import com.zhenhappy.ems.entity.TProduct;
import com.zhenhappy.ems.entity.TProductType;
import com.zhenhappy.ems.manager.dto.QueryProductsRequest;
import com.zhenhappy.ems.manager.dto.QueryProductsResponse;
import com.zhenhappy.ems.service.ProductService;
import com.zhenhappy.util.Page;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wujianbin on 2014-04-24.
 */
@Service
public class ProductManagerService extends ProductService {
	@Autowired
    private ProductDao productDao;
	@Autowired
    private ProductTypeDao productTypeDao;
	@Autowired
	private ProductImageDao productImageDao;
	@Autowired
    private ExhibitorManagerService exhibitorManagerService;
	
	/**
	 * 获取产品列表
	 * @param request
	 * @param einfoId
	 * @return
	 */
	@Transactional
    public QueryProductsResponse queryProducts(QueryProductsRequest request, Integer einfoId) {
        Page page = new Page();
        page.setPageSize(request.getRows());
        page.setPageIndex(request.getPage());
		List<TProduct> products = getProductDaoImp().queryPageByHQL("select count(*) from TProduct where einfoId=?", "from TProduct where einfoId=?", new Object[]{einfoId}, page);
		QueryProductsResponse response = new QueryProductsResponse();
		response.setResultCode(0);
        response.setRows(products);
        response.setTotal(page.getTotalCount());
        return response;
    }
	
	/**
	 * 通过id获取产品
	 * @param id
	 * @return
	 */
	@Transactional
    public TProduct loadProductById(Integer id) {
        return productDao.query(id);
    }
	
	/**
	 * 通过einfoid获取产品
	 * @param einfoid
	 * @return
	 */
	@Transactional
    public List<TProduct> loadProductsByEinfoid(Integer einfoId) {
        return productDao.queryByHql("from TProduct where einfoId=?", new Object[]{einfoId});
    }

	/**
	 * 添加产品
	 * @param product
	 * @throws DuplicateProductException
	 */
    @Transactional
    public void addProduct(TProduct product){
        getHibernateTemplate().save(product);
    }
    
    @Transactional
    public void modifyProduct(TProduct product){
        getHibernateTemplate().update(product);
    }
    
    /**
     * 删除产品
     * @param pids
     */
    @Transactional
    public void removeProductsByPIds(Integer[] pids){
    	List<TProduct> products = productDao.loadProductByPIds(pids);
    	for(TProduct product:products){
    		getHibernateTemplate().delete(product);
    	}
    }
    
    /**
     * 删除产品
     * @param eids
     */
    @Transactional
    public void deleteProductByEids(Integer[] eids){
    	List<TExhibitorInfo> exhibitorInfos = exhibitorManagerService.loadExhibitorInfoByExhibitors(exhibitorManagerService.loadSelectedExhibitors(eids));
    	if(exhibitorInfos != null){
    		for(TExhibitorInfo exhibitorInfo:exhibitorInfos){
    			List<TProduct> products = loadProductsByEinfoid(exhibitorInfo.getEinfoid());
    			if(products != null){
    				for(TProduct product:products){
    					getHibernateTemplate().delete(product);
    				}
    			}
    		}
    	}
    }
    
    /**
     * 查询所有产品类型
     * @return
     */
    @Transactional
    public List<TProductType> queryProductsType(){
    	List<TProductType> productTypes = productTypeDao.query();
    	return productTypes;
    }
    
    /**
     * 根据Level查询产品类型
     * @param level
     * @return
     */
    @Transactional
    public List<TProductType> queryProductsTypeByLv(Integer level){
    	List<TProductType> productTypes = productTypeDao.queryByHql("from TProductType where level = ? order by createTime", new Object[]{level});
    	return productTypes;
    }
    
    /**
     * 根据ClassId查询ParentClassId
     * @param int
     * @return
     */
    @Transactional
    public int loadParentClassIdById(Integer id){
    	TProductType productType = productTypeDao.query(id);
    	return productType.getParentId();
    }
    
    /**
     * 根据ClassId查询类型名
     * @param id
     * @return
     */
    @Transactional
    public String loadClassNameById(Integer id){
    	TProductType productType = productTypeDao.query(id);
    	return productType.getClassName();
    }

	/**
	 * 根据Id查询产品图片路径
	 * @param id
	 * @return
	 */
	@Transactional
	public String loadProductImgById(Integer id){
		return productImageDao.query(id).getImage();
	}
}
