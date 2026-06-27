package com.Internship.ecommerce_backend_design.Service;

import com.Internship.ecommerce_backend_design.Entity.ProductDetails;
import com.Internship.ecommerce_backend_design.Entity.Products;
import com.Internship.ecommerce_backend_design.Repository.ProductRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    private ProductDetailService productDetailService;

    public List<Products> getAllCategories(){
        return productRepository.findAll();
    }

    public void saveProduct(ProductDetails productDetails, String category) {
        Products byCategory = productRepository.findByCategory(category);
        if(byCategory==null){
            byCategory=new Products();
            byCategory.setCategory(category);
            byCategory.setImage(category+"jpg");
        }
        byCategory.getProductDetailsList().add(productDetails);
        productRepository.save(byCategory);
    }
    public List<ProductDetails> getByCategory(String category){
        Products byCategory = productRepository.findByCategory(category);
        List<ProductDetails> productDetailsList = byCategory.getProductDetailsList();
       return productDetailsList;
    }
    @Transactional
    public void deleteDetail(ObjectId id){
        Optional<ProductDetails> detailsbyId = productDetailService.getDetailsbyId(id);
        if(detailsbyId.isPresent()){
            ProductDetails productDetails = detailsbyId.get();
            Products product=productRepository.findByCategory(productDetails.getCategory());
            product.getProductDetailsList().remove(productDetails);
            int size=product.getProductDetailsList().size();
            if(size==0){
                productRepository.deleteByCategory(product.getCategory());
                return;
            }
            productRepository.save(product);
        }
    }

    public void saveCategory(Products product) {
        productRepository.save(product);
    }

}
