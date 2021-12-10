package Model;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Model {
	
	public int score;
	
	public int piececount;
	
	public Board myBoard;
	
	
	HashMap<Coordinate, Piece>PiecesWhere;
	ArrayList<Coordinate> selectedCoords= new ArrayList<Coordinate>();
	Piece SelectedPiece;
	
	public boolean whiteMoves;
	
	
	public Model() throws IOException {
		this.score=0;
		this.piececount=16;
		this.myBoard=new Board();
		whiteMoves=true;
		PiecesWhere= myBoard.getPositions();
	}
	
	public HashMap<Coordinate, Piece> getPieceswhere(){
		return this.PiecesWhere;
	}
	public ArrayList<Coordinate> getSelectedCoordinates(){
		return this.selectedCoords;
	}
	public Piece getSelectedPiece() {
		return this.SelectedPiece;
	}
	public void setscore(int s) {
		score=s;
	}
	
	public void capturePiece() {
		piececount--;
	}
	
	public void selectCoord(Coordinate coord) {
		if(selectedCoords.isEmpty()) {
			
		
		if(containsPiece(coord) && ((PiecesWhere.get(coord).color==Color.WHITE)==whiteMoves)) {
			selectedCoords.add(coord);
			SelectedPiece= PiecesWhere.get(coord);
			
		}
		}
		
		else if(!selectedCoords.contains(coord)) {
			selectedCoords.add(coord);
			
		}
		}
	
	
	
	private boolean containsPiece(Coordinate coord) {
		
		if(PiecesWhere.get(coord)!=null) {
			return true;
		}
		return false;
	}
	
	public void SelectCoordandPiece(Coordinate Coord){
		
		if ((PiecesWhere.get(Coord).getColor()==Color.WHITE)==whiteMoves){
		
		if(selectedCoords.size()==0 &&PiecesWhere.get(Coord)!=null) {
			selectedCoords.add(Coord);
			SelectedPiece=PiecesWhere.get(Coord);
			
		}
		
		else if(selectedCoords.size()==1) {
			if(PiecesWhere.get(Coord)!=null&&((PiecesWhere.get(Coord).getColor().equals(Color.WHITE))==whiteMoves)) {
				selectedCoords.clear();
				SelectedPiece=PiecesWhere.get(Coord);
				selectedCoords.add(Coord);
			}
			
			else {
				
				selectedCoords.add(Coord);
			}
		
	}
		}
	}
	



	public void move(Coordinate from, Coordinate to) {
		SelectedPiece= PiecesWhere.get(from);
		if(SelectedPiece==null) {
			return;
		}
		
		if ((SelectedPiece.color==Color.WHITE)==whiteMoves) {
			if(myBoard.canMove(SelectedPiece, from, to)) {
				PiecesWhere.remove(from);
				PiecesWhere.put(to, SelectedPiece);
				myBoard.update(PiecesWhere);
				whiteMoves= !whiteMoves;
			}
			
			
		}
		   
			
		
		
	}
	
	public Board getBoard() {
		return myBoard;
	}

}
