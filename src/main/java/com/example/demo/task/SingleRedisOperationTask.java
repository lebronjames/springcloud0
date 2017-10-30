package com.example.demo.task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.example.demo.constants.SingleRedisConstants;
import com.example.demo.model.SingleRedisOperationModel;

public class SingleRedisOperationTask implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(SingleRedisOperationTask.class);
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	public static ExecutorService executorService = Executors.newFixedThreadPool(100);
	
	@Override
	public void run() {
		logger.info("启动 SingleRedisOperationTask");
		Thread.currentThread().setName("SingleRedisOperationTaskThread");
		this.start();
	}
	
	public void start() {
		BlockingQueue<SingleRedisOperationModel> blockingQueue = new LinkedBlockingQueue<SingleRedisOperationModel>(SingleRedisConstants.QUEUE_NUM);
		Producer producer = new Producer(blockingQueue);
		Consumer consumer = new Consumer(blockingQueue);
		new Thread(producer).start();
		new Thread(consumer).start();
	}

	class Producer implements Runnable{
		
		private BlockingQueue<SingleRedisOperationModel> blockingQueue;
		
		Producer(BlockingQueue<SingleRedisOperationModel> blockingQueue){
			this.blockingQueue = blockingQueue;
		}

		int i = 10;
		
		@Override
		public void run() {
			while(i>0) {
				try {
					long beginTime = System.currentTimeMillis();
					SingleRedisOperationModel model = new SingleRedisOperationModel();
					for(int i=0;i<10000;i++) {
						model.setKey(SingleRedisConstants.REDIS_TASK_PREFIX+i);
						model.setValue(SingleRedisConstants.REDIS_STRING_TEST_VALUE);
						blockingQueue.put(model);
					}
					long endTime = System.currentTimeMillis();
					logger.info(Thread.currentThread().getName()+"--------第"+(11-i)+"轮，向阻塞队列批量插入10000次，耗时：{}ms",endTime-beginTime);
					i--;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	class Consumer implements Runnable{
		
		private BlockingQueue<SingleRedisOperationModel> blockingQueue;
		
		Consumer(BlockingQueue<SingleRedisOperationModel> blockingQueue){
			this.blockingQueue = blockingQueue;
		}

		@Override
		public void run() {
			while(true) {
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						SingleRedisOperationModel model = null;
						try {
							model = blockingQueue.take();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						stringRedisTemplate.opsForValue().set(model.getKey(), model.getValue().toString());
						logger.info(Thread.currentThread().getName()+"####插入数据："+model.getKey()+"成功！！！");
					}
					
				});
			}
		}
		
	}
}
