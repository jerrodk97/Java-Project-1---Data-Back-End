package guiProject;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import java.awt.ItemSelectable;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Jpanel extends JPanel {
	private Predictor predict;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public Jpanel() {
		
		JComboBox allData = new JComboBox();
		predict = new Predictor("","./guiProject/weather.numeric.txt");
		
		setPreferredSize(new Dimension(800, 500));
		setLayout(null);
		setBackground(Color.LIGHT_GRAY);
		
		JLabel lblNewLabel = new JLabel("All Data");
		lblNewLabel.setBounds(103, 24, 46, 14);
		add(lblNewLabel);
		
		JLabel temperature = new JLabel("Temperature");
		temperature.setBounds(270, 83, 126, 23);
		add(temperature);
		
		JLabel CurrentWeather = new JLabel("Current Weather");
		CurrentWeather.setBounds(270, 153, 117, 14);
		add(CurrentWeather);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 41, 242, 207);
		add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setText(predict.toString());
		scrollPane.setViewportView(textArea);
		
		
		JComboBox activities = new JComboBox();
		String[] myActivities = predict.getActivities();
		activities.setModel(new DefaultComboBoxModel(myActivities));
		activities.setBounds(269, 43, 200, 41);
		add(activities);
		
		JSlider tempSlider = new JSlider();
		tempSlider.setSnapToTicks(true);
		tempSlider.setPaintLabels(true);
		tempSlider.setPaintTicks(true);
		tempSlider.setMinorTickSpacing(1);
		tempSlider.setMajorTickSpacing(5);
		tempSlider.setBounds(269, 102, 460, 45);
		add(tempSlider);
	
		
		
		JRadioButton sunnyRadioButton = new JRadioButton("Sunny");
		sunnyRadioButton.setActionCommand("sunny");
		buttonGroup.add(sunnyRadioButton);
		sunnyRadioButton.setSelected(true);
		sunnyRadioButton.setBounds(269, 174, 109, 23);
		add(sunnyRadioButton);
		
		JRadioButton overcastRadioButton = new JRadioButton("Overcast");
		overcastRadioButton.setActionCommand("overcast");
		buttonGroup.add(overcastRadioButton);
		overcastRadioButton.setBounds(269, 200, 109, 23);
		add(overcastRadioButton);
		
		JRadioButton rainyRadioButton = new JRadioButton("Rainy");
		rainyRadioButton.setActionCommand("rainy");
		buttonGroup.add(rainyRadioButton);
		rainyRadioButton.setBounds(269, 226, 109, 23);
		add(rainyRadioButton);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Jpanel.class.getResource("/guiProject/sun.gif")));
		lblNewLabel_1.setBounds(534, 157, 256, 207);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Activities");
		lblNewLabel_2.setBounds(270, 24, 78, 14);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Windy?");
		lblNewLabel_3.setBounds(518, 24, 46, 14);
		add(lblNewLabel_3);
		
		JCheckBox windyCheckBox = new JCheckBox("Yes");
		windyCheckBox.setBounds(508, 43, 56, 23);
		add(windyCheckBox);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(50, 0, 96, 1));
		spinner.setBounds(645, 44, 46, 20);
		add(spinner);
		int spinnerValue = (int) spinner.getValue();
		if(spinnerValue > 100) {
			spinner.setValue(100);
		}
		else {
			spinner.setValue(0);
		}
		

		
		JLabel lblNewLabel_4 = new JLabel("Humidity");
		lblNewLabel_4.setBounds(645, 24, 84, 14);
		add(lblNewLabel_4);
		
		JButton addNewActivity = new JButton("Add New Activity");
		addNewActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String windy = "FALSE";
				String activity = "Tennis";
				int temperature = 0;
				int humidity = 0;
				String weather = buttonGroup.getSelection().getActionCommand();
				if(windyCheckBox.isSelected()) {
					windy = "TRUE";
				}
				activity = (String) activities.getSelectedItem();
				temperature = tempSlider.getValue();
				humidity = (int) spinner.getValue();
				Instance toAdd = new Instance(weather, temperature, humidity, windy, activity);
				//System.out.println(toAdd.toString());
				predict.add(toAdd);
				textArea.setText(predict.toString());
				predict.writeFile();
				//System.out.println(predict.toString());
				
			}
		});
		addNewActivity.setBounds(10, 364, 155, 45);
		add(addNewActivity);
		
		JButton updateCurrentData = new JButton("Update Current Data");
		updateCurrentData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String windy = "FALSE";
				String weather = buttonGroup.getSelection().getActionCommand();
				if(windyCheckBox.isSelected()) {
					windy = "TRUE";
				}
				String activity = (String) activities.getSelectedItem();
				int temperature = tempSlider.getValue();
				int humidity = (int) spinner.getValue();
				//Instance toUpdate = new Instance(weather, temperature, humidity, windy, activity);
				predict.update(weather, temperature, humidity, windy, activity);
				textArea.setText(predict.toString());
				predict.writeFile();
				//System.out.println(predict.toString());
				
			}
		});
		updateCurrentData.setBounds(202, 364, 155, 45);
		add(updateCurrentData);
		
		JButton removeCurrentData = new JButton("Remove Last Line of Data");
		removeCurrentData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String windy = "FALSE";
				String activity = "Tennis";
				int temperature = 0;
				int humidity = 0;
				String weather = buttonGroup.getSelection().getActionCommand();
				if(windyCheckBox.isSelected()) {
					windy = "TRUE";
				}
				activity = (String) activities.getSelectedItem();
				temperature = tempSlider.getValue();
				humidity = (int) spinner.getValue();
				Instance toRemove = new Instance(weather, temperature, humidity, windy, activity);
				//System.out.println(predict.toString());
				//predict.removeLine(toRemove);
				predict.removeThis();
				//System.out.println(predict.toString());
				
				textArea.setText(predict.toString());
				predict.writeFile();
				
			}
		});
		removeCurrentData.setBounds(393, 364, 212, 45);
		add(removeCurrentData);
		
		
	}	
}