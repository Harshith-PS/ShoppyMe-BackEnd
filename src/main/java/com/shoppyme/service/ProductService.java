package com.shoppyme.service;

import java.util.List;

import org.json.simple.JSONObject;

import com.shoppyme.dto.CartDTO;
import com.shoppyme.dto.CategoryDTO;
import com.shoppyme.dto.ProductDTO;
import com.shoppyme.dto.PurchaseDetailsDTO;
import com.shoppyme.dto.RatingDTO;
import com.shoppyme.dto.UsersDTO;
import com.shoppyme.exception.ShoppyMeException;

public interface ProductService {
	
	public List<ProductDTO> getProducts() throws ShoppyMeException;
	public List<CategoryDTO> getCategories() throws ShoppyMeException;
	public List<PurchaseDetailsDTO> getPurchaseDetails(String emailId) throws ShoppyMeException;
	public List<CartDTO> getCartByEmailId(String emailId) throws ShoppyMeException;
	public ProductDTO getProductsByProductId(String productId) throws ShoppyMeException;
	public JSONObject validateCredentials(UsersDTO userDTO) throws ShoppyMeException;
	public Boolean addUser(UsersDTO user) throws ShoppyMeException;
	public Boolean addProductCart(CartDTO cartDTO) throws ShoppyMeException;
	public Boolean addRating(RatingDTO ratingDTO) throws ShoppyMeException;
	public Boolean updateCart(CartDTO cartDTO) throws ShoppyMeException;
	public Boolean deleteProductFromCart(String emailId, String productId) throws ShoppyMeException;
	
}
