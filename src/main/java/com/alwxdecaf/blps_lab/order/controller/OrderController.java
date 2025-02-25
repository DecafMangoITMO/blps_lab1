package com.alwxdecaf.blps_lab.order.controller;

import com.alwxdecaf.blps_lab.order.dto.CreateOrderDto;
import com.alwxdecaf.blps_lab.order.dto.OrderDto;
import com.alwxdecaf.blps_lab.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<OrderDto> getByCustomerId(@RequestHeader(name = "X-User-Id") long customerId) {
        log.info("Get all orders to customer with id: {}", customerId);
        return orderService.getByCustomerId(customerId);
    }

    @PostMapping
    public List<OrderDto> create(@Valid @RequestBody CreateOrderDto createOrderDto, @RequestHeader(name = "X-User-Id") long userId){
        log.info("Create order {} by user with id: {}", createOrderDto, userId);
        return orderService.create(createOrderDto, userId);
    }

    @PatchMapping("/{order_id}")
    public OrderDto answer(
            @PathVariable long orderId,
            @RequestParam(value = "order_status", required = false) String orderStatus,
            @RequestHeader(name = "X-User-Id") long customerId) {
        log.info("Answer with status to order with id {} by customer with id {}", orderStatus, orderId, customerId);
        return orderService.answer(orderId, orderStatus, customerId);
    }
}
