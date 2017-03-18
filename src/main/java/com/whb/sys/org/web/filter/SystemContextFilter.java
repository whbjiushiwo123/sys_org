package com.whb.sys.org.web.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.whb.sys.org.basic.model.SystemRequest;
import com.whb.sys.org.basic.model.SystemRequestHolder;


/**
 * 使用注解标注过滤器
 * @WebFilter将一个实现了javax.servlet.Filter 接口的类定义为过滤器
 * 属性filterName声明过滤器的名称
 * 属性urlPatterns指定要过滤的URL模式
 * 也可以使用属性value来声明（指定要过滤的URL模式是必选的属性）
 * @author WHB
 *
 */
@WebFilter(filterName="pagerFilter",urlPatterns="/*")
public class SystemContextFilter implements Filter{
	private final static Logger logger = LoggerFactory.getLogger(SystemContextFilter.class);
	private Integer pageSize;

	public void destroy() {
		logger.info("destroy");
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		Integer offset = 0;
		try {
			if(req.getParameter("pager.offset")!=null){
				offset = Integer.parseInt(req.getParameter("pager.offset"));
			}
			logger.info("offset:{}",offset);
		} catch (NumberFormatException e) {
			
		}
		try {
			SystemRequest systemRequest =  new SystemRequest();
			systemRequest.setOrder(req.getParameter("order"));
			systemRequest.setPageOffset(offset);
			systemRequest.setPageSize(pageSize);
			systemRequest.setRequest((HttpServletRequest)req);
			systemRequest.setSort(req.getParameter("sort"));
			SystemRequestHolder.initRequestHolder(systemRequest);
			chain.doFilter(req,resp);
			logger.info("systemRequest:{}",systemRequest.toString());
		} finally {
			SystemRequestHolder.remove();
		}
	}

	public void init(FilterConfig cfg) throws ServletException {
		try {
			pageSize = Integer.parseInt(cfg.getInitParameter("pageSize"));
		} catch (NumberFormatException e) {
			pageSize = 15;
		}
		logger.info("pageSize:{}",pageSize);
	}

}
