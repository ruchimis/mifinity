package com.mifinity.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import lombok.*;

@Data
@Entity
@Table(name = "card")
public class Card {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String name;
	@NotBlank
	private String ccNumber;
	@NotBlank
	private String expMonthYear;
	
	  
	@ManyToOne
    @JoinColumn
    private User user;
	public Card() {
	}
	
	public Card(String ccNumber) {
		this.ccNumber = ccNumber;
	}

	public Card(String name, String ccNumber, String expMonthYear, User user) {
		super();
		this.name = name;
		this.ccNumber = ccNumber;
		this.expMonthYear = expMonthYear;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCcNumber() {
		return ccNumber;
	}

	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}

	public String getExpMonthYear() {
		return expMonthYear;
	}

	public void setExpMonthYear(String expMonthYear) {
		this.expMonthYear = expMonthYear;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
