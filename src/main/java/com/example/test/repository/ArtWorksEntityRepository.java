package com.example.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.test.entity.ArtWorksEntity;

public interface ArtWorksEntityRepository extends JpaRepository<ArtWorksEntity, Integer> {

    Iterable<ArtWorksEntity> findByFkIdArtistAndCountry(int fkIdArtist, String Country);

}
