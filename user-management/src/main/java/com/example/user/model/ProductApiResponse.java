package com.example.user.model;

public class ProductApiResponse {

	private String productName;
	
	private Integer productMRP;
	
	private String brand;
	
	private String modelNumber;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getProductMRP() {
		return productMRP;
	}

	public void setProductMRP(Integer productMRP) {
		this.productMRP = productMRP;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public ProductApiResponse(String productName, Integer productMRP, String brand, String modelNumber) {
		super();
		this.productName = productName;
		this.productMRP = productMRP;
		this.brand = brand;
		this.modelNumber = modelNumber;
	}

	public ProductApiResponse() {
		super();
	}

	
	
}
