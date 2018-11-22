package com.tek.batch.filters;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

import com.tek.batch.main.BatchImage;
import com.tek.batch.options.EnumParameter;
import com.tek.batch.options.FilterParameter;

public class FlipFilter implements IFilter, Cloneable {

	private EnumParameter<FlipDirection> direction;
	
	public FlipFilter() {
		direction = new EnumParameter<FlipDirection>(FlipDirection.HORIZONTAL, "Flip Direction");
	}
	
	@Override
	public String toString() {
		return "Flip";
	}

	@Override
	public List<FilterParameter<?>> getParameters() {
		return Arrays.asList(direction);
	}

	@Override
	public void apply(int i, BatchImage image) {
		BufferedImage img = image.getImage();
		int width = img.getWidth();
		int height = img.getHeight();
		BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				int pixelColor = img.getRGB(x, y);
				if(direction.getValue().equals(FlipDirection.HORIZONTAL)) {
					newImage.setRGB(width - x - 1, y, pixelColor);
				}else if(direction.getValue().equals(FlipDirection.VERTICAL)) {
					newImage.setRGB(x, height - y - 1, pixelColor);
				}
			}
		}
		
		image.setImage(newImage);
	}

	public EnumParameter<FlipDirection> getDirection() {
		return direction;
	}
	
	public enum FlipDirection {
		HORIZONTAL("Horizontal"),
		VERTICAL("Vertical");
		
		private String visible;
		
		private FlipDirection(String visible) {
			this.visible = visible;
		}
		
		public String getVisible() {
			return visible;
		}
		
		@Override
		public String toString() {
			return visible;
		}
	}
	
}
