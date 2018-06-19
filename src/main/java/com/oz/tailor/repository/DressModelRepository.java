package com.oz.tailor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oz.tailor.model.DressModel;

@Repository
public interface DressModelRepository extends CrudRepository<DressModel, Long>{

}
