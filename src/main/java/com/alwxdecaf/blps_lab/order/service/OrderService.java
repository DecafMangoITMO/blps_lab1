package com.alwxdecaf.blps_lab.order.service;

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

    public OrderDto create(OrderDto orderDto, long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Product> products = orderDto.getProducts().stream().map(x -> productRepository.findById(x)
            .orElseThrow(() -> new RuntimeException(String.format("Product with id %d not find", x)))).toList();
        
        if (products.stream().anyMatch(product -> product.getQuantity() == 0)){
            OrderMapper.toDto(orderRepository.save(OrderMapper.toEntity(orderDto, user, products, OrderStatus.REJECT)));
            throw new RuntimeException("Some products are out of stock");
        }

        double total = user.getBalance() - products.stream().mapToDouble(Product::getPrice).sum();

        if(total < 0){
            OrderMapper.toDto(orderRepository.save(OrderMapper.toEntity(orderDto, user, products, OrderStatus.REJECT)));
            throw new RuntimeException("You dont have enouth money");
        } 

        user.setBalance(total);
        products.stream().forEach(prod -> prod.setQuantity(prod.getQuantity()-1));

        productRepository.saveAll(products);
        userRepository.save(user);
        
        
        return OrderMapper.toDto(orderRepository.save(OrderMapper.toEntity(orderDto, user, products, OrderStatus.APPROVED)));
    }
}
