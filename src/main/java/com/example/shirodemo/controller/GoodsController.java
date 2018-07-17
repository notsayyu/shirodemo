package com.example.shirodemo.controller;

import com.example.shirodemo.model.Goods;
import com.example.shirodemo.service.GoodsService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
    @RequestMapping("goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    /**
     * 分页查询所有编号为001的商品
     * */
    @RequestMapping("page")
    public Map getGoodsByPage(@RequestParam int pageNum, @RequestParam String number){
        return goodsService.getGoodsByPage(pageNum, number);
    }

    /**
     * 事务回滚测试
     * */
    @RequestMapping("transactionTest")
    public int transactionTest(){
        return goodsService.transactionTest();
    }

    /**
     * Set去重实体类数据测试
     * */
    @RequestMapping("setTest")
    public List<Goods> setTest(){
        return goodsService.setTest();
    }

    /**
     * AOP注解测试
     */
    @RequestMapping("aopTest")
    public Goods aopTest(@RequestParam int id){
        return goodsService.aopTest(id);
    }

    /**
     * 反射test
     */
    @GetMapping("reflectTest")
    public Goods reflectTest(@RequestParam String modelName, @RequestParam int id) throws ClassNotFoundException {
        Class<?> clazz = Class.forName("com.example.shirodemo.model." +  modelName);
        return goodsService.reflectTest(modelName, id, clazz);
    }

}
