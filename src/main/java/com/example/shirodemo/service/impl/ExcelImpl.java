package com.example.shirodemo.service.impl;

import com.example.shirodemo.service.ExcelService;
import com.example.shirodemo.utils.ConstantUtil;
import com.example.shirodemo.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Service
public class ExcelImpl implements ExcelService {
    @Override
    public void excelTemplate(HttpServletResponse response) {
        String headName = "浙江工商大学校园建设、维修工程招投标汇总表（二）";
        String[] headName1 = new String[] { "序号", "项目名称", "项目编号", "政采确认号", "确认资金额", "工程概况",
                "招标时间", "施工工期" , "投标单位1","投标单位1", "投标单位2","投标单位2", "投标单位3","投标单位3",  "投标单位4", "投标单位4",
                "中标单位", "中标价", "中标联系人", "招标执行部门", "备注"};
        String[] headName2 = new String[] { "名称", "最终报价", "名称", "最终报价", "名称", "最终报价", "名称", "最终报价" };
        String[] headnum0 = new String[] { "1,2,0,0", "1,2,1,1","1,2,2,2","1,2,3,3","1,2,4,4","1,2,5,5","1,2,6,6","1,2,7,7",
                "1,1,8,9", "1,1,10,11", "1,1,12,13", "1,1,14,15", "1,2,16,16", "1,2,17,17", "1,2,18,18", "1,2,19,19", "1,2,20,20" };
        String[] headnum1 = new String[] { "2,2,8,8", "2,2,9,9", "2,2,10,10",
                "2,2,11,11", "2,2,12,12", "2,2,13,13", "2,2,14,14", "2,2,15,15"};

        // 创建一个webbook，对应一个excel文件
        SXSSFWorkbook workbook = getHeadWorkBook(headName, headName1);
        // 在webbook中添加一个sheet,对应excel文件中的sheet
        Sheet sheet = workbook.getSheetAt(0);
        Row row;
        Cell cell;

        // 列名样式
        CellStyle style = columnStyle(workbook);

        // 普通单元格样式（中文）
        CellStyle normalStyle = normalStyle(workbook);

        // 设置列宽  （第几列，宽度）
        sheet.setColumnWidth(0, 3500);
        sheet.setColumnWidth(1, 3500);
        sheet.setColumnWidth(2, 3500);
        sheet.setColumnWidth(3, 3500);
        sheet.setColumnWidth(4, 3500);
        sheet.setColumnWidth(5, 3500);
        sheet.setColumnWidth(6, 3500);
        sheet.setColumnWidth(7, 3500);
        sheet.setColumnWidth(8, 3500);
        sheet.setColumnWidth(9, 3500);
        sheet.setColumnWidth(10,3500);
        sheet.setColumnWidth(11, 3500);
        sheet.setColumnWidth(12, 3500);
        sheet.setColumnWidth(13, 3500);
        sheet.setColumnWidth(14, 3500);
        sheet.setColumnWidth(15, 3500);
        sheet.setColumnWidth(16, 3500);
        sheet.setColumnWidth(17, 3500);
        sheet.setColumnWidth(18, 3500);
        sheet.setColumnWidth(19, 3500);
        sheet.setColumnWidth(20, 3500);
        sheet.setDefaultRowHeightInPoints(20); //设置行高


        //动态合并单元格headnum0
        workbook = mergeCell(workbook, headnum0);

        //设置合并单元格的参数并初始化带边框的表头（这样做可以避免因为合并单元格后有的单元格的边框显示不出来）
        row = sheet.createRow(2);  //因为下标从0开始，所以这里表示的是excel中的第三行
        row.setHeight((short) 0x150);
        for (int i = 0; i < headName1.length; i++){
            cell = row.createCell(i);
            cell.setCellStyle(style);
            if(i > 7 && i< 16){
                for (int j = 0; j < headName2.length; j++){
                    cell = row.createCell(j + 8);
                    cell.setCellValue(headName2[j]);
                    cell.setCellStyle(style);
                }
            }
        }
        //动态合并单元格headnum1
        workbook = mergeCell(workbook, headnum1);






        String sysTime = ConstantUtil.dateToString(new Date(), ConstantUtil.DATE_OBJ_2);
        String fileName = "已完成维修项目价格统计表" + sysTime + ".xlsx";
        ExcelUtil.writeExcel(workbook, fileName, response);
        return;
    }

