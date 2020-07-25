package com.hughes.repos;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hughes.entities.OrderInfo;

public interface OrderingRepository extends MongoRepository<OrderInfo, String>{

}
