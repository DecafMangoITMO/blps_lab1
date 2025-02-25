package com.alwxdecaf.blps_lab.product.controller;

import com.alwxdecaf.blps_lab.product.dto.ProductDto;
import com.alwxdecaf.blps_lab.product.dto.PublishProductDto;
import com.alwxdecaf.blps_lab.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDto> getAll(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "min_price", required = false) Double minPrice,
            @RequestParam(name = "max_price", required = false) Double maxPrice,
            @RequestParam(name = "type", required = false) String typeStr
    ) {
        log.info("Get all products with name {}, minPrice {}, maxPrice {}, type {}", name, minPrice, maxPrice, typeStr);
        return productService.getAll(name, minPrice, maxPrice, typeStr);
    }

    @PostMapping
    public ProductDto publish(@Valid @RequestBody PublishProductDto publishProductDto,
                              @RequestHeader(name = "X-User-Id") long customerId) {
        log.info("Publish product {} by customer with id: {}", customerId);
        return productService.publish(publishProductDto, customerId);
    }

}
