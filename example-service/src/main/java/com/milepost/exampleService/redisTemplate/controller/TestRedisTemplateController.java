package com.milepost.exampleService.redisTemplate.controller;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ruifu Hua on 2020/2/28.
 */
@Controller
@RequestMapping("/testRedisTemplate")
public class TestRedisTemplateController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 测试键值对，设置值
     * @param key
     * @param value
     * @return
     */
    @ResponseBody
    @GetMapping("/testRedisTemplate/opsForValue")
    public String opsForValue(@RequestParam("key") String key, @RequestParam("value") String value){
        redisTemplate.opsForValue().set(key, value);
        return DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date());
    }

    /**
     * 测试键值对，获取值
     * @param key
     * @return
     */
    @ResponseBody
    @GetMapping("/testRedisTemplate/opsForValue/get")
    public String opsForValueGet(@RequestParam("key") String key){
        Object value = redisTemplate.opsForValue().get(key);
        return (String) value;
    }

    /**
     * 测试map
     * @param key
     * @param value
     * @return
     */
    @ResponseBody
    @GetMapping("/testRedisTemplate/opsForHash")
    public String opsForHash(@RequestParam("mapKey") String mapKey, @RequestParam("key") String key, @RequestParam("value") String value){
        redisTemplate.opsForHash().put(mapKey, key, value);
        return DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date());
    }

    /**
     * 测试list
     * @param key
     * @param value
     * @return
     */
    @ResponseBody
    @GetMapping("/testRedisTemplate/opsForList")
    public String rightPush(@RequestParam("key") String key, @RequestParam("value") String value){
        redisTemplate.opsForList().rightPush(key, value);
        return DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date());
    }

    /**
     * 测试set
     * @param key
     * @param value
     * @return
     */
    @ResponseBody
    @GetMapping("/testRedisTemplate/opsForSet")
    public String opsForSet(@RequestParam("key") String key, @RequestParam("value") String value){
        redisTemplate.opsForSet().add(key, value);
        return DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date());
    }

    /**
     * 测试zset
     * @param key
     * @param value
     * @return
     */
    @ResponseBody
    @GetMapping("/testRedisTemplate/opsForZSet")
    public String opsForZSet(@RequestParam("key") String key, @RequestParam("value") String value){
        redisTemplate.opsForZSet().add(key, value, 1);
        return DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date());
    }

    /**
     * 测试Jedis使用后是否需要关闭，
     * 获取的jedisCluster在使用后不需要关闭，多次获取到的都是同一个JedisCluster对象。
     * @param count 一定要大于配置的连接数才能看出效果来
     * @return
     */
    @ResponseBody
    @GetMapping("/testJedisClusterClose")
    public String testJedisClusterClose(@RequestParam("count") int count) throws IOException {
        List<JedisCluster> jedisClusterList = new ArrayList<>();
        try {
            for(int i=0; i<count; i++){
                JedisCluster jedisCluster = (JedisCluster) redisTemplate.getConnectionFactory().getConnection().getNativeConnection();
                jedisClusterList.add(jedisCluster);
                System.out.println(jedisCluster);
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }
        return DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date());
    }

    /**
     * jedisCluster设置值
     * @param key
     * @param value
     * @return
     */
    @ResponseBody
    @GetMapping("/testJedisCluster/set")
    public String testJedisClusterSet(@RequestParam("key") String key, @RequestParam("value") String value){
        JedisCluster jedisCluster = null;
        try {
            jedisCluster = (JedisCluster) redisTemplate.getConnectionFactory().getConnection().getNativeConnection();
            jedisCluster.set(key, value);
        }finally {
//            if(jedisCluster != null){
//                jedisCluster.close();
//            }
        }
        return DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date());
    }

    /**
     * jedisCluster获取值
     * @param key
     * @return
     */
    @ResponseBody
    @GetMapping("/testJedisCluster/get")
    public String testJedisClusterGet(@RequestParam("key") String key){
        JedisCluster jedisCluster = null;
        Object result = null;
        try {
            jedisCluster = (JedisCluster) redisTemplate.getConnectionFactory().getConnection().getNativeConnection();
            result = jedisCluster.get(key);
        }finally {
//            if(jedisCluster != null){
//                jedisCluster.close();
//            }
        }
        return (String) result;
    }

    /**
     * 测试Jedis使用后是否需要关闭，
     * 获取的jedis在使用后一定要关闭。
     * @param count，一定要大于配置的连接数才能看出效果来
     * @return
     */
    @ResponseBody
    @GetMapping("/testJedisClose")
    public String testJedisClose(@RequestParam("count") int count){
        List<Jedis> jedisList = new ArrayList<>();
        try {
            for(int i=0; i<count; i++){
                Jedis jedis = (Jedis) redisTemplate.getConnectionFactory().getConnection().getNativeConnection();
                jedisList.add(jedis);
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            logger.info("获取了"+ jedisList.size() +"个连接，连接被使用尽了，开始关闭连接");
            for(Jedis jedis : jedisList){
                jedis.close();
            }
        }
        return DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date());
    }

    /**
     * jedis设置值
     *
     * @param key
     * @param value
     * @return
     */
    @ResponseBody
    @GetMapping("/testJedis/set")
    public String testJedisSet(@RequestParam("key") String key, @RequestParam("value") String value){
        Jedis jedis = null;
        try {
            jedis = (Jedis) redisTemplate.getConnectionFactory().getConnection().getNativeConnection();
            jedis.set(key, value);
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date());
    }

    /**
     * jedis获取值
     *
     * @param key
     * @return
     */
    @ResponseBody
    @GetMapping("/testJedis/get")
    public String testJedisGet(@RequestParam("key") String key){
        Jedis jedis = null;
        Object result = null;
        try {
            jedis = (Jedis) redisTemplate.getConnectionFactory().getConnection().getNativeConnection();
            result = jedis.get(key);
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return (String) result;
    }
}
