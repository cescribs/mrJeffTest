package com.mrjeff.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrjeff.dtos.Product;
import com.mrjeff.repository.CuponRepository;
import com.mrjeff.repository.Voucher;

/**
 * Created by Cesar on 28/02/2019.
 */
@Service
public class PurchaseOrdersServiceImpl implements PurchaseOrdersService {

	private static final Logger log = LoggerFactory.getLogger(PurchaseOrdersServiceImpl.class);

	@Autowired
	private CuponRepository cuponRepository;

	@Override
	public Integer calculateTotal(List<Product> productList, String voucherCode) {
		Integer total = 0;
		for (Product prod : productList) {
			total = total + prod.getCost();
		}
		if (voucherCode != null) {
			Voucher voucher = cuponRepository.findByCode(voucherCode);
			if (voucher != null) {
				total = total - voucher.getAmount();
			}
		}
		return total;
	}

	@Override
	public String addVoucher(String voucherCode, Integer voucherValue) {
		Voucher result = cuponRepository.findByCode(voucherCode);
		if (result != null) {
			log.info(String.format("Updating voucher %s", voucherCode));
			result.setAmount(voucherValue);
			return cuponRepository.save(result).getCode();
		}
		return cuponRepository.save(new Voucher(voucherCode, voucherValue)).getCode();
	}

	@Override
	public List<String> getVouchers() {
		List<String> result = new ArrayList<String>();
		cuponRepository.findAll().forEach(voucher -> result.add(voucher.getCode()));
		return result;
	}

}