    /**
     * 生成一个写好表头和列名的SXSSFWorkbook
     * */
    public SXSSFWorkbook getHeadWorkBook(String headName, String[] headName1){
        // 创建一个webbook，对应一个excel文件
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        // 在webbook中添加一个sheet,对应excel文件中的sheet
        Sheet sheet = workbook.createSheet("Sheet1");
        Row row;
        Cell cell;
        // 表头标题样式
        CellStyle headStyle = headStyle(workbook);

        // 列名样式
        CellStyle style = columnStyle(workbook);

        // 第一行表头标题
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,headName1.length-1));
        row = sheet.createRow(0);
        row.setHeight((short) 0x300);
        cell = row.createCell(0);
        cell.setCellStyle(headStyle);
        cell.setCellValue(headName);

        // 第二行列名
        row = sheet.createRow(1);
        row.setHeight((short) 0x200);
        for (int i = 0; i < headName1.length; i++){
            cell = row.createCell(i);
            cell.setCellValue(headName1[i]);
            cell.setCellStyle(style);
        }

        return workbook;
    }


    /**
     * 普通单元格样式（中文）
     * */
    public CellStyle normalStyle(SXSSFWorkbook workbook){
        Font normalFont = workbook.createFont();
        normalFont.setFontName("宋体");
        normalFont.setFontHeightInPoints((short) 12);
        CellStyle normalStyle = workbook.createCellStyle();
        normalStyle.setBorderBottom(CellStyle.BORDER_THIN);//下边框
        normalStyle.setBorderLeft(CellStyle.BORDER_THIN);//左边框
        normalStyle.setBorderTop(CellStyle.BORDER_THIN);//上边框
        normalStyle.setBorderRight(CellStyle.BORDER_THIN);//右边框
        normalStyle.setFont(normalFont);
        normalStyle.setWrapText(true); // 换行
        normalStyle.setAlignment(CellStyle.ALIGN_CENTER);// 左右居中
        normalStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中
        return normalStyle;
    }

    /**
     * 列名样式
     * */
    public CellStyle columnStyle(SXSSFWorkbook workbook){
        Font font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 14);// 字体大小
        CellStyle style = workbook.createCellStyle();
        style.setBorderBottom(CellStyle.BORDER_THIN);//下边框
        style.setBorderLeft(CellStyle.BORDER_THIN);//左边框
        style.setBorderTop(CellStyle.BORDER_THIN);//上边框
        style.setBorderRight(CellStyle.BORDER_THIN);//右边框
        style.setFont(font);
        style.setWrapText(true); // 换行
        style.setAlignment(CellStyle.ALIGN_CENTER);// 左右居中
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中
        style.setLocked(true);
        return style;
    }

    /**
     * 表头标题样式
     * */
    public CellStyle headStyle(SXSSFWorkbook workbook){
        Font headFont = workbook.createFont();
        headFont.setFontName("宋体");
        headFont.setFontHeightInPoints((short)20);// 字体大小
        CellStyle headStyle = workbook.createCellStyle();
        headStyle.setFont(headFont);
        headStyle.setAlignment(CellStyle.ALIGN_CENTER);// 左右居中
        headStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中
        headStyle.setLocked(true);
        return headStyle;
    }

    /**
     * 动态合并单元格
     * */
    public SXSSFWorkbook mergeCell(SXSSFWorkbook workbook, String[] headnum1){
        Sheet sheet = workbook.getSheetAt(0);
        //动态合并单元格
        for (int i = 0; i < headnum1.length; i++) {
            String[] temp = headnum1[i].split(",");
            Integer startrow = Integer.parseInt(temp[0]);
            Integer overrow = Integer.parseInt(temp[1]);
            Integer startcol = Integer.parseInt(temp[2]);
            Integer overcol = Integer.parseInt(temp[3]);
            sheet.addMergedRegion(new CellRangeAddress(startrow, overrow,
                    startcol, overcol));
        }
        return workbook;
    }

}
