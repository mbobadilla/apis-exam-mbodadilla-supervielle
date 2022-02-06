package com.supervielle.examen.repositories;

import com.supervielle.examen.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository  extends JpaRepository<Country,String> {
    public Optional<Country> findByName(String name);
}
