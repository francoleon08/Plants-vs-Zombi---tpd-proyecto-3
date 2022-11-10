package ente.grafico;

import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class EnteGrafico{	
	private JLabel skin;
	
	public EnteGrafico(Point position, int width, int heigth, String skin) {
		this.skin = new JLabel(new ImageIcon(skin));						
		this.skin.setBounds((int)position.getX(),(int) position.getY(), width, heigth);		
	}
	
	public JLabel getSkin()	{
		return skin;
	}
	
	public void setSkin(String skin) {
		this.skin.setIcon(new ImageIcon(skin));
	}
	
	public void setPosition(Point p) {
		skin.setLocation(p);
	}
	
	public void update(Point position) {
		skin.setLocation(position);
	}
}
