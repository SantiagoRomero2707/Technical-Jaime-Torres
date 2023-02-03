package com.example.test.controller;

import com.example.test.dto.utils.Mensaje;
import org.springframework.http.HttpStatus;
import com.example.test.entity.ArtistEntity;
import com.example.test.dto.Entity.request.ArtistRequest;
import io.micrometer.common.util.StringUtils;
import com.example.test.service.ArtistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/artist")
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }


    @GetMapping("/list")
    public ResponseEntity<List<ArtistEntity>> getAllMovements(){
        List<ArtistEntity> list = artistService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<ArtistEntity> getProductById(@PathVariable("id") int id){
        if(!artistService.existsById(id))
            return new ResponseEntity(new Mensaje("NO existe"), HttpStatus.NOT_FOUND);
        ArtistEntity artistEntity = artistService.findById(id).get();
        return new ResponseEntity<>(artistEntity, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ArtistRequest artistRequest){
        if(StringUtils.isBlank(String.valueOf(artistRequest.getName())))
            return new ResponseEntity<>(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(artistRequest.getLastName()))
            return new ResponseEntity<>(new Mensaje("El apellido es obligatorio"), HttpStatus.BAD_REQUEST);
        ArtistEntity movementSave = new ArtistEntity();

        movementSave.setName(artistRequest.getName());
        movementSave.setLastName(artistRequest.getLastName());
        movementSave.setTypeIDE(artistRequest.getTypeIDE());
        movementSave.setNumberIDE(artistRequest.getNumberIDE());
        artistService.save(movementSave);
        return new ResponseEntity<>(new Mensaje("Artista registrado y creado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody ArtistRequest artistRequest){
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!artistService.existsById(id))
            return new ResponseEntity<>(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        artistService.deleteById(id);
        return new ResponseEntity<>(new Mensaje("Artista eliminado"), HttpStatus.OK);
    }

}
