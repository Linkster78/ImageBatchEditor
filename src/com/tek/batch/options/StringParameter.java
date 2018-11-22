package com.tek.batch.options;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class StringParameter extends FilterParameter<String> {

	private JTextField textField;
	
	public StringParameter(String defaultValue, String name) {
		super(defaultValue, name);
		
		this.textField = new JTextField();
		this.textField.setColumns(10);
		this.textField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				setValue(textField.getText());
			}

			@Override
			public void keyPressed(KeyEvent e) { 
				setValue(textField.getText());
			}

			@Override
			public void keyReleased(KeyEvent e) {
				setValue(textField.getText());
			}
		});
	}

	@Override
	public Component getComponent() {
		return textField;
	}

}
