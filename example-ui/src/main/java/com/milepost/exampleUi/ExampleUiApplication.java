package com.milepost.exampleUi;

import com.milepost.ui.MilepostUiApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
@EnableAspectJAutoProxy //自定义注解
@EnableHystrix
public class ExampleUiApplication extends MilepostUiApplication {

	public static void main(String[] args) {
		//SpringApplication.run(ExampleUiApplication.class, args);
		run(ExampleUiApplication.class, args);
	}

}
