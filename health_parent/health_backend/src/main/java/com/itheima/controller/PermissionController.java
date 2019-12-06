package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.Permission;
import com.itheima.service.PermissionService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Reference
    PermissionService permissionService;
    @RequestMapping("/findAll")
    public Result findAll() {
        try{
            List<Permission> list = permissionService.findAll();
            return  new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,list);
        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }
    @RequestMapping("/pageQuery")
    public Result queryPage(@RequestBody QueryPageBean queryPageBean){
        try {
            PageResult pageResult=permissionService.pageQuery(queryPageBean);
            return new Result(true, "查询权限列表成功",pageResult);
        }catch (Exception e){
            return new Result(false,"查询权限列表失败");
        }

    }

    @RequestMapping("/add")
    public Result add(@RequestBody Permission permission){
        try {
            permissionService.add(permission);
            return new Result(true,"添加权限成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"添加权限失败");
        }
    }

    @RequestMapping("/edit")
    public Result edit(@RequestBody Permission permission){
        try{
            permissionService.edit(permission);
            return new Result(true,"修改权限信息成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"修改权限信息失败");
        }
    }

    @RequestMapping("/delete")
    public Result delete(Integer id){
        try{
            permissionService.delete(id);
            return new Result(true,"删除成功");
        }catch (Exception e){
            return new Result(false,"删除失败");
        }
    }

}
