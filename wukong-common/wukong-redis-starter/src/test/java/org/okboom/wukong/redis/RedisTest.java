package org.okboom.wukong.redis;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tookbra
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RedisApplication.class)
public class RedisTest {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testStringCache() {
        redisTemplate.opsForValue().set("test", "test");
        String test = (String) redisTemplate.opsForValue().get("test");
        Assert.assertEquals(test, "test");
    }


    @Test
    public void testHashMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "wukong");
        redisTemplate.opsForHash().putAll("testHash", map);
    }
}
