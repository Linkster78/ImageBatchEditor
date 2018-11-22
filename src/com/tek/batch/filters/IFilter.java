package com.tek.batch.filters;

import java.util.List;

import com.tek.batch.main.BatchImage;
import com.tek.batch.options.FilterParameter;

public interface IFilter {
	
	public List<FilterParameter<?>> getParameters();
	public void apply(int i, BatchImage image);
	
}
