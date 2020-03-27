package com.milepost.exampleService;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import com.milepost.core.lock.SchedulerLock;
import com.milepost.core.lock.SchedulerLockModel;
import com.milepost.service.MilepostServiceApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@EnableEurekaClient
@EnableFeignClients    //service也可能调用其他service
@SpringBootApplication
@EnableAspectJAutoProxy
@EnableDistributedTransaction
@EnableHystrix
public class ExampleServiceApplication extends MilepostServiceApplication {

	public static void main(String[] args) {
		//SpringApplication.run(ExampleServiceApplication.class, args);
		run(ExampleServiceApplication.class, args);
	}

	@SchedulerLock(model = SchedulerLockModel.slave)
	@Scheduled(initialDelay = 10000, fixedDelay = 5000)
	public void testSchedulerLockSlave() {
		System.out.println("所有slave运行这个定时任务");
	}

}
