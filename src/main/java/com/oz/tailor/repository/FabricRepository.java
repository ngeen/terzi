package com.oz.tailor.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oz.tailor.model.Fabric;

@Repository
public interface FabricRepository extends CrudRepository<Fabric, Long> {
	List<Fabric> findAllByUserId(long userId);
}
