package com.Internship.ecommerce_backend_design.Controller;

import com.Internship.ecommerce_backend_design.Entity.ProductDetails;
import com.Internship.ecommerce_backend_design.Entity.Products;
import com.Internship.ecommerce_backend_design.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("Get")
    public ResponseEntity<?> getAllCategories(){
        List<Products> allCategories = productService.getAllCategories();
        if (allCategories.size()>0){
            return new ResponseEntity<>(allCategories, HttpStatus.OK);
        }
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }


   @GetMapping("/GetById/{Category}")
   public ResponseEntity<?> getProductByCategories(@PathVariable String Category){
       List<ProductDetails> byCategory = productService.getByCategory(Category);
       if(!byCategory.isEmpty()){
           return new ResponseEntity<>(byCategory, HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }


   /*     It useless     */

    @PostMapping
    public ResponseEntity addProduct(@RequestBody Products product){
        productService.saveCategory(product);
        return ResponseEntity.ok().build();
    }
}
