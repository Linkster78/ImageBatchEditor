package com.tek.batch.options;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

public class BooleanParameter extends FilterParameter<Boolean> {

	private JCheckBox checkBox;
	
	public BooleanParameter(Boolean defaultValue, String name) {
		super(defaultValue, name);
		
		this.checkBox = new JCheckBox();
		this.checkBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setValue(checkBox.isSelected());
			}
		});
	}

	@Override
	public Component getComponent() {
		return checkBox;
	}

}
