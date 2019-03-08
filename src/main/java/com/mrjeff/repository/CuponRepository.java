package com.mrjeff.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Cesar on 28/02/2019.
 */
@Repository
public interface CuponRepository extends CrudRepository<Voucher, Long>{

	Voucher findByCode(String code);
}
