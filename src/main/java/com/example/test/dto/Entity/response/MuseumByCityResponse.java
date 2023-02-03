package com.example.test.dto.Entity.response;

import com.example.test.entity.MuseumEntity;
import com.example.test.security.entity.User;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class MuseumByCityResponse {
    private User user;
    private String nameCity;
    private List<MuseumEntity> museumEntityList;

}
