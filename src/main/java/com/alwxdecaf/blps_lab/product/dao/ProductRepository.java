package com.alwxdecaf.blps_lab.product.dao;

import com.alwxdecaf.blps_lab.product.model.Product;
import com.alwxdecaf.blps_lab.product.model.ProductType;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE " +
            "(:name is null OR p.name LIKE %:name%) AND" +
            "(:min_price is null OR p.price >= :min_price) AND" +
            "(:max_price is null OR p.price <= :max_price) AND" +
            "(:type is null OR p.type = :type)")
    List<Product> findByFilters(@Param("name") String name,
                                @Param("min_price") Double minPrice,
                                @Param("max_price") Double maxPrice,
                                @Param("type") ProductType type);

}
