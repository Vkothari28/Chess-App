import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import Model.Model;
import View.ChessApp;

public class Main {
	public static void main(String[] args) throws IOException {
		
		Model m = new Model();
		
		ChessApp app = new ChessApp(m);
		
		
		
		app.setVisible(true);
	}

}
	