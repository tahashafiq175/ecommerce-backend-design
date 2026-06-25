package com.Internship.ecommerce_backend_design.Service;

import com.Internship.ecommerce_backend_design.Entity.ProductDetails;
import com.Internship.ecommerce_backend_design.Repository.ProductDetailRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductDetailService {
    @Autowired
    private ProductDetailRepository productDetailRepository;
    @Autowired
    private ProductService productService;
    public List<ProductDetails> getAllDetails() {
        List<ProductDetails> all = productDetailRepository.findAll();
        return all;
    }

    public Optional<ProductDetails> getDetailsbyId(ObjectId Id) {
        Optional<ProductDetails> byId = productDetailRepository.findAllById(Id);
        if(!byId.isEmpty()){
            return byId;
        }
        return null;
    }

    public void saveProductDetails(ProductDetails productDetails) {
        productDetailRepository.save(productDetails);
    }

    public void deleteUser(ObjectId id) {
        productDetailRepository.deleteById(id);
    }
}
