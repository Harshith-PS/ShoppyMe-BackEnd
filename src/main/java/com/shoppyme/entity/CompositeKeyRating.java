package com.shoppyme.entity;

import java.io.Serializable;

public class CompositeKeyRating implements Serializable{

	private String productId;
	private String emailId;
	
	public CompositeKeyRating() {
		
	}
	
	public CompositeKeyRating(String productId, String emailId) {
		super();
		this.productId = productId;
		this.emailId = emailId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	
}
