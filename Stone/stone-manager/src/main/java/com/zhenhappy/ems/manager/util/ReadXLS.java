/*  
 * Created on July 14, 2014 
 *  
 * To change the template for this generated file go to  
 * Window>Preferences>Java>Code Generation>Code and Comments  
 */  
package com.zhenhappy.ems.manager.util; 
  
import java.io.*;
import org.apache.commons.lang.*;
import jxl.*;
import taobe.tec.jcc.JChineseConvertor;

/**  
 * @author wujianbin 
 *  
 * To change the template for this generated type comment go to  
 * Window>Preferences>Java>Code Generation>Code and Comments  
 */  
public class ReadXLS {   
  
    public static void main(String[] args) {   
    	try {
            Workbook book = Workbook.getWorkbook(new File("D:\\Test.xls"));
            // 获得第一个工作表对象
            Sheet sheet = book.getSheet(0);
            // 得到单元格
//            System.out.println("SHANGHAI AOQI MACHINERY MANUFACTURING CO., LTD. MA".length());
//            System.out.println("江苏仁寿至尊建材发展有限公司江苏仁寿至尊建材发展有限公司限公司".length());
//            System.out.println(sheet.getRows());
            /*for (int j = 0; j < sheet.getRows(); j++) {
//                System.out.println(sheet.getColumns());
                Cell cell = sheet.getCell(0, j);

                Cell cell1 = sheet.getCell(1, j);
                if(StringUtils.isNotEmpty(cell1.getContents())){
                    if(cell1.getContents().length() <= 50){
                        System.out.println(cell1.getContents() + "\t");
//                        System.out.println(cell.getContents() + "\t");
                    }else if(cell1.getContents().length() > 50){
                        System.out.println(cell1.getContents().substring(0, 50) + "\t");
//                        System.out.println(cell.getContents() + "\t");
                        System.out.print("▲" + cell1.getContents().substring(50, cell1.getContents().length()).trim() + "\t");
                        System.out.println();
                    }

                    Cell cell2 = sheet.getCell(2, j);
                    if(StringUtils.isNotEmpty(cell2.getContents())) {
                        if(cell2.getContents().length() <= 31){
                            System.out.println(JChineseConvertor.getInstance().t2s(cell2.getContents()) + "\t");
                        }else if(cell2.getContents().length() > 31) {
                            System.out.print(JChineseConvertor.getInstance().t2s(cell2.getContents().substring(0, 31)) + "\t");
                            System.out.print("▲" + JChineseConvertor.getInstance().t2s(cell2.getContents().substring(31, cell2.getContents().length()).trim()) + "\t");
                            System.out.println();
                        }
                    }
                }else{
                    Cell cell2 = sheet.getCell(2, j);
                    if(StringUtils.isNotEmpty(cell2.getContents())) {
                        if(cell2.getContents().length() <= 31){
                            System.out.println(JChineseConvertor.getInstance().t2s(cell2.getContents()) + "\t");
//                            System.out.println(cell.getContents() + "\t");
                        }else if(cell2.getContents().length() > 31) {
                            System.out.println(JChineseConvertor.getInstance().t2s(cell2.getContents().substring(0, 31)) + "\t");
//                            System.out.println(cell.getContents() + "\t");
                            System.out.println("▲" + JChineseConvertor.getInstance().t2s(cell2.getContents().substring(31, cell2.getContents().length()).trim()) + "\t");
                        }
                    }
                }


//            	for (int i = 0; i < sheet.getColumns(); i++) {
//                    Cell cell = sheet.getCell(i, j);
//                    System.out.print(cell.getContents() + "\t");
//                }

            }*/


            for (int j = 0; j < sheet.getRows(); j++) {
                Cell cell = sheet.getCell(0, j);
                Cell cell_en = sheet.getCell(1, j);
                Cell cell_cn = sheet.getCell(2, j);
                if(StringUtils.isNotEmpty(cell_en.getContents())){
                    if(cell_en.getContents().length() <= 50){
                        System.out.println(cell.getContents());
                    }else if(cell_en.getContents().length() > 50){
                        System.out.println(cell.getContents());
                        System.out.println();
                    }
                    if(StringUtils.isNotEmpty(cell_cn.getContents())){
                        if(cell_cn.getContents().length() <= 31){
                            System.out.println();
                        }else if(cell_cn.getContents().length() > 31) {
                            System.out.println();
                            System.out.println();
                        }
                    }
                }else if(StringUtils.isNotEmpty(cell_cn.getContents())){
                    if(StringUtils.isNotEmpty(cell_cn.getContents())){
                        if(cell_cn.getContents().length() <= 31){
                            System.out.println(cell.getContents());
                        }else if(cell_cn.getContents().length() > 31) {
                            System.out.println(cell.getContents());
                            System.out.println();
                        }
                    }
                }
            }
            book.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}