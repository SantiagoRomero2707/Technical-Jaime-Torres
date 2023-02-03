package com.example.test.repository;

import com.example.test.entity.ArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistEntityRepository extends JpaRepository<ArtistEntity, Integer> {
}
