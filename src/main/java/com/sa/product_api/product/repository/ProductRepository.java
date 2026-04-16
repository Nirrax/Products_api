package com.sa.product_api.product.repository;

import com.sa.product_api.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * FROM PRODUCTS WHERE attributes @> CAST(:filter AS jsonb)", nativeQuery = true)
    List<Product> findByAttributes(@Param("filter") String filter);
}
