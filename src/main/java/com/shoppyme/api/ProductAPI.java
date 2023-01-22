package com.shoppyme.api;

import java.util.List;

import javax.validation.Valid;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppyme.dto.CartDTO;
import com.shoppyme.dto.CategoryDTO;
import com.shoppyme.dto.ProductDTO;
import com.shoppyme.dto.PurchaseDetailsDTO;
import com.shoppyme.dto.RatingDTO;
import com.shoppyme.dto.UsersDTO;
import com.shoppyme.exception.ShoppyMeException;
import com.shoppyme.service.ProductServiceImpl;

@RestController
@Validated
@RequestMapping(value = "/shoppyme")
@CrossOrigin(origins = "*")
public class ProductAPI {

	@Autowired
	private ProductServiceImpl productServiceImpl;

	@Autowired
	private Environment environment;

	@GetMapping(value = "/getproduct")
	public ResponseEntity<List<ProductDTO>> getProduct() throws ShoppyMeException {
		List<ProductDTO> productDTO = productServiceImpl.getProducts();
		return new ResponseEntity<List<ProductDTO>>(productDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/getcategory")
	public ResponseEntity<List<CategoryDTO>> getCategory() throws ShoppyMeException {
		List<CategoryDTO> categoryDTO = productServiceImpl.getCategories();
		return new ResponseEntity<List<CategoryDTO>>(categoryDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/getpurchase/{emailId}")
	public ResponseEntity<List<PurchaseDetailsDTO>> getPurchase(@PathVariable String emailId) throws ShoppyMeException {
		List<PurchaseDetailsDTO> purchaseDTO = productServiceImpl.getPurchaseDetails(emailId);
		return new ResponseEntity<List<PurchaseDetailsDTO>>(purchaseDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/getcart")
	public ResponseEntity<JSONArray> getCartByEmailId(@RequestParam String emailId) throws ShoppyMeException {
		JSONArray cartDetails = productServiceImpl.getCartByEmailId(emailId);
		return new ResponseEntity<JSONArray>(cartDetails, HttpStatus.OK);
	}
	
	@PostMapping(value = "/validateuser")
	public ResponseEntity<JSONObject> validateUser(@RequestBody UsersDTO userDTO) throws ShoppyMeException {
		JSONObject result = productServiceImpl.validateCredentials(userDTO);
		return new ResponseEntity<JSONObject>(result, HttpStatus.OK);
	}

	@PostMapping(value = "/user")
	public ResponseEntity<Boolean> addUser(@RequestBody UsersDTO userDTO) throws ShoppyMeException {
//		System.out.println("****************************************");
//		System.out.println(userDTO.getEmailId());
//		System.out.println(userDTO.getAddress());
//		System.out.println(userDTO.getUserPassword());
//		System.out.println("****************************************");
		Boolean result = productServiceImpl.addUser(userDTO);
		return new ResponseEntity<Boolean>(result,HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/addProductToCart")
	public ResponseEntity<Boolean> addProductToCart(@RequestBody CartDTO cartDTO) throws ShoppyMeException {
		Boolean result = productServiceImpl.addProductCart(cartDTO);
		return new ResponseEntity<Boolean>(result,HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/addRating")
	public ResponseEntity<Boolean> addRating(@RequestBody RatingDTO ratingDTO) throws ShoppyMeException{
		Boolean result = productServiceImpl.addRating(ratingDTO);
		return new ResponseEntity<Boolean>(result,HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/updateCart")
	public ResponseEntity<Boolean> updateCart (@RequestBody CartDTO cartDTO) throws ShoppyMeException{
		Boolean result = productServiceImpl.updateCart(cartDTO);
		return new ResponseEntity<Boolean>(result,HttpStatus.CREATED);
	}
	
//	@DeleteMapping(value = "/deleteProductFromCart")
//	public ResponseEntity<Boolean> deleteProductFromCart(@RequestParam String emailId, @RequestParam String productId) throws ShoppyMeException {
//		Boolean result = productServiceImpl.deleteProductFromCart(emailId, productId);
//		return new ResponseEntity<>(result, HttpStatus.OK);
//	}
	
	@DeleteMapping(value = "/deleteProductFromCart")
	public ResponseEntity<Boolean> deleteProductFromCart(@RequestBody CartDTO cartDTO) throws ShoppyMeException {
		Boolean result = productServiceImpl.deleteProductFromCart(cartDTO.getEmailId(), cartDTO.getProductId());
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
