package com.example.test.service;

import com.example.test.entity.ArtistEntity;
import java.util.Optional;
import java.util.List;


public interface ArtistService {

    public List<ArtistEntity> findAll();

    public Optional<ArtistEntity> findById(int id);

    public ArtistEntity save(ArtistEntity artistEntity);

    public boolean existsById (int id);

    public boolean deleteById (int id);
}
