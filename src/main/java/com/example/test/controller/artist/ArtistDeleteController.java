package com.example.test.controller.artist;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import com.example.test.service.ArtistService;
import org.springframework.http.HttpStatus;
import com.example.test.dto.utils.Mensaje;

@RestController
@RequestMapping("/api/v1/artist")
public class ArtistDeleteController {

    private final ArtistService artistService;

    public ArtistDeleteController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!artistService.existsById(id))
            return new ResponseEntity<>(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        artistService.deleteById(id);
        return new ResponseEntity<>(new Mensaje("Artista eliminado"), HttpStatus.OK);
    }
}
