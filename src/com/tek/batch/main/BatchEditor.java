package com.tek.batch.main;

import java.awt.EventQueue;

import com.tek.batch.gui.Window;

public class BatchEditor {
	
	private Window window;
	
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frmBatcheditor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Window getWindow() {
		return window;
	}
	
}
