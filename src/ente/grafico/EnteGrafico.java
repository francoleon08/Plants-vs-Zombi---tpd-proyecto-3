package ente.grafico;

import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class EnteGrafico {
	private JLabel skin;
	private Point position;
	
	public EnteGrafico(Point position, int width, int heigth, String skin) {
		this.position = position;
		this.skin = new JLabel(new ImageIcon(skin));
		this.skin.setBounds((int) this.position.getX(), (int) this.position.getY(), width, heigth);
	}
	
	public JLabel getSkin()	{
		return this.skin;
	}
	
	public void setSkin(String skin) {
		this.skin.setIcon(new ImageIcon(skin));
	}
	
	public void update() {
		
	}
}
