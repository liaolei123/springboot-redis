package com.kk.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.kk.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Mr.kk
 */
@RestController
public class RedisTest {

    //operations.set("expire", user,100, TimeUnit.MILLISECONDS);  可以设置失效的时间
    //redisTemplate.delete("deletekey"); 可以根据key删除数据
    //Set<String> diffs=set.difference(key1,key2); 对比key1和key2 set中不同的
    //Set<String> unions=set.union(key3,key4);unions 会取两个集合的合集

    @Autowired
    private RedisTemplate redisTemplate;

    //测试redis的链接
    @RequestMapping(value = "/test")
    public void testRedis(){
        redisTemplate.opsForValue().set("kk", "ityouknow");
        System.out.println("ityouknow"+"===================="+redisTemplate.opsForValue().get("kk"));
    }

    //测试redis的对象的存取
    @RequestMapping(value = "/testObject")
    public void testObject(){
        User user = new User("123456","kk","zhaokk@yonyo.com","山西省西安市");
        ValueOperations<String, User> operations=redisTemplate.opsForValue();
        operations.set("zkk", user);
        User u=operations.get("zkk");
        System.out.println("ityouknow"+"===================="+redisTemplate.opsForValue().get("zkk"));
    }

    //测试redis的hash的存取
    @RequestMapping(value = "/testHash")
    public void testHash(){
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put("hash","you","you");
        String value=(String) hash.get("hash","you");
        System.out.println("hash value :"+value);
    }

    //测试redis的list的存取
    @RequestMapping(value = "/testList")
    public void testList(){
        ListOperations<String, String> list = redisTemplate.opsForList();
        list.leftPush("list","it");
        list.leftPush("list","you");
        list.leftPush("list","know");
        String value=(String)list.leftPop("list");
        System.out.println("list value :"+value.toString());
    }


    //测试redis的set的存取
    @RequestMapping(value = "/testSet")
    public void testSet(){
        String key="set";
        SetOperations<String, String> set = redisTemplate.opsForSet();
        set.add(key,"it");
        set.add(key,"you");
        set.add(key,"you");
        set.add(key,"know");
        Set<String> values=set.members(key);
        for (String v:values){
            System.out.println("set value :"+v);
        }
    }

    //测试redis的zset的存取
    @RequestMapping(value = "/testZset")
    public void testZset(){
        String key="zset";
        redisTemplate.delete(key);
        ZSetOperations<String, String> zset = redisTemplate.opsForZSet();
        zset.add(key,"it",1);
        zset.add(key,"you",6);
        zset.add(key,"know",4);
        zset.add(key,"neo",3);

        Set<String> zsets=zset.range(key,0,3);
        for (String v:zsets){
            System.out.println("zset value :"+v);
        }
        Set<String> zsetB=zset.rangeByScore(key,0,3);
        for (String v:zsetB){
            System.out.println("zsetB value :"+v);
        }
    }

}
