package com.library.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.library.entity.Image;
import com.library.exception.ImageException;

public interface ImageService {
	
	Long uploadImage(MultipartFile file) throws IOException, ImageException;
	
	Image getImage(String name) throws ImageException;

}
