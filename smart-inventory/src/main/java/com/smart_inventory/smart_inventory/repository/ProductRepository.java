package com.smart_inventory.smart_inventory.repository;

import com.smart_inventory.smart_inventory.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Search product by name
    List<Product> findByNameContainingIgnoreCase(String name);

    // Total inventory value
    @Query("SELECT SUM(p.price * p.quantity) FROM Product p")
    Double getTotalInventoryValue();

    // Low stock products count
    @Query("SELECT COUNT(p) FROM Product p WHERE p.quantity < 5")
    Long getLowStockCount();

    // Total unique categories
    @Query("SELECT COUNT(DISTINCT p.category) FROM Product p")
    Long getTotalCategories();

    // Top products by quantity
    @Query("SELECT p FROM Product p ORDER BY p.quantity DESC")
    List<Product> findTopProducts(Pageable pageable);

}
