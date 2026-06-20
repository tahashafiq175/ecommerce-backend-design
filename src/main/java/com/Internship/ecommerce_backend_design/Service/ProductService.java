package com.Internship.ecommerce_backend_design.Service;

import com.Internship.ecommerce_backend_design.Entity.ProductDetails;
import com.Internship.ecommerce_backend_design.Entity.Products;
import com.Internship.ecommerce_backend_design.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Products> getAllCategories(){
        return productRepository.findAll();
    }

    public void saveProduct(ProductDetails productDetails, String category) {
        Products byCategory = productRepository.findByCategory(category);
        if(byCategory==null){
            byCategory=new Products();
            byCategory.setCategory(category);
        }
        byCategory.getProductDetailsList().add(productDetails);
        productRepository.save(byCategory);
    }
    @Transactional
    public void deleteDetail(String name,ProductDetails productDetail){
        Products product=productRepository.findByCategory(name);
        product.getProductDetailsList().remove(productDetail);
        int size=product.getProductDetailsList().size();
        if(size==0){
            productRepository.deleteByCategory(product.getCategory());
            return;
        }
        productRepository.save(product);
    }

    public void saveCategory(Products product) {
        productRepository.save(product);
    }
}
