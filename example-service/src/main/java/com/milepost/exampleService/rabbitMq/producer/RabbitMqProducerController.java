package com.milepost.exampleService.rabbitMq.producer;

import com.milepost.api.vo.response.Response;
import com.milepost.api.vo.response.ResponseHelper;
import com.milepost.core.rabbitMq.RabbitMqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rabbitMqProducer")
public class RabbitMqProducerController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RabbitMqService rabbitMqService;

    private String success = "发送成功";

    @GetMapping("/send2AllInstancesOfTheTenant")
    public Response<String> send2AllInstancesOfTheTenant(String tenant, String message) {
        Response<String> response;
        try {
            rabbitMqService.send2AllInstancesOfTheTenant(tenant, message);
            response = ResponseHelper.createSuccessResponse(success);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }

        return response;
    }

    @GetMapping("/send2AllInstancesOfCurrTenant")
    public Response<String> send2AllInstancesOfCurrTenant(String message) {
        Response<String> response;
        try {
            rabbitMqService.send2AllInstancesOfCurrTenant(message);
            response = ResponseHelper.createSuccessResponse(success);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }

        return response;
    }

    @GetMapping("/send2AllInstancesOfTheService")
    public Response<String> send2AllInstancesOfTheService(String serviceName, String message) {
        Response<String> response;
        try {
            rabbitMqService.send2AllInstancesOfTheService(serviceName, message);
            response = ResponseHelper.createSuccessResponse(success);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }

        return response;
    }


    @GetMapping("/send2AllInstancesOfCurrService")
    public Response<String> send2AllInstancesOfCurrService(String message) {
        Response<String> response;
        try {
            rabbitMqService.send2AllInstancesOfCurrService(message);
            response = ResponseHelper.createSuccessResponse(success);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }

        return response;
    }

    @GetMapping("/send2OneInstancesOfTheService")
    public Response<String> send2OneInstancesOfTheService(String serviceName, String message) {
        Response<String> response;
        try {
            rabbitMqService.send2OneInstancesOfTheService(serviceName, message);
            response = ResponseHelper.createSuccessResponse(success);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }

        return response;
    }

    @GetMapping("/send2TheInstance")
    public Response<String> send2TheInstance(String instanceId, String message) {
        Response<String> response;
        try {
            rabbitMqService.send2TheInstance(instanceId, message);
            response = ResponseHelper.createSuccessResponse(success);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }

        return response;
    }
}
