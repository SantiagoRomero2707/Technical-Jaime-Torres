package com.example.test.service.impl;

import org.springframework.transaction.annotation.Transactional;
import com.example.test.repository.ArtWorksEntityRepository;
import com.example.test.service.ArtWorkService;
import com.example.test.entity.ArtWorksEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class ArtWorkServiceImpl implements ArtWorkService {
    private final ArtWorksEntityRepository artWorksEntityRepository;

    public ArtWorkServiceImpl(ArtWorksEntityRepository artWorksEntityRepository) {
        this.artWorksEntityRepository = artWorksEntityRepository;
    }

    @Override
    @Transactional
    public List<ArtWorksEntity> findAll() {
        return artWorksEntityRepository.findAll();
    }

    @Override
    @Transactional
    public Iterable<ArtWorksEntity> findWorkArtsByArtist(int idArtist) {
        return artWorksEntityRepository.findByFkIdArtistAndCountry(idArtist, "Colombia");
    }

    @Override
    @Transactional
    public Optional<ArtWorksEntity> findById(int id) {
        return artWorksEntityRepository.findById(id);
    }

    @Override
    @Transactional
    public ArtWorksEntity save(ArtWorksEntity artWorksEntity) {
        return artWorksEntityRepository.save(artWorksEntity);
    }

    @Override
    @Transactional
    public boolean existsById(int id) {
        return artWorksEntityRepository.existsById(id);
    }

    @Override
    @Transactional
    public boolean deleteById(int id){
        try{
            artWorksEntityRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
