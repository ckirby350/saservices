package com.radsyn.saservices.controller;


import com.radsyn.saservices.dto.CountryData;
import com.radsyn.saservices.service.CountryService;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {
	
	 @Resource(name = "countryService")
	 private CountryService countryService;

	    /**
	     * <p>Get all country data in the system.For production system you many want to use
	     * pagination.</p>
	     * @return List<CountryData>
	     */
	    @GetMapping
	    public List<CountryData> getCountries(){
	        return countryService.getAllCountries();
	    }

	    /**
	     * Method to get the country data based on the ID.
	     * @param id
	     * @return CountryData
	     */
	    @GetMapping("/country/{id}")
	    public CountryData getCountry(@PathVariable Long id){
	        return countryService.getCountryById(id);
	    }

	    /**
	     * Post request to create country information in the system.
	     * @param countryData
	     * @return
	     */
	    @PostMapping("/country")
	    public CountryData saveCountry(final @RequestBody CountryData countryData){
	        return countryService.saveCountry(countryData);
	    }

	    /**
	     * Delete country from the system based on the ID. The method mapping is similar to the getCountry with difference of
	     * @DeleteMapping and @GetMapping
	     * @param id
	     * @return
	     */
	    @DeleteMapping("/country/{id}")
	    public Boolean deleteCountry(@PathVariable Long id){
	        return countryService.deleteCountry(id);
	    }

}
