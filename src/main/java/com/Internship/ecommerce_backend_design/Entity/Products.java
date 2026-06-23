package com.Internship.ecommerce_backend_design.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Product")
public class Products {
    private String category;
    @Id
    private ObjectId Id;
    @DBRef
    private List<ProductDetails> productDetailsList=new ArrayList<>();
    String image;
}
