package com.example.shirodemo.utils;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExcelUtil {

    /**
     * 生成Excel表
     */
    public static void writeExcel(SXSSFWorkbook workbook, String fileName, HttpServletResponse response){
        try {
            //定义excle名称 ISO-8859-1 防止名称乱码
            String msg = new String(
                    (fileName).getBytes(),"ISO-8859-1");
            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Content-Disposition", "attachment;filename=\""
                    + msg +"\"");
            workbook.write(response.getOutputStream());

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
