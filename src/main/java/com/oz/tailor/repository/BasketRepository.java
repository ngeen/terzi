package com.oz.tailor.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oz.tailor.model.Basket;

@Repository
public interface BasketRepository extends CrudRepository<Basket, Long> {
	List<Basket> findAllByUserId(long userId);
}
