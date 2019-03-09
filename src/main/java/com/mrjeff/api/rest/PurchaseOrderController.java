package com.mrjeff.api.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrjeff.dtos.PurchaseOrder;
import com.mrjeff.services.PurchaseOrdersService;

/**
 * Created by Cesar on 28/02/2019.
 */
@RestController
@RequestMapping(value = "/purchaseOrder", produces = APPLICATION_JSON_VALUE)
public class PurchaseOrderController {

	private PurchaseOrdersService purchaseOrdersService;

	private static final Logger log = LoggerFactory.getLogger(PurchaseOrderController.class);

	@PostMapping(value = "/getTotal", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> getTotal(@RequestBody PurchaseOrder purchaseOrder) {
		log.info(String.format("Getting purchase Order total for number of articles %s and discount code %s",
				purchaseOrder.getProductsList().size(), purchaseOrder.getCupon()));
		return ResponseEntity
				.ok(purchaseOrdersService.calculateTotal(purchaseOrder));
	}

	@Autowired
	PurchaseOrderController(PurchaseOrdersService purchaseOrdersService) {
		this.purchaseOrdersService = purchaseOrdersService;
	}
}
