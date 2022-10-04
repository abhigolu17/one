package com.movie.ticket.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.movie.ticket.model.Image;
import com.movie.ticket.service.ImageService;

@RestController
public class ImageController {

	@Autowired
	ImageService imageService;

	@PostMapping("/imageAdd")
	public String imageAdd(@RequestParam String title, MultipartFile file) throws IOException {
		imageService.addImages(title, file);
		return "Successfully Uploaded";
	}

	@GetMapping("getImage/{id}")
	public Image getImage(@PathVariable String id) {
		return imageService.getImages(id);
	}
}
