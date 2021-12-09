package Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import Model.Coordinate;
import Model.Model;
import Model.Piece;
import View.ChessApp;

public class SelectPieceController extends MouseAdapter{
	
	Model model;
	ChessApp app;
	
	
	
	public SelectPieceController(Model model, ChessApp app) {
		this.model=model;
		this.app=app;
	}
	
	@Override 
	public void mousePressed(MouseEvent me) {
		Piece p=model.getSelectedPiece();
	}
	
}
