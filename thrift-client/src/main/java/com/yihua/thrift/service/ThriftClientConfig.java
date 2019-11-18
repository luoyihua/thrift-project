package com.yihua.thrift.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author luoyihua
 * @description TODO
 * @date 2019/11/15 上午10:40
 **/
@Configuration
public class ThriftClientConfig {
    @Value("${thrift.host}")
    private String host;
    @Value("${thrift.port}")
    private int port;

    @Bean(initMethod = "init")
    public OrderThriftClient rpcThriftClient() {
        OrderThriftClient rpcThriftClient = new OrderThriftClient();
        rpcThriftClient.setHost(host);
        rpcThriftClient.setPort(port);
        return rpcThriftClient;
    }
}