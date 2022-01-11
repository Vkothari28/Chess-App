package Model;

import java.awt.Color;
import java.awt.Image;
import java.util.Objects;

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


@Override
/*
 * Override equals based on geeksforgeeks override
 * @return boolean, returns a parameter depending on whether the object equals the current
 */
 public boolean equals(Object o) { 
	 if (o == this) { 
            return true; 
        } 
  
        /* Check if o is an instance of Complex or not 
          "null instanceof [type]" also returns false */
        if (!(o instanceof Piece)) { 
            return false; 
        } 
          
        // typecast o to Complex so that we can compare data members  
        Piece c = (Piece) o; 
          
        // Compare the data members and return accordingly  
        return piecename.equals(c.piecename) && movement.equals(c.movement) &&  color.equals(c.color);
                
    } 


@Override 
/*
 * Implemented using Geeksforgeeks
 */
public int hashCode() {
	return Objects.hash(piecename,movement,color,img);
}



}