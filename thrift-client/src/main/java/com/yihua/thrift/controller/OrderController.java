package com.yihua.thrift.controller;

import com.yihua.thrift.service.OrderThriftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author luoyihua
 * @description TODO
 * @date 2019/11/15 上午10:40
 **/
@RestController
@RequestMapping("/thrift")
public class OrderController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private OrderThriftClient rpcThriftClient;

    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
    public Object order(HttpServletRequest request, HttpServletResponse response, @PathVariable(name="id") long id) {
        try {
            rpcThriftClient.open();
            return rpcThriftClient.getThriftClient().getById(id);
        } catch (Exception e) {
            logger.error("RPC调用失败", e);
            return "error";
        } finally {
            rpcThriftClient.close();
        }
    }

    @RequestMapping(value = "/order/user/{userId}", method = RequestMethod.GET)
    public Object orders(HttpServletRequest request, HttpServletResponse response, @PathVariable(name="userId") long userId) {
        try {
            rpcThriftClient.open();
            return rpcThriftClient.getThriftClient().getByUserId(userId);
        } catch (Exception e) {
            logger.error("RPC调用失败", e);
            return "error";
        } finally {
            rpcThriftClient.close();
        }
    }
}
