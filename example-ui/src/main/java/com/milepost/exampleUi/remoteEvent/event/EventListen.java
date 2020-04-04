package com.milepost.exampleUi.remoteEvent.event;

import com.milepost.exampleApi.remoteEvent.event.EventObj;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Created by Ruifu Hua on 2020/2/26.
 * 事件监听
 */
@Component("remoteEvent")
public class EventListen {

    /**
     * 监听EventObj事件
     * @param eventObj
     */
    @EventListener
    public void listenerForCustomEvent(EventObj eventObj){
        System.out.println(eventObj.getSource());
        System.out.println(eventObj.getOriginService());
        System.out.println(eventObj.getDestinationService());
    }
}
