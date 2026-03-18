package com.example.urlshortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.urlshortener.model.URL;

import java.util.Optional;

public interface URLRepository extends JpaRepository<URL, Long> {

    Optional<URL> findByShortCode(String shortCode);

}