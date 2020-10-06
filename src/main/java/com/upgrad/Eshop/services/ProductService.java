package com.upgrad.Eshop.services;

import com.upgrad.Eshop.entities.Product;
import com.upgrad.Eshop.exceptions.ProductNotFoundException;

import java.util.Optional;

public interface ProductService {
    public Product saveProduct(Product product);
    public Product getProduct(int id) throws ProductNotFoundException;
    public Product updateProduct(int id, Product product) throws ProductNotFoundException;
    public String deleteProduct(int id);
}
