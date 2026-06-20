package com.Internship.ecommerce_backend_design.Repository;

import com.Internship.ecommerce_backend_design.Entity.ProductDetails;
import com.Internship.ecommerce_backend_design.Entity.Products;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Products, ObjectId> {
    Products findByCategory(String category);

    void deleteByCategory(String category);
}
