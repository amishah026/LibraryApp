package com.library.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.library.entity.Image;
import com.library.exception.ImageException;
import com.library.repository.ImageRepository;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {
	
	@Autowired
	ImageRepository imageRepository;

	@Override
	public Long uploadImage(MultipartFile file) throws IOException, ImageException {
		if(imageRepository.findByName(file.getOriginalFilename()).isPresent()) {
			throw new ImageException("Image.ALREADY_PRESENT");
		}
	
		Image image = new Image();
		image.setName(file.getOriginalFilename());
		image.setImageData(compressBytes(file.getBytes()));
		
		
		Image img = imageRepository.save(image);

		return img.getId();
	}

	@Override
	public Image getImage(String name) throws ImageException {
		
		Image image=imageRepository.findByName(name).orElseThrow(()-> new ImageException("Image.NOT_FOUND"));
		Image image2 = new Image();
		image2.setId(image.getId());
		image2.setName(image.getName());
		image2.setImageData(decompressBytes(image.getImageData()));
		
		return image2;
	}
	
	
	public static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

		return outputStream.toByteArray();
	}
	
	public static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		return outputStream.toByteArray();
	}

}
