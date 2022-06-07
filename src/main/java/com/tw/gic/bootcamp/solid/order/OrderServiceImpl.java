package com.tw.gic.bootcamp.solid.order;

import com.tw.gic.bootcamp.solid.product.InventoryService;
import com.tw.gic.bootcamp.solid.product.Product;
import com.tw.gic.bootcamp.solid.product.ProductStatus;
import com.tw.gic.bootcamp.solid.shipping.ShippingService;
import com.tw.gic.bootcamp.solid.error.ServiceException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private InventoryService inventoryService;

    private ShippingService shippingService;

    private OrderList orderList;

    public OrderServiceImpl(InventoryService inventoryService, ShippingService shippingService, OrderList orderList) {
        this.inventoryService = inventoryService;
        this.shippingService = shippingService;
        this.orderList = orderList;
    }

    @Override
    public Collection<Order> getAllOrders(OrderStatus status) {
        if (status == null) {
            return orderList.getAll();
        }
        return orderList.getAll().stream().filter(o -> o.getStatus().equals(status)).collect(Collectors.toList());
    }

    @Override
    public Order getOrder(int orderId) throws ServiceException {
        Order order = orderList.getOrder(orderId);
        if (order == null) {
            throw new ServiceException("No such order id " + orderId);
        }
        return order;
    }

    @Override
    public String addOder(Order order) throws ServiceException {
        Order existingOrder = orderList.getOrder(order.getId());
        if (existingOrder!=null){
            return "order ID is already present. "+order.getId();
        }

        if (inventoryService.getProduct(order.getProductId())==null){
            throw new ServiceException("No such product with ID "+order.getProductId());
        }

        if (order.getAddress()==null){
            throw new ServiceException("Address can't be empty");
        }

        orderList.addOrder(order);
        return null;
    }


    @Override
    public void updateOrderStatus(int orderId, OrderStatus status) throws ServiceException {
        if (status == OrderStatus.CANCELLED) {
            orderCancelled(orderId);
        } else if (status == OrderStatus.PROCESSING) {
            markAsProcessing(orderId);
        } else if (status == OrderStatus.SHIPPED) {
            orderShipped(orderId);
        } else if (status == OrderStatus.DELIVERED) {
            orderCompleted(orderId);
        }
    }

    public void markAsProcessing(int orderId) throws ServiceException {
        Order order = orderList.getOrder(orderId);
        Product product = inventoryService.getProduct(order.getProductId());
        product.setProductStatus(ProductStatus.SOLD);
        order.setStatus(OrderStatus.PROCESSING);
    }

    public void orderCancelled(int orderId) throws ServiceException {
        Order order = orderList.getOrder(orderId);
        Product product = inventoryService.getProduct(order.getProductId());
        product.setProductStatus(ProductStatus.AVAILABLE);
        order.setStatus(OrderStatus.CANCELLED);
    }

    public void orderShipped(int orderId) throws ServiceException {
        Order order = orderList.getOrder(orderId);
        Product product = inventoryService.getProduct(order.getProductId());
        product.setProductStatus(ProductStatus.SOLD);
        order.setStatus(OrderStatus.SHIPPED);
    }

    public void orderCompleted(int orderId) throws ServiceException {
        Order order = orderList.getOrder(orderId);
        Product product = inventoryService.getProduct(order.getProductId());
        product.setProductStatus(ProductStatus.SOLD);
        order.setStatus(OrderStatus.DELIVERED);
    }

    @Override
    public String dispatchProduct(Integer orderId) throws ServiceException {
        Order order = getOrder(orderId);
        int productId = order.getProductId();

        Product product = inventoryService.getProduct(productId);
        if (product == null) {
            throw new ServiceException("no such product id " + productId);
        }
        if (product.getProductStatus() == ProductStatus.AVAILABLE) {
            return "product " + product.getSerialNumber() + " is not yet sold";
        }

        return shippingService.dispatchProduct(productId, orderId, order.getCustomerId(), order.getAddress(),product.getProductType());
    }
}
