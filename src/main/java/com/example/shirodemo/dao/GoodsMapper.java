package com.example.shirodemo.dao;

import com.example.shirodemo.model.Goods;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GoodsMapper {
    @Delete({
        "delete from goods",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into goods (id, name, ",
        "number)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{number,jdbcType=VARCHAR})"
    })
    int insert(Goods record);

    @InsertProvider(type=GoodsSqlProvider.class, method="insertSelective")
    int insertSelective(Goods record);

    @Select({
        "select",
        "id, name, number",
        "from goods",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="number", property="number", jdbcType=JdbcType.VARCHAR)
    })
    Goods selectByPrimaryKey(Integer id);

    @UpdateProvider(type=GoodsSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Goods record);

    @Update({
        "update goods",
        "set name = #{name,jdbcType=VARCHAR},",
          "number = #{number,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Goods record);

    @SelectProvider(type = GoodsSqlProvider.class, method = "selectByNumber")
    List<Goods> selectByNumber(String number);

    @SelectProvider(type = GoodsSqlProvider.class, method = "selectTotalCounts")
    Integer selectTotalCounts(String number);

    @SelectProvider(type = GoodsSqlProvider.class, method = "selectAllGoods")
    List<Goods> selectAllGoods();

    @SelectProvider(type = GoodsSqlProvider.class, method = "selectByName")
    Goods selectByName(String name);
}