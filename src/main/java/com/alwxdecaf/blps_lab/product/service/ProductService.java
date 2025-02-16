package com.alwxdecaf.blps_lab.product.service;

import com.alwxdecaf.blps_lab.product.dao.ProductRepository;
import com.alwxdecaf.blps_lab.product.dto.FiltersDto;
import com.alwxdecaf.blps_lab.product.dto.ProductDto;
import com.alwxdecaf.blps_lab.product.dto.PublishProductDto;
import com.alwxdecaf.blps_lab.product.model.Product;
import com.alwxdecaf.blps_lab.product.util.ProductMapper;
import com.alwxdecaf.blps_lab.user.dao.UserRepository;
import com.alwxdecaf.blps_lab.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    public List<ProductDto> getAll() {
        return productRepository.findAll().stream()
                .map(ProductMapper::toDto)
                .toList();
    }

    public ProductDto publish(PublishProductDto publishProductDto, long customerId) {
        User customer = userRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return ProductMapper.toDto(productRepository.save(ProductMapper.toEntity(publishProductDto, customer)));
    }

    public List<ProductDto> getFilteredProducts(FiltersDto filters) {
        List<Product> products = productRepository.findByFilters(filters.getName(), filters.getPrice(), filters.getQuantity(), filters.getType());
        return products.stream().map(ProductMapper::toDto).toList();
    }

}
