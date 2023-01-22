package com.shoppyme.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.shoppyme.entity.PurchaseDetails;
import com.shoppyme.entity.Users;

public interface PurchaseDetailsRepository extends CrudRepository<PurchaseDetails, Integer> {

//	public List<PurchaseDetails> findByEmailId(String emailId);
	public List<PurchaseDetails> findByUser(Users user);
}
