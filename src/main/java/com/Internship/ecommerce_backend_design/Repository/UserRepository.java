package com.Internship.ecommerce_backend_design.Repository;

import com.Internship.ecommerce_backend_design.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String username);
}
