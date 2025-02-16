package com.alwxdecaf.blps_lab.order.controller;

import com.alwxdecaf.blps_lab.order.dto.CreateOrderDto;
import com.alwxdecaf.blps_lab.order.dto.OrderDto;
import com.alwxdecaf.blps_lab.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<OrderDto> getByCustomerId(@RequestHeader(name = "X-User-Id") long customerId) {
        return orderService.getByCustomerId(customerId);
    }

    @PostMapping
    public List<OrderDto> create(@Valid @RequestBody CreateOrderDto createOrderDto, @RequestHeader(name = "X-User-Id") long userId){
        return orderService.create(createOrderDto, userId);
    }

    @PatchMapping("/{order_id}")
    public OrderDto answer(
            @PathVariable long order_id,
            @RequestParam(value = "order_status", required = false) String orderStatus,
            @RequestHeader(name = "X-User-Id") long customerId) {
        return orderService.answer(order_id, orderStatus, customerId);
    }
}
