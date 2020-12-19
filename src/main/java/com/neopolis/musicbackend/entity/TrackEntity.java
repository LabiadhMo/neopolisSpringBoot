package com.neopolis.musicbackend.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
public class TrackEntity {
    @Id
    @GeneratedValue
    int id;

    String name;
    String path;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JsonIgnore
    AlbumEntity album_id;

}
