package com.fulan.springboot.service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

	private static Log log = LogFactory.getLog(RedisService.class);

	@Autowired
	private StringRedisTemplate redisTemplate;

	public boolean set(final String key, String value) {
		boolean result = false;
		try {
			ValueOperations<String, String> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			result = true;
		} catch (Exception e) {
			log.info("redis设置值出错:" + e.getMessage());
		}
		return result;
	}

	public boolean set(final String key, String value, Long expireTime) {
		boolean result = false;
		try {
			ValueOperations<String, String> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void removePattern(final String pattern) {
		Set<String> keys = redisTemplate.keys(pattern);
		if (keys.size() > 0)
			redisTemplate.delete(keys);
	}

	public void remove(final String key) {
		if (exists(key)) {
			redisTemplate.delete(key);
		}
	}

	public boolean exists(final String key) {
		return redisTemplate.hasKey(key);
	}

	public Object get(final String key) {
		Object result = null;
		ValueOperations<String, String> operations = redisTemplate.opsForValue();
		result = operations.get(key);
		return result;
	}
}
