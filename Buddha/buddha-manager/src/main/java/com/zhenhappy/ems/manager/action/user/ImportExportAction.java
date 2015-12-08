package com.zhenhappy.ems.manager.action.user;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhenhappy.ems.manager.dto.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.zhenhappy.ems.entity.TExhibitor;
import com.zhenhappy.ems.entity.TExhibitorInfo;
import com.zhenhappy.ems.entity.TInvoiceApply;
import com.zhenhappy.ems.manager.action.BaseAction;
import com.zhenhappy.ems.manager.service.ExhibitorManagerService;
import com.zhenhappy.ems.manager.service.ImportExportService;
import com.zhenhappy.ems.manager.sys.Constants;
import com.zhenhappy.ems.manager.util.CreateZip;
import com.zhenhappy.ems.manager.util.JXLExcelView;
import com.zhenhappy.ems.service.InvoiceService;
import com.zhenhappy.ems.service.MeipaiService;
import com.zhenhappy.system.SystemConfig;

import freemarker.template.Template;

/**
 * Created by wujianbin on 2014-08-26.
 */
@Controller
@RequestMapping(value = "user")
@SessionAttributes(value = ManagerPrinciple.MANAGERPRINCIPLE)
public class ImportExportAction extends BaseAction {

	private static Logger log = Logger.getLogger(ImportExportAction.class);

    @Autowired
    private ExhibitorManagerService exhibitorManagerService;
    @Autowired
    private ImportExportService importExportService;
    @Autowired
	private FreeMarkerConfigurer freeMarker;// 注入FreeMarker模版封装框架
    @Autowired
    private SystemConfig systemConfig;

    /**
     * 导出展商列表到Excel
     * @param eids
     * @return
     */
    @RequestMapping(value = "exportExhibitorsToExcel", method = RequestMethod.POST)
    public ModelAndView exportExhibitorsToExcel(@RequestParam(value = "eids", defaultValue = "") Integer[] eids) {
        Map model = new HashMap();
        List<TExhibitor> exhibitors = new ArrayList<TExhibitor>();
        if(eids[0] == -1) exhibitors = exhibitorManagerService.loadAllExhibitors();
        else exhibitors = exhibitorManagerService.loadSelectedExhibitors(eids);
        List<QueryExhibitorInfo> queryExhibitorInfos = importExportService.exportExhibitor(exhibitors);
        model.put("list", queryExhibitorInfos);
        String[] titles = new String[] { "展位号", "公司中文名", "公司英文名", "电话", "传真", "邮箱", "网址", "中文地址", "英文地址", "邮编", "产品分类", "主营产品(中文)", "主营产品(英文)", "公司简介", "发票抬头", "地税税号" };
        model.put("titles", titles);
        String[] columns = new String[] { "boothNumber", "company", "companyEn", "phone", "fax", "email", "website", "address", "addressEn", "zipcode", "productType", "mainProduct", "mainProductEn", "mark", "invoiceTitle", "invoiceNo" };
        model.put("columns", columns);
        Integer[] columnWidths = new Integer[]{20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20};
        model.put("columnWidths", columnWidths);
        model.put("fileName", "展商基本信息.xls");
        model.put("sheetName", "展商基本信息");
        return new ModelAndView(new JXLExcelView(), model);
    }

