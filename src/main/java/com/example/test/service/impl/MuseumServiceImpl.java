package com.example.test.service.impl;

import org.springframework.transaction.annotation.Transactional;
import com.example.test.repository.MuseumEntityRepository;
import com.example.test.service.MuseumService;
import org.springframework.stereotype.Service;
import com.example.test.entity.MuseumEntity;
import java.util.Optional;
import java.util.List;

@Service
public class MuseumServiceImpl implements MuseumService {

    private final MuseumEntityRepository museumEntityRepository;

    public MuseumServiceImpl(MuseumEntityRepository museumEntityRepository) {
        this.museumEntityRepository = museumEntityRepository;
    }


    @Override
    @Transactional
    public List<MuseumEntity> findAll() {
        return museumEntityRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<MuseumEntity> findById(int id) {
        return museumEntityRepository.findById(id);
    }

    @Override
    public Iterable<MuseumEntity> findMuseumsByCity(String city) {
        return museumEntityRepository.findByCityAndNameStartingWith(city, "L");
    }
    
    @Override
    @Transactional
    public MuseumEntity save(MuseumEntity museumEntity) {
        return museumEntityRepository.save(museumEntity);
    }

    @Override
    @Transactional
    public boolean existsById(int id) {
        return museumEntityRepository.existsById(id);
    }

    @Override
    @Transactional
    public boolean existsByCity(String city) {
        return museumEntityRepository.existsByCity(city);
    }

    @Override
    @Transactional
    public boolean deleteById(int id){
        try{
            museumEntityRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
