package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import Model.Coordinate;
import Model.Model;
import Model.Piece;

public class TestMovement {
	
	
	@Test
	public void TestMove() throws IOException {
		
	Model m= new Model();
	
	Coordinate pawn= new Coordinate(6,0);
	Piece paw=m.getPieceswhere().get(pawn);
	
	System.out.println(paw);
	//assertEquals(true,true);
	assertEquals(m.getBoard().canMove(paw, pawn, new Coordinate(5,0)),true);
	}

}