    /**
     * 导出展位信息到Excel
     * @return
     */
    @RequestMapping("/exportBoothInfoToExcel_2")
    public ModelAndView exportBoothInfoToExcel_2() {
        Map model = new HashMap();
        List<TExhibitor> exhibitors = exhibitorManagerService.loadAllExhibitors();
        List<QueryExhibitorInfoT> queryExhibitorInfos = new ArrayList<QueryExhibitorInfoT>();
        if(exhibitors != null){
            for(TExhibitor exhibitor:exhibitors){
                String boothNum = exhibitorManagerService.loadBoothNum(exhibitor.getEid());
                String boothNums[] = boothNum.split(",");
                if(boothNums.length > 1) {
                    for(String booth:boothNums){
                        QueryExhibitorInfoT queryExhibitorInfoT = new QueryExhibitorInfoT();
                        queryExhibitorInfoT.setEid(exhibitor.getEid());
                        queryExhibitorInfoT.setBoothNumber(booth.trim());
                        if(StringUtils.isNotEmpty(exhibitor.getCompany())) queryExhibitorInfoT.setCompany(exhibitor.getCompany());
                        else queryExhibitorInfoT.setCompany(exhibitor.getCompanye());
                        queryExhibitorInfos.add(queryExhibitorInfoT);
                    }
                }else if(boothNums.length == 1) {
                    QueryExhibitorInfoT queryExhibitorInfoT = new QueryExhibitorInfoT();
                    queryExhibitorInfoT.setEid(exhibitor.getEid());
                    queryExhibitorInfoT.setBoothNumber(boothNum.trim());
                    if(StringUtils.isNotEmpty(exhibitor.getCompany())) queryExhibitorInfoT.setCompany(exhibitor.getCompany());
                    else queryExhibitorInfoT.setCompany(exhibitor.getCompanye());
                    queryExhibitorInfos.add(queryExhibitorInfoT);
                }
            }
        }
        model.put("list", queryExhibitorInfos);
        String[] titles = new String[] { "ID", "展位号", "公司名" };
        model.put("titles", titles);
        String[] columns = new String[] { "eid", "boothNumber", "company" };
        model.put("columns", columns);
        Integer[] columnWidths = new Integer[]{20,20,20};
        model.put("columnWidths", columnWidths);
        model.put("fileName", "展位信息.xls");
        model.put("sheetName", "展位信息");
        return new ModelAndView(new JXLExcelView(), model);
    }

    /**
     * 导出展位信息到Excel
     * @return
     */
    @RequestMapping("/exportBoothInfoToExcel_1")
    public ModelAndView exportBoothInfoToExcel_1() {
        Map model = new HashMap();
        // 构造数据
        List<TExhibitor> exhibitors = exhibitorManagerService.loadAllExhibitors();
        List<QueryExhibitorInfoT> queryExhibitorInfos = new ArrayList<QueryExhibitorInfoT>();
        if(exhibitors != null){
            for(TExhibitor exhibitor:exhibitors){
                String boothNum = exhibitorManagerService.loadBoothNum(exhibitor.getEid());
                String boothNums[] = boothNum.split(",");
                if(boothNums.length > 1) {
                    for(String booth:boothNums){
                        QueryExhibitorInfoT queryExhibitorInfoT = new QueryExhibitorInfoT();
                        queryExhibitorInfoT.setEid(exhibitor.getEid());
                        queryExhibitorInfoT.setBoothNumber(booth.trim());
                        if(StringUtils.isNotEmpty(exhibitor.getCompany())) queryExhibitorInfoT.setCompany(exhibitor.getCompany());
                        else queryExhibitorInfoT.setCompany(exhibitor.getCompanye());
                        queryExhibitorInfos.add(queryExhibitorInfoT);
                    }
                }else if(boothNums.length == 1) {
                    QueryExhibitorInfoT queryExhibitorInfoT = new QueryExhibitorInfoT();
                    queryExhibitorInfoT.setEid(exhibitor.getEid());
                    queryExhibitorInfoT.setBoothNumber(boothNum.trim());
                    if(StringUtils.isNotEmpty(exhibitor.getCompany())) queryExhibitorInfoT.setCompany(exhibitor.getCompany());
                    if(StringUtils.isNotEmpty(exhibitor.getCompanye())) queryExhibitorInfoT.setCompanye(exhibitor.getCompanye());
                    if(StringUtils.isNotEmpty(exhibitor.getCompanyt())) queryExhibitorInfoT.setCompanyt(exhibitor.getCompanyt());
                    queryExhibitorInfos.add(queryExhibitorInfoT);
                }
            }
        }
        model.put("list", queryExhibitorInfos);
        String[] titles = new String[] { "ID", "展位号", "公司中文名", "公司繁体名", "公司英文名" };
        model.put("titles", titles);
        String[] columns = new String[] { "eid", "boothNumber", "company", "companyt", "companye" };
        model.put("columns", columns);
        Integer[] columnWidths = new Integer[]{20,20,20,20,20};
        model.put("columnWidths", columnWidths);
        model.put("fileName", "展位信息.xls");
        model.put("sheetName", "展位信息");
        return new ModelAndView(new JXLExcelView(), model);
    }

