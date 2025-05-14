package com.ecommerce.E_Commerce.repository;

import com.ecommerce.E_Commerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}