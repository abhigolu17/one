package com.movie.ticket.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
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
import com.movie.ticket.model.MovieFile;
import com.movie.ticket.repository.MovieRepository;
import com.movie.ticket.repository.PhotoRepository;

@Service
public class MovieFileServiceImpl implements MovieFileService {

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	MovieRepository movieRepo;

	@Autowired
	GridFsTemplate template;

	@Autowired
	GridFsOperations operations;

	@Autowired
	PhotoRepository photoRepo;

	MovieFile movieFile;

	DBObject metadata = new BasicDBObject();

	public String addFile(MultipartFile file, String movieID, String movieName, String movieType,
			String movieTicket, String movieTime, Integer movieSeat, String movieDate) throws IOException {
		
		
		metadata.put("movieID", movieID);
		metadata.put("movieName", movieName);
		metadata.put("movieTicket", movieTicket);
		metadata.put("movieType", movieType);
		metadata.put("movieTime", movieTime);
		metadata.put("movieSeat", movieSeat);
		metadata.put("movieDate", movieDate);

		
		
		Object fileID = template.store(file.getInputStream(), file.getOriginalFilename(),metadata);
		
		return fileID.toString();
	}
	
	public MovieFile movieDetails(MovieFile movie) {
		return mongoTemplate.insert(movie);		
	}

	public String addMovieFile(MultipartFile file, String movieID, String movieName, String movieType,
			String movieTicket, String movieTime, Integer movieSeat, String movieDate) throws IOException {

		metadata.put("movieID", movieID);
		metadata.put("movieName", movieName);
		metadata.put("movieTicket", movieTicket);
		metadata.put("movieType", movieType);
		metadata.put("movieTime", movieTime);
		metadata.put("movieSeat", movieSeat);
		metadata.put("movieDate", movieDate);

		Object fileID = template.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType(),
				metadata);

		return fileID.toString();
	}

	public List<GridFSFile> allImgFile() throws IOException {

		List<GridFSFile> fileList = new ArrayList<GridFSFile>();
		
		//List<GridFSFile> result =template.find(new Query(Criteria.where("metadata.movieID").is("abhi3333"))).into(fileList);
		List<GridFSFile> result =operations.find(new Query()).into(fileList);
		return result;
	}

	public Document addFileDetails(MultipartFile file, String movieID, String movieName, String movieType,
			String movieTicket, String movieTime, Integer movieSeat, String movieDate) throws IOException {

		// List<WriteModel<BasicDBObject>> bulkInsertData = new
		// ArrayList<WriteModel<BasicDBObject>>();

		Object fileID = template.store(file.getInputStream(), file.getOriginalFilename());

		Document bulk = new Document("movieId", movieID).append("movieName", movieName)
				.append("movieTicket", movieTicket).append("movieTime", movieTime).append("movieSeat", movieSeat)
				.append("movieDate", movieDate).append("image", fileID);

		return mongoTemplate.insert(bulk);
	}

}

//data.get("movieID");
//data.get("movieName");
//data.get("movieTicket");
//data.get("movieType");
//data.get("totalSeat");
//data.get("date");
//data.get("time");
//data.get("fileSize");
//data.get("file");
