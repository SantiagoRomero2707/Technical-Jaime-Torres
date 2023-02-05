package com.example.test.controller.artist;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.test.dto.Entity.request.ArtistRequest;
import org.springframework.http.ResponseEntity;
import com.example.test.service.ArtistService;
import io.micrometer.common.util.StringUtils;
import com.example.test.entity.ArtistEntity;
import org.springframework.http.HttpStatus;
import com.example.test.dto.utils.Mensaje;

@RestController
@RequestMapping("/api/v1/artist")
public class ArtistPostController {

    private final ArtistService artistService;

    public ArtistPostController(ArtistService artistService) {
        this.artistService = artistService;
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
}
