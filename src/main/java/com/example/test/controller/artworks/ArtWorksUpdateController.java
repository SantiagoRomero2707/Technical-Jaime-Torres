package com.example.test.controller.artworks;


import com.example.test.dto.Entity.request.ArtWorksRequest;
import com.example.test.dto.utils.Mensaje;
import com.example.test.entity.ArtWorksEntity;
import com.example.test.service.ArtWorkService;
import io.micrometer.common.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/artwork")
public class ArtWorksUpdateController {

    private final ArtWorkService artWorkService;

    public ArtWorksUpdateController(ArtWorkService artWorkService) {
        this.artWorkService = artWorkService;
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateArtWork(@PathVariable("id")int id, @RequestBody ArtWorksRequest artWorksRequest){

        if(!artWorkService.existsById(id))
            return new ResponseEntity<>(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(String.valueOf(artWorksRequest.getName())))
            return new ResponseEntity<>(new Mensaje("La fecha es obligatoria"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(artWorksRequest.getCountry()))
            return new ResponseEntity<>(new Mensaje("El tipo de movimiento es obligatario"), HttpStatus.BAD_REQUEST);

        ArtWorksEntity savedArtWorks = artWorkService.findById(id).get();
        savedArtWorks.setName(artWorksRequest.getName());
        savedArtWorks.setCountry(artWorksRequest.getCountry());
        savedArtWorks.setFkIdArtist(artWorksRequest.getFkIdArtist());
        artWorkService.save(savedArtWorks);
        return new ResponseEntity<>(new Mensaje("Artista actualizado"), HttpStatus.OK);
    }

}
