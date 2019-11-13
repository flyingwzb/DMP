package com.example.dmp.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

/**
 * @ClassName:    JsonLUtils
 * @Description:  json转换工具类
 * @Author:       王志彪(Will Wang)
 * @Date:         2019/11/13 11:37
 * @Version:      V1.0
 * @Since:        V1.0
 */
public final class JsonLUtils {
	
	private final static Logger logger = LoggerFactory.getLogger(JsonLUtils.class);

	public static ObjectMapper objectMapper;

	/**
	 * 使用泛型方法，把json字符串转换为相应的JavaBean对象。
	 * (1)转换为普通JavaBean：readValue(json,Student.class)
	 * (2)转换为List,如List<Student>,将第二个参数传递为Student
	 * 然后使用Arrays.asList();方法把得到的数组转换为特定类型的List
	 * 
	 * @param jsonStr
	 * @param valueType
	 * @return
	 */
	public static <T> T readValue(String jsonStr, Class<T> valueType) {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
		}
		configure(objectMapper);
		try {
			return objectMapper.readValue(jsonStr, valueType);
		} catch (Exception e) {
			logger.error("json字符串转换为相应的JavaBean对象错误",e);
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * @description json数组转List
	 * @author houkj
	 * @date 2019/3/21 16:10
	 * @param jsonStr
     * @param valueTypeRef
	 * @return T
	 */
	public static <T> T readValue(String jsonStr, TypeReference<T> valueTypeRef) {
			if (objectMapper == null) {
			objectMapper = new ObjectMapper();
		}

		configure(objectMapper);

		try {
			return objectMapper.readValue(jsonStr, valueTypeRef);
		} catch (Exception e) {
			logger.error("json数组转List错误",e);
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * @description 把JavaBean转换为json字符串
	 * @author houkj
	 * @date 2019/3/21 16:09
	 * @param object
	 * @return java.lang.String
	 */
	public static String toJSon(Object object) {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
		}
		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			logger.error("把JavaBean转换为json字符串错误",e);
		}
		return null;
	}

	/**
	 * @description 设置json转换配置
	 * @author houkj
	 * @date 2019/3/21 16:11
	 * @param objectMapper
	 * @return void
	 */
	private static void configure(ObjectMapper objectMapper){

		// 忽略json字符串中不识别的属性
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		// 忽略无法转换的对象 “No serializer found for class com.xxx.xxx”
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
		objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));

		JavaTimeModule javaTimeModule = new JavaTimeModule();
		javaTimeModule.addSerializer(LocalDateTime.class,new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(LocalDateLUtils.yyyy_MM_dd_HH_mm_ss)));
		javaTimeModule.addSerializer(LocalDate.class,new LocalDateSerializer(DateTimeFormatter.ofPattern(LocalDateLUtils.yyyy_MM_dd)));
		javaTimeModule.addSerializer(LocalTime.class,new LocalTimeSerializer(DateTimeFormatter.ofPattern(LocalDateLUtils.HH_mm_ss)));
		javaTimeModule.addDeserializer(LocalDateTime.class,new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(LocalDateLUtils.yyyy_MM_dd_HH_mm_ss)));
		javaTimeModule.addDeserializer(LocalDate.class,new LocalDateDeserializer(DateTimeFormatter.ofPattern(LocalDateLUtils.yyyy_MM_dd)));
		javaTimeModule.addDeserializer(LocalTime.class,new LocalTimeDeserializer(DateTimeFormatter.ofPattern(LocalDateLUtils.HH_mm_ss)));
		// 使用java8的日期对象
		objectMapper.registerModule(new Jdk8Module())
				.registerModule(javaTimeModule);

	}

}