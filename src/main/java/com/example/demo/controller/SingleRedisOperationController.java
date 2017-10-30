package com.example.demo.controller;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constants.SingleRedisConstants;
import com.example.demo.task.SingleRedisOperationTask;

import io.swagger.annotations.Api;

/**
 * Redis 单机常用操作
* 
* 项目名称:  springCloud0
* 包:       com.example.demo.controller   
* 类名称:    SingleRedisOperationController.java
* 类描述:    
* 创建人:    yzx 
* 创建时间:  2017年10月26日
 */
@RestController
@RequestMapping("/redis/operation")
@Api(value = "redisOperation", description = "Redis单机操作管理")
public class SingleRedisOperationController {
	
	private static final Logger logger = LoggerFactory.getLogger(SingleRedisOperationController.class);
	public static LinkedBlockingDeque<Map<String,Object>> blockingQueue = new LinkedBlockingDeque<Map<String,Object>>(10000);
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	
	@GetMapping("/get")
	public String getRedis() {
		stringRedisTemplate.opsForValue().set(SingleRedisConstants.REDIS_PREFIX+"v5", SingleRedisConstants.REDIS_STRING_TEST_VALUE);
		return stringRedisTemplate.opsForValue().get("v5");
	}
	
	@GetMapping("/getOne")
	public void getOne() {
		long beginTime = System.nanoTime();
		stringRedisTemplate.opsForValue().get(SingleRedisConstants.REDIS_PREFIX+"v5");
		long endTime = System.nanoTime();
		logger.info("Redis 单条记录查询耗时：{}ns,{}ms",endTime-beginTime,(endTime-beginTime)/1000000);
	}
	
	@GetMapping("/batchGet")
	public void batchGet() {
		long beginTime = System.currentTimeMillis();
		for(int i=0;i<10000;i++) {
			stringRedisTemplate.opsForValue().get(SingleRedisConstants.REDIS_PREFIX+"v5");
		}
		long endTime = System.currentTimeMillis();
		logger.info("Redis 批量记录查询10000次，耗时：{}ms",endTime-beginTime);
	}
	
	/**
	 * 10个线程读10000数据
	 */
	@GetMapping("/threadPoolGet")
	public void threadPoolGet() {
		long beginTime = System.currentTimeMillis();
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		for(int i=0;i<10000;i++) {
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					stringRedisTemplate.opsForValue().get(SingleRedisConstants.REDIS_PREFIX+"v5");
					logger.info(Thread.currentThread().getName()+"=======已查询："+SingleRedisConstants.REDIS_PREFIX+"v5");
				}
			});
		}
		executorService.shutdown();
		long endTime = System.currentTimeMillis();
		logger.info("Redis 10个线程读10000数据，耗时：{}ms",endTime-beginTime);
	}
	
	@GetMapping("/set")
	public void setRedisString() {
		long beginTime = System.nanoTime();
		stringRedisTemplate.opsForValue().set(SingleRedisConstants.REDIS_PREFIX+new Random().nextInt(100), SingleRedisConstants.REDIS_STRING_TEST_VALUE);
		long endTime = System.nanoTime();
		logger.info("Redis 单值插入耗时：{}ns,{}ms",endTime-beginTime,(endTime-beginTime)/1000000);
	}
	
	@GetMapping("/batchSet")
	public void batchSetRedisString() {
		long beginTime = System.currentTimeMillis();
		for(int i=0;i<10000;i++) {
			stringRedisTemplate.opsForValue().set(SingleRedisConstants.REDIS_PREFIX+i, SingleRedisConstants.REDIS_STRING_TEST_VALUE);
		}
		long endTime = System.currentTimeMillis();
		logger.info("Redis 批量插入10000次，耗时：{}ms",endTime-beginTime);
	}
	
	/**
	 * 10个线程写100000数据
	 */
	@GetMapping("/threadPoolSet")
	public void threadPoolSet() {
		long beginTime = System.currentTimeMillis();
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		for(int i=0;i<100000;i++) {
			final int j = i;
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					stringRedisTemplate.opsForValue().set(SingleRedisConstants.REDIS_THREAD_PREFIX+j, 
							SingleRedisConstants.REDIS_STRING_TEST_VALUE);
					logger.info(Thread.currentThread().getName()+"=======已插入："+SingleRedisConstants.REDIS_THREAD_PREFIX+j);
				}
			});
		}
		executorService.shutdown();
		long endTime = System.currentTimeMillis();
		logger.info("Redis 10个线程跑100000数据，耗时：{}ms",endTime-beginTime);
	}
	
	/**
	 * 100个线程跑100000数据
	 */
	@GetMapping("/threadPoolPlusSet")
	public void threadPoolPlusSet() {
		long beginTime = System.currentTimeMillis();
		ExecutorService executorService = Executors.newFixedThreadPool(100);
		for(int i=0;i<100000;i++) {
			final int j = i;
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					stringRedisTemplate.opsForValue().set(SingleRedisConstants.REDIS_THREAD_PREFIX+j, 
							SingleRedisConstants.REDIS_STRING_TEST_VALUE);
					logger.info(Thread.currentThread().getName()+"=======已插入："+SingleRedisConstants.REDIS_THREAD_PREFIX+j);
				}
			});
		}
		executorService.shutdown();
		long endTime = System.currentTimeMillis();
		logger.info("Redis 100个线程跑100000数据，耗时：{}ms",endTime-beginTime);
	}
	
	@GetMapping("/queue")
	public void blockingQueueStart() {
		new Thread(new SingleRedisOperationTask()).start();
	}
}
