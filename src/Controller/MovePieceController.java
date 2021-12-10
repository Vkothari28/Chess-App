package Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import Model.Coordinate;
import Model.Model;
import View.ChessApp;

public class MovePieceController extends MouseAdapter {
	
	Model model;
	ChessApp app;
	public MovePieceController(Model model, ChessApp app) throws IOException {
		this.model=model;
		this.app=app;
		
		
	}
	
	
	
	 @Override
		public void mousePressed(MouseEvent me) {
		 System.out.println("Clicked on"+me.getX());
		 
			 
		
		
		
	}

}
