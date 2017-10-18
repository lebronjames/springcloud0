package com.example.demo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.listener.MyHttpSessionListener;

/**
 * 设置全局filter
* 
* 项目名称:  springCloud0
* 包:       com.example.demo.filter   
* 类名称:    WholeFilter.java
* 类描述:    
* 创建人:    yzx
* 创建时间:  2017年10月17日
 */
@WebFilter(filterName="myFilter",urlPatterns="/*")
public class WholeFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(WholeFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("过滤器初始化");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.info("执行过滤操作");
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		logger.info("过滤器销毁");
	}

}
