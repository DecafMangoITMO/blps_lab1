package com.alwxdecaf.blps_lab.product.controller;

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
    public List<ProductDto> getAll(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "min_price", required = false) Double minPrice,
            @RequestParam(name = "max_price", required = false) Double maxPrice,
            @RequestParam(name = "type", required = false) String typeStr
    ) {
        return productService.getAll(name, minPrice, maxPrice, typeStr);
    }

    @PostMapping
    public ProductDto publish(@Valid @RequestBody PublishProductDto publishProductDto,
                              @RequestHeader(name = "X-User-Id") long customerId) {
        return productService.publish(publishProductDto, customerId);
    }

}
