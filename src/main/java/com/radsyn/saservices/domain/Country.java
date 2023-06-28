package com.radsyn.saservices.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "country")
public class Country {

	public Country() { }
	
	public Country(String countryStr, LocalDateTime last_updateDT) {
		this.country = countryStr;
		this.last_update = last_updateDT;
	}

	//@GeneratedValue(strategy = GenerationType.SEQUENCE)
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long country_id;
	private String country;
	private LocalDateTime last_update;
 
	public Long getCountry_id() {
		return country_id;
	}
	public void setCountry_id(Long country_id) {
		this.country_id = country_id;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	public LocalDateTime getLast_update() {
		return last_update;
	}
	public void setLast_update(LocalDateTime last_update) {
		this.last_update = last_update;
	}
 
}
