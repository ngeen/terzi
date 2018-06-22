package com.oz.tailor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oz.tailor.model.Receipt;

@Repository
public interface ReceiptRepository  extends CrudRepository<Receipt, Long>{

}
