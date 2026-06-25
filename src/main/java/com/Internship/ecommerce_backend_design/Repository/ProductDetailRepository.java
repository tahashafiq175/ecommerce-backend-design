package com.Internship.ecommerce_backend_design.Repository;

import com.Internship.ecommerce_backend_design.Entity.ProductDetails;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ProductDetailRepository extends MongoRepository<ProductDetails, ObjectId> {


    List<ProductDetails> findAllByCategory(String category);

    Optional<ProductDetails> findAllById(Object id);
}
