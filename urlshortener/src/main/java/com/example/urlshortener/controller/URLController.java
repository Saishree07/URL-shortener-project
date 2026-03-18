package com.example.urlshortener.controller;

import com.example.urlshortener.model.URL;
import com.example.urlshortener.service.URLService;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;   // added import

import java.util.List;

@RestController
@RequestMapping("/api/urls")
@CrossOrigin

public class URLController {

    private URLService service;

    public URLController(URLService service){
        this.service=service;
    }

    @PostMapping
    public URL create(@RequestBody URL url){
        return service.createShortUrl(url.getOriginalUrl());
    }

    @GetMapping
    public List<URL> getAll(){
        return service.getAllUrls();
    }

    // ⭐ ADDED THIS METHOD
    @GetMapping("/{shortCode}")
    public ResponseEntity<?> redirect(@PathVariable String shortCode){

        URL url = service.getByShortCode(shortCode);

        return ResponseEntity
                .status(302)
                .header("Location", url.getOriginalUrl())
                .build();
    }

}