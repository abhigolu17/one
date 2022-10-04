package com.movie.ticket.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.movie.ticket.model.MovieFile;

public interface PhotoRepository extends MongoRepository<MovieFile, String>{

}
