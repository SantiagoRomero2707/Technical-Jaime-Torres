package com.example.test.repository;

import com.example.test.entity.MuseumEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MuseumEntityRepository extends JpaRepository<MuseumEntity, Integer> {

    Iterable<MuseumEntity> findByCityAndNameStartingWith(String city, String startWith);

    boolean existsByCity (String city);
}
