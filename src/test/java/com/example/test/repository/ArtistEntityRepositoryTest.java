package com.example.test.repository;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.MethodOrderer;
import com.example.test.entity.ArtistEntity;
import org.junit.jupiter.api.TestMethodOrder;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.test.annotation.Rollback;
import static org.hamcrest.MatcherAssert.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ArtistEntityRepositoryTest {

    @Autowired
    private ArtistEntityRepository artistEntityRepository;

    @Test
    @Rollback(value = false)
    @Order(1)
    public void testSaveArtist(){
        ArtistEntity artistEntity = new ArtistEntity();
        artistEntity.setName("Johan Santiago");
        artistEntity.setLastName("Romero Duarte");
        artistEntity.setTypeIDE("AB-CD");
        artistEntity.setNumberIDE("EF-GH");
        ArtistEntity artistSaved = artistEntityRepository.save(artistEntity);
        assertNotNull(artistSaved);
    }

    @Test
    @Rollback(value = false)
    @Order(3)
    public void testUpdateArtist(){
        String newLastName = "Castro Sabogal"; // Values for update
        int idArtist = 6;
        Optional<ArtistEntity> existArtist = artistEntityRepository.findById(idArtist);

        if(existArtist.isPresent()){
            ArtistEntity artistEntity = new ArtistEntity();
            artistEntity.setIdArtists(idArtist);
            artistEntity.setName(existArtist.get().getName());
            artistEntity.setLastName(newLastName);
            artistEntity.setTypeIDE(existArtist.get().getTypeIDE());
            artistEntity.setNumberIDE(existArtist.get().getNumberIDE());

            artistEntityRepository.save(artistEntity);

            Optional<ArtistEntity> updateArtist = artistEntityRepository.findById(idArtist);

            /*assertThat(updateArtist.get().getName(),is(equalTo(newName)));*/
            assertThat(updateArtist.get().getLastName(),is(equalTo(newLastName)));
        }
        throw new RuntimeException("El artista no está creado en base de datos");
    }

    @Test
    @Rollback(value = false)
    @Order(5)
    public void testDeleteArtist(){
        int idArtist = 0;

        boolean isExistBeforeDelete = artistEntityRepository.findById(idArtist).isPresent();
        artistEntityRepository.deleteById(idArtist);

        boolean notExistAfterDelete = artistEntityRepository.findById(idArtist).isPresent();

        assertTrue(isExistBeforeDelete);
        assertFalse(notExistAfterDelete);
    }

    @Test
    @Order(2)
    public void testFindById(){
        int idArtist = 2;
        Optional<ArtistEntity> artistEntity = artistEntityRepository.findById(idArtist);

        assertThat(artistEntity.get().getIdArtists(), is(equalTo(idArtist)));
    }

    @Test
    @Order(4)
    public void testListArtist(){
        List<ArtistEntity> allArtist = artistEntityRepository.findAll();

        for(ArtistEntity artistEntity: allArtist){
            System.out.println(artistEntity);
        }
        MatcherAssert.assertThat(allArtist, hasSize(greaterThan(0)));
    }

}
