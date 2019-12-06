package com.itcast.test;

import com.itheima.utils.QiniuUtils;
import org.junit.Test;

public class Demo1 {
    @Test
    public void test1(){
        QiniuUtils.upload2Qiniu("E:\\谷歌\\12d56.jpg","02.jpg");
    }
    @Test
    public void test2(){
        QiniuUtils.deleteFileFromQiniu("01.jpg");
    }
}
