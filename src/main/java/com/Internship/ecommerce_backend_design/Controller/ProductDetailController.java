package com.Internship.ecommerce_backend_design.Controller;

import com.Internship.ecommerce_backend_design.Entity.ProductDetails;
import com.Internship.ecommerce_backend_design.Entity.Products;
import com.Internship.ecommerce_backend_design.Service.ProductDetailService;
import com.Internship.ecommerce_backend_design.Service.ProductService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ProductDetail")
public class ProductDetailController {
    @Autowired
    private ProductDetailService productDetailService;
    @Autowired
    private ProductService productService;
    @GetMapping("/getAllDetails")
    public List<ProductDetails> getAllProducts(){
        return productDetailService.getAllDetails();
    }
    @GetMapping("/getAllDetails/{category}")
    public List<ProductDetails> getAllProductsByCategory(@PathVariable String category){
        List<ProductDetails> allDetails = productDetailService.getAllDetails(category);
        return allDetails;
    }
    @PostMapping("/postDetail/{category}")
    public ResponseEntity addProductDetails(@RequestBody ProductDetails productDetails, @PathVariable String category){
        productDetailService.saveUserDetails(productDetails);
        productService.saveProduct(productDetails,category);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/DeleteDetail/{Id}")
    public ResponseEntity deleteProductDetails(@PathVariable ObjectId Id){
        productDetailService.deleteUser(Id);
        return ResponseEntity.ok().build();
    }
}
