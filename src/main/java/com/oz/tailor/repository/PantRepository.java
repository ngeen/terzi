package com.oz.tailor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oz.tailor.model.Pantolon;

@Repository
public interface PantRepository extends CrudRepository<Pantolon, Long> {
	Pantolon	findByCustomerId(long customerId);
}
