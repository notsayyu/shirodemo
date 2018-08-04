package com.example.shirodemo.service.impl;

import com.example.shirodemo.dao.GoodsMapper;
import com.example.shirodemo.dao.RoleMapper;
import com.example.shirodemo.dao.UserMapper;
import com.example.shirodemo.model.Goods;
import com.example.shirodemo.model.Role;
import com.example.shirodemo.model.User;
import com.example.shirodemo.service.GoodsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

@Service
@CacheConfig(cacheNames="goods")
public class GoodsImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Map<String, Object> getGoodsByPage(int pageNum, String number) {
        Map<String, Object> map = new HashMap();
        //使用分页插件,核心代码就这一行
        PageHelper.startPage(pageNum, 4);
        // 获取数据
        Page<Goods> goodsList = (Page)goodsMapper.selectByNumber(number);
        map.put("dataList", goodsList);
        //总页数
        int totalPages = goodsList.getPages();
        map.put("totalPages", totalPages);
        return map;
    }

    @Override
    public Map<String, Object> getAllGoodsByPage(int pageNum) {
        Map<String, Object> map = new HashMap();
        //使用分页插件,核心代码就这一行
        PageHelper.startPage(pageNum, 4);
        // 获取数据
        Page<Goods> goodsList = (Page)goodsMapper.selectAllGoods();
        map.put("dataList", goodsList);
        //总页数
        int totalPages = goodsList.getPages();
        map.put("totalPages", totalPages);
        return map;
    }

    @Transactional
    @Override
    public int transactionTest() {
        try {
            Goods goods = new Goods();
            goods.setName("transactionTest");
            goods.setNumber("100");
            goodsMapper.insert(goods);
            Role role = new Role();
            role.setName("test");
            roleMapper.insert(role);
            User user = new User();
            user.setPassword("123456");
            user.setRoleId(1);
            user.setName("test");
            return userMapper.insert(user);
        }catch (Exception e){
            throw e;
        }

    }

    @Override
    public List<Goods> setTest() {
        List<Goods> goodsList = goodsMapper.selectAllGoods();
        goodsList.add(goodsList.get(0));
        goodsList.add(goodsList.get(1));
        Set<Goods> goodsSet = new HashSet<>();
        goodsSet.addAll(goodsList);
        List<Goods> newGoodsList = new ArrayList<>();
        newGoodsList.addAll(goodsSet);
        return newGoodsList;
    }

    @Override
    public Goods aopTest(int id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public Goods reflectTest(String modeoName, int id, Class<?> clazz) {
            Goods goods = goodsMapper.selectByPrimaryKey(id);
            return goods;

    }

    @Override
    @Cacheable(key="'goodsKey'")
    public int save(Goods goods) {
        return goodsMapper.insert(goods);
    }

    @Override
    @Cacheable(key="'id:'+#p0")
    public Goods get(int id) {
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        return goods;
    }

    @Override
    @CachePut(key="'goodsKey'")
    public int update(Goods goods) {
        return goodsMapper.updateByPrimaryKeySelective(goods);
    }

    @Override
    @CacheEvict(key="'goodsKey'")
    public int delete(int id) {
        return goodsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Goods getSingle(String name) {
        return goodsMapper.selectByName(name);
    }

//    @Override
//    public Integer getTotalPages(String number) {
//        Integer totalCounts = goodsMapper.selectTotalCounts(number);
//        int totalPages = (totalCounts  +  4  - 1)/4;
//        return totalPages;
//    }
}
