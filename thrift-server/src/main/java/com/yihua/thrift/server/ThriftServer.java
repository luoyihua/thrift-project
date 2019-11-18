package com.yihua.thrift.server;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TTransportFactory;

/**
 * @author luoyihua
 * @description TODO
 * @date 2019/11/15 上午10:26
 **/
public abstract class ThriftServer {

    TBinaryProtocol.Factory protocolFactory;
    TTransportFactory transportFactory;

    public void init() {
        protocolFactory = new TBinaryProtocol.Factory();
//        transportFactory = new TTransportFactory();
        // 非阻塞式Server必须使用TFramedTransport，为了方便，所有的server就默认都用TFramedTransport传输方式
        transportFactory = new TFramedTransport.Factory();
    }

    public abstract void start();
}
