package View;
import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class StartGUI {
	private JFrame frame;
	private JPanel panel;
	private JLabel gameIconLabel, playerNumberText;
	private ImageIcon gameIcon;
	private JTextField playerNumberField;
	private JButton startButton;
	
	public StartGUI() {
		// Frame constructor
		frame = new JFrame();
		frame.setSize(450, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setTitle("Start Game");
		
		panel = new JPanel();
		panel.setLayout(new GridBagLayout()); // use rows and columns to place buttons and such
		
		// Create GridBagConstraints instance
		GridBagConstraints gridConstraints = new GridBagConstraints();
		gridConstraints.weightx = 0.2;
		gridConstraints.weighty = 0.2;
		
		// Place logo image
		gridConstraints.insets = new Insets(25, 0, 0, 0); // Pad logo only for that grid
		gridConstraints.gridwidth = 2;
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 0;
		gridConstraints.anchor = GridBagConstraints.PAGE_START;
		gameIcon = new ImageIcon("logo.jpg");
		gameIconLabel = new JLabel();
		gameIconLabel.setIcon(gameIcon);
		panel.add(gameIconLabel, gridConstraints);
		
		// Place text label "Number of players:"
		playerNumberText = new JLabel("Number of players: ");
		gridConstraints.gridwidth = 1; // Reset to default
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 1;
		panel.add(playerNumberText, gridConstraints);
		
		// Place text field left of "Number of players:"
		playerNumberField = new JTextField();
		playerNumberField.setColumns(4);
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 1;
		panel.add(playerNumberField, gridConstraints);
		
		
		startButton = new JButton("Start");
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 2;
		gridConstraints.anchor = GridBagConstraints.CENTER;
		panel.add(startButton, gridConstraints);
		
		frame.add(panel);
	}
	
	public void setListener(ActionListener listener) {
		startButton.addActionListener(listener);
	}
	
	public String getPlayerNumberFieldString() {
		return playerNumberField.getText();
	}

}
