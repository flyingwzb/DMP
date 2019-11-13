package com.example.dmp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName:    RestTemplateConfig
 * @Description:  rest请求模板配置
 * @Author:       王志彪(Will Wang)
 * @Date:         2019/11/13 16:40
 * @Version:      V1.0
 * @Since:        V1.0
 */
@Configuration
public class RestTemplateConfig {


	@Bean
	//@ConditionalOnMissingBean(RestTemplate.class) 该注解表示，如果存在它修饰的类的bean，则不需要再创建这个bean；可以给该注解传入参数例如@ConditionOnMissingBean(name = "example")，这个表示如果name为“example”的bean存在，这该注解修饰的代码块不执行。
	public RestTemplate restTemplate() {

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MyMappingJackson2HttpMessageConverter());
		return restTemplate;
	}
	


}
