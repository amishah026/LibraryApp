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

	// Method for uploading an image
	@Override
	public Long uploadImage(MultipartFile file) throws IOException, ImageException {
		// Check if an image with the same name already exists in the repository
		if (imageRepository.findByName(file.getOriginalFilename()).isPresent()) {
			throw new ImageException("Image.ALREADY_PRESENT");
		}

		// Create a new Image object
		Image image = new Image();
		image.setName(file.getOriginalFilename());
		// Compress the image data before storing it
		image.setImageData(compressBytes(file.getBytes()));

		// Save the compressed image to the repository
		Image img = imageRepository.save(image);

		return img.getId();
	}

	// Method for retrieving an image by name
	@Override
	public Image getImage(String name) throws ImageException, DataFormatException, IOException {
		// Find the image by name in the repository, or throw an exception if not found
		Image image = imageRepository.findByName(name).orElseThrow(() -> new ImageException("Image.NOT_FOUND"));

		// Create a new Image object to hold the decompressed data
		Image image2 = new Image();
		image2.setId(image.getId());
		image2.setName(image.getName());
		// Decompress the image data before returning it
		image2.setImageData(decompressBytes(image.getImageData()));

		return image2;
	}

	// Method to compress a byte array using Deflater
	public static byte[] compressBytes(byte[] data) throws IOException {
		// Create a Deflater to compress the data
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();

		// Create a ByteArrayOutputStream to store the compressed data
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];

		// Compress the data in chunks and write it to the output stream
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}

		// Close the output stream
		outputStream.close();

		// Return the compressed data as a byte array
		return outputStream.toByteArray();
	}

	// Method to decompress a byte array using Inflater
	public static byte[] decompressBytes(byte[] data) throws DataFormatException, IOException {
		// Create an Inflater to decompress the data
		Inflater inflater = new Inflater();
		inflater.setInput(data);

		// Create a ByteArrayOutputStream to store the decompressed data
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];

		// Decompress the data in chunks and write it to the output stream
		while (!inflater.finished()) {
			int count = inflater.inflate(buffer);
			outputStream.write(buffer, 0, count);
		}

		// Close the output stream
		outputStream.close();

		// Return the decompressed data as a byte array
		return outputStream.toByteArray();
	}

}
