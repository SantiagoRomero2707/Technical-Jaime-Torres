package com.example.test.service;

import com.example.test.entity.MuseumEntity;
import java.util.Optional;
import java.util.List;

public interface MuseumService {

    public List<MuseumEntity> findAll();

    public Optional<MuseumEntity> findById(int id);

    public Iterable<MuseumEntity> findMuseumsByCity(String city);

    public MuseumEntity save(MuseumEntity artistEntity);

    public boolean existsById (int id);

    public boolean existsByCity (String city);

    public boolean deleteById (int id);

}
