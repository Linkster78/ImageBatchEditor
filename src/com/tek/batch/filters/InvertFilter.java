package com.tek.batch.filters;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

import com.tek.batch.main.BatchImage;
import com.tek.batch.options.FilterParameter;

public class InvertFilter implements IFilter, Cloneable {

	@Override
	public String toString() {
		return "Invert Colors";
	}

	@Override
	public List<FilterParameter<?>> getParameters() {
		return Arrays.asList();
	}

	@Override
	public void apply(int i, BatchImage image) {
		BufferedImage img = image.getImage();
		
		for(int x = 0; x < img.getWidth(); x++) {
			for(int y = 0; y < img.getHeight(); y++) {
				Color clr = new Color(img.getRGB(x, y), true);
				Color newClr = new Color(255 - clr.getRed(), 255 - clr.getGreen(), 255 - clr.getBlue(), clr.getAlpha());
				img.setRGB(x, y, newClr.getRGB());
			}
		}
	}

}
