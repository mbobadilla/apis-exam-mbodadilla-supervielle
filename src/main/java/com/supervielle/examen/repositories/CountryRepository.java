package com.supervielle.examen.repositories;

import com.supervielle.examen.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CountryRepository  extends JpaRepository<Country,String> {
    public Optional<Country> findByName(String name);
}
