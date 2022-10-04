package com.movie.ticket.service;

import java.io.IOException;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.movie.ticket.model.Image;
import com.movie.ticket.repository.ImageRepository;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageRepository imageRepo;

	public String addImages(String title, MultipartFile file) throws IOException {
		Image photo = new Image();
		photo.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
		photo = imageRepo.insert(photo);
		return photo.getId();
	}

	public Image getImages(String id) {
		return imageRepo.findById(id).get();
	}
}
