package com.oz.tailor.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oz.tailor.model.Basket;
import com.oz.tailor.model.Receipt;

@Repository
public interface ReceiptRepository  extends CrudRepository<Receipt, Long>{
	List<Receipt> findAllByUserId(long userId);
}
