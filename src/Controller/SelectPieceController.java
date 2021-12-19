package Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

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
		System.out.println("got here");
		HashMap<Coordinate,Piece> mymap=model.getPieceswhere();
		
		ArrayList<Coordinate> coords =model.getSelectedCoordinates();
		Coordinate C= new Coordinate((me.getY()-55)/45,((me.getX()-100)/45));
		
		
		System.out.println(coords);
		model.SelectCoordandPiece(C);
		System.out.println(coords);
		
		
		
		
		//for(Coordinate s:mymap.keySet()) {
			//System.out.println("got here");
			//if ((s.getX()*45) +100==me.getY()&&(s.getY()*45)+55==me.getX()) {
		//		System.out.println(mymap.get(s).getName());
			//	System.out.println(mymap.get(s).getColor());
		//	}
	//}
	app.repaint();
	}
	
}


