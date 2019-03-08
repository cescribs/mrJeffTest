package com.mrjeff.api.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mrjeff.services.PurchaseOrdersServiceImpl;

@RestController
@RequestMapping(value = "/vouchers", produces = APPLICATION_JSON_VALUE)
public class VoucherController {

	private PurchaseOrdersServiceImpl purchaseOrdersService;

	private static final Logger log = LoggerFactory.getLogger(VoucherController.class);

	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> getVouchers() {
		log.info("Getting all vouchers");
		return ResponseEntity.ok(purchaseOrdersService.getVouchers());
	}

	@PostMapping(value = "/add", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addVoucher(@RequestParam("voucherCode") String voucherCode,
			@RequestParam("voucherValue") Integer voucherValue) {
		log.info(String.format("Adding new voucher %s with value %s", voucherCode, voucherValue));
		return ResponseEntity.ok(purchaseOrdersService.addVoucher(voucherCode, voucherValue));
	}

	@Autowired
	public VoucherController(PurchaseOrdersServiceImpl purchaseOrdersService) {
		super();
		this.purchaseOrdersService = purchaseOrdersService;
	}

}
