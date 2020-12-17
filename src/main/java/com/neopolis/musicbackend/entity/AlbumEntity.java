package com.neopolis.musicbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class AlbumEntity {
    @GeneratedValue
    @Id
    int album_id;
    String album_name;

    @OneToMany(fetch = FetchType.LAZY   , mappedBy = "albumId")
    @JsonIgnore
    private List<TrackEntity> tracks;
}
