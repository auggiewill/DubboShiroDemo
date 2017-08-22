package com.dongtong;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.dongtong.component.BeanPostProcessor;

/**
 * 启动器
 * @author auggie
 *
 */
//临时关闭定时任务触发
//@EnableScheduling
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringCloudApplication
public class RantApplication {

    public static void main(String[] args) {
    	
    	ConfigurableApplicationContext context = new SpringApplicationBuilder(RantApplication.class).headless(false).run(args);
    	BeanPostProcessor processor = context.getBean(BeanPostProcessor.class);
    	processor.init(); 
    }

}
