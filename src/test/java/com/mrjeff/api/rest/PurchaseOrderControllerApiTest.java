package com.mrjeff.api.rest;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mrjeff.dtos.PurchaseOrder;
import com.mrjeff.exception.CustomBusinessExceptionControllerAdvice;
import com.mrjeff.services.PurchaseOrdersService;

/**
 * Created by Cesar on 28/02/2019.
 */
public class PurchaseOrderControllerApiTest {

	private MockMvc mockMvc;
	private PurchaseOrdersService purchaseOrdersService;
	private static final String GET_TOTAL_ENDPOINT = "/purchaseOrder/getTotal";

	@Before
	public void setUp() {
		purchaseOrdersService = Mockito.mock(PurchaseOrdersService.class);

		mockMvc = MockMvcBuilders.standaloneSetup(new PurchaseOrderController(purchaseOrdersService))
				.setControllerAdvice(new CustomBusinessExceptionControllerAdvice()).build();
	}

	@After
	public void tearDown() {
		mockMvc = null;
		purchaseOrdersService = null;
	}

	@Test
	public void getTotal_shouldReturnOk() throws Exception {
		// Given
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		String bodyContent = "{\"cupon\":\"pepe\",\"productsList\":[{\"code\":\"camisa\",\"cost\":3},{\"code\":\"traje\",\"cost\":12}]}";
		doReturn(12).when(purchaseOrdersService).calculateTotal(purchaseOrder);
		// When
		final ResultActions result = mockMvc.perform(post(GET_TOTAL_ENDPOINT).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(bodyContent));
		// Assert
		result.andExpect(status().isOk());
		result.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}
}
