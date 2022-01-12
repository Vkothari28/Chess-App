package Model;

import static org.junit.Assert.assertNotNull;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
	Piece Enpessant;
	Piece SelectedPiece;
	
	
	private boolean whiteMoves;
	private boolean[] CastlingWhite=new boolean[2];
	private boolean[] CastlingBlack=new boolean[2];
	
	 
	public Model() throws IOException {
		this.score=0;
		this.piececount=16;
		this.myBoard=new Board();
		whiteMoves=myBoard.playerChoseWhite;
		PiecesWhere= myBoard.getPositions();
		KingLocWhite= new Coordinate(7,4);
		KingLocBlack= new Coordinate(0,4);
		Arrays.fill(CastlingBlack, true);
		Arrays.fill(CastlingWhite, true);
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
		boolean horizCheck=false;
		boolean verticCheck=false;
		
		
		
		Coordinate current=KingLocBlack;
		ArrayList<Boolean> checklist= new ArrayList<Boolean>();
		if(whiteMoves) {
		current=KingLocWhite;
		}
		boolean knightCheck=myBoard.KnightCheck(current, PiecesWhere.get(current).getColor());
//		System.out.println("Knight"+knightCheck);
//		System.out.println("curr king at"+current.getY());
		boolean pawnCheck=myBoard.PawnProtection(current, PiecesWhere.get(current).getColor());
		boolean diagCheck= myBoard.diagProtection(current, PiecesWhere.get(current).getColor());
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
			
		
	
	
	return knightCheck||horizCheck||verticCheck||pawnCheck||diagCheck ;
	
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
	
	/*
	 * Function for Selecting a coordinate that a piece will move to 
	 */
	
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
	
	
	/*
	 * Function to check if Castling is possible
	 */
	boolean CastleCheck(Coordinate from, Coordinate to) {
		
		
	boolean[] myarray= CastlingBlack;
	
	if (whiteMoves) {
		myarray=CastlingWhite;
	}
	Piece king=PiecesWhere.get(from);
	Color co=king.getColor();
	int div=1;
		
	if(from.getX()!=4) {
		return false;
	}
	if (from.getX()>to.getX()) {
		div=-1;
		
		if(!PiecesWhere.get(new Coordinate(from.getY(),0)).getName().contains("Rook")) {
			return false;
		}
		if(myarray[0]==false) {
			return false;
		}
		
	}
	
	else if(from.getX()<to.getX()) {
		
		if(!PiecesWhere.get(new Coordinate(from.getY(),7)).getName().contains("Rook")) {
			return false;
		}
		
		if(myarray[1]==false) {
			return false;
		}
		
	}
	
	
	int i=1;
	Coordinate current=new Coordinate(from.getY(),from.getX());
	
	if(king!=null) {
	
		
	while(i<=2) {
	current.x+=(i/div);	
		
	if(PiecesWhere.get(current)!=null) {
		
		return false;
	}
	
		i++;
	}
		
	
		
		
		
		
		
	}
	
	
	
		return true;
	}
	
	
	public void CastlingKing(Coordinate from, Coordinate to) {
		Coordinate rookCoord=new Coordinate(from.getY(),7);
		Coordinate put= new Coordinate(from.getY(),from.getX()+1);
		if(from.getY()==to.getY()&&Math.abs(from.getX()-to.getX())==2){
			if(CastleCheck(from, to)) {
				
			if(from.getX()>to.getX()) {
				rookCoord=new Coordinate(from.getY(),0);
				put=new Coordinate(from.getY(),from.x-1);
			}
			Piece rook= PiecesWhere.get(rookCoord);
			PiecesWhere.remove(rookCoord);
			PiecesWhere.put(put, rook);
			
			if(whiteMoves) {
				KingLocWhite=to;
			}
			else {
				KingLocBlack=to;
			}
			PiecesWhere.put(to,SelectedPiece);
			PiecesWhere.remove(from);
			selectedCoords.clear();
			SelectedPiece=null;
			myBoard.update(PiecesWhere);
			whiteMoves=!whiteMoves;
			Enpessant=null;
			}
			
			else {
				SelectedPiece=null;
				selectedCoords.clear();
			}
			
		
	}	
		else {
			selectedCoords.clear();
			SelectedPiece=null;
			return;
		}
	}
	



	public void move(Coordinate from, Coordinate to) {
		
		HashMap<Coordinate, Piece> CheckCase= new HashMap<Coordinate, Piece>(); 
		//SelectedPiece= PiecesWhere.get(from);
		CheckCase=PiecesWhere;
		Piece currentEnpessant=null;
		
		
		if(SelectedPiece==null) {
			return;
		}
		
		
		
		if ((selectedCoords.size()==2&&SelectedPiece.color==Color.WHITE)==whiteMoves) {
			if(!myBoard.canMove(SelectedPiece, from, to)) {
				if(SelectedPiece.getName().contains("King")) {
					if(!isInCheck()) {
					CastlingKing(from, to);
				
				}
				}
					
					if(Math.abs(from.getY()-to.getY())==1&& Math.abs(from.getX()-to.getX())==1&&Enpessant!=null&&SelectedPiece.getName().contains("Pawn")&&Enpessant.getColor()!=SelectedPiece.getColor()) {
						Coordinate coordinate=new Coordinate(from.getY(),from.getX()+(to.getX()-from.getX()));
						
						System.out.println(PiecesWhere.get(coordinate));
							if(PiecesWhere.get(coordinate).equals(Enpessant)) {
								
								PiecesWhere.remove(coordinate);
								PiecesWhere.remove(from);
								PiecesWhere.put(to, SelectedPiece);
								selectedCoords.clear();
								SelectedPiece=null;
							}
						}
						
						
					
					
				
				else {
					selectedCoords.remove(1);
					
				}
			}
		
			
			
			
			else if(myBoard.canMove(SelectedPiece, from, to)) {
				
			
				
				if(PiecesWhere.get(to)!=null) {
					PiecesWhere.remove(to);
				}
				if(SelectedPiece.getName().contains("Pawn")) {
					if(Math.abs(to.getY()-from.getY())==2) {
						currentEnpessant=SelectedPiece;
					}
				}
				 if(SelectedPiece.getName().contains("Rook2")) {
					if (whiteMoves) {
						CastlingWhite[0]=false;
					}
					else {
						CastlingBlack[0]=false;
					}
				}
				
				else if(SelectedPiece.getName().contains("Rook")) {
					if (whiteMoves) {
						CastlingWhite[1]=false;
					}
					else {
						CastlingBlack[1]=false;
					}
				}
				
				
				 if(SelectedPiece.getName().contains("King")) {
					if (whiteMoves) {
				
						KingLocWhite=to;
						
						if(CastlingWhite[0]==true||CastlingWhite[1]==true) {
							Arrays.fill(CastlingWhite, false);
						}
					}
					else {
						KingLocBlack=to;
						
						if(CastlingBlack[0]==true||CastlingBlack[1]==true) {
							Arrays.fill(CastlingBlack, false);
						}
					}
				}
				
				
				System.out.println("Piece before check"+(PiecesWhere.get(to)));
				PiecesWhere.remove(from);
				PiecesWhere.put(to, SelectedPiece);
				if(isInCheck()) {
					
					System.out.println("Checking this");
				
					PiecesWhere.remove(to);
					PiecesWhere.put(from,SelectedPiece);
					
					if(SelectedPiece.getName().contains("King")) {
						if(whiteMoves) {
							KingLocWhite=from;
						}
						else {
							KingLocBlack=from;
						}
					}
					SelectedPiece=null;
					selectedCoords.clear();
					myBoard.update(PiecesWhere);
					
					//myBoard.update(PiecesWhere);
					
				}
				
				
				else {
				
				myBoard.update(PiecesWhere);
				whiteMoves= !whiteMoves;
				selectedCoords.clear();
				LastPiece=SelectedPiece;
				LastMovedTo=to;
				
				SelectedPiece=null;
				
				
				}
				
				
			}
			

			if(selectedCoords.size()==2) {
				
				selectedCoords.clear();
				SelectedPiece=null;
				System.out.println("choose another move");
				
			}
			
		}
			
			
			Enpessant=currentEnpessant;
			
			
			
			
		}
	
		
		   
		
	
	
	public void reset(boolean b) throws IOException {
		check=false;
		LastPiece=null;
		SelectedPiece=null;		
		this.score=0;
		this.piececount=16;
		this.myBoard=new Board(b);
		whiteMoves=b;
		PiecesWhere= myBoard.getPositions();
		KingLocWhite= new Coordinate(7,4);
		KingLocBlack= new Coordinate(0,4);
		Arrays.fill(CastlingWhite, true);
		Arrays.fill(CastlingBlack, true);
		
	}
	
	public Board getBoard() {
		return myBoard;
	}

}