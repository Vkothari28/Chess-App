package View;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import javax.swing.*;

import Model.Coordinate;
import Model.Model;
import Model.Piece;

import static javax.swing.JOptionPane.showMessageDialog;
import java.util.Observable;
public class Puzzle_drawer extends JPanel  {
	
	Model model;
	int count=0;
	
	public Puzzle_drawer(Model model) {
		this.model=model;
	}
		
	/**
	 * Create the panel.
	 */
	
	
	
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		if(model==null) {return;}

		HashMap<Coordinate,Piece> myPieces =model.getPieceswhere();
		ArrayList<Coordinate> selectedCoords = model.getSelectedCoordinates();
		Piece selectedPiece=model.getSelectedPiece();
		ArrayList<Coordinate> coordlist= new ArrayList<Coordinate>();
		
		int last=0;
	
		boolean change=true;
		for(int i =0;i<8;i++) {
			for (int j=0;j<8;j++) {
				Coordinate c= new Coordinate(i, j);
				
				
				
				c.setColor(change);
				coordlist.add(c);
				change=!change;
				
					
				if(c.getColor()==Color.WHITE) {
				g.setColor(c.getColor().brighter().darker());
				}
				else {
					g.setColor(Color.GRAY);
				}
				  
				g.fillRect(((c.getX()*45)+100), (c.getY()*45)+55,50,50);
				
				
				
				
				
				
				last+=1;
				
				
				if(last>7) {
					change=!change;
					last=0;
				}
				
				if(myPieces.get(c)!=null) {
					Piece p=myPieces.get(c);
					
				
					Image newImage = p.getImage().getScaledInstance(45, 45, Image.SCALE_DEFAULT);
					
					g.drawImage(newImage, (c.getX()*45)+100,  (c.getY()*45)+55, null);
					/*if(p.getColor()==Color.BLACK) {
						g.setColor(Color.MAGENTA);
					}
					else {
						g.setColor(Color.ORANGE.darker());
					}*/
					//g.setFont(new Font("TimesRoman", Font.PLAIN, 15)); 
					
					//g.drawString(""+myPieces.get(c).getName().charAt(0)+myPieces.get(c).getName().charAt(1), (c.getX()*30)+150, (c.getY()*30)+67);
					
					if(model.getSelectedPiece()!=null&&model.getSelectedPiece().equals(myPieces.get(c))) {
						g.setColor(Color.red);
						g.drawRect((c.getX()*45)+100,c.getY()*45+55, 42, 46);
						
					}
					
					if(model.getSelectedCoordinates().size()==2) {
						model.move(model.getSelectedCoordinates().get(0), model.getSelectedCoordinates().get(1));
						
						
					}
					
				}
				
				if(model.isInCheck()) {
					System.out.println("check");
					
				}
				
				
				
			
		}
		}
		
		
		
		
	}
	
	
}

		



		// do the math and figure out WHERE to draw all the boxes (nodes) lines (edges)
		
		// DRAW ENTIRE PUZZLE HERE...
		
		





/*public void paintComponent(Graphics g) {
		
		g.setFont(new Font("Comic Sans MS",Font.PLAIN,40));
		//g.drawString(""+ 18, 100, 100);
	if(model==null) {return;}	
	for(int checker=0; checker<9;checker++) {
	Tile t =model.getTile(checker);
	int num = t.VisibleDigit();
	Location loc = t.getLocation();
	
	int x= 100*loc.row;
	int y= 100*loc.col;
	g.setColor(t.getColor());
	g.fillRect(x, y, 99, 99);
	g.setColor(t.digitColor());
	g.drawString(""+ num, x+35, y+70);
	
	
	}
	*/
	
	
	
	
	
	


