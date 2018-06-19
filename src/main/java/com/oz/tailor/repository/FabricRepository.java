package com.oz.tailor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oz.tailor.model.Fabric;

@Repository
public interface FabricRepository extends CrudRepository<Fabric, Long> {

}
