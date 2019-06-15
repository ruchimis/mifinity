package com.mifinity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mifinity.model.Card;
import com.mifinity.repository.CardRepository;

@Service
public class CardService {

	@Autowired
	private CardRepository cardRepository;
	
	public  List<Card> getAllCards(Long userId) {
		List<Card> cards = cardRepository.findByUserId(userId);
		
		return cards;
	}
	
	public void addCard(Card card) {
		cardRepository.save(card);
	}

	public void deleteCard(Long id) {
		cardRepository.delete(id);
	}
}
