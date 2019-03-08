package com.mrjeff.dtos;

import java.util.List;

public class PurchaseOrder {
	
	List<Product> productsList;
	String cupon;
	public List<Product> getProductsList() {
		return productsList;
	}
	public void setProductsList(List<Product> productsList) {
		this.productsList = productsList;
	}
	public String getCupon() {
		return cupon;
	}
	public void setCupon(String cupon) {
		this.cupon = cupon;
	}

}
