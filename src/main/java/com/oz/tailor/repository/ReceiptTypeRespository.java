package com.oz.tailor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oz.tailor.model.ReceiptType;

@Repository
public interface ReceiptTypeRespository extends CrudRepository<ReceiptType, Long>{

}
