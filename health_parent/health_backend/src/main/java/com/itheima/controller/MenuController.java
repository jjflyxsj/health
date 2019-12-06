package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.Menu;
import com.itheima.service.MenuService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Reference
    private MenuService menuService;

    @RequestMapping("/fingAllMenu")
    public List<Menu> fingAllMenu(String username){
        /*System.out.println(username);*/
        try {
            List<Menu> menuList= menuService.findAllMeau(username);
            return menuList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        //return null;
    }
    @RequestMapping("/pageQuery")
    public Result pageQuery(@RequestBody QueryPageBean queryPageBean){
        try{
            PageResult pageResult=menuService.pageQuery(queryPageBean);
            return new Result(true,"查询菜单成功",pageResult);
        }catch (Exception e){
            return new Result(false,"查询菜单失败");
        }
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Menu menu){
        try{
            menuService.add(menu);
            return new Result(true,"查询菜单成功");
        }catch (Exception e){
            return new Result(false,"查询菜单失败");
        }
    }

    @RequestMapping("/edit")
    public Result edit(@RequestBody Menu menu){
        try{
            menuService.edit(menu);
            return new Result(true,"修改菜单成功");
        }catch (Exception e){
            return new Result(false,"修改菜单失败");
        }
    }

    @RequestMapping("/delete")
    public Result delete(Integer id){
        try{
            menuService.delete(id);
            return new Result(true,"删除成功");
        }catch (Exception e){
            return new Result(false,"删除失败");
        }
    }
    @RequestMapping("/findAll")
    public Result findAll() {
        try{
            List<Menu> list = menuService.findAll();
            return  new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,list);
        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }
}
