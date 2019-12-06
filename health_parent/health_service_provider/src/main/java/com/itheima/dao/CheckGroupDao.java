package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;
import org.apache.ibatis.annotations.Insert;

import java.util.List;
import java.util.Map;

public interface CheckGroupDao {

    void add(CheckGroup checkGroup);

    void setCheckGroupAndCheckItem(Map map);

    Page<CheckGroup> findByqueryString(String queryString);

    void UpdataCheckGroup(CheckGroup checkGroup);

    List<CheckGroup> findAll();

    void deleteAssocication(Integer id);

    List<Integer> findCheckItemIds(int CheckGroupId);

    CheckGroup findById(int id);
}
