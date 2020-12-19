package com.neopolis.musicbackend.controller;


import com.neopolis.musicbackend.entity.AlbumEntity;
import com.neopolis.musicbackend.entity.TrackEntity;
import com.neopolis.musicbackend.models.TrackResponse;
import com.neopolis.musicbackend.repository.AlbumRepo;
import com.neopolis.musicbackend.repository.TrackRepo;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
    @RequestMapping("/alltracks")
public class TrackController {
    @Autowired
    TrackRepo trackRepo;

    @Autowired
    AlbumRepo albumRepo;

    @GetMapping
    ResponseEntity<?> getAll(){
        return new ResponseEntity(trackRepo.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    ResponseEntity  getTracksByAlbum (@RequestParam Integer id ){
        return new ResponseEntity(trackRepo.findByAlbum(id),HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity initServer(){
        List<AlbumEntity> l=albumRepo.findAll();
        for(Integer j=1;j<l.size()+1;j++){
        File folder = new File("C:\\Users\\33610\\Documents\\workspace\\musicbackend\\src\\main\\resources\\static\\music\\album"+j.toString());
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println("File " + listOfFiles[i].getName());
                TrackEntity trackEntity=new TrackEntity();
                trackEntity.setName(listOfFiles[i].getName());
                trackEntity.setPath(listOfFiles[i].getAbsolutePath());
                Optional<AlbumEntity> albumEntity=albumRepo.findById(j);
                AlbumEntity al=albumEntity.get();
                trackEntity.setAlbum_id(al);
                trackRepo.save(trackEntity);
            }
        }}
        return new ResponseEntity(HttpStatus.OK);
    }
}