    /**
     * 导出展位号+企业楣牌到Excel
     * @param eids
     * @return
     */
    @RequestMapping(value = "exportBoothNumAndMeipaiToExcel", method = RequestMethod.POST)
	public ModelAndView exportBoothNumAndMeipaiToExcel(@RequestParam(value = "eids", defaultValue = "") Integer[] eids) {
    	Map model = new HashMap();
        List<QueryBoothNumAndMeipai> boothNumAndMeipais = new ArrayList<QueryBoothNumAndMeipai>();
        if(eids[0] == -1) boothNumAndMeipais = exhibitorManagerService.loadBoothNumAndMeipai(null);
        else boothNumAndMeipais = exhibitorManagerService.loadBoothNumAndMeipai(eids);
        model.put("list", boothNumAndMeipais);
        String[] titles = new String[] { "展位号", "企业楣牌(中文)", "企业楣牌(英文)" };
		model.put("titles", titles);
		String[] columns = new String[] { "boothNumber", "meipai", "meipaiEn" };
		model.put("columns", columns);
		Integer[] columnWidths = new Integer[]{20,20,20};
		model.put("columnWidths", columnWidths);
		model.put("fileName", "展位号+企业楣牌.xls");
		model.put("sheetName", "展位号+企业楣牌");
		return new ModelAndView(new JXLExcelView(), model);
	}

