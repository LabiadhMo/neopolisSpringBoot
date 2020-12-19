package com.neopolis.musicbackend.controller;

import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
@RequestMapping("/")
public class HomeController {
   // private Path fileStorageLocation = Paths.get("C:\\Users\\33610\\Documents\\workspace\\musicbackend\\src\\main\\resources\\static\\music\\album1\\");
    @GetMapping
    ResponseEntity helloWorld(){
        return new ResponseEntity("hello World",HttpStatus.OK);
    }
/*
    @GetMapping(
            value = "/gettrack/{audio}",
            produces = MediaType.MULTIPART_MIXED_VALUE

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
    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity downloadFileFromLocal(@PathVariable String fileName) {
        Path path = Paths.get("C:\\Users\\33610\\Documents\\workspace\\musicbackend\\src\\main\\resources\\static\\music\\album1\\" + fileName);
        Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/a")
    ResponseEntity getSong(){
        return new ResponseEntity(" <video controls=\"\" autoplay=\"\" name=\"media\"><source src=\"http://192.168.43.48:8080/sound/character/get/a-little-more-ft-victoria-monet-(Songsify.Com)\" type=\"audio/mp3\"></video>",HttpStatus.OK);
    }*/

    @RequestMapping(value = "/sound/character/get/{album}/{audio}", method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_OCTET_STREAM_VALUE })
    public HttpServletResponse playAudio(HttpServletRequest request, HttpServletResponse response, @PathVariable("album") String album, @PathVariable("audio") String audio) throws IOException, ServletException {


        String file ="C:\\Users\\33610\\Documents\\workspace\\musicbackend\\src\\main\\resources\\static\\music\\"+album+"\\"+audio+".mp3" ;

        long length = new File(file).length();


        ServletOutputStream stream = null;
        BufferedInputStream buf = null;
        try {
            stream = response.getOutputStream();
            File mp3 = new File(file);

            //set response headers
            response.setContentType("audio/mpeg");

            response.addHeader("Content-Disposition", "attachment; filename=" + audio+".mp3");

            response.setContentLength((int) mp3.length());

            FileInputStream input = new FileInputStream(mp3);
            buf = new BufferedInputStream(input);
            int readBytes = 0;
            //read from the file; write to the ServletOutputStream
            while ((readBytes = buf.read()) != -1)
                stream.write(readBytes);
        } catch (IOException ioe) {
            throw new ServletException(ioe.getMessage());
        } finally {
            if (stream != null)
                stream.close();
            if (buf != null)
                buf.close();
        }
        return response;
    }

}
