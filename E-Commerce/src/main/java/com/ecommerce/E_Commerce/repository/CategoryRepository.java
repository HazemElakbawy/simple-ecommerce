package com.ecommerce.E_Commerce.repository;

import com.ecommerce.E_Commerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  Optional<Category> findByName(String name);
}