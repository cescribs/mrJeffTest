package com.mrjeff.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrjeff.dtos.Product;
import com.mrjeff.dtos.PurchaseOrder;
import com.mrjeff.repository.CuponRepository;
import com.mrjeff.repository.Voucher;

/**
 * Created by Cesar on 28/02/2019.
 */
@Service
public class PurchaseOrdersServiceImpl implements PurchaseOrdersService {

	private static final Logger log = LoggerFactory.getLogger(PurchaseOrdersServiceImpl.class);

	private CuponRepository cuponRepository;

	@Override
	public Integer calculateTotal(PurchaseOrder purchaseOrder) {
		Integer total = 0;
		for (Product prod : purchaseOrder.getProductsList()) {
			total = total + prod.getCost();
		}
		if (purchaseOrder.getCupon() != null) {
			Voucher voucher = cuponRepository.findByCode(purchaseOrder.getCupon());
			if (voucher != null) {
				total = total - voucher.getAmount();
			}
		}
		return total;
	}

	@Override
	public String addVoucher(String voucherCode, Integer voucherValue) {
		Voucher previous = cuponRepository.findByCode(voucherCode);
		Voucher result;
		if (previous != null) {
			log.info(String.format("Updating voucher %s", voucherCode));
			previous.setAmount(voucherValue);
			result = cuponRepository.save(previous);

		} else {
			result = cuponRepository.save(new Voucher(voucherCode, voucherValue));
		}
		return result.getCode();
	}

	@Override
	public List<String> getVouchers() {
		List<String> result = new ArrayList<String>();
		cuponRepository.findAll().forEach(voucher -> result.add(voucher.getCode()));
		return result;
	}

	@Autowired
	PurchaseOrdersServiceImpl(CuponRepository cuponRepository) {
		this.cuponRepository = cuponRepository;
	}

}
