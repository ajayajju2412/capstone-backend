package com.upgrad.Eshop.services;


import com.upgrad.Eshop.daos.ProductDAO;
import com.upgrad.Eshop.entities.Product;
import com.upgrad.Eshop.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDAO productDAO;

    @Override
    public Product saveProduct(Product product) {
        return productDAO.save(product);
    }

    //work1
    @Override
    public Product getProduct(int id) throws ProductNotFoundException {
        Product product =  productDAO.findById(id).orElseThrow(
                ()-> new ProductNotFoundException("product not found for id:"+id)
        );
        return product;
    }

    @Override
    public Product updateProduct(int id, Product product) throws ProductNotFoundException {
        Product existingProduct = getProduct(id);
        existingProduct.setAvailableItems(product.getAvailableItems());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setCreated(product.getCreated());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setImageUrl(product.getImageUrl());
        existingProduct.setManufacturer(product.getManufacturer());
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setUpdated(product.getUpdated());

        return productDAO.save(existingProduct);
    }

    @Override
    public String deleteProduct(int id) {
        productDAO.deleteById(id);
        return "Product deleted:"+id;
    }

}
