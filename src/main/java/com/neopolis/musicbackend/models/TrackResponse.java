package com.neopolis.musicbackend.models;

import com.neopolis.musicbackend.entity.TrackEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter
@ToString
public class TrackResponse {
    List<TrackEntity> tracks;
}
