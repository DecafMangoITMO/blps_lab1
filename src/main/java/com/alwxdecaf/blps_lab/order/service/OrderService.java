package com.alwxdecaf.blps_lab.order.service;

import com.alwxdecaf.blps_lab.order.dao.OrderRepository;
import com.alwxdecaf.blps_lab.order.dto.CreateOrderDto;
import com.alwxdecaf.blps_lab.order.dto.OrderDto;
import com.alwxdecaf.blps_lab.order.model.Order;
import com.alwxdecaf.blps_lab.order.model.OrderStatus;
import com.alwxdecaf.blps_lab.order.utils.OrderMapper;
import com.alwxdecaf.blps_lab.product.dao.ProductRepository;
import com.alwxdecaf.blps_lab.product.model.Product;
import com.alwxdecaf.blps_lab.user.dao.UserRepository;
import com.alwxdecaf.blps_lab.user.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public List<OrderDto> getByCustomerId(long customerId) {
        if (customerId <= 0)
            throw new RuntimeException("Customer id must be greater than zero");

        return orderRepository.findALlByCustomerId(customerId).stream()
                .map(OrderMapper::toDto)
                .toList();
    }

    public List<OrderDto> create(CreateOrderDto createOrderDto, long userId) {
        // Products in order
        HashMap<Long, Integer> prodAndCount = createOrderDto.getProducts();
        // Info about products in repo
        List<Product> products = prodAndCount.keySet().stream().map(productId -> productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException(String.format("Product with id %d does not exist", productId)))).toList();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException(String.format("User with id %d does not exist", userId)));

        if (products.stream().anyMatch(product -> product.getQuantity() < prodAndCount.get(product.getId()))) {
            throw new RuntimeException("Some products are out of stock");
        }

        double total = user.getBalance() - products.stream().mapToDouble(prod -> prod.getPrice() * prodAndCount.get(prod.getId())).sum();

        if (total < 0) {
            throw new RuntimeException("User does not have enough balance");
        }

        List<OrderDto> ordersDto = new LinkedList<>();

        products.forEach(
                prod -> ordersDto.add(
                        OrderMapper.toDto(orderRepository.save(
                                        OrderMapper.toEntity(
                                                createOrderDto,
                                                user,
                                                prod.getCustomer(),
                                                OrderStatus.PENDING,
                                                prodAndCount.get(prod.getId()),
                                                prod)
                                )
                        )
                )
        );

        user.setBalance(total);
        userRepository.save(user);

        return ordersDto;
    }

    public OrderDto answer(long orderId, String action, long customerId) {
        if (action == null)
            throw new RuntimeException("action can not be null");
        OrderStatus orderStatus = switch (action.toLowerCase()) {
            case "approve" -> OrderStatus.APPROVED;
            case "reject" -> OrderStatus.REJECTED;
            case "pending" -> OrderStatus.PENDING;
            default -> throw new RuntimeException("Unknown action: " + action);
        };

        // todo Во второй лабораторной это убрать
        User customer = userRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException(String.format("Customer with id %d does not exist", customerId)));

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException(String.format("Order with id %d does not exist", orderId)));

        Product product = order.getProduct();

        if (order.getCustomer().getId() != customer.getId())
            throw new RuntimeException(String.format(
                    "Customer with id %d does not have an access to order with id %d",
                    customer.getId(),
                    orderId
            ));

        if (orderStatus == OrderStatus.PENDING && order.getStatus() != OrderStatus.PENDING)
            throw new RuntimeException("Order is already answered");

        if (order.getStatus() == OrderStatus.APPROVED && orderStatus == OrderStatus.APPROVED)
            throw new RuntimeException("Order is already approved");

        if (order.getStatus() == OrderStatus.REJECTED && orderStatus == OrderStatus.REJECTED)
            throw new RuntimeException("Order is already rejected");

        if (orderStatus == OrderStatus.APPROVED) {
            if (order.getProduct().getQuantity() < order.getQuantity())
                throw new RuntimeException("Some products are out of stock");
            product.setQuantity(product.getQuantity() - order.getQuantity());
        } else if (orderStatus == OrderStatus.REJECTED) {
            if (order.getStatus() == OrderStatus.APPROVED)
                product.setQuantity(product.getQuantity() + order.getQuantity());
        }

        order.setStatus(orderStatus);
        order = orderRepository.save(order);
        productRepository.save(product);

        productRepository.save(product);
        return OrderMapper.toDto(order);
    }

}
