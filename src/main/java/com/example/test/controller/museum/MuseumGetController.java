package com.example.test.controller.museum;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import com.example.test.service.MuseumService;
import com.example.test.entity.MuseumEntity;
import org.springframework.http.HttpStatus;
import com.example.test.dto.utils.Mensaje;

import java.util.List;

@RestController
@RequestMapping("/api/v1/museum")
public class MuseumGetController {

    private final MuseumService museumService;

    public MuseumGetController(MuseumService museumService) {
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


}
