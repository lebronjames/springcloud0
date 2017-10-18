package com.example.demo.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class MyHttpSessionListener implements HttpSessionListener {
	private static final Logger logger = LoggerFactory.getLogger(MyHttpSessionListener.class);
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		//服务器一创建httpSession，就向list集合中添加HttpSession
        HttpSession httpSession = se.getSession();
        ServletContext application = httpSession.getServletContext();
        List<HttpSession> list = (List<HttpSession>) application.getAttribute("list");
        logger.info("session创建添加了{}",httpSession.getId());
        list.add(httpSession);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		logger.info("Http Session 被销毁");
	}

}
