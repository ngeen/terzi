package com.oz.tailor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oz.tailor.model.Basket;

@Repository
public interface BasketRepository extends CrudRepository<Basket, Long> {

}
