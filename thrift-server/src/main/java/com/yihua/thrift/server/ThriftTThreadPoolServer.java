package com.yihua.thrift.server;

import com.yihua.thrift.api.OrderService;
import com.yihua.thrift.service.OrderServiceImpl;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author luoyihua
 * @description TODO
 * @date 2019/11/15 下午1:11
 **/
@Component
public class ThriftTThreadPoolServer extends ThriftServer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private ThriftServerConfig thriftServerConfig;

    @Override
    public void start() {
        OrderService.Processor processor = new OrderService.Processor<OrderService.Iface>(orderService);
        super.init();

        try {
            TServerTransport serverTransport = new TServerSocket(thriftServerConfig.getPort());
            TThreadPoolServer.Args args = new TThreadPoolServer.Args(serverTransport).
                    transportFactory(transportFactory).
                    protocolFactory(protocolFactory).
                    processor(processor).
                    maxWorkerThreads(thriftServerConfig.getMinThreads()).
                    maxWorkerThreads(thriftServerConfig.getMaxThreads());

            TServer server = new TThreadPoolServer(args);
            logger.info("thrift服务[TThreadPoolServer]启动成功, 端口={}", thriftServerConfig.getPort());
            server.serve();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("thrift服务启动失败", ex);
        }
    }
}
