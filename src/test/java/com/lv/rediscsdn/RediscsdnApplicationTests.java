package com.lv.rediscsdn;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.db.Db;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class RediscsdnApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    void contextLoads() {
    }

    //Redis --- String API的使用
    @Test
    public void redisString(int notifyId,String content){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String key = "redis:springboot:string";
        valueOperations.set(key,"23");
        valueOperations.increment(key);

    }
    //Redis --- List API的使用
    @Test
    public void redisList(){
        ListOperations listOperations = redisTemplate.opsForList();
        String key = "redis:springboot:list";
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        //leftPushAll()将 集合从左放入 对应key的list中
        listOperations.leftPushAll(key,list);
        listOperations.range(key,0,-1);
        log.info("redis集合<redis:springboot:list>一共有{}个数",listOperations.size(key));
        log.info("redis集合<redis:springboot:list>数有这些",listOperations.range(key,0,-1));
    }

    //Redis --- ZSet API的使用
    @Test
    public void redisZset(){
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        String key = "redis:springboot:zset";
        zSetOperations.add(key,"15364611507",40);
        zSetOperations.add(key,"15364611507",40);
        zSetOperations.add(key,"1086",10);
        zSetOperations.add(key,"1010",100);
        Set<ZSetOperations.TypedTuple<String>> set = zSetOperations.rangeWithScores(key, 0, -1);
        set.forEach(new Consumer<ZSetOperations.TypedTuple<String>>() {
            @Override
            public void accept(ZSetOperations.TypedTuple<String> typel) {
                log.info("排序----{}--{}",typel.getValue(),typel.getScore());
            }
        });
    }
    @Test
    public void redisHash(){
        HashOperations hashOperations = redisTemplate.opsForHash();
        String key = "redis:springboot:hash";
        hashOperations.put(key,"lucy",100);
        Map<String, String> map = new HashMap<>();
        map.put("jack","100");
        map.put("rose","100");
        map.put("james","100");
        hashOperations.putAll(key,map);
        Map<String, String> entries = hashOperations.entries(key);
        //hash 获取filed 中的value ----> get(key,field) ---> 返回field中的value
        String jack = (String) hashOperations.get(key, "jack");


    }
}
