package com.radsyn.saservices.service;

import com.radsyn.saservices.domain.Country;
import com.radsyn.saservices.dto.CountryData;
import com.radsyn.saservices.repo.CountryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("countryService")
public class DefaultCountryService implements CountryService {

    @Autowired
    private CountryRepo countryRepository;

    /**
     * Create a country based on the data sent to the service class.
     * @param country
     * @return DTO representation of the country
     */
    @Override
    public CountryData saveCountry(CountryData country) {
        Country countryModel = populateCountryEntity(country);
        return populateCountryData(countryRepository.save(countryModel));
    }

    /**
     * Delete country based on the country ID.We can also use other option to delete country
     * based on the entity (passing JPA entity class as method parameter)
     * @param countryId
     * @return boolean flag showing the request status
     */
    @Override
    public boolean deleteCountry(Long countryId) {
        countryRepository.deleteById(countryId);
        return true;
    }

    /**
     * Method to return the list of all the countries in the system.This is a simple
     * implementation but use pagination in the real world example.
     * @return list of country
     */
    @Override
    public List<CountryData> getAllCountries() {    	
        List<CountryData> countries = new ArrayList<> ();
        List<Country> countryList = countryRepository.findAll();
        countryList.forEach(country -> {
        	countries.add(populateCountryData(country));
        });
        return countries;
    }

    /**
     * Get country by ID. The service will send the country data else will throw the exception. 
     * @param countryId
     * @return CountryData
     */
    @Override
    public CountryData getCountryById(Long countryId) {
        return populateCountryData(countryRepository.findById(countryId).orElseThrow(() -> new EntityNotFoundException("Country not found")));
    }

    /**
     * Internal method to convert Country JPA entity to the DTO object
     * for frontend data
     * @param country
     * @return CountryData
     */
    private CountryData populateCountryData(final Country country) {
        CountryData countryData = new CountryData();
        countryData.setCountry_id(country.getCountry_id());
        countryData.setCountry(country.getCountry());
        countryData.setLast_update(country.getLast_update());
        return countryData;
    }

    /**
     * Method to map the front end country object to the JPA country entity.
     * @param countryData
     * @return Country
     */
    private Country populateCountryEntity(CountryData countryData) {
        Country country = new Country();
        if (countryData.getCountry_id() != null && countryData.getCountry_id().longValue() > 0) {
        	country.setCountry_id(countryData.getCountry_id());
        }
        country.setCountry(countryData.getCountry());
        country.setLast_update(countryData.getLast_update());
        return country;
    }
}