package com.zhenhappy.ems.manager.service;

import com.zhenhappy.ems.dao.WVisaDao;
import com.zhenhappy.ems.entity.TArticle;
import com.zhenhappy.ems.entity.WVisa;
import com.zhenhappy.ems.manager.dto.QueryWVisaRequest;
import com.zhenhappy.ems.manager.dto.QueryWVisaResponse;
import com.zhenhappy.ems.service.WVisaService;
import com.zhenhappy.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wujianbin on 2014-07-03.
 */
@Service
public class WVisaManagerService extends WVisaService {
	@Autowired
	private WVisaDao wVisaDao;

	@Transactional
	public QueryWVisaResponse queryWVisaByPage(QueryWVisaRequest request) throws UnsupportedEncodingException {
	    Page page = new Page();
        page.setPageSize(request.getRows());
        page.setPageIndex(request.getPage());
        List<WVisa> wVisas = wVisaDao.queryPageByHQL("select count(*) from WVisa", "from WVisa order by createTime DESC, id DESC", new Object[]{}, page);
		QueryWVisaResponse response = new QueryWVisaResponse();
        response.setResultCode(0);
        response.setRows(wVisas);
        response.setTotal(page.getTotalCount());
        return response;
	}

	/**
	 * 查询所有VISA
	 * @return
	 */
	@Transactional
	public List<WVisa> loadAllWVisas() {
		List<WVisa> wVisas = wVisaDao.queryByHql("from WVisa order by createTime DESC, id DESC", new Object[]{ });
		return wVisas;
	}

	/**
	 * 根据vids查询展商列表
	 * @return
	 */
	@Transactional
	public List<WVisa> loadSelectedWVisas(Integer[] vids) {
		List<WVisa> wVisas = null;
		wVisas = wVisaDao.loadWVisasByVids(vids);
		return wVisas.size() > 0 ? wVisas : null;
	}
}
