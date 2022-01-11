package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Controller.ResetController;
import Controller.SelectPieceController;
import Model.Coordinate;
import Model.Model;








public class ChessApp extends JFrame  {
	 Model model;
	 Puzzle_drawer panel;
	
	 JLabel Congratulations;
	 JButton btnReset;
		
		JLabel lblMoveCount = new JLabel("Move Count");
		
	public boolean choicetoReset;
	private JPanel contentPane;
	private JLabel panel_1;
	
	public ChessApp(Model model) throws IOException {
		
		
	
		this.model=model;
		setResizable(false);
		setTitle("Chess App"); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 400, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); 
		setContentPane(contentPane);
		
	
		btnReset=new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println("trying this");
					new ResetController(model, ChessApp.this).reset(choicetoReset);
					
					ChessApp.this.model= new Model();
					ChessApp.this.repaint();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
	
		
		panel = new Puzzle_drawer(model);
		
		 
		 panel.addMouseListener(new MouseListener() {

				
				
	
				
				

				@Override
				public void mouseClicked(MouseEvent e) {
					new SelectPieceController(model, ChessApp.this).mousePressed(e);
					
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent e) {
					
				}

				@Override
				public void mouseReleased(MouseEvent me) {
				
					
					

					// TODO Auto-generated method stub
					
				}
			});;
		 
		
		btnReset.setVisible(false);
			panel.setBackground(Color.GREEN.darker());
			panel.setVisible(false);
		
		JButton btnNewGame = new JButton("New game");
		JButton btnBlack = new JButton("Black");
		
		btnBlack.setBackground(Color.BLACK);
		btnBlack.setVisible(false);
		btnBlack.setForeground(Color.WHITE);
		JButton btnWhute = new JButton("White");
		btnWhute.setBackground(Color.WHITE);
		
		btnWhute.setVisible(false);
		JButton btnRandom = new JButton("Random");
		btnRandom.setBackground(Color.DARK_GRAY.brighter());
		btnRandom.setForeground(Color.CYAN);
		btnRandom.setVisible(false);
		
		
		btnWhute.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				btnWhute.setVisible(false);
				btnBlack.setVisible(false);
				btnRandom.setVisible(false);
				btnReset.setVisible(true);
				panel.setVisible(true);
				btnNewGame.setVisible(true);
				choicetoReset=true;
				panel_1.setVisible(false);
				try {
					System.out.println("trying this");
					new ResetController(model, ChessApp.this).reset(choicetoReset);
					
					ChessApp.this.model= new Model();
					ChessApp.this.repaint();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
			}
		});
			
btnBlack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btnWhute.setVisible(false);
				btnBlack.setVisible(false);
				btnRandom.setVisible(false);
				btnReset.setVisible(true);
				panel.setVisible(true);
				btnNewGame.setVisible(true);
				choicetoReset=false;
				panel_1.setVisible(false);
				try {
					System.out.println("trying this");
					new ResetController(model, ChessApp.this).reset(choicetoReset);
					
					ChessApp.this.model= new Model();
					ChessApp.this.repaint();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		 
		btnNewGame.addMouseListener(new MouseListener() {
		
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				btnNewGame.setVisible(false);
				btnWhute.setVisible(true);
				btnBlack.setVisible(true);
				btnRandom.setVisible(true);
				panel.setVisible(false);
				btnReset.setVisible(false);
				panel_1.setVisible(true);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		BufferedImage myPicture = ImageIO.read(new File("ChessAPP.png"));
		panel_1 = new JLabel(new ImageIcon(myPicture));
		
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(55)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 477, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblMoveCount, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnReset)
							.addGap(13)
							.addComponent(btnNewGame)
							.addGap(13)
							.addComponent(btnBlack)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnWhute)
							.addPreferredGap(ComponentPlacement.RELATED, 517, Short.MAX_VALUE)
							.addComponent(btnRandom)))
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(441, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 540, GroupLayout.PREFERRED_SIZE)
					.addGap(13))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 480, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 457, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMoveCount)
						.addComponent(btnWhute)
						.addComponent(btnRandom)
						.addComponent(btnReset)
						.addComponent(btnNewGame)
						.addComponent(btnBlack))
					.addGap(5))
		);
		
		 Congratulations = new JLabel("Check");
		 panel.add(Congratulations, BorderLayout.NORTH);
		 
		 
		 if (model.isInCheck()) {
			 Congratulations.setVisible(true);
		 }

		 else {	
		 Congratulations.setVisible(false);
		 
		 }
		 
		 Congratulations.setForeground(new Color(255, 0, 0));
		 Congratulations.setFont(new Font("Tahoma", Font.PLAIN, 26));
		 Congratulations.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.setLayout(gl_contentPane);

}
	
	public JLabel getCheckLabel() {
		return Congratulations;
		
	}
}
