package com.milepost.exampleApi.remoteEvent.event;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;

/**
 * Created by Ruifu Hua on 2020/2/26.
 * 事件对象，要求所有服务使用相同的类，全类名要相同，
 */
public class EventObj extends RemoteApplicationEvent {

    public EventObj() {
    }

    public EventObj(Object source, String originService, String destinationService) {
        super(source, originService, destinationService);

    }

    public EventObj(Object source, String originService) {
        super(source, originService);
    }

}
