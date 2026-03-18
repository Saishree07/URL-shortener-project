package com.example.urlshortener.service;

import com.example.urlshortener.model.URL;
import com.example.urlshortener.repository.URLRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service

public class URLService {

    private URLRepository repository;

    public URLService(URLRepository repository){

        this.repository=repository;

    }

    public URL createShortUrl(String originalUrl){

        String shortCode=generateCode();

        URL url=new URL();

        url.setOriginalUrl(originalUrl);

        url.setShortCode(shortCode);

        url.setCreatedAt(LocalDateTime.now());

        url.setClickCount(0);

        return repository.save(url);

    }

    public List<URL> getAllUrls(){

        return repository.findAll();

    }

    public URL getByShortCode(String shortCode){

        URL url = repository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("URL not found"));

        url.setClickCount(url.getClickCount()+1);

        repository.save(url);

        return url;

    }

    public void incrementClicks(URL url){

        url.setClickCount(url.getClickCount()+1);

        repository.save(url);

    }

    private String generateCode(){

        String chars="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        Random random=new Random();

        StringBuilder code=new StringBuilder();

        for(int i=0;i<6;i++){

            code.append(chars.charAt(random.nextInt(chars.length())));

        }

        return code.toString();

    }
   

}