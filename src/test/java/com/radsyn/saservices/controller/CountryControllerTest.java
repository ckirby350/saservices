package com.radsyn.saservices.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.radsyn.saservices.MainApp;
import com.radsyn.saservices.domain.Country;
import com.radsyn.saservices.repo.CountryRepo;
import com.radsyn.saservices.JsonUtil;

/***
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = MainApp.class)
@AutoConfigureMockMvc 
@EnableAutoConfiguration(exclude=SecurityAutoConfiguration.class)
// @TestPropertySource(locations = "classpath:application-integrationtest.properties")
@AutoConfigureTestDatabase
***/
public class CountryControllerTest {
	@Autowired
    private MockMvc mvc;

    @Autowired
    private CountryRepo repository;

    @After
    public void resetDb() {
        repository.deleteAll();
    }
    
    //@Test
    public void whenValidInput_thenCreateCountry() throws IOException, Exception {
    	LocalDateTime rightNow = LocalDateTime.now();
		 Country ca = new Country("Canada", rightNow);
		 ca.setCountry_id(33L);
        mvc.perform(post("/countries").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(ca)));

        List<Country> found = repository.findAll();
        assertThat(found).extracting(Country::getCountry).containsOnly("Canada");
    }
}
