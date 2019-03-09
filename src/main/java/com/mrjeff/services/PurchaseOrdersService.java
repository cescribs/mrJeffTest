package com.mrjeff.services;

import java.util.List;

import com.mrjeff.dtos.Product;
import com.mrjeff.dtos.PurchaseOrder;

public interface PurchaseOrdersService {
	
	public Integer calculateTotal(PurchaseOrder purchaseOrder);
	public String addVoucher(String voucherCode, Integer voucherValue);
	public List<String> getVouchers();

}
