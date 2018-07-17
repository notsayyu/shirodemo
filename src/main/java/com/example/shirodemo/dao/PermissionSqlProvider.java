package com.example.shirodemo.dao;

import com.example.shirodemo.model.Permission;
import org.apache.ibatis.jdbc.SQL;

public class PermissionSqlProvider {

    public String insertSelective(Permission record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("permission");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getPermission() != null) {
            sql.VALUES("permission", "#{permission,jdbcType=VARCHAR}");
        }
        
        if (record.getDescription() != null) {
            sql.VALUES("description", "#{description,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Permission record) {
        SQL sql = new SQL();
        sql.UPDATE("permission");
        
        if (record.getPermission() != null) {
            sql.SET("permission = #{permission,jdbcType=VARCHAR}");
        }
        
        if (record.getDescription() != null) {
            sql.SET("description = #{description,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    public String selectAll(){
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("permission");
        return sql.toString();
    }
}