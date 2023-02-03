package com.example.test.service.impl;

import org.springframework.transaction.annotation.Transactional;
import com.example.test.repository.ArtistEntityRepository;
import com.example.test.service.ArtistService;
import org.springframework.stereotype.Service;
import com.example.test.entity.ArtistEntity;
import java.util.Optional;
import java.util.List;


@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistEntityRepository artistEntityRepository;

    public ArtistServiceImpl(ArtistEntityRepository artistEntityRepository) {
        this.artistEntityRepository = artistEntityRepository;
    }

    @Override
    @Transactional
    public List<ArtistEntity> findAll() {
        return artistEntityRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<ArtistEntity> findById(int id) {
        return artistEntityRepository.findById(id);
    }

    @Override
    @Transactional
    public ArtistEntity save(ArtistEntity artistEntity) {
        return artistEntityRepository.save(artistEntity);
    }

    @Override
    @Transactional
    public boolean existsById(int id) {
        return artistEntityRepository.existsById(id);
    }

    @Override
    @Transactional
    public boolean deleteById(int id){
        try{
            artistEntityRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
