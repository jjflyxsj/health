package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.entity.Result;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetmealService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/setmeal")
public class Setmeal_Controller {
    @Reference
    private SetmealService setmealService;

    @RequestMapping("/getSetmeal")
    public Result findAll(){
        try {
            List<Setmeal> list= setmealService.findAll();
            return new Result(true, "查询数据成功",list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "查询数据失败");
        }
    }
    @RequestMapping("/findById")
    public Result findById(Integer id){
        try {
            Setmeal setmeal= setmealService.findById(id);
            return new Result(true, "查询数据成功",setmeal);
        } catch (Exception e) {
            return new Result(false, "查询数据失败");
        }
    }
}
