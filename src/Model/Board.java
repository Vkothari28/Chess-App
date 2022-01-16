package Model;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Board {
	
	
	Color color1=Color.WHITE;
	Color color2=Color.BLACK;
	
	protected boolean playerChoseWhite=true;
	
	HashMap<Coordinate,Piece> InitialPieces=new HashMap<Coordinate, Piece>();
	//ArrayList<Coordinate> SelectedCoordinates= new ArrayList<Coordinate>();
	Image imgs[]=new Image[12];
	protected int image_assigner=0;
	Piece Enpessant=null;
	public Board() throws IOException {
		
		
if(!playerChoseWhite) {
			
			image_assigner=6;
			
		}
		InitialPieces=this.Initialize();
		
		
		
	}
	
	public Board(boolean b) throws IOException {
		
		playerChoseWhite=b;
		if(!playerChoseWhite) { 
					
					image_assigner=6;
					
				}
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
	
	protected void setPlayerChosen(boolean b) {
		this.playerChoseWhite=b;
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
		
		
		// white initialization
		Piece RookWhite=new Piece("RookWhite", "Linear",color1,imgs[4+image_assigner]);
		Piece RookWhite2=new Piece("RookWhite2", "Linear",color1,imgs[4+image_assigner]);
		Piece BishopWhite=new Piece("BishopWhite", "Diagonal",color1,imgs[2+image_assigner]);
		Piece BishopWhite2=new Piece("BishopWhite2", "Diagonal",color1,imgs[2+image_assigner]);
		Piece KnightWhite=new Piece("KnightWhite", "Jump",color1,imgs[3+image_assigner]);
		Piece KnightWhite2=new Piece("KnightWhite2", "Jump",color1,imgs[3+image_assigner]);
		Piece QueenWhite= new Piece("QueenWhite","omni",color1,imgs[1+image_assigner] );
		Piece KingWhite=new Piece("KingWhite","Omni_1",color1,imgs[0+image_assigner]);
		
		
		
		for(int i=0;i<8;i++) {
			IP.put(new Coordinate(1,i), new Pawn("PawnBlack"+i,"Straight",color2,imgs[11-image_assigner]));
			
			
		
		}
		Piece RookBlack=new Piece("RookBlack", "Linear",color2,imgs[10-image_assigner]);
		Piece RookBlack2=new Piece("RookBlack2", "Linear",color2,imgs[10-image_assigner]);
		Piece BishopBlack=new Piece("BishopBlack", "Diagonal",color2,imgs[8-image_assigner]);
		Piece BishopBlack2=new Piece("BishopBlack2", "Diagonal",color2,imgs[8-image_assigner]);
		Piece KnightBlack=new Piece("KnightBlack", "Jump",color2,imgs[9-image_assigner]);
		Piece KnightBlack2=new Piece("KnightBlack2", "Jump",color2,imgs[9-image_assigner]);
		Piece QueenBlack= new Piece("QueenBlack","omni",color2,imgs[7-image_assigner] );
		Piece KingBlack=new Piece("KingBlack","Omni_1",color2,imgs[6-image_assigner]);
		
		
		//white initalization
		IP.put(new Coordinate(7,0), RookWhite);
		IP.put(new Coordinate(7,1), KnightWhite);
		IP.put(new Coordinate(7,2),BishopWhite);
		IP.put(new Coordinate(7,3), QueenWhite);
		IP.put(new Coordinate(7,4), KingWhite);
		IP.put(new Coordinate(7,5), BishopWhite2);
		IP.put(new Coordinate(7,6), KnightWhite2 );
	    IP.put(new Coordinate(7,7), RookWhite2);
		
		 
		
		
		//Black initialization
		
		IP.put(new Coordinate(0,0), RookBlack);
		IP.put(new Coordinate(0,1), KnightBlack);
		IP.put(new Coordinate(0,2), BishopBlack);
		IP.put(new Coordinate(0,3), QueenBlack);
		IP.put(new Coordinate(0,4), KingBlack);
		IP.put(new Coordinate(0,5), BishopBlack2);
		IP.put(new Coordinate(0,6), KnightBlack2 );
		IP.put(new Coordinate(0,7), RookBlack2);
		
		
		
		
		
		for(int i=0;i<8;i++) {
			Coordinate c= new Coordinate(6,i);
			IP.put(c, new Pawn("PawnWhite"+i,"Straight",color1,imgs[5+image_assigner]));
			
			
		
		}
		

		
		
		
		return IP;
		
	}
	
	
	
	
	
	public boolean safeMove(Coordinate from, Coordinate coord) {  // checks if there is an enemy king 1 square from the to coord in any direction
		
		Color current=InitialPieces.get(from).getColor();
		Coordinate diagDownright=new Coordinate(coord.getY()+1,coord.getX()+1);
		
		Coordinate diagupright=new Coordinate(coord.getY()-1,coord.getX()+1);
		Coordinate left=new Coordinate(coord.getY(),coord.getX()-1);
		
		Coordinate right=new Coordinate(coord.getY(),coord.getX()+1);
		
		Coordinate diagDownleft=new Coordinate(coord.getY()+1,coord.getX()-1);
		Coordinate diagUpLeft= new Coordinate(coord.getY()-1,coord.getX()-1); 
		Coordinate up= new Coordinate(coord.getY()-1,coord.getX());
		Coordinate down= new Coordinate(coord.getY()+1, coord.getX());
		
		ArrayList<Coordinate> mycoords= new ArrayList<Coordinate>( Arrays.asList(up,down, diagDownleft, diagDownright, diagupright,diagUpLeft,left, right));
		
		
		for(int i=0;i<mycoords.size();i++) {
			Piece p= InitialPieces.get(mycoords.get(i));
			
			if(p!=null) {
			System.out.println("here");
			System.out.println(p.getName());
			System.out.println(current);
			}
			if(p!=null && p.getColor()!=current &&p.getName().contains("King")) {
				return false;
			}
			
			
		}
		
		return true;
		
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
		
		mylist.add(UpAndRight);
		mylist.add(UpAndLeft);
		
		mylist.add(DownAndLeft);
		mylist.add(DownAndRight);
		mylist.add(RightAndUp);
		mylist.add(RightAndDown);
		mylist.add(LeftAndUp);
		mylist.add(LeftAndDown);
	
		
		for(int a=0;a<mylist.size();a++) {
			
		Piece p=	InitialPieces.get(mylist.get(a));
		
		if(p!=null) {
			
		}
		
		
			if(p!=null &&p.getColor()!=co && p.getName().contains("Knight")) {
				return true;
			}
			
		}
		
		return false;
	}
	
	public boolean PawnProtection(Coordinate c, Color co) {
		
		int div=1;

		
		if(co==color2) {
			
		
		div=-1;	
			
		}
		Coordinate check1= new Coordinate(c.getY()-(1/div), c.getX()+1); 	
		Coordinate check2= new Coordinate(c.getY()-(1/div), c.getX()-1);

		
		Piece p1=InitialPieces.get(check1);
		Piece p2= InitialPieces.get(check2);
	if(p1!=null && p1.getName().contains("Pawn")&&p1.getColor()!=co) {
		return true;
	}
	
	if(p2!=null && p2.getName().contains("Pawn")&&p2.getColor()!=co) {
		return true;
	}
		
		return false;
	}
	public boolean diagProtection(Coordinate c, Color co) { //checks if a piece is supported by another piece diagonally
	
		int diff=1;
		Coordinate DiagonalUpRight;
		Coordinate DiagonalUpLeft;
		Coordinate DiagonalDownLeft;
		Coordinate DiagonalDownRight;
		
		Piece[] myarray= new Piece[4];
		
		
		while(diff<8) {
			
			 DiagonalUpRight= new Coordinate(c.getY()-diff,c.getX()+diff);
			 DiagonalUpLeft= new Coordinate(c.getY()-diff,c.getX()-diff);
			 DiagonalDownLeft= new Coordinate(c.getY()+diff,c.getX()-diff);
			 DiagonalDownRight= new Coordinate(c.getY()+diff,c.getX()+diff);
			
			 
			 
			Piece p;
			 if(DiagonalUpRight.getX()<=7 && DiagonalUpRight.getY()<=7) {
				 p=InitialPieces.get(DiagonalUpRight);
				 if (p!=null &&myarray[0]==null) {
					 myarray[0]=p;
			 }
				 
				 
				 
				 
				 
			 }
			 
			 if(DiagonalUpLeft.getX()<=7 && DiagonalUpLeft.getY()<=7 ) {
				 p=InitialPieces.get(DiagonalUpLeft);
				 if (p!=null && myarray[1]==null) {
					 myarray[1]=p;
			 }
			 }
				 
				 if(DiagonalDownLeft.getX()<=7 && DiagonalDownLeft.getY()<=7) {
					 p=InitialPieces.get(DiagonalDownLeft);
					 if (p!=null && myarray[2]==null ) {
						 myarray[2]=p;;
				 }
				 }
				 
				 if(DiagonalDownRight.getX()<=7 && DiagonalDownRight.getY()<=7) {
					 p=InitialPieces.get(DiagonalDownRight);
					 if (p!=null && myarray[3]==null)  {
						 myarray[3]=p;
				 }
				 }
					 
					 
					 
					 
				 
				 
				 
				 
				 
			 
			
			diff+=1;
			
			
		}
		
		
		
		for(int i=0;i<myarray.length;i++) {
		if(myarray[i]!=null&& myarray[i].getColor()!=co&&(myarray[i].getName().contains("Queen")||myarray[i].getName().contains("Bishop"))){
		return true;
			
		}
		}
		
		
		return false;
		
	}
	

	
	public boolean linearProtection(Coordinate c, Color co) { // checks if a piece is supported linearly
		
		int diff=1;
	
		
		Piece FirstLeftPiece=null, FirstRightPiece=null, FirstUpPiece=null, FirstDownPiece=null;

		
		
		while(diff<=7 && FirstLeftPiece==null) {
			
			Coordinate left=new Coordinate(c.getY(),c.getX()-diff);
			Coordinate right=new Coordinate(c.getY(),c.getX()+diff);
			Coordinate up= new Coordinate(c.getY()+diff, c.getX());
			Coordinate down= new Coordinate(c.getY()-diff, c.getX());
			
			Piece p;
			if(left.getX()>=0) {
				p=InitialPieces.get(left);
				if(p!=null) {
					FirstLeftPiece=p;
				}
				
			}
			
			if(right.getX()<8 && FirstRightPiece==null) {
				p=InitialPieces.get(right);
				if(p!=null) {
					
					FirstRightPiece=p;
				}
				
			}
			
			if(up.getY()<=7 &&FirstUpPiece==null) {
				p=InitialPieces.get(up);
				if(p!=null) {
					
					FirstUpPiece=p;
				}
				
				
				
				
			}
			
			if(down.getY()>=0 &&FirstDownPiece==null) {
				p=InitialPieces.get(down);
				if(p!=null) {
					
					FirstDownPiece=p;
				}
				
				
				
				
			}
			
			
			
			diff+=1;
		}
		
		if(FirstRightPiece!=null&&(FirstRightPiece.getColor()!=co && (FirstRightPiece.getName().contains("Queen")||FirstRightPiece.getName().contains("Rook")))){
			return true;
		}
		
		if(FirstLeftPiece!=null&&(FirstLeftPiece.getColor()!=co && (FirstLeftPiece.getName().contains("Queen")||FirstLeftPiece.getName().contains("Rook")))){
			return true;
		}
		
		
		if(FirstUpPiece!=null&&(FirstUpPiece.getColor()!=co && (FirstUpPiece.getName().contains("Queen")||FirstUpPiece.getName().contains("Rook")))){
			return true;
		}
		
		if(FirstDownPiece!=null&&(FirstDownPiece.getColor()!=co && (FirstDownPiece.getName().contains("Queen")||FirstDownPiece.getName().contains("Rook")))){
			return true;
		}
		return false;
		
	}
	
	
		
		
		
	
	
	public boolean canMove(Piece myPiece,Coordinate from, Coordinate to) {
		
		
		
		
		if(to.getX()>7||to.getY()>7||to.getY()<0||from.getX()<0 ) {
			return false;
		}
		
		if(InitialPieces.get(new Coordinate(from.getY(),from.getX()))==null) {
			return false;
		}
		
		if(from.getX()==to.getX() && from.getY()==to.getY()) {
			return false;
		}
		
		
		
		boolean check=false;
		// rook movement 
		if(myPiece.movement.equals("Linear")) {
			int div=1;
			if(from.getX()==to.getX()) {
				
				
				if(from.getY()>to.getY()) {
				
					div=-1;
					
				}
					
					for(int i=1;i<Math.abs(from.getY()-to.getY());i++) {
						
						
						
						Coordinate c= new Coordinate(from.getY()+(i/div),from.getX());
						
						if(InitialPieces.get(c)!=null &&!c.equals(to)) {
							return false;
						}
						
					}
					check=true;
				}
				
				
		/*	int start=from.getY();
			int end=to.getY();
			if(from.getY()>to.getY()) {
				start=to.getY();
				end=from.getY();
				
			}*/
			
			
			else if(from.getY()==to.getY()) {
				 div=1;
				if(from.getX()>to.getX()) {
					
					div=-1;
					
				}
				
				for(int i=1;i<Math.abs(from.getX()-to.getX());i++) {
					
				
					
					Coordinate c= new Coordinate(from.getY(),from.getX()+(i/div));
					
					if(InitialPieces.get(c)!=null &&!c.equals(to)) {
						return false;
					}
					
				}
					
			
						
				check=true;;	
				}
			
				
			
		
			return check; 
		}
			
			
		else if(myPiece.getMovement().equals("Omni_1")) { // king_movement
			int distance= Math.abs(from.getX()-to.getX());
			
			if(Math.abs(from.getY()-to.getY())>distance) {
				distance=Math.abs(from.getY()-to.getY());
			}
			
			if(distance==1 && safeMove(from, to)&&!KnightCheck(to,myPiece.getColor())&&!PawnProtection(to,myPiece.getColor())&&!diagProtection(to, myPiece.getColor())&&!linearProtection(to, myPiece.getColor())) {
				
				return true;
			}
			
			
			
			return false;
				
			
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
		
		
		else if(myPiece.getMovement().equals("Jump")){
			return Math.abs((from.getX() - to.getX()) * (from.getY() -to.getY())) ==2;
			
			
		}
		else if(myPiece.getMovement().equals("Straight")) {
			
			if(myPiece.getColor().equals(color2)) {
				
				
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
				
				else if((to.getY()==from.getY()+1)&&InitialPieces.get(to)!=null &&InitialPieces.get(to).getColor()!=InitialPieces.get(from).getColor()) {
					
					if(to.getX()==from.getX()+1 ||to.getX()==from.getX()-1) {
					
					return true;
					
				}
				}
				
			
				
			}
			
			
			else if(myPiece.getColor().equals(color1)) {
				
				
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
				
else if((to.getY()==from.getY()-1)&&InitialPieces.get(to)!=null &&InitialPieces.get(to).getColor()!=InitialPieces.get(from).getColor()) {
					
					if(to.getX()==from.getX()+1 ||to.getX()==from.getX()-1) {
					
					return true;
					
				}
}
				
			}
			
		}
		
		
		return check;
		
	
	
	
}
}
