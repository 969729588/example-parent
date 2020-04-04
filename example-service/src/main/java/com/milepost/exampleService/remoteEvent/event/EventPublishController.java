package com.milepost.exampleService.remoteEvent.event;

import com.milepost.exampleApi.remoteEvent.event.EventObj;
import com.milepost.exampleApi.remoteEvent.event.EventSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.bus.BusProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ruifu Hua on 2020/2/26.
 * 事件发布
 */
@RestController("remoteEventPublish")
@RequestMapping("/remoteEventPublish")
public class EventPublishController {

    //消息总线配置
    @Autowired
    private BusProperties busProperties;

    /**
     * 发布事件
     */
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * 测试事件发布
     *
     * @return
     */
    @GetMapping("testPublishEvent")
    public String testPublishEvent(EventSource eventSource){
        String originService = busProperties.getId();
        //"*:**";//表示所有服务，也可指定具体指，对应于spring.cloud.bus.id配置项，多个服务实例要保证此配置项唯一
        String destinationService = "example-ui111";
        EventObj eventObj = new EventObj(eventSource, originService, destinationService);
        applicationEventPublisher.publishEvent(eventObj);
        return "testPublishEvent";
    }
}
