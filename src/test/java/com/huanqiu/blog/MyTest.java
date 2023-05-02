package com.huanqiu.blog;

import com.huanqiu.blog.common.Constants;
import com.huanqiu.blog.common.ResponseResult;
import com.huanqiu.blog.recommend.Recommend;
import com.huanqiu.blog.recommend.entity.BlogEntity;
import com.huanqiu.blog.util.SnowFlakeUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huanqiu
 * @version 1.0
 * @description
 * @date 2023/02/18 下午 5:25
 */
public class MyTest {
    @Test
    public void test01(){
        for (int i = 0; i < 20; i++) {
            long nextId = SnowFlakeUtil.getNextId();
            //获取最后9位数字
            String s = String.valueOf(nextId).substring(String.valueOf(nextId).length() - 9);
            System.out.println(s);
        }
    }

    @Test
    public void test02(){
        BlogEntity blog1=new BlogEntity("001", List.of("java","c#","python","c++","编程","生活","美食","娱乐"));
        BlogEntity blog2=new BlogEntity("002", List.of("java","python","c++","编程","生活"));
        BlogEntity blog3=new BlogEntity("003", List.of("java","python","c++","编程"));
        BlogEntity blog4=new BlogEntity("004", List.of("java","c#","c++"));
        BlogEntity blog5=new BlogEntity("005", List.of("java","python","c++","编程","娱乐"));

        List<BlogEntity> allList = new ArrayList<>();
        allList.add(blog1);
        allList.add(blog2);
        allList.add(blog3);
        allList.add(blog4);
        allList.add(blog5);
        List<BlogEntity> myList = new ArrayList<>();
        myList.add(blog4);
        myList.add(blog5);
        long start = System.currentTimeMillis();
        Recommend.recommendItemByContent(myList,allList).forEach(System.out::println);
        long end = System.currentTimeMillis();
        System.out.println("耗时："+(end-start)+"ms");


    }
}
