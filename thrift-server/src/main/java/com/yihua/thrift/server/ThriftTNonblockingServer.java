package com.yihua.thrift.server;

import com.yihua.thrift.api.OrderService;
import com.yihua.thrift.service.OrderServiceImpl;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author luoyihua
 * @description TODO
 * @date 2019/11/18 上午11:25
 **/
@Component
public class ThriftTNonblockingServer extends ThriftServer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private ThriftServerConfig thriftServerConfig;

    @Override
    public void start() {
        OrderService.Processor processor = new OrderService.Processor<OrderService.Iface>( orderService );
        super.init();

        try {
            TNonblockingServerSocket serverTransport = new TNonblockingServerSocket(thriftServerConfig.getPort());
            TNonblockingServer.Args args = new TNonblockingServer.Args(serverTransport).
                    transportFactory(transportFactory).
                    protocolFactory(protocolFactory).
                    processor(processor);

            TServer server = new TNonblockingServer(args);
            logger.info("thrift服务[TNonblockingServer]启动成功, 端口={}", thriftServerConfig.getPort());
            server.serve();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("thrift服务启动失败", ex);
        }
    }
}
