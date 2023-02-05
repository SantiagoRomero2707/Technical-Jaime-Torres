package com.example.test.controller.artist;

import com.example.test.dto.Entity.request.ArtistRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.example.test.service.ArtistService;
import io.micrometer.common.util.StringUtils;
import com.example.test.entity.ArtistEntity;
import org.springframework.http.HttpStatus;
import com.example.test.dto.utils.Mensaje;

@RestController
@RequestMapping("/api/v1/artist")
public class ArtistUpdateController {

    private final ArtistService artistService;

    public ArtistUpdateController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateArtist(@PathVariable("id")int id, @RequestBody ArtistRequest artistRequest){
        if(!artistService.existsById(id))
            return new ResponseEntity<>(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(String.valueOf(artistRequest.getName())))
            return new ResponseEntity<>(new Mensaje("La fecha es obligatoria"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(artistRequest.getLastName()))
            return new ResponseEntity<>(new Mensaje("El tipo de movimiento es obligatario"), HttpStatus.BAD_REQUEST);

        ArtistEntity artistEntity = artistService.findById(id).get();
        artistEntity.setName(artistRequest.getName());
        artistEntity.setLastName(artistRequest.getLastName());
        artistEntity.setNumberIDE(artistRequest.getNumberIDE());
        artistEntity.setTypeIDE(artistRequest.getTypeIDE());
        artistService.save(artistEntity);
        return new ResponseEntity<>(new Mensaje("Artista actualizado"), HttpStatus.OK);
    }
}
