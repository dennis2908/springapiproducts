package com.example.live.product;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  @Query("SELECT u FROM Product u ORDER BY u.id LIMIT ?1 OFFSET ?2")
  List<Product> getProduct(Long limit, Long offset);
}
