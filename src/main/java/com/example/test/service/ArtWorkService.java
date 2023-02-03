package com.example.test.service;

import com.example.test.entity.ArtWorksEntity;
import java.util.Optional;
import java.util.List;

public interface ArtWorkService {

    public List<ArtWorksEntity> findAll();

    public Iterable<ArtWorksEntity> findWorkArtsByArtist(int idArtist);

    public Optional<ArtWorksEntity> findById(int id);

    public ArtWorksEntity save(ArtWorksEntity artWorksEntity);

    public boolean existsById (int id);

    public boolean deleteById (int id);

}
