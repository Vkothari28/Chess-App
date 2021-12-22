package Model;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Model {
	
	public int score;
	
	public int piececount;
	
	public Board myBoard;
	
	private Piece LastPiece=null;
	private Coordinate LastMovedTo=null;
	public boolean check=false;
	
	private Coordinate KingLocWhite;

	private Coordinate KingLocBlack;
	
	
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
		KingLocWhite= new Coordinate(7,4);
		KingLocBlack= new Coordinate(0,4);
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
	
	public boolean isInCheck() {
		Coordinate incx;
		Coordinate incy;
		Coordinate decx;
		Coordinate decy;
		Coordinate current=KingLocBlack;
		ArrayList<Boolean> checklist= new ArrayList<Boolean>();
		if(whiteMoves) {
		current=KingLocWhite;
		}
		
		for(int i=0;i<=7;i++) { // linear check;
		
			incx=new Coordinate(current.getY(),current.getX()+i);
			decx=new Coordinate(current.getY(),current.getX()-i);
			incy= new Coordinate(current.getY()+i,current.getX());
			decy= new Coordinate(current.getY()-i,current.getX());
			Piece p=PiecesWhere.get(incx);
			
			if(incx.getX()<=7&&containsPiece(incx)&&((p.getColor()==Color.WHITE)!=whiteMoves)) {
			
			
			
				checklist.add(myBoard.canMove(p,incx,current)&&p.getColor().equals(Color.WHITE)==whiteMoves);
		}
			
			p=PiecesWhere.get(decx);
			if(decx.getX()>=0&&containsPiece(decx)&&((p.getColor()==Color.WHITE)!=whiteMoves)) {
				
				
				checklist.add(myBoard.canMove(p, decx, current)&&p.getColor().equals(Color.WHITE)==whiteMoves);
				
			}
			
		}
		
	
			
		
	if(checklist.contains(true)) {
		
		return true;
	}
	
	return false;
	
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
		
		
//		if(selectedCoords.size()==2) {
//			move(selectedCoords.get(0), selectedCoords.get(1));
//		}
		
		
		if(selectedCoords.size()==0 &&PiecesWhere.get(Coord)!=null&&(PiecesWhere.get(Coord).getColor()==Color.WHITE)==whiteMoves) {
			System.out.println("madarchod");
			if (LastPiece!=null) {
				
			}
			//System.out.println(LastPiece);
			if((LastPiece==null||!isInCheck())) {
			selectedCoords.add(Coord);
			SelectedPiece=PiecesWhere.get(Coord);
			
		}
			else if((isInCheck()&&PiecesWhere.get(Coord).getName().contains("King"))){
			
				selectedCoords.add(Coord);
				SelectedPiece=PiecesWhere.get(Coord);
				
			}
			
		}
			
				
			

		
		else if(selectedCoords.size()==1) {
			if(PiecesWhere.get(Coord)!=null &&((PiecesWhere.get(Coord).getColor()==Color.WHITE)==whiteMoves)) {
				selectedCoords.clear();
				SelectedPiece=PiecesWhere.get(Coord);
				selectedCoords.add(Coord);
			
			
			}
			
		
			
			
			else if(PiecesWhere.get(Coord)==null) {
				selectedCoords.add(Coord);
			}
			
			
			else {
				selectedCoords.add(Coord);
				
			}
			
			
			
		
	}
		
				}
	
	



	public void move(Coordinate from, Coordinate to) {
		
		
		//SelectedPiece= PiecesWhere.get(from);
		
		
		
		
		if(SelectedPiece==null) {
			return;
		}
		
		
		
		if ((selectedCoords.size()==2&&SelectedPiece.color==Color.WHITE)==whiteMoves) {
			if(myBoard.canMove(SelectedPiece, from, to)) {
				
				if(PiecesWhere.get(to)!=null) {
					PiecesWhere.remove(to);
				}
				
				PiecesWhere.remove(from);
				PiecesWhere.put(to, SelectedPiece);
				myBoard.update(PiecesWhere);
				whiteMoves= !whiteMoves;
				selectedCoords.clear();
				LastPiece=SelectedPiece;
				LastMovedTo=to;
				SelectedPiece=null;
				
				
				
			}
			
			else {
				selectedCoords.remove(1);
				
			}
			
			
			
			
			
		}
		
		   
				
		
		
	}
	
	public Board getBoard() {
		return myBoard;
	}

}
