package com.example.urlshortener.controller;

import com.example.urlshortener.model.URL;
import com.example.urlshortener.service.URLService;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.net.URI;

@RestController

public class RedirectController {

    private URLService service;

    public RedirectController(URLService service){

        this.service=service;

    }

    @GetMapping("/{shortCode}")

    public ResponseEntity<?> redirect(@PathVariable String shortCode){

        URL url = service.getByShortCode(shortCode);

        if(url == null){

            return ResponseEntity.notFound().build();

        }

        service.incrementClicks(url);

        return ResponseEntity
                .status(302)
                .location(URI.create(url.getOriginalUrl()))
                .build();

    }

}