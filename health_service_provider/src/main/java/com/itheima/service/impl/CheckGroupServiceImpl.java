package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.CheckGroupDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;
import com.itheima.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = CheckGroupService.class,retries = -1)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {
    @Autowired
    CheckGroupDao checkGroupDao;

    @Override
    public void add(CheckGroup checkGroup, Integer[] checkItemIds) {
        checkGroupDao.add(checkGroup);
        Integer id = checkGroup.getId();
        if (checkItemIds != null && checkItemIds.length > 0) {
            Map<String,Integer> map=new HashMap<>();
            for (Integer checkItemId : checkItemIds) {
                map.put("checkGroupId",id);
                map.put("checkItemId",checkItemId);
                checkGroupDao.setCheckGroupAndCheckItem(map);
            }
        }
    }


    @Override
    public PageResult selectByCondition(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage,pageSize);
        Page<CheckGroup> page= checkGroupDao.findByqueryString(queryString);
        List<CheckGroup> result = page.getResult();

        return new PageResult(page.getTotal(),result);
    }

    @Override
    public void updata(CheckGroup checkGroup, Integer[] checkitemIds) {
        checkGroupDao.UpdataCheckGroup(checkGroup);
        Integer id = checkGroup.getId();
        checkGroupDao.deleteAssocication(id);
        if (checkitemIds != null && checkitemIds.length > 0) {
            Map<String,Integer> map=new HashMap<>();
            for (Integer checkItemId : checkitemIds) {
                map.put("checkGroupId",id);
                map.put("checkItemId",checkItemId);
                checkGroupDao.setCheckGroupAndCheckItem(map);
            }
        }
    }

    @Override
    public List<CheckGroup> findAll() {
        List<CheckGroup> checkGroups = checkGroupDao.findAll();
        return checkGroups;
    }

    @Override
    public List<Integer> findCheckItemIds(int CheckGroupId) {
        return checkGroupDao.findCheckItemIds(CheckGroupId);
    }




}
