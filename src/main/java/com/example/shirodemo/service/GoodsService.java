package com.example.shirodemo.service;

import com.example.shirodemo.component.Auth;
import com.example.shirodemo.model.Goods;

import java.util.List;
import java.util.Map;

public interface GoodsService {

    Map<String, Object> getGoodsByPage(int pageNum, String number);

    int transactionTest();

    List<Goods> setTest();

    @Auth()
    Goods aopTest(int id);

    Goods reflectTest(String modeoName, int id, Class<?> clazz);

//    Integer getTotalPages(String number);

}
