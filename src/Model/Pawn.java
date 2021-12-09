package Model;

import java.awt.Color;
import java.awt.Image;

public class Pawn extends Piece {

	protected boolean enpessant=false;
	public Pawn(String name, String movement, Color color, Image img) {
		
		super(name, movement, color, img);
		
	}
	
	
	public void canEnpessant() {
		enpessant=!enpessant;
	}
	
	public boolean getEnpessant() {
		return enpessant;
	}

	

}