    /**
     * 导入展商账号
     * @param file
     * @param request
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value="upload/exhibitors", method={RequestMethod.POST,RequestMethod.GET})
    public List<String> importExhibitors(@RequestParam MultipartFile file,
										 @ModelAttribute ImportExhibitorsRequest request) throws IOException {
    	File importFile = upload(file, "\\import", FilenameUtils.getBaseName(file.getOriginalFilename()) + new Date().getTime() + "." + FilenameUtils.getExtension(file.getOriginalFilename()));
        List<String> report = importExportService.importExhibitor(importFile, request);
//        FileUtils.deleteQuietly(importFile); // 删除临时文件
        return report;
    }
    
    /**
     * 导出会刊
     * @param eids
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/exportTransactionsToZip")
    public ModelAndView exportTransactionsToZip(@RequestParam(value = "eids", defaultValue = "") Integer[] eids,
                                                        HttpServletRequest request,
                                                        HttpServletResponse response) throws Exception {
//    	String dirPath = "D:\\Users\\Foshi\\tmp\\" + UUID.randomUUID();
    	String appendix_directory = systemConfig.getVal(Constants.appendix_directory).replaceAll("\\\\\\\\", "\\\\");
        String randomFile = UUID.randomUUID().toString();
    	String destDir = appendix_directory + "\\tmp\\" + randomFile;
    	FileUtils.forceMkdir(new File(destDir)); // 创建临时文件夹
        if(eids[0] == -1){
            exportTransactions(null, destDir);
            importExportService.copyLogo(null, destDir);
        }else{
            exportTransactions(eids, destDir);
            importExportService.copyLogo(eids, destDir);
        }
	    CreateZip.zipToFile(destDir, randomFile);
    	return download(destDir, randomFile, request, response);
    }

    private void exportTransactions(Integer[] eids, String dirPath) throws Exception {
    	List<TExhibitor> exhibitors = new ArrayList<TExhibitor>();
    	if(eids == null){
    		exhibitors = exhibitorManagerService.loadAllExhibitors();
    	}else{
    		exhibitors = exhibitorManagerService.loadSelectedExhibitors(eids);
    	}
    	if(exhibitors.size() > 0){
    		for(TExhibitor exhibitor:exhibitors){
        		TExhibitorInfo exhibitorInfo = exhibitorManagerService.loadExhibitorInfoByEid(exhibitor.getEid());
        		String boothNumber = exhibitorManagerService.loadBoothNum(exhibitor.getEid());
        		Transaction transaction = new Transaction();
        		if(exhibitorInfo != null){
    	    			if((StringUtils.isNotEmpty(exhibitor.getCompany()) || StringUtils.isNotEmpty(exhibitor.getCompanye())) && StringUtils.isNotEmpty(boothNumber)){
    		        		transaction.setBoothNumber(boothNumber.trim());
    		        		if(StringUtils.isNotEmpty(exhibitor.getCompany())) transaction.setCompany(exhibitor.getCompany().trim());
    		        		else transaction.setCompany(null);
    		        		if(StringUtils.isNotEmpty(exhibitor.getCompanye())) transaction.setCompanye(exhibitor.getCompanye().trim());
    		        		else transaction.setCompanye(null);
    		        		if(StringUtils.isNotEmpty(exhibitorInfo.getAddress())) transaction.setAddress(exhibitorInfo.getAddress().trim());
    		        		else transaction.setAddress(null);
    		        		if(StringUtils.isNotEmpty(exhibitorInfo.getAddressEn())) transaction.setAddressEn(exhibitorInfo.getAddressEn().trim());
    		        		else transaction.setAddressEn(null);
    		        		if(StringUtils.isNotEmpty(exhibitorInfo.getZipcode())) transaction.setZipcode(exhibitorInfo.getZipcode().trim());
    		        		else transaction.setZipcode(null);
    		        		if(StringUtils.isNotEmpty(exhibitorInfo.getPhone())) transaction.setPhone(exhibitorInfo.getPhone().trim());
    		        		else transaction.setPhone(null);
    		        		if(StringUtils.isNotEmpty(exhibitorInfo.getFax())) transaction.setFax(exhibitorInfo.getFax().trim());
    		        		else transaction.setFax(null);
    		        		if(StringUtils.isNotEmpty(exhibitorInfo.getWebsite())) transaction.setWebsite(exhibitorInfo.getWebsite().trim());
    		        		else transaction.setWebsite(null);
    		        		if(StringUtils.isNotEmpty(exhibitorInfo.getEmail())) transaction.setEmail(exhibitorInfo.getEmail().trim());
    		        		else transaction.setEmail(null);
    		        		if(StringUtils.isNotEmpty(exhibitorInfo.getMark())) transaction.setMark(exhibitorInfo.getMark().trim());
    		        		else transaction.setMark(null);
    	    			}
        		}else if((StringUtils.isNotEmpty(exhibitor.getCompany()) || StringUtils.isNotEmpty(exhibitor.getCompanye())) && StringUtils.isNotEmpty(boothNumber)){
            		transaction.setBoothNumber(boothNumber.trim());
            		ModifyExhibitorInfoRequest modifyExhibitorInfoRequest = new ModifyExhibitorInfoRequest();
            		if(StringUtils.isNotEmpty(exhibitor.getCompany())) {
            			transaction.setCompany(exhibitor.getCompany().trim());
            			modifyExhibitorInfoRequest.setCompany(exhibitor.getCompany().trim());
            		}
	        		else transaction.setCompany(null);
	        		if(StringUtils.isNotEmpty(exhibitor.getCompanye())) {
	        			transaction.setCompanye(exhibitor.getCompanye().trim());
	        			modifyExhibitorInfoRequest.setCompanyEn(exhibitor.getCompanye().trim());
	        		}
	        		else transaction.setCompanye(null);
            		transaction.setAddress(null);
            		transaction.setZipcode(null);
            		transaction.setPhone(null);
            		transaction.setFax(null);
            		transaction.setWebsite(null);
            		transaction.setEmail(null);
            		transaction.setMark(null);
            		exhibitorManagerService.modifyExhibitorInfo(modifyExhibitorInfoRequest, exhibitor.getEid(), 1);
    			}else{
    				continue;
    			}
        		String filePath = "";
        		if(StringUtils.isNotEmpty(exhibitor.getCompany())) filePath = dirPath + "\\" + exhibitor.getCompany().replaceAll("/", "") + boothNumber.replaceAll("/", "") + ".txt";
        		else filePath = dirPath + "\\" + exhibitor.getCompanye().replaceAll("/", "") + boothNumber.replaceAll("/", "") + ".txt";
        		importExportService.WriteStringToFile(getTransactionText(transaction), filePath);
//	        		System.out.println("导出" + exhibitor.getCompany() + "成功");
        	}
    	}
//    	System.out.println("全部会刊信息导出完成");
    }
    
    /**
	 * 通过模板构造邮件内容，参数expressNumber将替换模板文件中的${expressNumber}标签。
	 */
	private String getTransactionText(Transaction transaction) throws Exception {
		// 通过指定模板名获取FreeMarker模板实例
		Template template = freeMarker.getConfiguration().getTemplate("transaction/transaction.ftl");
		
		// FreeMarker通过Map传递动态数据
		Map<Object, Object> model = new HashMap<Object, Object>();
		model.put("transaction", transaction); // 注意动态数据的key和模板标签中指定的属性相匹配
		
		// 解析模板并替换动态数据，最终content将替换模板文件中的${content}标签。
		String htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
		return htmlText;
	}
	
