package com.oz.tailor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oz.tailor.model.Gomlek;

@Repository
public interface ShirtRepository extends CrudRepository<Gomlek, Long> {

}
