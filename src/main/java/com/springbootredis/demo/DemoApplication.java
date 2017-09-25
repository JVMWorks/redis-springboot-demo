package com.springbootredis.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisKeyValueTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPool;

@SpringBootApplication
public class DemoApplication {

	@Value("${spring.redis.host}")
	private String redisHost;
	private static ApplicationContext context;

	public static void main(String[] args) {
		context = SpringApplication.run(DemoApplication.class, args);
//		RedisKeyValueTemplate template = ctx.getBean(RedisKeyValueTemplate.class);
	}

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory();
	}

	@Bean
	RedisTemplate<Object, Object> redisTemplate() {
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
	}

	@Bean
	JedisPool jedisPool() {
		return new JedisPool(jedisConnectionFactory().getPoolConfig(), redisHost);
	}
}
