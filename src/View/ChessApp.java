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
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
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
		
		JLabel numberMoveslabel = new JLabel("New label");
		
	
	private JPanel contentPane;
	
	public ChessApp(Model model) {
		
	
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
					new ResetController(model, ChessApp.this).reset();
					
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
		 
		
		panel.setBackground(new Color(153,102,0));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 540, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(24, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblMoveCount, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(numberMoveslabel, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 193, Short.MAX_VALUE)
					.addComponent(btnReset)
					.addGap(207))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(29)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 457, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMoveCount)
						.addComponent(btnReset)
						.addComponent(numberMoveslabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(22))
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
