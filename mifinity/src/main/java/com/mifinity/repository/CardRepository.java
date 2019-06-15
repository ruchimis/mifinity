package com.mifinity.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mifinity.model.Card;

public interface CardRepository extends CrudRepository<Card, Long> {

	public List<Card> findByUserId(Long userId);
}
