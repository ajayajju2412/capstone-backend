package com.upgrad.Eshop.daos;

import com.upgrad.Eshop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("productDAO")
public interface ProductDAO extends JpaRepository<Product, Integer> {

}
