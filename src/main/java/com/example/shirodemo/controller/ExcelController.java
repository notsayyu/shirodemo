package com.example.shirodemo.controller;

import com.example.shirodemo.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("excel")
public class ExcelController {
    @Autowired
    private ExcelService excelService;

    /**
     * 多表头、合并单元格
     * */
    @GetMapping
    public void excelTemplate(HttpServletResponse response){
        excelService.excelTemplate(response);
    }

}
