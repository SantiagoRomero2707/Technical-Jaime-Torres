package com.example.test.controller.artworks;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.test.dto.Entity.request.ArtWorksRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.test.service.ArtWorkService;
import org.springframework.http.ResponseEntity;
import com.example.test.entity.ArtWorksEntity;
import io.micrometer.common.util.StringUtils;
import org.springframework.http.HttpStatus;
import com.example.test.dto.utils.Mensaje;

@RestController
@RequestMapping("/api/v1/artwork")
public class ArtWorksPostController {

    private final ArtWorkService artWorkService;

    public ArtWorksPostController(ArtWorkService artWorkService) {
        this.artWorkService = artWorkService;
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
}
