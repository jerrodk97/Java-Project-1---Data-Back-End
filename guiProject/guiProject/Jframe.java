package guiProject;

import javax.swing.JFrame;

public class Jframe {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("GUI Project");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,500);
	
		frame.getContentPane().add(new Jpanel());
			
		frame.pack();
		frame.setVisible(true);
	}
}


