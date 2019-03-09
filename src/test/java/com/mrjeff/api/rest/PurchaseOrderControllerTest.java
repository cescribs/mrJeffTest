package com.mrjeff.api.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mrjeff.dtos.Product;
import com.mrjeff.dtos.PurchaseOrder;
import com.mrjeff.services.PurchaseOrdersService;

public class PurchaseOrderControllerTest {

	private PurchaseOrdersService purchaseOrdersService;
	private PurchaseOrderController purchaseOrderController;

	@Before
	public void setUp() {
		purchaseOrdersService = Mockito.mock(PurchaseOrdersService.class);
		purchaseOrderController = new PurchaseOrderController(purchaseOrdersService);
	}

	@After
	public void tearDown() {
		purchaseOrdersService = null;
		purchaseOrderController = null;
	}
	
	@Test
	public void getTotal_shouldReturnExactIntegerAndOk() throws Exception {
		// Given
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setCupon("valid");
		Product product = new Product();
		product.setCode("Camisa");
		product.setCost(12);
		List<Product> productsList = Collections.singletonList(product);
		purchaseOrder.setProductsList(productsList);
		doReturn(12).when(purchaseOrdersService).calculateTotal(purchaseOrder);
		// When
		ResponseEntity<Integer> result = purchaseOrderController.getTotal(purchaseOrder);
		// Assert

		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(result.getBody()).isEqualTo(12);
	}

}
