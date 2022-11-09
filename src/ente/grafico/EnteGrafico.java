package ente.grafico;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class EnteGrafico extends JLabel {	
	
	public EnteGrafico(Point position, int width, int heigth, String skin) {		
		ImageIcon img=new ImageIcon(new ImageIcon(skin).getImage().getScaledInstance(width, heigth, Image.SCALE_SMOOTH));		
		this.setIcon(img);		
		this.setLocation(position);
	}
	
	public JLabel getSkin()	{
		return this;
	}
	
	public void setSkin(String skin) {
		this.setIcon(new ImageIcon(skin));
	}
	
	public void update(Point position) {
		this.setLocation(position);
	}
}
