package com.oz.tailor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oz.tailor.model.Item;

@Repository
public interface ItemRepository  extends CrudRepository<Item, Long> {

}
