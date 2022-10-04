package com.movie.ticket.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.movie.ticket.model.LoadFile;
import com.movie.ticket.model.Movie;

public interface MovieService {


	LoadFile downloadFile(String id) throws IOException;

	String addFile(MultipartFile file) throws IOException;

	Movie addMovie(Movie movie, LoadFile file) throws IOException;

}
