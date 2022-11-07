package ente.grafico;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class EnteGrafico {
	private JLabel skin;
	private Point position;
	
	public EnteGrafico(Point position, int width, int heigth, String skin) {
		this.position = position;
		ImageIcon img2=new ImageIcon(new ImageIcon(skin).getImage().getScaledInstance(width, heigth, Image.SCALE_SMOOTH));
		this.skin = new JLabel(img2);
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
