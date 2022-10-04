package com.movie.ticket.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.movie.ticket.model.Image;

public interface ImageService {

	String addImages(String title, MultipartFile file) throws IOException;

	Image getImages(String id);

}
