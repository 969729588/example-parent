package com.milepost.exampleService.getServiceInEureka.controller;

import com.milepost.api.vo.response.Response;
import com.milepost.api.vo.response.ResponseHelper;
import com.milepost.core.serviceDiscovery.DiscoveryComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Ruifu Hua on 2020/4/4.
 */
@RestController
@RequestMapping("/testDiscoveryComponent")
public class TestDiscoveryComponentController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DiscoveryComponent discoveryComponent;


    @GetMapping("/getAllTenant")
    public Response<List<String>> getAllTenant() {
        Response<List<String>> response;
        try {
            List<String> allTenant = discoveryComponent.getAllTenant();
            response = ResponseHelper.createSuccessResponse(allTenant);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }

        return response;
    }

    @GetMapping("/getAllServiceNameByTenant")
    public Response<List<String>> getAllServiceNameByTenant(String tenant) {
        Response<List<String>> response;
        try {
            List<String> serviceName = discoveryComponent.getAllServiceNameByTenant(tenant);
            response = ResponseHelper.createSuccessResponse(serviceName);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }

        return response;
    }

    @GetMapping("/getAllInstanceIdsByServiceAndTenant")
    public Response<List<String>> getAllInstanceIdsByServiceAndTenant(String serviceName, String tenant) {
        Response<List<String>> response;
        try {
            List<String> instanceIds = discoveryComponent.getAllInstanceIdsByServiceAndTenant(serviceName, tenant);
            response = ResponseHelper.createSuccessResponse(instanceIds);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }

        return response;
    }
}
