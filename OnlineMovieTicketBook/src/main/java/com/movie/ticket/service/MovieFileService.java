package com.movie.ticket.service;

import java.io.IOException;
import java.util.List;

import org.bson.Document;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.client.gridfs.model.GridFSFile;

public interface MovieFileService {

	String addMovieFile(MultipartFile file, String movieID, String movieName, String movieType, String movieTicket,
			String movieTime, Integer movieSeat, String movieDate) throws IOException;

	Document addFileDetails(MultipartFile file, String movieID, String movieName, String movieType, String movieTicket,
			String movieTime, Integer movieSeat, String movieDate) throws IOException;

	List<GridFSFile> allImgFile() throws IOException;

	String addFile(MultipartFile file, String movieID, String movieName, String movieType, String movieTicket,
			String movieTime, Integer movieSeat, String movieDate) throws IOException;
}