package com.example.demo.listener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class MyServletContextListener implements ServletContextListener {

	private static final Logger logger = LoggerFactory.getLogger(MyServletContextListener.class);
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("ServletContex初始化");
		logger.info("初始化信息:{},时间:{}",sce.getServletContext().getServerInfo(),new Date());
        //创建一个list集合来存放所有的httpSession,必须要为这个集合加锁（多线程访问）
        List<HttpSession> list = Collections.synchronizedList(new ArrayList<HttpSession>());
        //servletContext添加这个集合
        sce.getServletContext().setAttribute("list", list);
        
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				logger.info("开始扫描了。。。");
				for(Iterator<HttpSession> it = list.iterator();it.hasNext();) {
					HttpSession session = it.next();
					//判断存活时间是否大于60s
					long l = System.currentTimeMillis() - session.getCreationTime();
					if(l > 60000) {
						//失效,从集合中删除httpSesion
                        logger.info("session失效了：{}", session.getId());
						session.invalidate();
						it.remove();//Iterator对象的remove方法是迭代过程中删除元素的唯一方法
					}
				}
			}
			//delay： 几秒后开始执行（单位：毫秒）
            //period：  执行的间隔时间（单位：毫秒）
		}, 2000, 5000);
        
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("ServletContex销毁");
	}

}
