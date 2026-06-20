package com.Internship.ecommerce_backend_design.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection ="ProductDetails")
public class ProductDetails {
    @Id
    private ObjectId id;
    private String name;
    private int price;
    private String category;
    private String description;
    private List<String> images;
    private int stock;
}
