package com.yihua.thrift;

import com.yihua.thrift.server.ThriftServer;
import com.yihua.thrift.server.ThriftTSimpleServer;
import com.yihua.thrift.server.ThriftTThreadedSelectorServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ThriftServerApplication {

    private static ThriftServer thriftServer;

    public static void main(String[] args ){
        ApplicationContext context = SpringApplication.run(ThriftServerApplication.class, args);
        startPRCServer(context);
    }

    private static void startPRCServer(ApplicationContext context) {
        try {
            thriftServer = context.getBean(ThriftTSimpleServer.class);
            thriftServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
