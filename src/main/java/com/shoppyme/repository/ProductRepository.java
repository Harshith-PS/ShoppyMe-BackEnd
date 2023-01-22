package com.shoppyme.repository;

import org.springframework.data.repository.CrudRepository;

import com.shoppyme.entity.Product;

public interface ProductRepository extends CrudRepository<Product, String> {

}
