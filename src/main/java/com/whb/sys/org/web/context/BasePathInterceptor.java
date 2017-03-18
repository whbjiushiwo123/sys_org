package com.whb.sys.org.web.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 增加spring拦截器，获取HttpServletRequest，拼装绝对路径放在request的attribute属性中，
 * ftl文件中直接${basePath}取值就可以了,静态文件
 * <link href="${basePath}/static/bower_components/bootstrap/dist/css/bootstrap.min.css"
 *  rel="stylesheet"> body中的隐藏表单<input type="hidden" id="base" value="${basePath}" />，
 *  js获取path也可以通过隐藏表单获取var base = $('#base').val();
 *  拦截器代码
 * @author WHB
 *
 */
public class BasePathInterceptor extends HandlerInterceptorAdapter{
	private static Logger logger = LoggerFactory.getLogger(BasePathInterceptor.class);

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	        String scheme = request.getScheme();
	        String servletPath = request.getServletPath();
	        String serverName = request.getServerName();
	        int port = request.getServerPort();
	        String path = request.getContextPath();
	        String basePath = scheme + "://" + serverName + ":" + port + path+"/templates";
	        String showPath = "/templates";
	        request.setAttribute("basePath", basePath);
	        request.setAttribute("showPath", showPath);
	        return true;
	    }

}
