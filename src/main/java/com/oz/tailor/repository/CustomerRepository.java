package com.oz.tailor.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oz.tailor.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
	List<Customer> findAllByUserId(long userId);
}
