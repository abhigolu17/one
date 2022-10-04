package com.movie.ticket.service;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.movie.ticket.model.LoadFile;
import com.movie.ticket.model.Movie;
import com.movie.ticket.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	MovieRepository movieRepo;

	@Autowired
	private GridFsTemplate template;

	@Autowired
	private GridFsOperations operations;

	MultipartFile upload = null;
	
	public Movie addMovie(Movie movie, LoadFile file) throws IOException {		

		return mongoTemplate.insert(movie,addFile(upload));
	}

	public String addFile(MultipartFile upload) throws IOException {

		DBObject metadata = new BasicDBObject();
		metadata.put("fileSize", upload.getSize());

		Object fileID = template.store(upload.getInputStream(), upload.getOriginalFilename(), upload.getContentType(),
				metadata);

		return fileID.toString();
	}

	public LoadFile downloadFile(String id) throws IOException {

		GridFSFile gridFSFile = template.findOne(new Query(Criteria.where("_id").is(id)));

		LoadFile loadFile = new LoadFile();

		if (gridFSFile != null && gridFSFile.getMetadata() != null) {
			loadFile.setFilename(gridFSFile.getFilename());

			loadFile.setFileType(gridFSFile.getMetadata().get("_contentType").toString());

			loadFile.setFileSize(gridFSFile.getMetadata().get("fileSize").toString());

			loadFile.setFile(IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream()));
		}

		return loadFile;
	}


//	public String addMovie(Movie movie, LoadFile file) throws IOException {
//		DBObject metadata = new BasicDBObject();
//		metadata.put("fileSize", file.getFileSize());
//		mongoTemplate.insert(movie);
//		 prepareData(parentRawData, rawData, eloc));
//		Object fileID = template.store(upload.getInputStream(), upload.getOriginalFilename(), upload.getContentType(), metadata);
//		return fileID.toString();
//	}
}

//public String addMovie(Movie movie, String movieType, MultipartFile file) throws IOException {
//Movie photo = new Movie(movieType);
//
//photo.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
//photo.setMovieType(file.getContentType());
//photo = mongoTemplate.insert(photo);
//return photo.getMovieID();
//}