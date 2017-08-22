package com.dongtong.task;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dongtong.core.utils.DateUtils;
import com.dongtong.crawler.GrapRunner;

@Component
@Configurable(autowire = Autowire.BY_TYPE)
public class RantTask {
	
	private static final Logger logger = LoggerFactory.getLogger(RantTask.class);

	@Scheduled(fixedRate = 1000 * 60 * 4)
//	@Scheduled(cron="0 3 * * * ?")
	public void testTask(){
		logger.info("test taks every 4 min");
	}
	
	@Scheduled(cron="0 0 3 * * ?")
//	@Scheduled(fixedRate = 1000 * 60 * 400)
	public void rantTaks(){
		logger.info("begin task");
		try {
			new Thread(GrapRunner.getGrap(DateUtils.formatDate(new Date(), "MM-dd"))).start();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error("check error!");
		}
        
	}
}
