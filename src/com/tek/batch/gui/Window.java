package com.tek.batch.gui;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.tek.batch.filters.FlipFilter;
import com.tek.batch.filters.IFilter;
import com.tek.batch.filters.InvertFilter;
import com.tek.batch.filters.RenameFilter;
import com.tek.batch.filters.RotateFilter;
import com.tek.batch.main.BatchImage;
import com.tek.batch.options.FilterParameter;

public class Window {

	public JFrame frmBatcheditor;
	private JTextField browseField;

	public Window() {
		initialize();
	}

	private void initialize() {
		//GUI Generated Code
		
		frmBatcheditor = new JFrame();
		frmBatcheditor.setTitle("BatchEditor");
		frmBatcheditor.setBounds(100, 100, 450, 750);
		frmBatcheditor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBatcheditor.getContentPane().setLayout(null);
		
		browseField = new JTextField();
		browseField.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		browseField.setBounds(15, 16, 261, 40);
		browseField.setEditable(false);
		frmBatcheditor.getContentPane().add(browseField);
		browseField.setColumns(10);
		
		JButton browseButton = new JButton("Browse...");
		browseButton.setBounds(291, 16, 122, 40);
		browseButton.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		frmBatcheditor.getContentPane().add(browseButton);
		
		JList<IFilter> filterList = new JList<IFilter>();
		filterList.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		filterList.setBounds(15, 72, 398, 135);
		filterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		frmBatcheditor.getContentPane().add(filterList);
		
		JComboBox<IFilter> filterCombo = new JComboBox<IFilter>();
		filterCombo.setBounds(15, 223, 261, 40);
		filterCombo.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		frmBatcheditor.getContentPane().add(filterCombo);
		
		JButton filterButton = new JButton("Add Filter");
		filterButton.setBounds(291, 223, 122, 40);
		filterButton.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		frmBatcheditor.getContentPane().add(filterButton);
		
		JScrollPane filterScrollPane = new JScrollPane();
		filterScrollPane.setBounds(15, 279, 398, 345);
		frmBatcheditor.getContentPane().add(filterScrollPane);
		
		JButton exportButton = new JButton("Export Images...");
		exportButton.setBounds(15, 638, 398, 40);
		exportButton.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		frmBatcheditor.getContentPane().add(exportButton);
		
		JPanel filterPane = new JPanel();
		FlowLayout flowLayout = (FlowLayout) filterPane.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		filterScrollPane.setViewportView(filterPane);
		
		//GUI User Code
		
		ArrayList<File> selectedFiles = new ArrayList<File>();
		
		filterList.setCellRenderer(new ListCellRenderer<IFilter>() {
			@Override
			public Component getListCellRendererComponent(JList<? extends IFilter> jlist,
					IFilter filter, int index,
					boolean isSelected, boolean cellHasFocus) {
				JLabel renderer = (JLabel) new DefaultListCellRenderer()
						.getListCellRendererComponent(jlist, filter, index, isSelected, cellHasFocus);
				
				renderer.setText(
					filter.toString()
						+ " ["
						+ filter.getParameters().stream().map(paramete -> paramete.getName() + ": " + paramete.getValue())
							.collect(Collectors.joining(", "))
						+ "]");
				
				return renderer;
			}
		});
		
		DefaultListModel<IFilter> model1 = new DefaultListModel<IFilter>();
		filterList.setModel(model1);
		
		DefaultComboBoxModel<IFilter> model2 = new DefaultComboBoxModel<IFilter>();
		model2.addElement(new FlipFilter());
		model2.addElement(new RotateFilter());
		model2.addElement(new InvertFilter());
		model2.addElement(new RenameFilter());
		filterCombo.setModel(model2);
		
		filterCombo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				IFilter filter = (IFilter) filterCombo.getSelectedItem();
				
				if(filter != e.getItem()) return;
				
				filterPane.removeAll();
				
				for(FilterParameter<?> parameter : filter.getParameters()) {
					JLabel label = new JLabel(parameter.getName() + ":");
					label.setFont(new Font("Segoe UI", Font.PLAIN, 20));
					filterPane.add(label);
					Component component = parameter.getComponent();
					component.setFont(new Font("Segoe UI", Font.PLAIN, 20));
					filterPane.add(component);
					
					filterScrollPane.validate();
					filterScrollPane.repaint();
					filterScrollPane.getViewport().validate();
					filterScrollPane.getViewport().repaint();
					filterPane.validate();
					filterPane.repaint();
				}
			}
		});
		
		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setMultiSelectionEnabled(true);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "png", "jpeg", "jpg");
				chooser.setFileFilter(filter);
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.showOpenDialog(frmBatcheditor);
				File[] files = chooser.getSelectedFiles();
				List<File> images = Arrays.asList(files);
				
				browseField.setText(images.stream().map(File::getName).collect(Collectors.joining(" ")));
				
				selectedFiles.clear();
				selectedFiles.addAll(images);
			}
		});
		
		filterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IFilter filter = (IFilter) filterCombo.getSelectedItem();
				model1.addElement(filter);
				model2.removeElement(filter);
				
				try {
					IFilter newFilter = filter.getClass().newInstance();
					model2.addElement(newFilter);
					filterCombo.setSelectedItem(newFilter);
				}catch(Exception e1) { e1.printStackTrace(); }
			}
		});
		
		exportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectedFiles.isEmpty()) return;		
				
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setCurrentDirectory(new File("."));
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setDialogTitle("Choose a folder");
				int status = chooser.showOpenDialog(frmBatcheditor);
				
				if(status != JFileChooser.APPROVE_OPTION) return;
				
				File selectedFolder = chooser.getSelectedFile();
				
				int i = 0;
				for(File file : selectedFiles) {
					if(file.exists()) {
						try {
							BatchImage batchImage = new BatchImage(file);
							
							i++;
							
							Enumeration<IFilter> filterIter = model1.elements();
							while(filterIter.hasMoreElements()) {
								IFilter filter = filterIter.nextElement();
								filter.apply(i, batchImage);
							}
							
							batchImage.save(selectedFolder);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
				
				JOptionPane.showMessageDialog(frmBatcheditor, "Exported images to " + selectedFolder.getPath());
			}
		});
		
		filterCombo.setSelectedIndex(1);
	}
}
