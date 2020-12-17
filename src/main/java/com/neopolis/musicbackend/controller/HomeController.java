package com.neopolis.musicbackend.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
@RequestMapping("/")
public class HomeController {
    private Path fileStorageLocation = Paths.get("C:\\Users\\33610\\Documents\\workspace\\musicbackend\\src\\main\\resources\\static\\music\\");
    @GetMapping
    ResponseEntity helloWorld(){
        return new ResponseEntity("hello World",HttpStatus.OK);
    }

    @GetMapping(
            value = "/{audio}",
            produces = "audio/mpeg"
    )


    public Resource loadFileAsResource(@PathVariable String audio ) throws MalformedURLException{

        Path filePath = this.fileStorageLocation.resolve(audio).normalize();
        Resource resource = new UrlResource(filePath.toUri());
        if (resource.exists()) {
            return resource;

        }
        else
            return  null ;
    }
}
