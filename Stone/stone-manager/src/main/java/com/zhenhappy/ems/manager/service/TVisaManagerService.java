package com.zhenhappy.ems.manager.service;

import com.zhenhappy.ems.dao.TVisaDao;
import com.zhenhappy.ems.dao.WVisaDao;
import com.zhenhappy.ems.entity.TVisa;
import com.zhenhappy.ems.entity.WVisa;
import com.zhenhappy.ems.manager.dto.QueryTVisaRequest;
import com.zhenhappy.ems.manager.dto.QueryTVisaResponse;
import com.zhenhappy.ems.manager.dto.QueryWVisaRequest;
import com.zhenhappy.ems.manager.dto.QueryWVisaResponse;
import com.zhenhappy.ems.service.VisaService;
import com.zhenhappy.ems.service.WVisaService;
import com.zhenhappy.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by wujianbin on 2014-11-26.
 */
@Service
public class TVisaManagerService extends VisaService {
	@Autowired
	private TVisaDao tVisaDao;

	@Transactional
	public QueryTVisaResponse queryTVisaByPage(QueryTVisaRequest request) throws UnsupportedEncodingException {
	    Page page = new Page();
        page.setPageSize(request.getRows());
        page.setPageIndex(request.getPage());
        List<TVisa> wVisas = tVisaDao.queryPageByHQL("select count(*) from TVisa", "from TVisa order by createTime DESC, id DESC", new Object[]{}, page);
		QueryTVisaResponse response = new QueryTVisaResponse();
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
	public List<TVisa> loadAllTVisas() {
		List<TVisa> tVisas = tVisaDao.queryByHql("from TVisa order by createTime DESC, id DESC", new Object[]{ });
		return tVisas;
	}

	/**
	 * 根据vids查询展商列表
	 * @return
	 */
	@Transactional
	public List<TVisa> loadSelectedTVisas(Integer[] vids) {
		List<TVisa> tVisas = null;
		tVisas = tVisaDao.loadTVisasByVids(vids);
		return tVisas.size() > 0 ? tVisas : null;
	}
}
