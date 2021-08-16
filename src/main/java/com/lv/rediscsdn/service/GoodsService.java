package com.lv.rediscsdn.service;

import com.lv.rediscsdn.dao.GoodsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author lvjiangtao
 * @create 2021-08-14-10:12
 */
@Service
public class GoodsService {

    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private RedisTemplate redisTemplate;

    public void findGoods(int id) {
        Object o = redisTemplate.opsForValue().get(id);
        if ( o==null){
            goodsDao.findGoods(id);

        }else {

        }
    }
}
