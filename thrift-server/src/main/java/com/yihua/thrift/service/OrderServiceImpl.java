package com.yihua.thrift.service;

import com.yihua.thrift.api.Order;
import com.yihua.thrift.api.OrderService;
import org.apache.thrift.TException;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luoyihua
 * @description TODO
 * @date 2019/11/18 上午10:42
 **/
@Service
public class OrderServiceImpl implements OrderService.Iface {
    @Override
    public Order getById(long orderId) throws TException {
        Order record = new Order();
        record.setOrderId(orderId);
        record.setTitle("test");
        record.setTradeTime(4567890L);
        record.setUrl("djkl");
        record.setUserId(11);
        return record;
    }

    @Override
    public List<Order> getByUserId(long userId) throws TException {
        Order record1 = new Order();
        record1.setOrderId(1L);
        record1.setTitle("test1");
        record1.setTradeTime(4567890L);
        record1.setUrl("djkl1");
        record1.setUserId(userId);

        Order record2 = new Order();
        record2.setOrderId(2L);
        record2.setTitle("test2");
        record2.setTradeTime(14567890L);
        record2.setUrl("djkl2");
        record2.setUserId(userId);
        List<Order> orderList = Lists.list(record1, record2);
        return orderList;
    }
}
