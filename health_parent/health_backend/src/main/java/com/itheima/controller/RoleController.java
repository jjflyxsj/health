package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.Role;
import com.itheima.service.RoleService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Reference
    RoleService roleService;
    @RequestMapping("/findAll")
    public Result findAll() {
        try{
            List<Role> list = roleService.findAll();
            return  new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,list);
        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }
    @RequestMapping("/findById")
    public Result findById(int id){

        try {
            Role role= roleService.findById(id);
            return new Result(true,"查询角色成功",role);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"查询角色失败");
        }

    }

    @RequestMapping("/delete")
    public Result delete(int id){

        try {
            roleService.delete(id);
            return new Result(true,"删除角色成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除角色失败");
        }

    }


    @RequestMapping("/add")
    public Result add(@RequestBody Role role, Integer[] menuIds, Integer[] permissionIds){

        try {
            roleService.add(role,menuIds,permissionIds);
            return new Result(true,"新增角色成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"新增角色失败");
        }

    }
    @RequestMapping("/edit")
    public Result edit(@RequestBody Role role,Integer[] menuIds,Integer[] permissionIds){
        //System.out.println(checkGroupParam);
        try {
            roleService.edit(role,menuIds,permissionIds);
        }catch (Exception e){

            return new Result(false,"修改角色失败");
        }

        return new Result(true,"修改角色成功");
    }
    @RequestMapping("/findMenuIdsByRoleId")
    public Result findMenuIdsByRoleId(Integer id){

        try {
            List<Integer> menuList= roleService.findMenuIdsByRoleId(id);
            return new Result(true,"查询菜单id成功",menuList);
        }catch (Exception e){

            return new Result(false,"查询菜单id失败");
        }

    }
    @RequestMapping("/findPermissionIdsByRoleId")
    public Result findPermissionIdsByRoleId(Integer id){

        try {
            List<Integer> permissionList= roleService.findPermissionIdsByRoleId(id);
            return new Result(true,"查询权限id成功",permissionList);
        }catch (Exception e){

            return new Result(false,"查询权限id失败");
        }

    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {

        PageResult pageResult = roleService.pageQuery(queryPageBean);


        return pageResult;
    }
}
