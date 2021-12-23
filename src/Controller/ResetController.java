package Controller;

import java.io.IOException;

import Model.Board;
import Model.Model;
import View.ChessApp;

public class ResetController {

	
	Model m;
	ChessApp app;
	
	public ResetController(Model m, ChessApp app) {
		this.m=m;
		this.app=app;
	}
	
	public void reset() throws IOException {
		
		
		m.reset();
		
		app.repaint();
	}
	
	
}
