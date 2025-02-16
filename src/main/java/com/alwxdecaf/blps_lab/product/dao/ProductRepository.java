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
        "(:price is null OR p.price = :price) AND" +
        "(:quantity is null OR p.quantity = :quantity) AND" +
        "(:type is null OR p.type = :type)")
    List<Product> findByFilters(@Param("name") String name, 
                                @Param("price") Double price, 
                                @Param("quantity") Integer quantity, 
                                @Param("type") ProductType type);

}
