package com.example.test.controller.artworks;

import com.example.test.dto.utils.Mensaje;
import com.example.test.service.ArtWorkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/artwork")
public class ArtWorksDeleteController {

    private final ArtWorkService artWorkService;

    public ArtWorksDeleteController(ArtWorkService artWorkService) {
        this.artWorkService = artWorkService;
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteArtWorks(@PathVariable("id")int id){
        if(!artWorkService.existsById(id))
            return new ResponseEntity<>(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        artWorkService.deleteById(id);
        return new ResponseEntity<>(new Mensaje("Artista eliminado"), HttpStatus.OK);
    }
}
