package com.tek.batch.options;

import java.awt.Component;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class RangedParameter extends FilterParameter<Integer> {

	private JSpinner spinner;
	private int min, max;
	
	public RangedParameter(int defaultValue, int min, int max, String name) {
		super(defaultValue, name);
		this.min = min;
		this.max = max;
		
		spinner = new JSpinner();
		((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setColumns(4);
		spinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int num = (int)spinner.getValue();
				
				if(num < min) num = min;
				if(num > max) num = max;
				
				spinner.setValue(num);
				setValue(num);
			}
		});
	}
	
	@Override
	public Component getComponent() {
		return spinner;
	}
	
	@Override
	public void setValue(Integer t) {
		if(t > max) t = max;
		if(t < min) t = min;
		this.value = t;
	}

}
