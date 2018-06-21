package com.oz.tailor.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oz.tailor.model.Item;

@Repository
public interface ItemRepository  extends CrudRepository<Item, Long> {
	List<Item> findAllByBasketId(long basketId);
}
