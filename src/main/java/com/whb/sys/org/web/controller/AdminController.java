package com.whb.sys.org.web.controller;

import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whb.sys.org.model.User;
import com.whb.sys.org.service.IUserService;
import com.whb.sys.org.service.impl.InitServiceImpl;

/**
 * Created by WHB on 2016/7/11.
 */
@Controller
@RequestMapping("/user")
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	@Autowired
	@Qualifier("userService")
	private IUserService userService;

	
    @RequestMapping(value = "/main",method=RequestMethod.GET)
    public String index(){
    	logger.info("欢迎访问本网站");
        return "index"; //自动寻找resources/templates中名字为welcome.vm的文件作为模板，拼装后返
    }
    
    @ResponseBody
    @RequestMapping(value = "/login",method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String login(@RequestBody Map<String,Object> body){
    	logger.info("登录:{}",body.size());
     	String name=(String)body.get("name");
    	String password=(String)body.get("password");
    	User u = userService.findByUserId(name,password);
		JSONObject json = new JSONObject();
		boolean success = false;
		int status  = 200;
		String url=null;
    	if(u!=null){
    		success = true;
    		status=100;
    		url="loginon";
    	}
    	logger.info("登录结果:{}",success);
		json.put("success", success);
		json.put("status", status);
		json.put("url", url);
		return json.toString();
    }
    
    @RequestMapping(value = "/loginon",method=RequestMethod.GET)
    public String loginon(){
    	logger.info("欢迎访问本网站");
        return "ftl/main"; 
    }
}
