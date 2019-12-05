package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;

import java.util.List;

public interface CheckGroupService {


    PageResult selectByCondition(QueryPageBean queryPageBean);

    void updata(CheckGroup checkGroup, Integer[] checkitemIds);

    List<CheckGroup> findAll();

    List<Integer> findCheckItemIds(int CheckGroupId);

    void add(CheckGroup checkGroup, Integer[] checkitemIds);
}
