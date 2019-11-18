package com.yihua.thrift.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author luoyihua
 * @description TODO
 * @date 2019/11/18 上午11:04
 **/
@Configuration
public class ThriftServerConfig {

    @Value("${thrift.port}")
    private int port;
    @Value("${thrift.minWorkerThreads}")
    private int minThreads;
    @Value("${thrift.maxWorkerThreads}")
    private int maxThreads;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getMinThreads() {
        return minThreads;
    }

    public void setMinThreads(int minThreads) {
        this.minThreads = minThreads;
    }

    public int getMaxThreads() {
        return maxThreads;
    }

    public void setMaxThreads(int maxThreads) {
        this.maxThreads = maxThreads;
    }
}
