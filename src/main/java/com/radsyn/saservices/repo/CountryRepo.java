package com.radsyn.saservices.repo;

import com.radsyn.saservices.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepo extends JpaRepository<Country,Long> {
}