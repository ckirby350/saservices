package com.radsyn.saservices.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import com.radsyn.saservices.repo.CountryRepo;
import com.radsyn.saservices.domain.Country;
import com.radsyn.saservices.dto.CountryData;

@RunWith(SpringRunner.class)
public class CountryServiceTest {

	@TestConfiguration
    static class CountryServiceTestContextConfiguration {
        @Bean
        public CountryService countryService() {
            return new DefaultCountryService();
        }
    }
	
	 @Autowired
	 private CountryService countryService;
	 
	 @MockBean
	 private CountryRepo countryRepository;
	
	 @Before
	 public void setUp() {
		 LocalDateTime rightNow = LocalDateTime.now();
		 Country us = new Country("United States", rightNow);
		 us.setCountry_id(11L);
		 Country mx = new Country("Mexico", rightNow.minusDays(1));
		 mx.setCountry_id(12L);
		 Country en = new Country("England", rightNow.minusHours(4));
		 en.setCountry_id(15L);
		 Country fr = new Country("France", rightNow.minusHours(11));
		 fr.setCountry_id(22L);
		
		 List<Country> allCountries = Arrays.asList(us, mx, en, fr);

		 Mockito.when(countryRepository.findById(us.getCountry_id())).thenReturn(Optional.of(us));
		 Mockito.when(countryRepository.findById(mx.getCountry_id())).thenReturn(Optional.of(mx));
		 Mockito.when(countryRepository.findById(en.getCountry_id())).thenReturn(Optional.of(en));
		 Mockito.when(countryRepository.findById(fr.getCountry_id())).thenReturn(Optional.of(fr));
		 
		 Mockito.when(countryRepository.findAll()).thenReturn(allCountries);
		 Mockito.when(countryRepository.findById(-99L)).thenReturn(Optional.empty());
	 }
	 
	 
    @Test
    public void whenValidId_thenCountryShouldBeFound() {
        CountryData fromDb = countryService.getCountryById(11L);
        assertThat(fromDb.getCountry()).isEqualTo("United States");

        verifyFindByIdIsCalledOnce();
    }

    @Test
    public void whenInValidId_thenCountryShouldNotBeFound() {
    	CountryData fromDb = null;
    	try {
    		fromDb = countryService.getCountryById(-99L);
    	} catch (jakarta.persistence.EntityNotFoundException e) {
    		fromDb = null;
    	}
        verifyFindByIdIsCalledOnce();
        assertThat(fromDb).isNull();
    }

    @Test
    public void given4Countries_whengetAll_thenReturn4Records() {
    	LocalDateTime rightNow = LocalDateTime.now();
    	 Country us = new Country("United States", rightNow);
		 us.setCountry_id(11L);
		 Country mx = new Country("Mexico", rightNow.minusDays(1));
		 mx.setCountry_id(12L);
		 Country en = new Country("England", rightNow.minusHours(4));
		 en.setCountry_id(15L);
		 Country fr = new Country("France", rightNow.minusHours(11));
		 fr.setCountry_id(22L);

        List<CountryData> allCountries = countryService.getAllCountries();
        verifyFindAllCountriesIsCalledOnce();
        assertThat(allCountries).hasSize(4).extracting(CountryData::getCountry).contains(us.getCountry(), mx.getCountry(), en.getCountry(), fr.getCountry());
    }

    private void verifyFindByIdIsCalledOnce() {
        Mockito.verify(countryRepository, VerificationModeFactory.times(1)).findById(Mockito.anyLong());
        Mockito.reset(countryRepository);
    }

    private void verifyFindAllCountriesIsCalledOnce() {
        Mockito.verify(countryRepository, VerificationModeFactory.times(1)).findAll();
        Mockito.reset(countryRepository);
    }
 
	 
}
