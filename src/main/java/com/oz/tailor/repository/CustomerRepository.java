package com.oz.tailor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oz.tailor.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
