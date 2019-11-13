package com.example.dmp.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

/**
 * 
 * @ClassName EmptyUtils
 * @Description 空对象判断工具
 * @author houkj
 * @version V1.0
 * @since V1.0
 * @date 2018年7月19日下午6:36:23
 *
 */
@SuppressWarnings("rawtypes")
public class EmptyUtils {

	public static boolean isNotEmpty(Object o) {
		return !isEmpty(o);
	}
	
	public static boolean isEmpty(Object o) {
		if (o == null){
			return true;
		} else if (o instanceof Collection) {
			return ((Collection) o).isEmpty();
		} else if (o instanceof Map) {
			return ((Map) o).isEmpty();
		} else if (o instanceof String) {
			return ((String) o).trim().length() <= 0;
		} else if (o.getClass().isArray()){
			return Array.getLength(o) <= 0;
		}		
		return false;
	}

	//判断该对象是否: 返回ture表示所有属性为null  返回false表示不是所有属性都是null
	public static boolean isAllFieldNull(Object obj) throws Exception{
		Class stuCla = (Class) obj.getClass();// 得到类对象
		Field[] fs = stuCla.getDeclaredFields();//得到属性集合
		boolean flag = true;
		for (Field f : fs) {//遍历属性
			f.setAccessible(true); // 设置属性是可以访问的(私有的也可以)
			Object val = f.get(obj);// 得到此属性的值
			if(val!=null) {//只要有1个属性不为空,那么就不是所有的属性值都为空
				flag = false;
				break;
			}
		}
		return flag;
	}

}
