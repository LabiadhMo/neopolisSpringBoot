package com.neopolis.musicbackend.controller;

import com.neopolis.musicbackend.entity.AlbumEntity;
import com.neopolis.musicbackend.models.PostAlbums;
import com.neopolis.musicbackend.repository.AlbumRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/allalbums")
public class AlbumController {

    @Autowired
    AlbumRepo albumRepo;

    @GetMapping
    ResponseEntity getAllAlbums(){
        return new ResponseEntity(albumRepo.findAll(), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity postAlbum(@RequestBody PostAlbums albums){
        for (String album:albums.getAlbums()) {
            AlbumEntity albumEntity = new AlbumEntity();
            albumEntity.setAlbum_name(album);
            albumRepo.save(albumEntity);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
