package com.milepost.exampleSingleBoot;

import com.milepost.singleBoot.MilepostSingleBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableAspectJAutoProxy //自定义注解
public class ExampleSingleBootApplication extends MilepostSingleBootApplication {

	public static void main(String[] args) {
		//SpringApplication.run(ExampleSingleBootApplication.class, args);
		run(ExampleSingleBootApplication.class, args);
	}

}
