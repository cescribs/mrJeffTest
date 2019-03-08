package com.mrjeff.services;

import java.util.List;

import com.mrjeff.dtos.Product;

public interface PurchaseOrdersService {
	
	public Integer calculateTotal(List<Product> productList, String cuponCode);
	public String addVoucher(String voucherCode, Integer voucherValue);
	public List<String> getVouchers();

}
