package com.radsyn.saservices.service;

import com.radsyn.saservices.dto.CountryData;

import java.util.List;

public interface CountryService {

	CountryData saveCountry(CountryData customer);
    boolean deleteCountry(final Long country_id);
    List<CountryData> getAllCountries();
    CountryData getCountryById(final Long country_id); 
}
