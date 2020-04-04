package com.milepost.exampleApi.remoteEvent.event;

import java.io.Serializable;

/**
 * Created by Ruifu Hua on 2020/2/26.
 * 事件源，与普通事件驱动模型的区别是实现Serializable接口，
 * 因为发送到RabbitMQ时需要序列化，
 * 同样要求所有服务使用相同的类，全类名要相同，
 */
public class EventSource implements Serializable {
    private String username;
    private String pwd;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "EventSource{" +
                "username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }

    public EventSource(String username, String pwd) {
        this.username = username;
        this.pwd = pwd;
    }
}
