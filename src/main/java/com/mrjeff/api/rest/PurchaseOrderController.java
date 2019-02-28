package com.mrjeff.api.rest;

import com.mrjeff.dtos.Product;
import com.mrjeff.services.PurchaseOrdersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by Cesar on 28/02/2019.
 */
@RestController
@RequestMapping(value = "/purchaseOrder", produces = APPLICATION_JSON_VALUE)
public class PurchaseOrderController {

    private PurchaseOrdersService purchaseOrdersService;

    private static final Logger log = LoggerFactory.getLogger(PurchaseOrderController.class);

    @RequestMapping(value = "/getTotal", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> getTotal(@RequestParam List<Product> productList, @RequestParam String cuponCode) {
        log.info("Getting purchase Order total for number of articles %s and discount code %s", productList.size(), cuponCode);
        return ResponseEntity.ok(purchaseOrdersService.calculateTotal(productList, cuponCode));
    }

    @Autowired
    PurchaseOrderController(PurchaseOrdersService purchaseOrdersService) {
        this.purchaseOrdersService = purchaseOrdersService;
    }
}
