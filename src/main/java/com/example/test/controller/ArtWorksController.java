package com.example.test.controller;

import com.example.test.dto.utils.Mensaje;
import org.springframework.http.HttpStatus;
import io.micrometer.common.util.StringUtils;
import com.example.test.entity.ArtWorksEntity;
import com.example.test.dto.Entity.request.ArtWorksRequest;
import com.example.test.service.ArtWorkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/artwork")
public class ArtWorksController {

    private final ArtWorkService artWorkService;

    public ArtWorksController(ArtWorkService artWorkService) {
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

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ArtWorksRequest artWorksRequest){
        if(StringUtils.isBlank(String.valueOf(artWorksRequest.getName())))
            return new ResponseEntity<>(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(artWorksRequest.getCountry()))
            return new ResponseEntity<>(new Mensaje("El país es obligatorio"), HttpStatus.BAD_REQUEST);
        ArtWorksEntity newWorkArtist = new ArtWorksEntity();

        newWorkArtist.setName(artWorksRequest.getName());
        newWorkArtist.setCountry(artWorksRequest.getCountry());
        newWorkArtist.setFkIdArtist(artWorksRequest.getFkIdArtist());
        artWorkService.save(newWorkArtist);
        return new ResponseEntity<>(new Mensaje("Artista registrado y creado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody ArtWorksRequest artWorksRequest){
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!artWorkService.existsById(id))
            return new ResponseEntity<>(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        artWorkService.deleteById(id);
        return new ResponseEntity<>(new Mensaje("Artista eliminado"), HttpStatus.OK);
    }
}
