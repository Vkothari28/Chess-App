package View;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class HomePage extends JPanel {
	JButton btnNewButton ;
	public HomePage() {
		
		GridLayout gridlayout= new GridLayout();
		
		
		
		
		
	
	}
	
	
	
	@Override
	public void paintComponent(Graphics g) {
		
		BufferedImage myPicture;
		try {
			myPicture = ImageIO.read(new File("path-to-file"));
			g.drawImage(myPicture, 200, 200, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	}
		
	
	
	
	


