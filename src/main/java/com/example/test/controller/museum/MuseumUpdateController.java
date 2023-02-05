package com.example.test.controller.museum;

import com.example.test.dto.Entity.request.MuseumRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.example.test.service.MuseumService;
import io.micrometer.common.util.StringUtils;
import com.example.test.entity.MuseumEntity;
import org.springframework.http.HttpStatus;
import com.example.test.dto.utils.Mensaje;

@RestController
@RequestMapping("/api/v1/museum")
public class MuseumUpdateController {

    private final MuseumService museumService;

    public MuseumUpdateController(MuseumService museumService) {
        this.museumService = museumService;
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
}
