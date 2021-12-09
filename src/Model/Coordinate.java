package Model;

import java.awt.Color;
import java.util.Objects;



public class Coordinate {
	
	int x;
	int y;
	Color color;
	public Coordinate(int y,int x,boolean b) {
		this.x=x;
		this.y=y;
		if(b==true) {
			this.color=Color.WHITE;
		}
		else {
		this.color=Color.BLACK;
		}
	}
	
	public Coordinate(int y,int x,Color c) {
		this.x=x;
		this.y=y;
		this.color=c;
	}
	
	public Coordinate(int y,int x) {
		this.x=x;
		this.y=y;
		this.color=Color.WHITE;
	}
	public Color getColor() {
		return this.color;
	}
	
	public void setColor(boolean t) {
		if(t==true) {
			this.color=Color.WHITE;
		}
		else {
			this.color=Color.BLACK;
		}
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
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
	        if (!(o instanceof Coordinate)) { 
	            return false; 
	        } 
	          
	        // typecast o to Complex so that we can compare data members  
	        Coordinate c = (Coordinate) o; 
	          
	        // Compare the data members and return accordingly  
	        return Double.compare(x, c.x) == 0
	                && Double.compare(y, c.y) == 0 && c.color.equals(color); 
	                
	    } 
	
	
	@Override 
	/*
	 * Implemented using Geeksforgeeks
	 */
	public int hashCode() {
		return Objects.hash(x,y,color);
	}
}
