package com.tw.gic.bootcamp.solid.order;

import com.tw.gic.bootcamp.solid.error.ServiceException;

import java.util.Collection;

public interface OrderService {

    Order getOrder(int orderId) throws ServiceException;

    String addOder(Order order) throws ServiceException;

    Collection<Order> getAllOrders(OrderStatus status);

    void updateOrderStatus(int orderId, OrderStatus status) throws ServiceException;

    String dispatchProduct(Integer orderId) throws ServiceException;
}
