package com.alwxdecaf.blps_lab.product.dao;

import com.alwxdecaf.blps_lab.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
