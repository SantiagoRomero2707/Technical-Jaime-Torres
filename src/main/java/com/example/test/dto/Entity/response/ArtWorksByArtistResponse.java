package com.example.test.dto.Entity.response;

import com.example.test.entity.ArtWorksEntity;
import com.example.test.security.entity.User;
import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class ArtWorksByArtistResponse {
    private User user;
    private int idArtist;
    private String nameArtist;
    private List<ArtWorksEntity> worksArts;
}
