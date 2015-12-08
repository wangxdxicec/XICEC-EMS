package com.zhenhappy.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.zhenhappy.dao.SurveyDao;
import com.zhenhappy.dto.ProductTypeDTO;
import com.zhenhappy.dto.ProductTypeDTO.ProductChildType;
import com.zhenhappy.util.QueryFactory;

@Service
public class ProductTypeService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private SurveyDao surveyDao;
	
	/**
	 * 
	 * @return
	 */
	public List<ProductTypeDTO> loadCompanyProductTypes(Integer userId) {

		List<Map<String, Object>> ftemps = jdbcTemplate.queryForList(
				"select TypeName typeName,TypeNameE typeNameE,TypeCode typeCode from productType", new Object[] {});

		List<ProductTypeDTO> ftypes = JSONArray.parseArray(JSONArray.toJSONString(ftemps), ProductTypeDTO.class);

		List<Map<String, Object>> childTemps = jdbcTemplate
				.queryForList(
						"select TypeDetailCode typeCode,TypeDetailName typeName,TypeDetailNameE typeNameE,FatherTypeCode fatherCode from productTypeDetail",
						new Object[] {});
		List<ProductChildType> childTypes = JSONArray.parseArray(JSONArray.toJSONString(childTemps),
				ProductChildType.class);

		// String interestProduct = jdbcTemplate
		// .queryForObject("select nullif(interest_product,\"\") from t_customer_survey where user_id = ?",
		// new Object[] { userId }, String.class);

		String interestProduct = (String) surveyDao.queryForObject(
				"select product_type from t_join_intention where user_id=" + userId, new Object[] {},
				QueryFactory.SQL);
		Map<String, String> selectRemark = null;
		if (StringUtils.isNotEmpty(interestProduct) && StringUtils.isNotEmpty(interestProduct.trim())) {
			String[] items = interestProduct.split(",");
			selectRemark = new HashMap<String, String>();
			for (String item : items) {
				if (item.contains("-Other-")) {
					// other
					selectRemark.put("father-" + item.split("-")[0], item.split("-")[2]);
				} else {
					// child
					selectRemark.put("child-" + item.split("-")[1], "");
				}
			}
		}

		Map<String, ProductTypeDTO> ftypes_map = new HashMap<String, ProductTypeDTO>(ftypes.size());

		for (ProductTypeDTO pt : ftypes) {
			ftypes_map.put(pt.getTypeCode(), pt);
		}

		for (ProductChildType pct : childTypes) {
			if (ftypes_map.containsKey(pct.getFatherCode())) {
				if (selectRemark != null && selectRemark.get("child-" + pct.getTypeCode()) != null) {
					ftypes_map.get(pct.getFatherCode()).addChild(pct.getTypeName(), pct.getTypeNameE(),
							pct.getTypeCode(), true, null, pct.getFatherCode());
				} else {
					ftypes_map.get(pct.getFatherCode()).addChild(pct.getTypeName(), pct.getTypeNameE(),
							pct.getTypeCode(), false, null, pct.getFatherCode());
				}
			}
		}
		
		for (ProductTypeDTO pt : ftypes) {
			if (selectRemark != null && selectRemark.get("father-" + pt.getTypeCode()) != null) {
				pt.addChild("其他", "Other", "Other", true, selectRemark.get("father-" + pt.getTypeCode()),
						pt.getTypeCode());
			} else {
				pt.addChild("其他", "Other", "Other", false, "", pt.getTypeCode());
			}
		}
		return ftypes;
	}

	/**
	 * 
	 * @return
	 */
	public List<ProductTypeDTO> loadProductTypes(Integer userId) {

		List<Map<String, Object>> ftemps = jdbcTemplate.queryForList(
				"select TypeName typeName,TypeNameE typeNameE,TypeCode typeCode from productType", new Object[] {});

		List<ProductTypeDTO> ftypes = JSONArray.parseArray(JSONArray.toJSONString(ftemps), ProductTypeDTO.class);

		List<Map<String, Object>> childTemps = jdbcTemplate
				.queryForList(
						"select TypeDetailCode typeCode,TypeDetailName typeName,TypeDetailNameE typeNameE,FatherTypeCode fatherCode from productTypeDetail",
						new Object[] {});
		List<ProductChildType> childTypes = JSONArray.parseArray(JSONArray.toJSONString(childTemps),
				ProductChildType.class);

		// String interestProduct = jdbcTemplate
		// .queryForObject("select nullif(interest_product,\"\") from t_customer_survey where user_id = ?",
		// new Object[] { userId }, String.class);

		String interestProduct = (String) surveyDao.queryForObject(
				"select interest_product from t_customer_survey where user_id=" + userId, new Object[] {},
				QueryFactory.SQL);
		Map<String, String> selectRemark = null;
		if (StringUtils.isNotEmpty(interestProduct) && StringUtils.isNotEmpty(interestProduct.trim())) {
			String[] items = interestProduct.split(",");
			selectRemark = new HashMap<String, String>();
			for (String item : items) {
				if (item.contains("-Other-")) {
					// other
					selectRemark.put("father-" + item.split("-")[0], item.split("-")[2]);
				} else {
					// child
					selectRemark.put("child-" + item.split("-")[1], "");
				}
			}
		}

		Map<String, ProductTypeDTO> ftypes_map = new HashMap<String, ProductTypeDTO>(ftypes.size());

		for (ProductTypeDTO pt : ftypes) {
			ftypes_map.put(pt.getTypeCode(), pt);
		}

		for (ProductChildType pct : childTypes) {
			if (ftypes_map.containsKey(pct.getFatherCode())) {
				if (selectRemark != null && selectRemark.get("child-" + pct.getTypeCode()) != null) {
					ftypes_map.get(pct.getFatherCode()).addChild(pct.getTypeName(), pct.getTypeNameE(),
							pct.getTypeCode(), true, null, pct.getFatherCode());
				} else {
					ftypes_map.get(pct.getFatherCode()).addChild(pct.getTypeName(), pct.getTypeNameE(),
							pct.getTypeCode(), false, null, pct.getFatherCode());
				}
			}
		}
		
		for (ProductTypeDTO pt : ftypes) {
			if (selectRemark != null && selectRemark.get("father-" + pt.getTypeCode()) != null) {
				pt.addChild("其他", "Other", "Other", true, selectRemark.get("father-" + pt.getTypeCode()),
						pt.getTypeCode());
			} else {
				pt.addChild("其他", "Other", "Other", false, "", pt.getTypeCode());
			}
		}
		return ftypes;
	}

	public Map<String, Integer> countCompanyUnderType(String typeCode) {
		List<String> childCodes = jdbcTemplate.queryForList(
				"select typeDetailCode from ProductTypeDetail where fatherTypeCode = ? ", new Object[] { typeCode },
				String.class);
		childCodes.add("Other");
		Map<String, Integer> typeCode_count = new HashMap<String, Integer>();
		if (childCodes != null && childCodes.size() > 0) {
			for (String childTypeCode : childCodes) {
				String r = typeCode + "-" + childTypeCode;
				int count = jdbcTemplate
						.queryForInt("select count(*) from exhibitorlist where productTypeDetail like '%" + r + "%'");
				typeCode_count.put(childTypeCode, count);
			}
		}
		return typeCode_count;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
