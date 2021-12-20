package Model;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Board {

	HashMap<Coordinate,Piece> InitialPieces=new HashMap<Coordinate, Piece>();
	//ArrayList<Coordinate> SelectedCoordinates= new ArrayList<Coordinate>();
	Image imgs[]=new Image[12];
	
	public Board() throws IOException {
		
		InitialPieces=this.Initialize();
		
		
		
	}
	
	public void update(HashMap<Coordinate,Piece> updatedMap) {
		if(InitialPieces==null) {
		return;	
		}
		
		InitialPieces=updatedMap;
		
	}
	public HashMap<Coordinate,Piece> getPositions() {
		return this.InitialPieces;
		
	}
	
	/*
	 * Code to cut out individual pieces from Chess.png file 
	 * Idea from ScreenWorks youtube
	 */
	private void cutPieces() throws IOException {
		 BufferedImage all=ImageIO.read(new File("chess.png"));
	        
	       int ind=0;
	        for(int y=0;y<400;y+=200){
	        for(int x=0;x<1200;x+=200){
	            imgs[ind]=all.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
	       ind++;
	        }    
	        }
	}
	public HashMap<Coordinate,Piece> Initialize() throws IOException {
		HashMap<Coordinate,Piece> IP= new HashMap<Coordinate,Piece>();
		
		cutPieces();
		System.out.println(imgs[0]);
		
		// white initialization
		Piece RookWhite=new Piece("RookWhite", "Linear",Color.WHITE,imgs[4]);
		Piece RookWhite2=new Piece("RookWhite2", "Linear",Color.WHITE,imgs[4]);
		Piece BishopWhite=new Piece("BishopWhite", "Diagonal",Color.WHITE,imgs[2]);
		Piece BishopWhite2=new Piece("BishopWhite2", "Diagonal",Color.WHITE,imgs[2]);
		Piece KnightWhite=new Piece("KnightWhite", "Jump",Color.WHITE,imgs[3]);
		Piece KnightWhite2=new Piece("KnightWhite2", "Jump",Color.WHITE,imgs[3]);
		Piece QueenWhite= new Piece("QueenWhite","omni",Color.WHITE,imgs[1] );
		Piece KingWhite=new Piece("KingWhite","Omni_1",Color.WHITE,imgs[0]);
		
		
		
		for(int i=0;i<8;i++) {
			IP.put(new Coordinate(1,i), new Pawn("PawnBlack"+i,"Straight",Color.BLACK,imgs[11]));
			
			
		
		}
		Piece RookBlack=new Piece("RookBlack", "Linear",Color.BLACK,imgs[10]);
		Piece RookBlack2=new Piece("RookBlack2", "Linear",Color.BLACK,imgs[10]);
		Piece BishopBlack=new Piece("BishopBlack", "Diagonal",Color.BLACK,imgs[8]);
		Piece BishopBlack2=new Piece("BishopBlack2", "Diagonal",Color.BLACK,imgs[8]);
		Piece KnightBlack=new Piece("KnightBlack", "Jump",Color.BLACK,imgs[9]);
		Piece KnightBlack2=new Piece("KnightBlack2", "Jump",Color.BLACK,imgs[9]);
		Piece QueenBlack= new Piece("QueenBlack","omni",Color.BLACK,imgs[7] );
		Piece KingBlack=new Piece("KingBlack","Omni_1",Color.BLACK,imgs[6]);
		
		
		//white initalization
		IP.put(new Coordinate(7,0,Color.BLACK), RookWhite);
		IP.put(new Coordinate(7,1,Color.WHITE), KnightWhite);
		IP.put(new Coordinate(7,2,Color.BLACK),BishopWhite);
		IP.put(new Coordinate(7,3,Color.WHITE), QueenWhite);
		IP.put(new Coordinate(7,4,Color.BLACK), KingWhite);
		IP.put(new Coordinate(7,5,Color.WHITE), BishopWhite2);
		IP.put(new Coordinate(7,6,Color.BLACK), KnightWhite2 );
	    IP.put(new Coordinate(7,7,Color.WHITE), RookWhite2);
		
		 
		
		
		//Black initialization
		
		IP.put(new Coordinate(0,0,Color.WHITE), RookBlack);
		IP.put(new Coordinate(0,1,Color.BLACK), KnightBlack);
		IP.put(new Coordinate(0,2,Color.WHITE), BishopBlack);
		IP.put(new Coordinate(0,3,Color.BLACK), QueenBlack);
		IP.put(new Coordinate(0,4,Color.WHITE), KingBlack);
		IP.put(new Coordinate(0,5,Color.BLACK), BishopBlack2);
		IP.put(new Coordinate(0,6,Color.WHITE), KnightBlack2 );
		IP.put(new Coordinate(0,7,Color.BLACK), RookBlack2);
		
		
		
		
		
		for(int i=0;i<8;i++) {
			Coordinate c= new Coordinate(6,i);
			IP.put(c, new Pawn("PawnWhite"+i,"Straight",Color.WHITE,imgs[5]));
			
			
		
		}
		

		
		
		
		return IP;
		
	}
	
	
	
	public boolean canMove(Piece myPiece,Coordinate from, Coordinate to) {
		
		if(InitialPieces.get(new Coordinate(from.getY(),from.getX()))==null) {
			return false;
		}
		
		if(from.getX()==to.getX() && from.getY()==to.getY()) {
			return false;
		}
		
		
		
		boolean check=false;
		// rook movement 
		if(myPiece.movement.equals("Linear")) {
			
			if(from.getX()==to.getX()) {
			int start=from.getY();
			int end=to.getY();
			if(from.getY()>to.getY()) {
				start=to.getY();
				end=from.getY();
				
			}
			
			for(int i=start;i<end;i++) {
			
			Coordinate c= new Coordinate(i,from.getX());
				if(InitialPieces.get(c)!=null) {
					return false;
				}
				else {
					check=true;
				}
			}
			
			return check; 
		}
			
			
			else if(from.getY()==to.getY()) {
				int start=from.getX();
				int end=to.getX();
				if(from.getY()>to.getY()) {
					start=to.getX();
					end=from.getX();
					
				}
				
				for(int i=start;i<end;i++) {
				
				Coordinate c= new Coordinate(from.getY(),i);
					if(InitialPieces.get(c)!=null) {
						return false;
					}
					
					else {
						check=true;
					}
				}
				
				
			}	
		}
		
		else if(myPiece.getMovement().equals("Diagonal")) {
			
			if(Math.abs((from.getX()-to.getX()))==Math.abs(from.getY()-to.getY())) {
			if(from.getY()>to.getY()) {
				int j1=to.getX()-from.getX();
				int j_it=1;
				if(from.getX()<to.getX()) {
				
				
				for(int i=1;i<Math.abs(from.getY()-to.getY())&&j_it<j1;i++){
					Coordinate coord= new Coordinate(from.getY()-i,from.getX()+j_it);
					j_it++;
					if(InitialPieces.get(coord)!=null) {
						System.out.println("returning False 1");
						return false;
					}
					
					
				}
				
				return true;
			}
				
				else {
					int j=from.getX()-to.getX();
					
					for(int i=1;i<from.getY()-to.getY();i++){
						Coordinate coord= new Coordinate(from.getY()-i,from.getX()-i);
						
						if(InitialPieces.get(coord)!=null) {
							System.out.println("returning False 2");
							return false;
						}
						
						
					}
					
					return true;
				}
					
				}
			else {
				int j=1;
				int iter=1;
			if(from.getX()>to.getX()) {
				j=-1;
				iter=-1;
				
			}
				for(int i=-1;i>from.getY()-to.getY();i--){
					Coordinate coord= new Coordinate(from.getY()+Math.abs(i),from.getX()+j);
					
					if(InitialPieces.get(coord)!=null) {
						return false;
					}
					j+=iter;
				}
				return true;
			}
			
//			
			}
		}

		
		
		
		
		else if(myPiece.getMovement().equals("omni")) { // queen can move diagonally and linearly
			Piece newPiece= new Piece("","Linear",myPiece.getColor());
			Piece newPiece2= new Piece("","Diagonal",myPiece.getColor());
			return (canMove(newPiece, from, to)|| canMove(newPiece2,from,to));
		}
		else if(myPiece.getMovement().equals("Omni_1")) { // king_movement
				
			
		}
		
		else if(myPiece.getMovement().equals("Jump")){
			return Math.abs((from.getX() - to.getX()) * (from.getY() -to.getY())) ==2;
			
			
		}
		else if(myPiece.getMovement().equals("Straight")) {
			
			if(myPiece.getColor().equals(Color.BLACK)) {
				
				
				if(to.getX()==from.getX()) {
				if(from.getY()+2==to.getY()&&from.getY()==1) {
					
				for(int i=from.getY()+1;i<=to.getY();i++) {
					if(InitialPieces.get(new Coordinate(i,from.getX()))!=null) {
						return false;
					}
					
				}
				
				return true;
				}
				else if(from.getY()+1==to.getY()) {
					
					if(InitialPieces.get(to)==null) {
						return true;
					}
					
					return false;
				}
				
					
				}
				
			}
			
			
			else if(myPiece.getColor().equals(Color.WHITE)) {
				
				
				if(to.getX()==from.getX()) {
				if(from.getY()-2==to.getY()&&from.getY()==6) {
					
				for(int i=from.getY()-1;i>=to.getY();i--) {
					if(InitialPieces.get(new Coordinate(i,from.getX()))!=null) {
						return false;
					}
					
				}
				
				return true;
				}
				else if(from.getY()-1==to.getY()) {
					
					if(InitialPieces.get(to)==null) {
						return true;
					}
					
					return false;
				}
				
					
				}
				
			}
			
		}
		
		
		return check;
		
	
	
	
}
}
