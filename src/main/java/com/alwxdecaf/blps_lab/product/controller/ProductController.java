package com.alwxdecaf.blps_lab.product.controller;

import com.alwxdecaf.blps_lab.product.dto.FiltersDto;
import com.alwxdecaf.blps_lab.product.dto.ProductDto;
import com.alwxdecaf.blps_lab.product.dto.PublishProductDto;
import com.alwxdecaf.blps_lab.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDto> getAll() {
        return productService.getAll();
    }

    @PostMapping
    public ProductDto publish(@Valid @RequestBody PublishProductDto publishProductDto,
                              @RequestHeader(name = "X-User-Id") long customerId) {
        return productService.publish(publishProductDto, customerId);
    }

    @GetMapping("/filtered")
    public List<ProductDto> getFilteredProducts(@Valid @ModelAttribute FiltersDto filters) {
        return productService.getFilteredProducts(filters);
    }

}
