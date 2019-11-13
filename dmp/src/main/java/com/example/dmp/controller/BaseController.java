package com.example.dmp.controller;

import com.example.dmp.bean.ResultBean;
import com.example.dmp.bean.ResultMongoPageBean;
import com.example.dmp.enums.ResultStatusEnum;
import com.example.dmp.util.EmptyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName:    BaseController
 * @Description:  基础Contorller
 * @Author:       王志彪(Will Wang)
 * @Date:         2019/11/13 10:44
 * @Version:      V1.0
 * @Since:        V1.0
 */
public class BaseController {

	@Autowired
	protected HttpServletRequest request;

	@Autowired
	protected HttpServletResponse response;


	/**
	 * 请求成功返回数据
	 * @param code 状态码
	 * @param msg  信息
	 * @param obj  对象
	 * @return
	 */
	protected <T> ResultBean<T> success(int code, String msg, T obj){
		//如果为空对象data直接返回null
		if(EmptyUtils.isEmpty(obj)) {
			obj = null;
		}
		return new ResultBean<T>(code, msg, obj);

	}
	
	/**
	 * 请求成功返回数据
	 * @param obj
	 * @return
	 */
	protected <T> ResultBean<T> success(T obj){
		//如果为空对象data直接返回null
		if(EmptyUtils.isEmpty(obj)) {
			obj = null;
		}
		return new ResultBean<T>(ResultStatusEnum.SUCCESS.getStatus(), ResultStatusEnum.SUCCESS.getMsg(), obj);
		
	}

	/**
	 * 未绑定手机号码
	 * @return
	 */
	protected <T> ResultBean<T> notBind(){
		return new ResultBean<T>(ResultStatusEnum.NOTBIND.getStatus(), ResultStatusEnum.NOTBIND.getMsg());

	}
	
	/**
	 * 请求成功返回
	 * @return
	 */
	protected <T> ResultBean<T> success(){
		
		return new ResultBean<T>(ResultStatusEnum.SUCCESS.getStatus(), ResultStatusEnum.SUCCESS.getMsg());
		
	}
	/**
	 * 请求成功返回带分页的数据(spring data mongo分页)
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	protected <T> ResultMongoPageBean<T> successMongoPage(Page page){
		//如果为空对象data直接返回null
		if(EmptyUtils.isEmpty(page)) {
			page = null;
		}
		return new ResultMongoPageBean<T>(ResultStatusEnum.SUCCESS.getStatus(), ResultStatusEnum.SUCCESS.getMsg(),page);
	}
	
	/**
	 * 请求成功返回自定义消息
	 * @return
	 */
	protected <T> ResultBean<T> success(String msg){
		
		return new ResultBean<T>(ResultStatusEnum.SUCCESS.getStatus(), msg);
		
	}
	
	/**
	 * 请求成功返回数据和自定义消息
	 * @param obj
	 * @return
	 */
	protected <T> ResultBean<T> success(String msg,T obj ){
		//如果为空对象data直接返回null
		if(EmptyUtils.isEmpty(obj)) {
			obj = null;
		}
		return new ResultBean<T>(ResultStatusEnum.SUCCESS.getStatus(), msg,obj);
		
	}

	/**
	 * 请求成功返回Id
	 * @param id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	protected ResultBean<Map> successReturnId(String id){
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", id);
		return new ResultBean<Map>(ResultStatusEnum.SUCCESS.getStatus(), ResultStatusEnum.SUCCESS.getMsg(),map);
		
	}
	
	
	/**
	 * 请求错误返回数据
	 * @return
	 */
	protected <T> ResultBean<T> fail(int status , String msg,T obj ){
		//如果为空对象data直接返回null
		if(EmptyUtils.isEmpty(obj)) {
			obj = null;
		}
		return new ResultBean<T>(status, msg,obj);

	}


	/**
	 * 请求错误返回数据
	 * @return
	 */
	protected <T> ResultBean<T> fail(int status , String msg ){
		return new ResultBean<T>(status, msg,null);

	}
	
	
	/**
	 * 请求错误返回自定义消息
	 * @return
	 */
	protected <T> ResultBean<T> fail(String msg){
		
		return new ResultBean<T>(ResultStatusEnum.FAIL.getStatus(), msg);
		
	}
	
	/**
	 * 请求错误返回
	 * @return
	 */
	protected <T> ResultBean<T> fail(){
		
		return new ResultBean<T>(ResultStatusEnum.FAIL.getStatus(), ResultStatusEnum.FAIL.getMsg());
		
	}
	
	/**
	 * 请求超时返回自定义消息
	 * @return
	 */
	protected <T> ResultBean<T> timeout(){
		
		return new ResultBean<T>(ResultStatusEnum.TIMEOUT.getStatus(), "请求超时");
		
	}


}
