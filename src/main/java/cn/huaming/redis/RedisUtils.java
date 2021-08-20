package cn.huaming.redis;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RedisUtils {

    @SuppressWarnings("rawtypes")
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     *
     * @param pattern
     */
    @SuppressWarnings("unchecked")
    public void removePattern(final String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    @SuppressWarnings("unchecked")
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public Object get(final String key) {
        Object result = null;
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * 模糊搜索
     *
     * @param pattern
     * @return
     */
    public Set keys(String pattern) {
        return redisTemplate.keys(pattern + "*");
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            log.error("key:" + key + ",value:" + value.toString() + "保存至redis失败：", e);
        }
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Long value) {
        boolean result = false;
        try {
            ValueOperations<String, Long> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            log.error("key:" + key + ",value:" + value.toString() + "保存至redis失败：", e);
        }
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            log.error("key:" + key + ",value:" + value.toString() + "保存至redis失败：", e);
        }
        return result;
    }

    public boolean setnx(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            operations.setIfAbsent(key, value);
            if (expireTime != null && expireTime > 0) {
                redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            }
            result = true;
        } catch (Exception e) {
            log.error("key:" + key + ",value:" + value.toString() + "保存至redis失败：", e);
        }
        return result;
    }

    /**
     * 指定秒
     */
    public long getExpireTimeType(String key) {
        long time = redisTemplate.getExpire(key, TimeUnit.SECONDS);
        return time;
    }

    /**
     * 指定分钟
     */
    public long getExpireTimeTypeForMin(String key) {
        long time = redisTemplate.getExpire(key, TimeUnit.MINUTES);
        return time;
    }

    public boolean setnx(final String key, Object value) {
        return setnx(key, value, 0L);
    }

}    
