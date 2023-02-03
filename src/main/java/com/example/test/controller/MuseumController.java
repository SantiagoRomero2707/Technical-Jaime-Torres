package com.example.test.controller;

import com.example.test.dto.Entity.request.MuseumRequest;
import com.example.test.dto.utils.Mensaje;
import org.springframework.http.HttpStatus;
import com.example.test.entity.MuseumEntity;
import io.micrometer.common.util.StringUtils;
import com.example.test.service.MuseumService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/museum")
public class MuseumController {
    private final MuseumService museumService;

    public MuseumController(MuseumService museumService) {
        this.museumService = museumService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<MuseumEntity>> getAllMuseums(){
        List<MuseumEntity> list = museumService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<Iterable<MuseumEntity>> getMuseumByCity(@PathVariable("city") String city){
        if(!museumService.existsByCity(city))
            return new ResponseEntity(new Mensaje("NO existe"), HttpStatus.NOT_FOUND);
        Iterable<MuseumEntity> museumEntities = museumService.findMuseumsByCity(city);
        return new ResponseEntity<>(museumEntities, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<MuseumEntity> getMuseumById(@PathVariable("id") int id){
        if(!museumService.existsById(id))
            return new ResponseEntity(new Mensaje("NO existe"), HttpStatus.NOT_FOUND);
        MuseumEntity museumEntity = museumService.findById(id).get();
        return new ResponseEntity<>(museumEntity, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createMuseum(@RequestBody MuseumRequest museumRequest){
        if(StringUtils.isBlank(String.valueOf(museumRequest.getName())))
            return new ResponseEntity<>(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(museumRequest.getCountry()))
            return new ResponseEntity<>(new Mensaje("El país es obligatorio"), HttpStatus.BAD_REQUEST);
        MuseumEntity newMuseum = new MuseumEntity();

        newMuseum.setName(museumRequest.getName());
        newMuseum.setCity(museumRequest.getCity());
        newMuseum.setCountry(museumRequest.getCountry());
        newMuseum.setAddress(museumRequest.getAddress());
        museumService.save(newMuseum);
        return new ResponseEntity<>(new Mensaje("Museo registrado y creado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMuseum(@PathVariable("id")int id, @RequestBody MuseumRequest museumRequest){
        if(!museumService.existsById(id))
            return new ResponseEntity<>(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(String.valueOf(museumRequest.getName())))
            return new ResponseEntity<>(new Mensaje("La fecha es obligatoria"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(museumRequest.getCountry()))
            return new ResponseEntity<>(new Mensaje("El tipo de movimiento es obligatario"), HttpStatus.BAD_REQUEST);

        MuseumEntity savedMuseum = museumService.findById(id).get();
        savedMuseum.setName(museumRequest.getName());
        savedMuseum.setCity(museumRequest.getCity());
        savedMuseum.setCountry(museumRequest.getCountry());
        savedMuseum.setAddress(museumRequest.getAddress());
        museumService.save(savedMuseum);
        return new ResponseEntity<>(new Mensaje("Artista actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!museumService.existsById(id))
            return new ResponseEntity<>(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        museumService.deleteById(id);
        return new ResponseEntity<>(new Mensaje("Artista eliminado"), HttpStatus.OK);
    }
}
