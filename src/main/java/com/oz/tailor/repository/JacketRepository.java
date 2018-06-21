package com.oz.tailor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oz.tailor.model.Ceket;

@Repository
public interface JacketRepository  extends CrudRepository<Ceket, Long> {

}
