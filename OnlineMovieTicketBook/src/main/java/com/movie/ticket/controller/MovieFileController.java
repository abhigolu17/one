package com.movie.ticket.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.client.gridfs.model.GridFSFile;
import com.movie.ticket.service.MovieFileService;

@RestController
public class MovieFileController {

	@Autowired
	MovieFileService movieFileService;

	@Autowired
	MongoTemplate mongoTemplate;

//	MongoCollection<Document> collection = mongoTemplate.getCollection("fs.files");

	@PostMapping("/uploadImage")
	public String uploadImage(MultipartFile file, @RequestParam String movieID, @RequestParam String movieName,
			@RequestParam String movieType, @RequestParam String movieTicket, @RequestParam String movieTime,
			@RequestParam Integer movieSeat, @RequestParam String movieDate) throws IOException {
		movieFileService.addMovieFile(file, movieID, movieName, movieType, movieTicket, movieTime, movieSeat,
				movieDate);
		return "Successfully Added";
	}

	@GetMapping("/allImgFile")
	public List<GridFSFile> allDetails() throws IOException {

		return movieFileService.allImgFile();
	}

	@PostMapping("/uploadFileDetails")
	public String uploadFileDetails(MultipartFile file, @RequestParam String movieID, @RequestParam String movieName,
			@RequestParam String movieType, @RequestParam String movieTicket, @RequestParam String movieTime,
			@RequestParam Integer movieSeat, @RequestParam String movieDate) throws IOException {
		movieFileService.addFileDetails(file, movieID, movieName, movieType, movieTicket, movieTime, movieSeat,
				movieDate);
		return "Successfully Added";
	}

	@PostMapping("/imageUpload")
	public String imageUpload(MultipartFile file, String movieID, String movieName, String movieType,
			String movieTicket, String movieTime, Integer movieSeat, String movieDate) throws IOException {
		movieFileService.addFile(file, movieID, movieName, movieType, movieTicket, movieTime, movieSeat,
				movieDate);
		return "Successfully Added";
	}

}