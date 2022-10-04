package com.movie.ticket.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.movie.ticket.model.Image;

public interface ImageRepository extends MongoRepository<Image, String>{

}
