package com.radsyn.saservices.dto;

import java.time.LocalDateTime;

public class CountryData {
	private Long country_id;
	private String country;
    private LocalDateTime last_update;

    public CountryData() {
    }
    
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

	public LocalDateTime getLast_update() {
		return last_update;
	}

	public void setLast_update(LocalDateTime last_update) {
		this.last_update = last_update;
	}

	
   
}
