package com.example.test.controller.museum;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.test.dto.Entity.request.MuseumRequest;
import org.springframework.http.ResponseEntity;
import com.example.test.service.MuseumService;
import io.micrometer.common.util.StringUtils;
import com.example.test.entity.MuseumEntity;
import org.springframework.http.HttpStatus;
import com.example.test.dto.utils.Mensaje;

@RestController
@RequestMapping("/api/v1/museum")
public class MuseumPostController {

    private final MuseumService museumService;

    public MuseumPostController(MuseumService museumService) {
        this.museumService = museumService;
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

}
