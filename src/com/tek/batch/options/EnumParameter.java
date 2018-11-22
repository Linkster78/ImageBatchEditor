package com.tek.batch.options;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class EnumParameter<T extends Enum<T>> extends FilterParameter<T>{

	private JComboBox<T> comboBox;
	
	public EnumParameter(T defaultValue, String name) {
		super(defaultValue, name);
		
		this.comboBox = new JComboBox<T>();
		
		DefaultComboBoxModel<T> model = new DefaultComboBoxModel<T>();
		
		try {
			Class<?> enumClass = defaultValue.getDeclaringClass();
			for(Object obj : enumClass.getEnumConstants()) {
				@SuppressWarnings("unchecked")
				T t = (T)obj;
				
				model.addElement(t);
			}
		}catch(Exception e) { e.printStackTrace(); }
		
		comboBox.setModel(model);
		
		this.comboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				@SuppressWarnings("unchecked")
				T selected = (T) comboBox.getSelectedItem();
				
				if(e.getItem() != selected) return;
				
				setValue(selected);
			}
		});
	}

	@Override
	public Component getComponent() {
		return comboBox;
	}

}