    @RequestMapping("/download")
    public ModelAndView download(String destDir, String zipName, HttpServletRequest request, HttpServletResponse response) throws Exception{
    	BufferedInputStream bis = null;
    	BufferedOutputStream bos = null;
    	String realName = URLEncoder.encode(zipName + ".zip", "UTF-8"); //设置下载文件名字
        /* 
         * @see http://support.microsoft.com/default.aspx?kbid=816868 
         */  
        if (realName.length() > 150) {  
            String guessCharset = "gb2312"; /*根据request的locale 得出可能的编码，中文操作系统通常是gb2312*/  
            realName = new String(realName.getBytes(guessCharset), "ISO8859-1");   
        }  
    	String fileName = destDir + "\\" + zipName + ".zip";  //获取完整的文件名
    	System.out.println(fileName);
    	long fileLength = new File(fileName).length();
    	response.setContentType("application/octet-stream");
    	response.setHeader("Content-Disposition", "attachment; filename=" + realName);
    	response.setHeader("Content-Length", String.valueOf(fileLength));
    	bis = new BufferedInputStream(new FileInputStream(fileName));
    	bos = new BufferedOutputStream(response.getOutputStream());
    	byte[] buff = new byte[2048];
    	int bytesRead;
    	while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
    			bos.write(buff, 0, bytesRead);
    	}
    	bis.close();
    	bos.close();
    	FileUtils.deleteDirectory(new File(destDir)); // 删除临时文件
        return null;
    }
    
    @RequestMapping("/upload")
    public File upload(@RequestParam MultipartFile file, String destDir, String fileName){
    	String appendix_directory = systemConfig.getVal(Constants.appendix_directory).replaceAll("\\\\\\\\", "\\\\");
    	if(StringUtils.isEmpty(fileName)) fileName = new Date().getTime() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
    	if(StringUtils.isNotEmpty(destDir)) destDir = appendix_directory + destDir;
    	else destDir = appendix_directory;
        File targetFile = new File(destDir, fileName);
        if(!targetFile.exists()) {
            targetFile.mkdirs();
        }
        try {
        	file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return targetFile;
    }
}
