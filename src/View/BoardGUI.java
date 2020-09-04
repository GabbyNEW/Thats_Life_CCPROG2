package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.*;

import Model.MainGame;

public class BoardGUI {
	
	private JFrame frame;
	private JPanel panel, panelNorth, panelSouth;
	private BoardArt panelMid;
	private JLabel[] balance, loan, interest, tax, salary, raiseCurrent, raiseMax;

	public BoardGUI() {
		frame = new JFrame();
		panel = new JPanel(new GridBagLayout());
		frame.setSize(1336, 900);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Set the state of the frame maximized
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setName("That's Life!");
		
		panelNorth = new JPanel(new GridBagLayout());
		GridBagConstraints gridConstraints = new GridBagConstraints();
		gridConstraints.weightx = 0.2;
		gridConstraints.weighty = 0.2;
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 0;
		gridConstraints.ipady = 20;
		gridConstraints.fill = GridBagConstraints.BOTH;
		gridConstraints.gridwidth = GridBagConstraints.REMAINDER;
		setupPanelNorth();
		panel.add(panelNorth, gridConstraints);
		
		panelMid = new BoardArt();
		gridConstraints.gridy = 1;
		gridConstraints.ipady = 500;
		panel.add(panelMid, gridConstraints);
		panelMid.repaint();
		
		panelSouth = new JPanel(new GridBagLayout());
		gridConstraints.gridy = 2;
		gridConstraints.ipady = 20;
		panel.add(new JButton("TEST SOUTH"), gridConstraints);
		
		frame.add(panel);
		frame.revalidate();
	}
	
	private void setupPanelNorth() {
		instantiateControlPanelNorth();
		GridBagConstraints gridConstraints = new GridBagConstraints();
		gridConstraints.fill = GridBagConstraints.BOTH;
		gridConstraints.weightx = 0.5;
		gridConstraints.weighty = 0.5;
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 0;
		panelNorth.add(new JLabel("Player 1"), gridConstraints);
		gridConstraints.gridx = 4;
		panelNorth.add(new JLabel("Player 2"), gridConstraints);
		gridConstraints.gridx = 7;
		panelNorth.add(new JLabel("Player 3"), gridConstraints);
		
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 1;
		balance[0].setText("Balance: $" + "200000.00");
		panelNorth.add(balance[0], gridConstraints);
		
	}
	
	private void instantiateControlPanelNorth() {
		balance = new JLabel[3];
		for (int i = 0; i < 3; i++) {
			balance[i] = new JLabel();
			loan[i] = new JLabel();
			interest[i] = new JLabel();
			tax[i] = new JLabel();
			salary[i] = new JLabel();
			raiseCurrent[i] = new JLabel();
			raiseMax[i] = new JLabel();
		}
	}
	
	public void setListener(ActionListener listener) {
		
	}
}
