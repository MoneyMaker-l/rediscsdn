package com.lv.rediscsdn.controller;

import com.lv.rediscsdn.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lvjiangtao
 * @create 2021-08-14-9:54
 */
@RestController
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @RequestMapping(value = "/findGoods",method = RequestMethod.GET)
    public void findGoods(@RequestBody Integer id ){
        if (id <= 0 ){
            System.out.println("输入错误");
        }
        goodsService.findGoods(id);
    }
}
