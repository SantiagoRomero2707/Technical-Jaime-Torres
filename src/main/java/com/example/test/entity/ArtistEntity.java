package com.example.test.entity;

import jakarta.persistence.*;
import java.util.Objects;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "artistas", schema = "prueba_jaime_torres")
public class ArtistEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_artista", nullable = false)
    private int idArtists;

    @Basic
    @Column(name = "tipo_ide", nullable = false, length = 5)
    private String typeIDE;

    @Basic
    @Column(name = "numero_ide", nullable = false, length = 20)
    private String numberIDE;

    @Basic
    @Column(name = "nombres", nullable = false, length = 30)
    private String name;

    @Basic
    @Column(name = "apellidos", nullable = false, length = 30)
    private String lastName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArtistEntity that)) return false;
        return getIdArtists() == that.getIdArtists() && getTypeIDE().equals(that.getTypeIDE()) && getNumberIDE().equals(that.getNumberIDE()) && getName().equals(that.getName()) && getLastName().equals(that.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdArtists(), getTypeIDE(), getNumberIDE(), getName(), getLastName());
    }
}
