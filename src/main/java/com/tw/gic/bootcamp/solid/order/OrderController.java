package com.tw.gic.bootcamp.solid.order;

import com.tw.gic.bootcamp.solid.error.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @GetMapping()
    public ResponseEntity getAllOrders(@RequestParam(required = false) OrderStatus orderStatus) {
        return ResponseEntity.ok(orderService.getAllOrders(orderStatus));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity getOrder(@PathVariable int orderId) throws ServiceException {
        return ResponseEntity.ok(orderService.getOrder(orderId));
    }

    @PostMapping()
    public ResponseEntity addOrder(@RequestBody Order order) throws ServiceException {
        return ResponseEntity.ok(orderService.addOder(order));
    }


    @PutMapping("/{orderId}")
    public ResponseEntity updateOrderStatus(@PathVariable int orderId, @RequestBody OrderStatus orderStatus) throws ServiceException {
        orderService.updateOrderStatus(orderId, orderStatus);
        return ResponseEntity.ok("Order " + orderId + " updated to " + orderStatus);
    }


    @PostMapping("/{orderId}/dispatch/")
    public ResponseEntity courierProduct(@PathVariable int orderId) throws ServiceException {
        String err = orderService.shipProduct(orderId);
        orderService.updateOrderStatus(orderId, OrderStatus.SHIPPED);
        return err == null ? ResponseEntity.ok("Successfully shipped order: " + orderId) : ResponseEntity.internalServerError().body(err);
    }

}
