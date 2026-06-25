package com.Internship.ecommerce_backend_design.Controller;

import com.Internship.ecommerce_backend_design.Entity.ProductDetails;
import com.Internship.ecommerce_backend_design.Repository.ProductDetailRepository;
import com.Internship.ecommerce_backend_design.Service.ProductDetailService;
import com.Internship.ecommerce_backend_design.Service.ProductService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/admin")
@RestController
public class AdminController {
    @Autowired
    private ProductDetailService productDetailService;
    @Autowired
    private ProductService productService;
    @PostMapping("/postDetail/{Category}")
    public ResponseEntity<?> addProductDetails(
            @RequestBody ProductDetails productDetails,
            @PathVariable String Category){
        productDetailService.saveProductDetails(productDetails);
        productService.saveProduct(productDetails,Category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/DeleteDetail/{Id}")
    public ResponseEntity<?> deleteProductDetails(@PathVariable ObjectId Id){
        Optional<ProductDetails> detailsbyId = productDetailService.getDetailsbyId(Id);
        if(detailsbyId.isPresent()){
            productDetailService.deleteUser(Id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/ChangeDetail/{Id}")
    public ResponseEntity<?> changeUserDetails(@PathVariable ObjectId Id,@RequestBody ProductDetails changeDetails){
        Optional<ProductDetails> optionalProductDetails = productDetailService.getDetailsbyId(Id);
       if(optionalProductDetails.isPresent()) {
           ProductDetails byId=optionalProductDetails.get();
           if (changeDetails.getCategory() != null &&
                   !changeDetails.getCategory().isBlank()) {
               byId.setCategory(changeDetails.getCategory());
           }

           if (changeDetails.getName() != null &&
                   !changeDetails.getName().isBlank()) {
               byId.setName(changeDetails.getName());
           }

           if (changeDetails.getDescription() != null &&
                   !changeDetails.getDescription().isBlank()) {
               byId.setDescription(changeDetails.getDescription());
           }

           if (changeDetails.getPrice() != null) {
               byId.setPrice(changeDetails.getPrice());
           }

           if (changeDetails.getStock() != null) {
               byId.setStock(changeDetails.getStock());
           }

           productDetailService.saveProductDetails(byId);
           return new ResponseEntity<>(HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
