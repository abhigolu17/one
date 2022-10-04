package com.movie.ticket.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.movie.ticket.model.LoadFile;
import com.movie.ticket.model.Movie;
import com.movie.ticket.service.MovieService;

@RestController
public class MoviesController {

	@Autowired
	MovieService movieService;

//	@RequestMapping(value = "addMovie", method = RequestMethod.POST)
//	public ModelAndView addMovie(Movie movie, String movieType, MultipartFile file) throws Exception {
//		movieService.addMovie(movie, movieType, file);
//		return new ModelAndView("adminHome");
//	}

	@RequestMapping(value = "addMovie", method = RequestMethod.POST)
	public ModelAndView addMovie(@RequestPart("movie") Movie movie, @RequestPart("file") LoadFile file)throws Exception {
		movieService.addMovie(movie, file);
		return new ModelAndView("adminHome");
	}
	
	@PostMapping("/upload")
	public ResponseEntity<?> upload(MultipartFile file) throws IOException {
		return new ResponseEntity<String>(movieService.addFile(file), HttpStatus.OK);
	}
	
	
	 @GetMapping("/download/{id}")
	    public ResponseEntity<ByteArrayResource> download(@PathVariable String id) throws IOException {
	        LoadFile loadFile = movieService.downloadFile(id);

	        return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(loadFile.getFileType() ))
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + loadFile.getFilename() + "\"")
	                .body(new ByteArrayResource(loadFile.getFile()));
	    }
	 
	@RequestMapping(value = "upload", method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
		File converFile = new File("/home/ceinfo/Image/Img" + file.getOriginalFilename());
		converFile.createNewFile();
		try(FileOutputStream fout = new FileOutputStream(converFile)){
			fout.write(file.getBytes());
		}catch(Exception exe) {
			exe.printStackTrace();
		}
		return "file has uploaded Successfully";
	}
}

//@RequestMapping(value = "upload", method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
//	File converFile = new File("C:/Users/lenovo/Desktop/fileSave" + file.getOriginalFilename());
//	converFile.createNewFile();
//	try(FileOutputStream fout = new FileOutputStream(converFile)){
//		fout.write(file.getBytes());
//	}catch(Exception exe) {
//		exe.printStackTrace();
//	}
//	return "file has uploaded Successfully";
//}
