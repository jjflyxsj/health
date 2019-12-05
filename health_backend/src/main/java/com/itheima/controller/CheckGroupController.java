package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckGroup;
import com.itheima.service.CheckGroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/checkGroup")
public class CheckGroupController {
    @Reference
    CheckGroupService checkGroupServisce;

    @RequestMapping("/add")
    public Result  add(@RequestBody CheckGroup checkGroup,Integer[] checkitemIds){
        try {
            checkGroupServisce.add(checkGroup,checkitemIds);
        } catch (Exception e) {
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }
        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = checkGroupServisce.selectByCondition(queryPageBean);
        return pageResult;
    }

    @RequestMapping("/update")
        public Result  update(@RequestBody CheckGroup checkGroup,Integer[] checkitemIds){
            try {
                checkGroupServisce.updata(checkGroup,checkitemIds);
            } catch (Exception e) {
                return new Result(false, MessageConstant.EDIT_CHECKITEM_FAIL);
            }
            return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
        }
    @RequestMapping("/findAll")
        public Result  findAll(){
            try {
                List<CheckGroup> list= checkGroupServisce.findAll();
                return new Result(true, "查询数据成功",list);
            } catch (Exception e) {
                return new Result(false, "查询数据失败");
            }

    }
    @RequestMapping("/findById")
    public Result  findById(Integer id){
        try {
            List<Integer> list= checkGroupServisce.findCheckItemIds(id);
            return new Result(true, "查询数据成功",list);
        } catch (Exception e) {
            return new Result(true, "查询数据失败");
        }

    }
    }

