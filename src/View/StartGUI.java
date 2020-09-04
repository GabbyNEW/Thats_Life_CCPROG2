package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.*;

import Model.MainGame;

public class StartGUI extends JFrame {
	private JFrame frame;
	private JPanel panel, panelNorth, panelSouth, panelCenter;
	private JLabel gameIconLabel, playerNumberText;
	private ImageIcon gameIcon;
	private JComboBox<String> playerNumberBox;
	public JButton startButton;
	
	public StartGUI() {
		// Frame constructor
		frame = new JFrame();
		frame.setSize(450, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setTitle("Start Game");
		
		panel = new JPanel();
		panel.setLayout(new GridBagLayout()); // use rows and columns to place buttons and such
		panel.setBackground(Color.WHITE);
		
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
		playerNumberBox = new JComboBox<String>();
		playerNumberBox.addItem("2");
		playerNumberBox.addItem("3");
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 1;
		panel.add(playerNumberBox, gridConstraints);
		
		// Place start button
		startButton = new JButton("Next");
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 2;
		panel.add(startButton, gridConstraints);
		
		frame.add(panel);
		frame.revalidate();
	}
	
	public void destroy() {
		// Remove starting window
		System.out.println("System: Start GUI frame disposed.");
		frame.dispose();
		panel.removeAll();
	}
	
	public void setListener(ActionListener listener) {
		startButton.addActionListener(listener);
	}
	
	public String getPlayerNumberFieldString() {
		return (String) playerNumberBox.getSelectedItem(); // Returns an object, cast object as String
	}

}
