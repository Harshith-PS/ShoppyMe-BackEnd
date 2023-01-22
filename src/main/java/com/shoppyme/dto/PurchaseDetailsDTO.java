package com.shoppyme.dto;

import java.time.LocalDateTime;

public class PurchaseDetailsDTO {

	private Integer purchaseId ;
	private UsersDTO user;
	private ProductDTO productDTO;
	private Integer quantityPurchased;
	private LocalDateTime dateOfPurchase;
	
	
	public Integer getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}
	public UsersDTO getUser() {
		return user;
	}
	public void setUser(UsersDTO user) {
		this.user = user;
	}
	public ProductDTO getProductDTO() {
		return productDTO;
	}
	public void setProductDTO(ProductDTO productDTO) {
		this.productDTO = productDTO;
	}
	public Integer getQuantityPurchased() {
		return quantityPurchased;
	}
	public void setQuantityPurchased(Integer quantityPurchased) {
		this.quantityPurchased = quantityPurchased;
	}
	public LocalDateTime getDateOfPurchase() {
		return dateOfPurchase;
	}
	public void setDateOfPurchase(LocalDateTime dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
		
}
