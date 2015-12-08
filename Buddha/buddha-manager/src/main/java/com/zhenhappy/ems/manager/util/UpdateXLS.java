/*  
 * Created on July 14, 2014 
 *  
 * To change the template for this generated file go to  
 * Window>Preferences>Java>Code Generation>Code and Comments  
 */  
package com.zhenhappy.ems.manager.util;  
  
import java.io.*;   
import jxl.*;   
import jxl.write.*;   
  
/**  
 * @author wujianbin
 *  
 * To change the template for this generated type comment go to  
 * Window>Preferences>Java>Code Generation>Code and Comments  
 */  
public class UpdateXLS {   
  
    public static void main(String[] args) {   
        try {   
            //get file.   
            Workbook wb = Workbook.getWorkbook(new File("d:/Test.xls"));   
            //open a copy file(new file), then write content with same content with Test.xls.     
            WritableWorkbook book =   
                Workbook.createWorkbook(new File("d:/Test.xls"), wb);   
            //add a Sheet.   
            WritableSheet sheet = book.createSheet("Sheet_2", 1);   
            sheet.addCell(new Label(0, 0, "test2"));   
            book.write();   
            book.close();   
        } catch (Exception e) {   
            e.printStackTrace();   
        }   
    }   
}  