package com.example.dmp.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @ClassName:    RedisTemplateConfig
 * @Description:  redis 缓存 配置
 * @Author:       王志彪(Will Wang)
 * @Date:         2019/11/13 16:44
 * @Version:      V1.0
 * @Since:        V1.0
 */
@Configuration
public class RedisTemplateConfig {

	/**
	 * 
	 * @Description key序列化使用String,value序列化使用jackson2JsonRedisSerializer
	 * @since V1.0
	 * @param redisConnectionFactory
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Bean(name="redisJsonTemplate")
	public RedisTemplate<Object, Object> redisJsonTemplate(RedisConnectionFactory redisConnectionFactory) {

		RedisTemplate<Object, Object> redisJsonTemplate = new RedisTemplate<>();
		redisJsonTemplate.setConnectionFactory(redisConnectionFactory);

        // 使用Jackson2JsonRedisSerialize 替换默认序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // 设置value的序列化规则和 key的序列化规则
        redisJsonTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisJsonTemplate.setKeySerializer(new StringRedisSerializer());
        redisJsonTemplate.afterPropertiesSet();
        return redisJsonTemplate;
	}

}
