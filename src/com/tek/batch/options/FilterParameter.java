package com.tek.batch.options;

import java.awt.Component;

public abstract class FilterParameter<T> {
	
	protected T value;
	private String name;
	
	public FilterParameter(T defaultValue, String name) {
		this.value = defaultValue;
		this.name = name;
	}
	
	public abstract Component getComponent();
	
	public void setValue(T t) {
		this.value = t;
	}
	
	public T getValue() {
		return this.value;
	}
	
	public String getName() {
		return name;
	}
	
}
