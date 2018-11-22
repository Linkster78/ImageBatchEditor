package com.tek.batch.filters;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

import com.tek.batch.main.BatchImage;
import com.tek.batch.options.FilterParameter;
import com.tek.batch.options.RangedParameter;

public class RotateFilter implements IFilter, Cloneable {

	private RangedParameter rotationAngle;
	
	public RotateFilter() {
		rotationAngle = new RangedParameter(0, 0, 360, "Angle");
	}
	
	@Override
	public String toString() {
		return "Rotate";
	}

	@Override
	public List<FilterParameter<?>> getParameters() {
		return Arrays.asList(rotationAngle);
	}
	
	@Override
	public void apply(int i, BatchImage image) {
		float width = image.getImage().getWidth();
		float height = image.getImage().getHeight();
		float angle = rotationAngle.getValue();
		float w1 = (float) (Math.abs(Math.sin(Math.toRadians(angle)) * height));
		float w2 = (float) (Math.abs(Math.cos(Math.toRadians(angle)) * width));
		int wt = (int) Math.ceil(w1 + w2);
		float h1 = (float) (Math.abs(Math.sin(Math.toRadians(angle)) * width));
		float h2 = (float) (Math.abs(Math.cos(Math.toRadians(angle)) * height));
		int ht = (int) Math.ceil(h1 + h2);
		
		BufferedImage oldImage = image.getImage();
		BufferedImage newImage = new BufferedImage(wt, ht, BufferedImage.TYPE_INT_ARGB);
		Graphics2D newImageGraphics = (Graphics2D) newImage.getGraphics();
		
		AffineTransform at = new AffineTransform();
		at.translate(wt / 2, ht / 2);
		at.rotate(Math.toRadians(angle));
		at.translate(-width/2, -height/2);
		newImageGraphics.drawImage(oldImage, at, null);
		newImageGraphics.dispose();
		
		image.setImage(newImage);
	}
	
	public RangedParameter getRotationAngle() {
		return rotationAngle;
	}

}
