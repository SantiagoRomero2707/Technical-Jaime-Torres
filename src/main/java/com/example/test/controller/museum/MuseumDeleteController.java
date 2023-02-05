package com.example.test.controller.museum;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import com.example.test.service.MuseumService;
import org.springframework.http.HttpStatus;
import com.example.test.dto.utils.Mensaje;

@RestController
@RequestMapping("/api/v1/museum")
public class MuseumDeleteController {

    private final MuseumService museumService;

    public MuseumDeleteController(MuseumService museumService) {
        this.museumService = museumService;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMuseum(@PathVariable("id")int id){
        if(!museumService.existsById(id))
            return new ResponseEntity<>(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        museumService.deleteById(id);
        return new ResponseEntity<>(new Mensaje("Artista eliminado"), HttpStatus.OK);
    }

}
