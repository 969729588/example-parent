package com.milepost.exampleService.rabbitMq.consumer;

import com.milepost.core.rabbitMq.RabbitMqListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * Created by Ruifu Hua on 2020/4/3.
 */
@Component
@ConditionalOnProperty("spring.rabbitmq.host")
public class RabbitMqListenerImpl implements RabbitMqListener {

    @Override
    public void receiveByTenant2AllInstances(String message) {
        System.out.println("【receiveByTenant2AllInstances】:" + message);
    }

    @Override
    public void receiveByService2AllInstances(String message) {
        System.out.println("【receiveByService2AllInstances】:" + message);
    }

    @Override
    public void receiveByInstance(String message) {
        System.out.println("【receiveByInstance】:" + message);
    }

    @Override
    public void receiveByService2OneInstances(String message) {
        System.out.println("【receiveByService2OneInstances】:" + message);
    }
}
