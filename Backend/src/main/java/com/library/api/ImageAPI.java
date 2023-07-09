package com.library.api;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.library.entity.Image;
import com.library.exception.ImageException;
import com.library.service.ImageService;

@RestController
@RequestMapping(value = "/image")
public class ImageAPI {

	@Autowired
	ImageService imageService;
	
	@Autowired
	Environment environment;
	
	@CrossOrigin(origins = "http://localhost:4200")
	 @PostMapping(value="/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	    public ResponseEntity<String> uploadImage(@RequestParam("imgfile") MultipartFile file) throws IOException, ImageException {
		System.out.println(file.getBytes().length);
	        Long id =  imageService.uploadImage(file);
	        String message = environment.getProperty("IMGAPI.upload_success") + " " + id;
	        return new ResponseEntity<>(message,HttpStatus.CREATED);
	    }
	
	@CrossOrigin(origins = "http://localhost:4200") 
	 @GetMapping("/{name}")
	    public ResponseEntity<Image>  getImageByName(@PathVariable("name") String name) throws ImageException{
	        Image image = imageService.getImage(name);

	        return new ResponseEntity<>(image, HttpStatus.OK);
	    }

	
	
}
