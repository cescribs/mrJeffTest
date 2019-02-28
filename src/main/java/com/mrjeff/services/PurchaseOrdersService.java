package com.mrjeff.services;

import com.mrjeff.dtos.Product;
import com.mrjeff.repository.Cupon;
import com.mrjeff.repository.CuponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Cesar on 28/02/2019.
 */
@Service
public class PurchaseOrdersService {

    @Autowired
    private CuponRepository cuponRepository;

    public Integer calculateTotal(List<Product> productList, String cuponCode) {
        Integer total = 0;
        for (Product prod : productList) {
            total = total + prod.getCost();
        }
        if (cuponCode != null) {
            Cupon cupon = cuponRepository.findByCode(cuponCode);
            if(cupon!= null){
                total = total - cupon.getAmount();
            }
        }
        return total;
    }

}
