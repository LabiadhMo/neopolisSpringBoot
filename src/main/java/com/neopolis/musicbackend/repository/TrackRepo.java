package com.neopolis.musicbackend.repository;


import com.neopolis.musicbackend.entity.TrackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface TrackRepo  extends JpaRepository<TrackEntity, Integer> {
    @Query(value ="select * from track_entity t where t.album_id=?1" ,nativeQuery = true)
    ArrayList<TrackEntity> findByAlbum(Integer id);
}
