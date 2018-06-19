package com.oz.tailor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oz.tailor.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	User findByUserName(String userName);
}
