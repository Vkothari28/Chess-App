package Model;

import java.awt.Color;
import java.awt.Image;

public class Piece {
	
	String piecename;
	String movement;
	Color color;
	boolean isSelected;
	Image img;



	public Piece(String name, String movement,Color color,Image img) {
		this.piecename=name;
		this.movement=movement;
		isSelected=false;
		this.color=color;
		this.img=img;
		
		
		
		
	}
	
	
public Piece(String name, String movement,Color color) {
	this.piecename=name;
	this.movement=movement;
	isSelected=false;
	this.color=color;
}


public String getName() {
	return this.piecename;
}

public void setSelected() {
	if(isSelected) {
		isSelected=false;
	}
	
	else {
		isSelected=true;
	}
}

public boolean getSelected() {
	return this.isSelected;
}

public String getMovement() {
	return this.movement;
}

public Color getColor() {
	return this.color;
}

public Image getImage() {
	return this.img;
}

}