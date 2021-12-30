package View;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class HomePage extends JPanel {
	public HomePage() {
		
		BorderLayout Layout = new BorderLayout();
		
		JButton btnNewButton = new JButton("New Game");
		
		
		
		btnNewButton.setLocation(200, 500);
		
		
		add(btnNewButton, BorderLayout.NORTH);
	
		
	}

}
