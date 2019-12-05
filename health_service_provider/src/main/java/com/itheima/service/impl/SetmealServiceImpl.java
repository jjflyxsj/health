package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.constant.RedisConstant;
import com.itheima.dao.SetmealDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = SetmealService.class,retries = -1)
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    SetmealDao setmealDao;
    @Autowired
    JedisPool jedisPool;
    @Override
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        setmealDao.add(setmeal);
        Integer setmealId = setmeal.getId();
        if (checkgroupIds!=null&&checkgroupIds.length>0){
        Map map=new HashMap<>();
        map.put("setmealId",setmealId);
        map.put("checkgroupIds",checkgroupIds);
        setmealDao.setSetmealAndCheckGroup(map);}
        String img = setmeal.getImg();
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,img);
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage,pageSize);
        Page<Setmeal> page=setmealDao.findPage(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public List<Setmeal> findAll() {
        List<Setmeal> list = setmealDao.findAll();
        return list;
    }

    @Override
    public Setmeal findById(Integer id) {
        Setmeal setmeal=setmealDao.findById(id);
        return setmeal;
    }

    @Override
    public List<Map> findSetmealReport() {
        return setmealDao.findSetmealReport();
    }
}
