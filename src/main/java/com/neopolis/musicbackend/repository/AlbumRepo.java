package com.neopolis.musicbackend.repository;

import com.neopolis.musicbackend.entity.AlbumEntity;
import com.neopolis.musicbackend.entity.TrackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface AlbumRepo extends JpaRepository<AlbumEntity,Integer> {


}
