package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckItem;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CheckItemDao {
    @Insert(" insert into t_checkitem(code,name,sex,age,price,type,remark,attention)\n" +
            "                      values\n" +
            "        (#{code},#{name},#{sex},#{age},#{price},#{type},#{remark},#{attention})")
    void add(CheckItem checkItem);

    @Select({"<script>",
            " select * from t_checkitem ",
            "<if test='value != null and value.length > 0'>",
            "where code = #{value} or name = #{value}",
            "</if>",
            "</script>"
    })
    Page<CheckItem> selectByCondition(String value);

    @Select("select count(*) from t_checkgroup_checkitem where checkitem_id=#{id}")
    Long selectByCheckitemId(int id);

    @Delete("delete from t_checkitem where id = #{id} ")
    void deleteById(int id);

    @Update({"<script>",
            "update t_checkitem ",
            "<set>",
            "<if test='code != null '>",
            "code=#{code},","</if>",
            "<if test='name != null '>",
            "name=#{name},","</if>",
            "<if test='sex != null '>",
            "sex=#{sex},","</if>",
            "<if test='age != null '>",
            "age=#{age},","</if>",
            "<if test='price != null '>",
            "price=#{price},","</if>",
            "<if test='type != null '>",
            "type=#{type},","</if>",
            "<if test='attention != null '>",
            "attention=#{attention},","</if>",
            "<if test='remark != null '>",
            "remark=#{remark},","</if>",
            "</set>",
            " where id = #{id}",
            "</script>",
    })
    void editById(CheckItem checkItem);


    @Select("select * from t_checkitem where id =#{id}")
    CheckItem findOne(int id);

    @Select("select * from t_checkitem")
    List<CheckItem> findAll();

    @Select("select * from t_checkitem where id in " +
            "(select checkitem_id from  t_checkgroup_checkitem where checkgroup_id=#{id})")
    CheckItem findById(int id);
}
