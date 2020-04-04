package com.milepost.exampleUi.remoteEvent.event;

import com.milepost.exampleApi.remoteEvent.event.EventObj;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Ruifu Hua on 2020/4/4.
 */
@Configuration
@RemoteApplicationEventScan(basePackageClasses = EventObj.class)
public class BusConfig {
}
