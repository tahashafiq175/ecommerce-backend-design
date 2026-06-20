package com.Internship.ecommerce_backend_design.Controller;

import com.Internship.ecommerce_backend_design.Entity.Products;
import com.Internship.ecommerce_backend_design.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("Get")
    public List<Products> getCategories(){
        return productService.getAllCategories();
    }
    @PostMapping
    public ResponseEntity addProduct(@RequestBody Products product){
        productService.saveCategory(product);
        return ResponseEntity.ok().build();
    }
}
