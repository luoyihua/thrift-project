package com.yihua.thrift.server;

import com.yihua.thrift.api.OrderService;
import com.yihua.thrift.service.OrderServiceImpl;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author luoyihua
 * @description TODO
 * @date 2019/11/15 下午1:17
 **/
@Component
public class ThriftTThreadedSelectorServer extends ThriftServer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ExecutorService pool;

    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private ThriftServerConfig thriftServerConfig;

    @Override
    public void init() {
        super.init();
        pool = Executors.newFixedThreadPool(10);
    }

    @Override
    public void start() {
        OrderService.Processor processor = new OrderService.Processor<OrderService.Iface>( orderService );
        super.init();

        try {
            TNonblockingServerSocket serverTransport = new TNonblockingServerSocket(thriftServerConfig.getPort());
            TThreadedSelectorServer.Args args = new TThreadedSelectorServer.Args(serverTransport).
                    transportFactory(transportFactory).
                    protocolFactory(protocolFactory).
                    processor(processor).
                    // 可不用设置，默认selectorThreads=2 executorService是Executors.newFixedThreadPool(5)
                    selectorThreads(2).
                    executorService(pool);

            TServer server = new TThreadedSelectorServer(args);
            logger.info("thrift服务[TThreadedSelectorServer]启动成功, 端口={}", thriftServerConfig.getPort());
            server.serve();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("thrift服务启动失败", ex);
        }
    }
}
