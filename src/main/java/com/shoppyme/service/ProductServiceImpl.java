package com.shoppyme.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoppyme.dto.CartDTO;
import com.shoppyme.dto.CategoryDTO;
import com.shoppyme.dto.ProductDTO;
import com.shoppyme.dto.PurchaseDetailsDTO;
import com.shoppyme.dto.RatingDTO;
import com.shoppyme.dto.RolesDTO;
import com.shoppyme.dto.UsersDTO;
import com.shoppyme.entity.Cart;
import com.shoppyme.entity.Category;
import com.shoppyme.entity.CompositeKeyCart;
import com.shoppyme.entity.CompositeKeyRating;
import com.shoppyme.entity.Product;
import com.shoppyme.entity.PurchaseDetails;
import com.shoppyme.entity.Rating;
import com.shoppyme.entity.Roles;
import com.shoppyme.entity.Users;
import com.shoppyme.exception.ShoppyMeException;
import com.shoppyme.repository.CartRepository;
import com.shoppyme.repository.CategoryRepository;
import com.shoppyme.repository.ProductRepository;
import com.shoppyme.repository.PurchaseDetailsRepository;
import com.shoppyme.repository.RatingRepository;
import com.shoppyme.repository.UsersRepository;

@Service(value = "productService")
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private PurchaseDetailsRepository purchaseDetailsRepository;

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private RatingRepository ratingRepository;

	@Override
	public List<ProductDTO> getProducts() throws ShoppyMeException {
		Iterable<Product> productItr = productRepository.findAll();

		if (productItr == null) {
			throw new ShoppyMeException("Service.NO_PRODUCT_FOUND");
		}

		List<ProductDTO> productDTOList = new ArrayList<>();

		for (Product p : productItr) {
			ProductDTO productDTO = new ProductDTO();

			productDTO.setProductId(p.getProductId());
			productDTO.setProductName(p.getProductName());
			productDTO.setPrice(p.getPrice());
			productDTO.setQuantityAvailable(p.getQuantityAvailable());

			CategoryDTO categoryDTO = new CategoryDTO();

			categoryDTO.setCategoryId(p.getCategory().getCategoryId());
			categoryDTO.setCategoryName(p.getCategory().getCategoryName());

			productDTO.setCategoryDTO(categoryDTO);

			productDTOList.add(productDTO);
		}
		return productDTOList;
	}

	@Override
	public ProductDTO getProductsByProductId(String productId) throws ShoppyMeException {
		Optional<Product> productOpt = productRepository.findById(productId);

		if (productOpt == null) {
			throw new ShoppyMeException("Service.NO_PRODUCT_FOUND");
		}
		Product product = productOpt.orElseThrow(() -> new ShoppyMeException("Service.NO_PRODUCT_FOUND"));

		ProductDTO productDTO = new ProductDTO();

		productDTO.setProductId(product.getProductId());
		productDTO.setProductName(product.getProductName());
		productDTO.setPrice(product.getPrice());
		productDTO.setQuantityAvailable(product.getQuantityAvailable());

		CategoryDTO categoryDTO = new CategoryDTO();

		categoryDTO.setCategoryId(product.getCategory().getCategoryId());
		categoryDTO.setCategoryName(product.getCategory().getCategoryName());

		productDTO.setCategoryDTO(categoryDTO);

		return productDTO;
	}

	public List<CategoryDTO> getCategories() throws ShoppyMeException {

		Iterable<Category> categoryItr = categoryRepository.findAll();

		if (categoryItr == null) {
			throw new ShoppyMeException("Service.NO_CATEGORY_FOUND");
		}

		List<CategoryDTO> categoryDTOList = new ArrayList<>();

		for (Category c : categoryItr) {

			CategoryDTO categoryDTO = new CategoryDTO();

			categoryDTO.setCategoryId(c.getCategoryId());
			categoryDTO.setCategoryName(c.getCategoryName());

			categoryDTOList.add(categoryDTO);
		}
		return categoryDTOList;
	}

	@Override
	public List<PurchaseDetailsDTO> getPurchaseDetails(String emailId) throws ShoppyMeException {
		Optional<Users> userOpt = usersRepository.findById(emailId);
		Users user = userOpt.orElseThrow(() -> new ShoppyMeException("Service.INVALID_EMAILID"));

		Iterable<PurchaseDetails> purchaseDetailsItr = purchaseDetailsRepository.findByUser(user);

		if (purchaseDetailsItr == null) {
			System.out.println("Wrong query");
		}

		List<PurchaseDetailsDTO> purchaseDetailsList = new ArrayList<>();

		for (PurchaseDetails p : purchaseDetailsItr) {
			PurchaseDetailsDTO purchaseDTO = new PurchaseDetailsDTO();
			purchaseDTO.setPurchaseId(p.getPurchaseId());

			ProductDTO productDTO = new ProductDTO();
			productDTO.setProductId(p.getProduct().getProductId());
			productDTO.setProductName(p.getProduct().getProductName());
			productDTO.setPrice(p.getProduct().getPrice());
			productDTO.setQuantityAvailable(p.getProduct().getQuantityAvailable());

			CategoryDTO categoryDTO = new CategoryDTO();
			categoryDTO.setCategoryId(p.getProduct().getCategory().getCategoryId());
			categoryDTO.setCategoryName(p.getProduct().getCategory().getCategoryName());

			productDTO.setCategoryDTO(categoryDTO);

			purchaseDTO.setProductDTO(productDTO);

			purchaseDTO.setDateOfPurchase(p.getDateOfPurchase());
			purchaseDTO.setQuantityPurchased(p.getQuantityPurchased());

			UsersDTO userDTO = new UsersDTO();
			userDTO.setAddress(p.getUser().getAddress());
			userDTO.setDateOfBirth(p.getUser().getDateOfBirth());
			userDTO.setEmailId(p.getUser().getEmailId());
			userDTO.setGender(p.getUser().getGender());

			RolesDTO roles = new RolesDTO();
			roles.setRoleId(p.getUser().getRole().getRoleId());
			roles.setRoleName(p.getUser().getRole().getRoleName());

			userDTO.setRole(roles);
			purchaseDTO.setUser(userDTO);

			purchaseDetailsList.add(purchaseDTO);

		}

		return purchaseDetailsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONArray getCartByEmailId(String emailId) throws ShoppyMeException {
		List<Cart> cartEntities = cartRepository.findByEmailId(emailId);
		JSONArray viewCartJSONArray = new JSONArray();

		for (Cart c : cartEntities) {
			JSONObject productDetails = new JSONObject();
			String productId = c.getProductId();
			ProductDTO productDTO = getProductsByProductId(productId);
			productDetails.put("productName", productDTO.getProductName());
			productDetails.put("price", productDTO.getPrice());
			productDetails.put("quantity", c.getQuantity());
			productDetails.put("productId", productId);
			productDetails.put("quantityAvailable", productDTO.getQuantityAvailable());
			
			viewCartJSONArray.add(productDetails);

		}
		return viewCartJSONArray;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject validateCredentials(UsersDTO userDTO) throws ShoppyMeException {
		Optional<Users> OptUser = usersRepository.findById(userDTO.getEmailId());
		JSONObject json = new JSONObject();
		Users userEntity = OptUser.orElseThrow(() -> new ShoppyMeException("Service.INVALID_EMAILID"));

		if (userDTO.getEmailId().toLowerCase().equals(userEntity.getEmailId().toLowerCase())
				&& userDTO.getUserPassword().equals(userEntity.getUserPassword())) {
			json.put("status", true);
			json.put("role", userEntity.getRole().getRoleId());
			return json;
		}
		json.put("status", false);
		return json;
	}

	@Override
	public Boolean addUser(UsersDTO user) throws ShoppyMeException {
		Optional<Users> OptUser = usersRepository.findById(user.getEmailId());

		if (OptUser.isPresent()) {
			return false;
		}
		Users userEntity = new Users();
		userEntity.setEmailId(user.getEmailId());
		userEntity.setUserPassword(user.getUserPassword());
		userEntity.setGender(user.getGender());
		userEntity.setDateOfBirth(user.getDateOfBirth());
		userEntity.setAddress(user.getAddress());

		Roles role = new Roles();
		role.setRoleId(user.getRole().getRoleId());
		role.setRoleName(user.getRole().getRoleName());

		userEntity.setRole(role);

		Users userResult = usersRepository.save(userEntity);

		return true;
	}

	@Override
	public Boolean addProductCart(CartDTO cartDTO) throws ShoppyMeException {
		CompositeKeyCart compositeCartId = new CompositeKeyCart(cartDTO.getProductId(), cartDTO.getEmailId());
		Optional<Cart> OptCart = cartRepository.findById(compositeCartId);

		if (OptCart.isEmpty()) {
			Cart cartEntity = new Cart();
			cartEntity.setEmailId(cartDTO.getEmailId());
			cartEntity.setProductId(cartDTO.getProductId());
			cartEntity.setQuantity(cartDTO.getQuantity());

			Cart cartResult = cartRepository.save(cartEntity);
		} else {
			Cart updateCartEntity = OptCart.get();
			updateCartEntity.setQuantity(updateCartEntity.getQuantity() + cartDTO.getQuantity());
		}

		Optional<Product> optProd = productRepository.findById(cartDTO.getProductId());

		Product productEntity = optProd.get();
		productEntity.setQuantityAvailable(productEntity.getQuantityAvailable() - cartDTO.getQuantity());

		return true;
	}

	@Override
	public Boolean addRating(RatingDTO ratingDTO) throws ShoppyMeException {
		CompositeKeyRating ratingId = new CompositeKeyRating( ratingDTO.getProductId(),ratingDTO.getEmailId());
		
		Rating ratingEntity = new Rating();
		ratingEntity.setEmailId(ratingDTO.getEmailId());
		ratingEntity.setProductId(ratingDTO.getProductId());
		ratingEntity.setProductName(ratingDTO.getProductName());
		ratingEntity.setReviewRating(ratingDTO.getReviewRating());
		ratingEntity.setReviewComments(ratingDTO.getReviewComments());		
		
		ratingRepository.save(ratingEntity);
		
		return true;
	}

	@Override
	public Boolean updateCart(CartDTO cartDTO) throws ShoppyMeException {
		
		CompositeKeyCart cartId =  new CompositeKeyCart(cartDTO.getProductId(),cartDTO.getEmailId());
		Optional<Cart> optCartId = cartRepository.findById(cartId);
		
		if(optCartId.isEmpty()) {
			return false;
		}
		
		Cart cartEntity = optCartId.get();
		cartEntity.setQuantity(cartDTO.getQuantity() );
		
		return true;
	}

	@Override
	public Boolean deleteProductFromCart(String emailId, String productId) throws ShoppyMeException {
		CompositeKeyCart cartId = new CompositeKeyCart(productId, emailId);
		Optional<Cart> cartOpt = cartRepository.findById(cartId);
		if(cartOpt.isEmpty()) {
			return false;	
		}
		cartRepository.deleteById(cartId);
		return true;
	}

}
