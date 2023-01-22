package com.shoppyme.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.shoppyme.entity.Cart;
import com.shoppyme.entity.CompositeKeyCart;

public interface CartRepository extends CrudRepository<Cart, CompositeKeyCart> {
	public List<Cart> findByEmailId(String emailId);
}
