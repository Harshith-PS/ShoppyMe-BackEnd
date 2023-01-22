
package com.shoppyme.entity;

import java.io.Serializable;


public class CompositeKeyCart implements Serializable {
	
	private String productId;
	private String emailId;
	
	public CompositeKeyCart() {
		
	}
	
	public CompositeKeyCart(String productId, String emailId) {
		super();
		this.productId = productId;
		this.emailId = emailId;
	}
	
	public String getProduct() {
		return productId;
	}
	public void setProduct(String productId) {
		this.productId = productId;
	}
	public String getUser() {
		return emailId;
	}
	public void setUser(String emailId) {
		this.emailId = emailId;
	}

}
