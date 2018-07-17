package com.example.shirodemo.dao;

import com.example.shirodemo.model.Permission;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PermissionMapper {
    @Delete({
        "delete from permission",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into permission (id, permission, ",
        "description)",
        "values (#{id,jdbcType=INTEGER}, #{permission,jdbcType=VARCHAR}, ",
        "#{description,jdbcType=VARCHAR})"
    })
    int insert(Permission record);

    @InsertProvider(type=PermissionSqlProvider.class, method="insertSelective")
    int insertSelective(Permission record);

    @Select({
        "select",
        "id, permission, description",
        "from permission",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="permission", property="permission", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR)
    })
    Permission selectByPrimaryKey(Integer id);

    @UpdateProvider(type=PermissionSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Permission record);

    @Update({
        "update permission",
        "set permission = #{permission,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Permission record);

    @SelectProvider(type = PermissionSqlProvider.class, method = "selectAll")
    List<Permission> selectAll();
}