package com.Internship.ecommerce_backend_design.Controller;


import com.Internship.ecommerce_backend_design.Entity.ProductDetails;
import com.Internship.ecommerce_backend_design.Service.ProductDetailService;
import com.Internship.ecommerce_backend_design.Service.ProductService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addProductDetails(
            @RequestBody ProductDetails productDetails,
            @PathVariable String Category
    ){
        productDetailService.saveProductDetails(productDetails);

        productService.saveProduct(productDetails,Category);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Product Added Successfully");
    }
    @DeleteMapping("/DeleteDetail/{Id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteProductDetails(
            @PathVariable ObjectId Id
    ){
        Optional<ProductDetails> detailsbyId =
                productDetailService.getDetailsbyId(Id);
        if(detailsbyId.isPresent()){
            productService.deleteDetail(Id);
            productDetailService.deleteUser(Id);
            return ResponseEntity
                    .ok()
                    .body("Product Deleted Successfully");
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Product Not Found");
    }
    @PutMapping("/ChangeDetail/{Id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> changeProductDetails(
            @PathVariable ObjectId Id,
            @RequestBody ProductDetails changeDetails)
    {
        Optional<ProductDetails> optionalProductDetails =
                productDetailService.getDetailsbyId(Id);
        if(optionalProductDetails.isPresent()){
            ProductDetails product =
                    optionalProductDetails.get();
            if(changeDetails.getCategory()!=null &&
                    !changeDetails.getCategory().isBlank()){

                product.setCategory(
                        changeDetails.getCategory()
                );
            }if(changeDetails.getName()!=null &&
                    !changeDetails.getName().isBlank()){

                product.setName(
                        changeDetails.getName()
                );
            }if(changeDetails.getDescription()!=null &&
                    !changeDetails.getDescription().isBlank()){

                product.setDescription(
                        changeDetails.getDescription()
                );
            }
            if(changeDetails.getPrice()!=null){

                product.setPrice(
                        changeDetails.getPrice()
                );
            }
            if(changeDetails.getStock()!=null){

                product.setStock(
                        changeDetails.getStock()
                );
            }
            productDetailService.saveProductDetails(product);
            return ResponseEntity
                    .ok()
                    .body("Product Updated Successfully");

        }return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Product Not Found");
    }

}