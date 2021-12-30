package Model;

import static org.junit.Assert.assertNotNull;

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
	
public boolean KnightCheck(Coordinate coord,Color co) {
		
		int i=1;
		int j=2;
		
		Coordinate UpAndRight= new Coordinate(coord.getY()-j,coord.getX()+i);
		
		Coordinate UpAndLeft= new Coordinate(coord.getY()-j,coord.getX()-i);
		Coordinate DownAndLeft =new Coordinate(coord.getY()+j,coord.getX()-i);
		Coordinate DownAndRight= new Coordinate(coord.getY()+j,coord.getX()+i);
		
		Coordinate RightAndDown=new Coordinate(coord.getY()+i,coord.getX()+j);
		
		Coordinate RightAndUp=new Coordinate(coord.getY()-i,coord.getX()+j);
		
		Coordinate LeftAndUp= new Coordinate(coord.getY()-i,coord.getX()-j);
		Coordinate LeftAndDown=new Coordinate(coord.getY()+i,coord.getX()-j);
		
		ArrayList<Coordinate> mylist= new ArrayList<Coordinate>();
		mylist.add(UpAndLeft);
		mylist.add(UpAndRight);
		mylist.add(DownAndLeft);
		mylist.add(DownAndRight);
		mylist.add(RightAndUp);
		mylist.add(RightAndDown);
		mylist.add(LeftAndUp);
		mylist.add(LeftAndDown);
	
		
		for(int a=0;i<mylist.size();i++) {
			
		Piece p=	PiecesWhere.get(mylist.get(a));
			if(p!=null &&p.getColor()!=co && p.getName().contains("Knight")) {
				return true;
			}
			
		}
		
		return false;
	}
	
	
	public boolean isInCheck() {
		Coordinate incx;
		Coordinate incy;
		Coordinate decx;
		Coordinate decy;
		boolean horizCheck=false;
		boolean verticCheck=false;
		
		Coordinate current=KingLocBlack;
		ArrayList<Boolean> checklist= new ArrayList<Boolean>();
		if(whiteMoves) {
		current=KingLocWhite;
		}
		boolean knightCheck=KnightCheck(current, PiecesWhere.get(current).getColor());
		for(int i=0;i<=7;i++) { 
		
			incx=new Coordinate(current.getY(),current.getX()+i);
			decx=new Coordinate(current.getY(),current.getX()-i);
			
			Piece p=PiecesWhere.get(incx);
			
			if(horizCheck) {
				break;
			}
			
			
			
			if(incx.getX()<=7&&containsPiece(incx)&&((p.getColor()==Color.WHITE)!=whiteMoves)&&horizCheck!=true) {
			
			
			if(myBoard.canMove(p, incx, current)) {
				horizCheck=true;
			}
			
		}
			
			p=PiecesWhere.get(decx);
			if(decx.getX()>=0&&containsPiece(decx)&&((p.getColor()==Color.WHITE)!=whiteMoves)&&horizCheck!=true) {
				
				
				if(myBoard.canMove(p, decx, current)) {
					horizCheck=true;
				}
				
			}
		}
			
			
			
			for(int i=0;i<=7;i++) { 
				
				incy=new Coordinate(current.getY()+i,current.getX());
				decy=new Coordinate(current.getY()-i,current.getX());
				
				Piece p=PiecesWhere.get(incy);
				
				if(verticCheck) {
					break;
				}
				
				
				
				if(incy.getY()<=7&&containsPiece(incy)&&((p.getColor()==Color.WHITE)!=whiteMoves)&&horizCheck!=true) {
				
				
				if(myBoard.canMove(p, incy, current)) {
					verticCheck=true;
				}
				
			}
				
				p=PiecesWhere.get(decy);
				if(decy.getY()>=0&&containsPiece(decy)&&((p.getColor()==Color.WHITE)!=whiteMoves)&&horizCheck!=true) {
					
					
					if(myBoard.canMove(p, decy, current)) {
						verticCheck=true;
					}
					
				}
				
			
		
		
		}
			
		
	
	
	return horizCheck||verticCheck||knightCheck ;
	
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
		
		System.out.println("Check?"+isInCheck());
		
		if(selectedCoords.size()==0 &&PiecesWhere.get(Coord)!=null&&(PiecesWhere.get(Coord).getColor()==Color.WHITE)==whiteMoves) {
			
			if (LastPiece!=null) {
				selectedCoords.add(Coord);
				SelectedPiece=PiecesWhere.get(Coord);
							}
			//System.out.println(LastPiece);
			if((LastPiece==null)) {
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
				
				if(SelectedPiece.getName().contains("King")) {
					if (whiteMoves) {
						KingLocWhite=to;
					}
					else {
						KingLocBlack=to;
					}
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
	
	public void reset() throws IOException {
		check=false;
		LastPiece=null;
		SelectedPiece=null;		
		this.score=0;
		this.piececount=16;
		this.myBoard=new Board();
		whiteMoves=true;
		PiecesWhere= myBoard.getPositions();
		KingLocWhite= new Coordinate(7,4);
		KingLocBlack= new Coordinate(0,4);
		
	}
	
	public Board getBoard() {
		return myBoard;
	}

}
