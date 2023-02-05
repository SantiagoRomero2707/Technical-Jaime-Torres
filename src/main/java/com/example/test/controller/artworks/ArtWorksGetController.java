package com.example.test.controller.artworks;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.test.entity.ArtWorksEntity;
import com.example.test.service.ArtWorkService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.example.test.dto.utils.Mensaje;

import java.util.List;

@RestController
@RequestMapping("/api/v1/artwork")
public class ArtWorksGetController {

    private final ArtWorkService artWorkService;

    public ArtWorksGetController(ArtWorkService artWorkService) {
        this.artWorkService = artWorkService;
    }


    @GetMapping("/list")
    public ResponseEntity<List<ArtWorksEntity>> getAllMovements(){
        List<ArtWorksEntity> list = artWorkService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<ArtWorksEntity> getProductById(@PathVariable("id") int id){
        if(!artWorkService.existsById(id))
            return new ResponseEntity(new Mensaje("NO existe"), HttpStatus.NOT_FOUND);
        ArtWorksEntity artWorksEntity = artWorkService.findById(id).get();
        return new ResponseEntity<>(artWorksEntity, HttpStatus.OK);
    }

    @GetMapping("/work-art/detail/{id}")
    public ResponseEntity<Iterable<ArtWorksEntity>> getWorkArtsByIdArtist(@PathVariable("id") int id){
        if(!artWorkService.existsById(id))
            return new ResponseEntity(new Mensaje("NO existe"), HttpStatus.NOT_FOUND);
        Iterable<ArtWorksEntity> artWorksEntity = artWorkService.findWorkArtsByArtist(id);
        return new ResponseEntity<>(artWorksEntity, HttpStatus.OK);
    }
}
