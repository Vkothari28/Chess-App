package Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import Model.Coordinate;
import Model.Model;
import View.ChessApp;

public class MovePieceController  {
	
	Model model;
	ChessApp app;
	public MovePieceController(Model model, ChessApp app) throws IOException {
		this.model=model;
		this.app=app;
		
		
	}
	
	
	
	
		public void MovePiece() {
		 ArrayList<Coordinate> mysel= model.getSelectedCoordinates();
		 
			 
		
		 if(mysel.size()>1) {
			 model.move(mysel.get(0), mysel.get(1));
		 }
		
		
		 
		 app.repaint();
	}

}
