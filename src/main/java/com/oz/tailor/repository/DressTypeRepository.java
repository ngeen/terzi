package com.oz.tailor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oz.tailor.model.DressType;

@Repository
public interface DressTypeRepository extends CrudRepository<DressType, Long> {

}
