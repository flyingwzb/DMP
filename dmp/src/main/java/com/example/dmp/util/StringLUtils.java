package com.example.dmp.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @ClassName StringLUtils
 * @Description 字符串工具类
 * @author houkj
 * @version V1.0
 * @since V1.0
 * @date 2018年7月19日下午6:39:23
 *
 */
public class StringLUtils {

	/**
	 * 批量判断空入参
	 * 
	 * @param strs
	 * @return
	 */
	public static boolean isBlank(String... strs) {

		for (String str : strs) {
			if (StringUtils.isBlank(str)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 获取6位随机数字验证码
	 * 
	 * @return
	 */
	public static String getCode() {

		Random rad = new Random();
		return (rad.nextInt(1000000) + "000000").substring(0, 6);

	}
	
	/**
	 * 获取  时间戳 + 6位随机数
	 * 
	 * @return
	 */
	public static String getCurrentTimeCode(String seg) {
		
		if(null==seg) {
			seg = "";
		}
		
		return System.currentTimeMillis() + seg + getCode();
	}

	/**
	 * 判断手机号码是否有问题 （有问题：true, 没问题：false）
	 * 
	 * @param mobile
	 * @return
	 */
	public static boolean isBlankMobile(String mobile) {
		if (StringUtils.isBlank(mobile)) {
			return true;
		}
		Pattern r = Pattern.compile(RegexUtils.MOBILE);
		Matcher m = r.matcher(mobile);
		if (!m.find()) {
			return true;
		}
		return false;

	}

	/**
	 * 判断字符串是否不为6位数字 （有问题：true, 没问题：false）
	 * 
	 * @param numStr
	 * @return
	 */
	public static boolean isBlankNum6(String numStr) {

		if (StringUtils.isBlank(numStr)) {
			return true;
		}
		Pattern r = Pattern.compile(RegexUtils.NUMB6);
		Matcher m = r.matcher(numStr);

		if (!m.find()) {
			return true;
		}

		return false;

	}

	/**
	 * 判断邮箱地址是否有问题 （有问题：true, 没问题：false）
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isBlankEmail(String email) {
		if (StringUtils.isBlank(email)) {
			return true;
		}
		Pattern r = Pattern.compile(RegexUtils.EMAIL);
		Matcher m = r.matcher(email);
		if (!m.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 随机生成UUID
	 * 
	 * @return
	 */
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		String uuidStr = str.replace("-", "");
		return uuidStr;
	}

	/**
	 * 根据字符串生成固定UUID
	 * 
	 * @param name
	 * @return
	 */
	public static String getUUID(String name) {
		UUID uuid = UUID.nameUUIDFromBytes(name.getBytes());
		String str = uuid.toString();
		String uuidStr = str.replace("-", "");
		return uuidStr;
	}


	/**
	 * 
	 * 去空
	 * @param o
	 */
	public static String str(Object o) {
		return o == null?"":o.toString();
	}
	
	/**
	 * Object转数字默认0
	 * @param o
	 */
	public static Double strToDouble(Object o) {
		return o == null?0:NumberUtils.toDouble(o.toString());
	}
	
	/**
	 * Double转Integer型String
	 * @param o
	 */
	public static String doubleToInteger(Double o) {
		return o == null?"":o.intValue()+"";
	}
	
	/**
	 * 
	 * @Description 判断source 是否有 customerId
	 * @since V1.0
	 * @author baoqf
	 * @date 2017年11月27日 上午10:48:44
	 *
	 * @param source
	 * @param customerId
	 * @return
	 *
	 */
	public static boolean isContains(String source, String customerId) {
		String[] split = source.split(",");
		for (String st : split) {
			if (st.equals(customerId)) {
				return true;
			}
		}
		return false;
	}


	/**
	 * 
	 * @Description 排除list1中与list2的共有元素
	 * @since V1.0
	 * @author baoqf
	 * @param <T>
	 * @date 2017年11月25日 下午4:24:36
	 *
	 * @param list1
	 *            list1
	 * @param list2
	 *            list2
	 * @return
	 *
	 */
	public static  <T> List<T> remove(List<T> list1, List<T> list2) {
		List<T> list = new ArrayList<T>();
		list.addAll(list1);
		if (list2.size() >0) {
			for (T l2 : list2) {
				for (T l1 : list1) {
					if(l1.equals(l2)) {
						list.remove(l1);
					}
				}
			}
		}
		return list;
	}
	
	
	/**
	 * @Description 判断是不是年期
	 * @since V1.0
	 * @author yangzhi
	 * @date 2017年11月27日 上午11:43:40 
	 * @param name
	 * @return
	 */
	public static boolean yearFlagAssert(String name) {
		return "payYears".equals(name)||"coverYears".equals(name)||"annPeriod".equals(name);
	}

	/**
	  * @Description 判断字符串是否都是数字
	  * @since V1.0
	  * @author xuli
	  * @param: [str]
	  * @return: boolean
	  * 2018/8/14
	  *
	*/
	public static boolean isNum(String str){
		Pattern pattern = Pattern.compile(RegexUtils.NUMB);
		char c[] = str.toCharArray();
		for(int i=0;i<c.length;i++){
			Matcher matcher = pattern.matcher(String.valueOf(c[i]));
			if(!matcher.matches()){
				return false;
			}
		}
		return true;
	}

	/**
	 * @Description 提取富文本中的文字
	 * @author yangzhi
	 * @date 2018/9/27
	 * @param inputString
	 * @return java.lang.String
	 */
	public static String Html2Text(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		Pattern p_script;
		Matcher m_script;
		Pattern p_style;
		Matcher m_style;
		Pattern p_html;
		Matcher m_html;
		try {
			// 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
			// 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
			// 定义HTML标签的正则表达式
			String regEx_html = "<[^>]+>";
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签
			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签
			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			// 过滤html标签
			htmlStr = m_html.replaceAll("");
			textStr = htmlStr;
		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}
		//剔除空格行
		textStr = textStr.replaceAll("[ ]+", " ");
		textStr = textStr.replaceAll("(?m)^\\s*$(\\n|\\r\\n)", "");
		// 返回文本字符
		return textStr;

	}

	/**
	 * @description 获取活动id   name + 6位随机字符串  默认是：“hd” + 6位随机字符串
	 * @author yihang
	 * @date 2019/6/5 11:25
	 * @param name
	 * @return String
	 */
	public static String activityId(String name) {
		if(EmptyUtils.isEmpty(name)){
			name = "hd";
		}

		String strIndex = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String strNext = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

		StringBuffer str = new StringBuffer(name);
		int indexA = (int) (51 * Math.random());
		str.append(strIndex.charAt(indexA));

		for (int i = 0; i <= 4; i++) {
			int indexB = (int) (61 * Math.random());
			str.append(strNext.charAt(indexB));
		}

		return str.toString();
	}

	/**
	 * @Description  获取随机字符串 Nonce Str
	 * @Author       王志彪(Will Wang)
	 * @Date         2019/8/13 14:31
	 * @Param        []
	 * @Return       java.lang.String
	 */
	public static String generateNonceStr() {
		return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
	}

	/**
	 * @Description  获取当前时间戳，单位秒
	 * @Author       王志彪(Will Wang)
	 * @Date         2019/8/13 14:32
	 * @Param        []
	 * @Return       java.lang.String
	 */
	public static String getCurrentTimestamp() {
		return System.currentTimeMillis() / 1000 + "";
	}

	/**
	 * @description emoji表情替换
	 * @author yangzhi
	 * @date 2019/10/28 11:23
	 * @param source 原字符串
	 * @param slipStr emoji表情替换成的字符串
	 * @return java.lang.String
	 */
	public static String filterEmoji(String source,String slipStr) {
		if(StringUtils.isNotBlank(source)){
			return source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", slipStr);
		}else{
			return source;
		}
	}

}
