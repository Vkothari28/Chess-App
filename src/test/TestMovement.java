package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
	
	Piece knight=m.getPieceswhere().get(new Coordinate(7,1));
	System.out.println(paw);
	//assertEquals(true,true);
	assertEquals(m.getBoard().canMove(knight, new Coordinate(7,1), new Coordinate( 5,2)),true);
	m.SelectCoordandPiece(new Coordinate(7,1));
	m.SelectCoordandPiece(new Coordinate(5,2));
	System.out.println(m.getSelectedCoordinates());
	m.move(m.getSelectedCoordinates().get(0), m.getSelectedCoordinates().get(1));
	assertNotNull(m.getPieceswhere().get(new Coordinate(5,2)));
	
	assertEquals(m.getBoard().canMove(paw, pawn, new Coordinate(5,0)),true);
	m.SelectCoordandPiece(new Coordinate(1,4));
	m.SelectCoordandPiece(new Coordinate(3,4));

	m.move(m.getSelectedCoordinates().get(0), m.getSelectedCoordinates().get(1));
	assertNotNull(m.getPieceswhere().get(new Coordinate(3,4)));
	
	}

}
