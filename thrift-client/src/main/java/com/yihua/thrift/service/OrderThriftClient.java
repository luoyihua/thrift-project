package com.yihua.thrift.service;

import com.yihua.thrift.api.OrderService;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author luoyihua
 * @description TODO
 * @date 2019/11/15 上午10:39
 **/
public class OrderThriftClient {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private OrderService.Client client;
    private TBinaryProtocol protocol;
    private TTransport transport;
    private String host;
    private int port;

    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }
    public int getPort() {
        return port;
    }
    public void setPort(int port) {
        this.port = port;
    }

    public void init() {
        transport = new TFramedTransport(new TSocket(host, port));
        protocol = new TBinaryProtocol(transport);
        client = new OrderService.Client(protocol);
    }

    public OrderService.Client getThriftClient() {
        return client;
    }

    public void open() throws TTransportException {
        logger.info("开启thrift服务连接");
        transport.open();
    }

    public void close() {
        logger.info("关闭thrift服务连接");
        transport.close();
    }
}
