package com.example.user.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.user.model.Product;
import com.example.user.model.ProductApiResponse;

@Component
public class ProductDetailsResponseMapper {

	public List<Product> mapProductDetails(List<ProductApiResponse> productResponseList) {
		List<Product> products = null;
		if(productResponseList!=null) {
			products = new ArrayList<>();
		for (ProductApiResponse productApiResponse : productResponseList) {
			Product product = new Product();
			product.setBrand(productApiResponse.getBrand());
			product.setModel(productApiResponse.getModelNumber());
			product.setName(productApiResponse.getProductName());
			product.setMrp(productApiResponse.getProductMRP());
			products.add(product);
		}
		}
		return products;
	}

}
