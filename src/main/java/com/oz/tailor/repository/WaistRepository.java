package com.oz.tailor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oz.tailor.model.Yelek;

@Repository
public interface WaistRepository extends CrudRepository<Yelek, Long> {

}
