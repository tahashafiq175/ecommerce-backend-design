package com.Internship.ecommerce_backend_design.Controller;

import com.Internship.ecommerce_backend_design.Entity.ProductDetails;
import com.Internship.ecommerce_backend_design.Entity.Products;
import com.Internship.ecommerce_backend_design.Service.ProductDetailService;
import com.Internship.ecommerce_backend_design.Service.ProductService;
import org.apache.coyote.Response;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ProductDetail")
public class ProductDetailController {
    @Autowired
    private ProductDetailService productDetailService;
    @Autowired
    private ProductService productService;
    @GetMapping("/getAllDetails")
    public ResponseEntity<?> getAllProducts()
    {
        List<ProductDetails> allDetails = productDetailService.getAllDetails();
        if(!allDetails.isEmpty()){
            return new ResponseEntity<>(allDetails,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getDetailsById/{Id}")
    public ResponseEntity<?> getAllProductsByCategory(@PathVariable ObjectId Id){
        Optional<ProductDetails> getDetailById = productDetailService.getDetailsbyId(Id);
        if(!getDetailById.isEmpty()) {
            ProductDetails productDetails = getDetailById.get();
            return new ResponseEntity<>(productDetails, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}


