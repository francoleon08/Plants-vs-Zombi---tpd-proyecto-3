package gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GUI extends JFrame {
	private JPanel panelGrafico;
	
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(1000, 700));
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
	}
	
	public void addJPanel(JPanel panel) {
		panelGrafico = panel;
		getContentPane().add(panelGrafico);
	}
	
	public void setVisible() {
		this.setVisible(true);
	}
}
