package com.example.shirodemo.dao;

import com.example.shirodemo.model.Goods;
import org.apache.ibatis.jdbc.SQL;
import sun.management.counter.Counter;

public class GoodsSqlProvider {

    public String insertSelective(Goods record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("goods");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getNumber() != null) {
            sql.VALUES("number", "#{number,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Goods record) {
        SQL sql = new SQL();
        sql.UPDATE("goods");
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getNumber() != null) {
            sql.SET("number = #{number,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    public String selectByNumber(String number){
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("goods");
        sql.WHERE("number = #{number}");
        return sql.toString();
    }

    public String selectTotalCounts(String number){
        SQL sql = new SQL();
        sql.SELECT("count(*)");
        sql.FROM("goods");
        sql.WHERE("number = #{number}");
        return sql.toString();
    }

    public String selectAllGoods(){
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("goods");
        return sql.toString();
    }

}