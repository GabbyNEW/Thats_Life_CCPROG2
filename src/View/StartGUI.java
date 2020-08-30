package View;
import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class StartGUI {
	private JFrame frame;
	private JPanel panel;
	private JLabel playerNumberText; 
	private JTextField playerNumberField;
	private JButton startButton;
	
	public StartGUI() {
		frame = new JFrame();
		frame.setSize(750, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setTitle("Start Game");
		
		panel = new JPanel();
		panel.setLayout(null); // No layout manager, use absolute positioning
		
		playerNumberText = new JLabel("Number of players: ");
		playerNumberText.setBounds(10, 20, 155, 25);
		panel.add(playerNumberText);
		
		playerNumberField = new JTextField();
		playerNumberField.setBounds(175, 20, 80, 25);
		panel.add(playerNumberField);
		
		startButton = new JButton("Start");
		startButton.setBounds(10, 400, 80, 25);
		panel.add(startButton);
		
		frame.add(panel);
	}
	
	public void setListener(ActionListener listener) {
		startButton.addActionListener(listener);
	}
	
	public String getPlayerNumberFieldString() {
		return playerNumberField.getText();
	}

}
