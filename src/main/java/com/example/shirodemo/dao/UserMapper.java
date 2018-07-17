package com.example.shirodemo.dao;

import com.example.shirodemo.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {
    @Delete({
        "delete from user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into user (id, name, ",
        "password, role_id)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{roleId,jdbcType=INTEGER})"
    })
    int insert(User record);

    @InsertProvider(type=UserSqlProvider.class, method="insertSelective")
    int insertSelective(User record);

    @Select({
        "select",
        "id, name, password, role_id",
        "from user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.INTEGER)
    })
    User selectByPrimaryKey(Integer id);

    @UpdateProvider(type=UserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(User record);

    @Update({
        "update user",
        "set name = #{name,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "role_id = #{roleId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(User record);

    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="role_id", property="roleId", jdbcType=JdbcType.INTEGER)
    })
    @SelectProvider(type = UserSqlProvider.class, method = "selectByName")
    User selectByName(String name);
}