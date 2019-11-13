package com.example.dmp.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName:    RegexUtils
 * @Description:  正则表达式工具类
 * @Author:       王志彪(Will Wang)
 * @Date:         2019/11/13 16:38
 * @Version:      V1.0
 * @Since:        V1.0
 */
public class RegexUtils {

	/**
	 * 数字
	 */
	public static  final String NUMB = "[0-9]";

	/**
	 * 字母
	 */
	public static final String ZIMU = "[A-Z]";

	/**
	 * 手机号码
	 */
	public static final String MOBILE = "^((13[0-9])|(14[0-9])|(15[0-9])|(16[0-9])|(17[0-9])|(18[0-9])|(19[0-9]))\\d{8}$";

	/**
	 * 6位数字
	 */
	public static final String NUMB6 = "\\d{6}";

	/**
	 * 6位字符
	 */
	public static final String STR6 = "[A-Za-z0-9]{6}";

	//邮箱
	public static final String EMAIL = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";

	/**
	 * 固定电话 允许格式：***-********、***-********-*** 、***********
	 */
	public static final String FIXED_PHONE = "(0\\d{2,3}-)?\\d{6,11}(-\\d{2,4})?";
	
	//public static final String PASSWORD = "/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,30}$/";
	//复杂（同时包含数字，字母，特殊符号）"^^(?![a-zA-z]+$)(?!\\d+$)(?![!@#$%^&*_-]+$)(?![a-zA-z\\d]+$)(?![a-zA-z!@#$%^&*_-]+$)(?![\\d!@#$%^&*_-]+$)[a-zA-Z\\d!@#$%^&*_-]{6,30}$"
	//简单（只包含数字或字母） "^(?:\\d+|[a-zA-Z]+|[!@#$%^&*]+){6,30}$"
	//中级（包含字母和数字） "^(?![a-zA-z]+$)(?!\\d+$)(?![!@#$%^&*]+$)[a-zA-Z\\d!@#$%^&*]{6,30}$"
	//密码复杂度
	public static final String PASSWORD = "^(?![a-zA-z]+$)(?!\\d+$)(?![!@#$%^&*]+$)[a-zA-Z\\d!@#$%^&*_-]{8,30}$";
	//"^(?=.*\\d)(?=.*[a-zA-Z])(?=.*[`~!@#$%^&*()_+|\\=\\-/*'\":;,.?<>]).*$";
	
	//public static final String EMAIL_MOBILE = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*|^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0,5-9]))\\d{8}$";//邮箱/手机号码

	/**
	 * 身份证 非精确匹配 正则
	 */
	public static final String ID_CARD_V = "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)";
	
	/**
	 * 身份证精确匹配 正则
	 */
	public static final String ID_CARD = "(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$)";
	
	/**
	 * 日期匹配
	 */
	public static final String DATA = "^[1-9]\\d{3}(\\d|年|-|/)(0[1-9]|1[0-2]|[1-9])(\\d|月|-|/)(0[1-9]|[1-2][0-9]|3[0-1]||[1-9])$";

	/**
	 * 昵称匹配
	 */
	public static final String NAME = "^([\u4e00-\u9fa5_a-zA-Z0-9]){1,20}$";

	/**
	 * 微信号匹配
	 */
	public static final String WX_NUM = "^[a-zA-Z]{1}[a-zA-Z0-9_-]{5,19}$";


	/**
	 * 字母、数字、下划线、中横杠
	 */
	public static final String CHANNEL_ID = "^[0-9a-zA-Z_-]{1,}$";
	/**
	 * 微信号或者手机号
	 */
	public static final String WX_PHONE_NUM = "("+WX_NUM+")|("+MOBILE+")";

	/**
	 * 微信号或者手机号
	 */
	public static final String URL = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
	
	public static void main(String[] args) {
//		Pattern pattern = Pattern.compile(NAME);
//		Matcher matcher = pattern.matcher("晃q一发地好的一个人的生商业街");  //true
//		boolean b = matcher.find();
//		System.out.println(b);
		
		Pattern pattern = Pattern.compile(NAME);
		Matcher matcher = pattern.matcher("");
		boolean find = matcher.find();
		System.out.println(find);
	}
	
	
}
