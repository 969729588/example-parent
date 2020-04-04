package com.milepost.exampleService.remoteEvent.event;

import com.milepost.exampleApi.remoteEvent.event.EventObj;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Ruifu Hua on 2020/4/4.
 * 配置实现远程应用事件的事件对象
 */
@Configuration
@RemoteApplicationEventScan(basePackageClasses = EventObj.class)
public class BusConfig {

}
