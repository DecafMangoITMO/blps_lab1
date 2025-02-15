package com.alwxdecaf.blps_lab.order.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alwxdecaf.blps_lab.order.dao.OrderRepository;
import com.alwxdecaf.blps_lab.order.dto.OrderDto;
import com.alwxdecaf.blps_lab.order.model.OrderStatus;
import com.alwxdecaf.blps_lab.order.utils.OrderMapper;
import com.alwxdecaf.blps_lab.product.dao.ProductRepository;
import com.alwxdecaf.blps_lab.product.model.Product;
import com.alwxdecaf.blps_lab.user.dao.UserRepository;
import com.alwxdecaf.blps_lab.user.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public List<Long> create(OrderDto orderDto, long userId){

        // Products in order
        HashMap<Long, Integer> prodAndCount = orderDto.getProducts();
        // Info about products in repo
        List<Product> products = prodAndCount.keySet().stream().map(x -> productRepository.findById(x)
            .orElseThrow(() -> new RuntimeException(String.format("Product with id %d not find", x)))).toList();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        double total = user.getBalance() - products.stream().mapToDouble(prod -> prod.getPrice() * prodAndCount.get(prod.getId())).sum();

        if(total < 0){
            products.stream().forEach(
            prod -> orderRepository.save(
                OrderMapper.toEntity(
                    orderDto, 
                    user, 
                    prod.getCustomer(),
                    OrderStatus.REJECT,
                    prodAndCount.get(prod.getId()),
                    prod)));
            throw new RuntimeException("You dont have enouth money");
        }

        if (products.stream().anyMatch(product -> product.getQuantity() < prodAndCount.get(product.getId()))){
            products.stream().forEach(
            prod -> orderRepository.save(
                OrderMapper.toEntity(
                    orderDto, 
                    user, 
                    prod.getCustomer(),
                    OrderStatus.REJECT,
                    prodAndCount.get(prod.getId()),
                    prod)));
            throw new RuntimeException("Some products are out of stock");
        }

        List<Long> orders = new LinkedList<>();

        products.stream().forEach(
            prod -> orders.add(orderRepository.save(
                    OrderMapper.toEntity(
                        orderDto, 
                        user, 
                        prod.getCustomer(),
                        OrderStatus.PENDING,
                        prodAndCount.get(prod.getId()),
                        prod))
                    .getId())
        );


        user.setBalance(total);
        products.stream().forEach(prod -> prod.setQuantity(prod.getQuantity() - prodAndCount.get(prod.getId())));

        productRepository.saveAll(products);
        userRepository.save(user);
        
        return orders;
    }
}
