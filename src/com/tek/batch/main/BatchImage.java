package com.tek.batch.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BatchImage {
	
	private String name;
	private BufferedImage image;
	
	public BatchImage(File file) throws IOException {
		name = file.getName();
		image = ImageIO.read(file);
	}
	
	public void save(File directory) throws IOException {
		ImageIO.write(image, "png", new File(directory.getPath() + "\\" + name + ".png"));
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
}
