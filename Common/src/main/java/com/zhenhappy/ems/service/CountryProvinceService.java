package com.zhenhappy.ems.service;

import com.zhenhappy.ems.entity.WCountry;
import com.zhenhappy.ems.entity.WProvince;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wujianbin on 2014-07-22.
 */
@Service
public class CountryProvinceService {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    /**
     * 查询所有国家
     * @return
     */
    @Transactional
    public List<WCountry> loadAllCountry(){
        List country = hibernateTemplate.find("from WCountry Order by (case when id = 44 then 0 else 1 end),id asc", null);
        if(country.size()==0){
            return null;
        }else{
            return country;
        }
    }
    
    /**
     * 根据id查询国家
     * @param id
     * @return
     */
    @Transactional
    public WCountry loadCountryById(Integer id){
        List country = hibernateTemplate.find("from WCountry where id=?", id);
        if(country.size()==0){
            return null;
        }else{
            return (WCountry) country.get(0);
        }
    }
    
    /**
     * 查询所有省份
     * @return
     */
    @Transactional
    public List<WProvince> loadAllProvince(){
        List province = hibernateTemplate.find("from WProvince", null);
        if(province.size()==0){
            return null;
        }else{
            return province;
        }
    }
    
    /**
     * 根据id查询省份
     * @param id
     * @return
     */
    @Transactional
    public WProvince loadProvinceById(Integer id){
        List province = hibernateTemplate.find("from WProvince where id=?", id);
        if(province.size()==0){
            return null;
        }else{
            return (WProvince) province.get(0);
        }
    }
    
    /**
     * 根据countryId查询省份
     * @param id
     * @return
     */
    @Transactional
    public List<WProvince> loadProvinceByCountryId(Integer countryId){
        List province = hibernateTemplate.find("from WProvince where countryId=?", countryId);
        if(province.size()==0){
            return null;
        }else{
            return province;
        }
    }
    
}
