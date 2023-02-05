package com.example.test.controller.artist;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import com.example.test.service.ArtistService;
import com.example.test.entity.ArtistEntity;
import org.springframework.http.HttpStatus;
import com.example.test.dto.utils.Mensaje;
import java.util.List;

@RestController
@RequestMapping("/api/v1/artist")
public class ArtistGetController {

    private final ArtistService artistService;

    public ArtistGetController(ArtistService artistService) {
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
}
