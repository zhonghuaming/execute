package cn.huaming.redis;

import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("redis")
@Slf4j
public class TestRedisController {
    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private StringRedisTemplate template;


    @RequestMapping(value = "/test")
    @ResponseBody
    public String go(){
        boolean set = redisUtils.set("ilhStubbornDonkeyToken", "dfsdfs");
        return set+"";
    }

    @RequestMapping(value = "/t1")
    @ResponseBody
    public String go1(){
        template.opsForValue().set("mytest", "1",1000, TimeUnit.SECONDS);
        return "success";
    }

    @RequestMapping(value = "/t2")
    @ResponseBody
    public String go2(){
        Long my = template.opsForValue().increment("mytest", 1);
        return String.valueOf(my);
    }

}