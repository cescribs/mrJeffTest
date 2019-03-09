package com.mrjeff.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.mrjeff.dtos.Product;
import com.mrjeff.dtos.PurchaseOrder;
import com.mrjeff.repository.CuponRepository;
import com.mrjeff.repository.Voucher;

/**
 * Created by Cesar on 28/02/2019.
 */
public class CuponServiceTest {

	private CuponRepository cuponRepository;
	private PurchaseOrdersServiceImpl purchaseOrdersServiceImpl;
	private static final String NOT_VALID_VOUCHER_CODE = "notValid";
	private static final String VALID_VOUCHER_CODE = "valid";

	@Before
	public void setUp() {
		cuponRepository = Mockito.mock(CuponRepository.class);
		purchaseOrdersServiceImpl = new PurchaseOrdersServiceImpl(cuponRepository);
	}

	@After
	public void tearDown() {
		cuponRepository = null;
		purchaseOrdersServiceImpl = null;
	}

	@Test
	public void calculateTotal_shouldReturnExactIntegerIfNoVoucherFound() throws Exception {
		// Given
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setCupon(NOT_VALID_VOUCHER_CODE);
		Product product = new Product();
		product.setCode("Camisa");
		product.setCost(12);
		List<Product> productsList = Collections.singletonList(product);
		purchaseOrder.setProductsList(productsList);
		doReturn(null).when(cuponRepository).findByCode(NOT_VALID_VOUCHER_CODE);
		// When
		Integer result = purchaseOrdersServiceImpl.calculateTotal(purchaseOrder);
		// Assert
		assertThat(result).isEqualTo(12);
	}

	@Test
	public void calculateTotal_shouldReturnLessIfNoVoucherFound() throws Exception {
		// Given
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setCupon(VALID_VOUCHER_CODE);
		Product product = new Product();
		product.setCode("Camisa");
		product.setCost(12);
		List<Product> productsList = Collections.singletonList(product);
		purchaseOrder.setProductsList(productsList);
		Voucher validVoucher = new Voucher(VALID_VOUCHER_CODE, 2);
		doReturn(validVoucher).when(cuponRepository).findByCode(VALID_VOUCHER_CODE);
		// When
		Integer result = purchaseOrdersServiceImpl.calculateTotal(purchaseOrder);
		// Assert
		assertThat(result).isEqualTo(10);
	}

	@Test
	public void getVouchers_emptyRepository_ShouldReturnEmptyList() {
		// Given empty repo
		List<Voucher> emptyVoucherList = new ArrayList<Voucher>();
		doReturn(emptyVoucherList).when(cuponRepository).findAll();
		// When
		List<String> result = purchaseOrdersServiceImpl.getVouchers();
		// Assert
		assertThat(result.size()).isEqualTo(0);
	}

	@Test
	public void getVouchers_notEmptyRepository_ShouldReturnEmptyList() {
		// Given
		List<Voucher> voucherList = new ArrayList<Voucher>();
		voucherList.add(new Voucher(VALID_VOUCHER_CODE, 11));
		voucherList.add(new Voucher(NOT_VALID_VOUCHER_CODE, 11));
		doReturn(voucherList).when(cuponRepository).findAll();
		// When
		List<String> result = purchaseOrdersServiceImpl.getVouchers();
		// Assert
		assertThat(result.size()).isEqualTo(voucherList.size());
	}

	@Test
	public void addVoucher_newOne_shouldAddVoucherToRepo() {
		// Given
		Voucher voucher = new Voucher(VALID_VOUCHER_CODE, 11);
		doReturn(null).when(cuponRepository).findByCode(VALID_VOUCHER_CODE);
		doReturn(voucher).when(cuponRepository).save(Mockito.<Voucher>any());
		// when
		String result = purchaseOrdersServiceImpl.addVoucher(VALID_VOUCHER_CODE, 11);
		// Assert
		assertThat(result).isEqualTo(VALID_VOUCHER_CODE);

	}

}
