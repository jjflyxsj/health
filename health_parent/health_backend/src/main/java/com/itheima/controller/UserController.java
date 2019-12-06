package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")

public class UserController {
    @Reference
    UserService userService;

    @RequestMapping("/getUsername")
    public Result getUsername() {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user);
        if (user == null) {
            return new Result(false, MessageConstant.GET_USERNAME_FAIL);
        } else {
            String username = user.getUsername();
            return new Result(true, MessageConstant.GET_USERNAME_SUCCESS, username);
        }
    }

    @RequestMapping("/add")
    public Result add(@RequestBody User user,Integer[] roleIds) {
        try {
            userService.add(user,roleIds);
        } catch (Exception e) {
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = userService.selectByCondition(queryPageBean);
        return pageResult;
    }

    @RequestMapping("/delete")
    public Result delete(Integer id) {
        System.out.println(id);
        try {
            userService.deleteById(id);
        } catch (Exception e) {
            return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }

    @RequestMapping("/edit")
    public Result edit(@RequestBody User user,Integer[] roleIds) {
        System.out.println(user);
        try {
            userService.editById(user,roleIds);
        } catch (Exception e) {
            return new Result(false, MessageConstant.EDIT_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }

    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
            List<Integer> list = userService.findRoleIds(id);
            return new Result(true, "查询数据成功", list);
        } catch (Exception e) {
            return new Result(true, "查询数据失败");
        }

    }
}