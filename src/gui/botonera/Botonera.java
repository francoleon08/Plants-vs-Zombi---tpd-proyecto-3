package gui.botonera;

import java.awt.GridLayout;
import java.awt.Image;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import ente.plantas.Planta;
import gui.GUI;

public class Botonera extends JLayeredPane{
	private LinkedList<Planta> plantasDisponibles;
	
	public Botonera(LinkedList<Planta> p,GUI gui) {
		plantasDisponibles = p;
		
		this.setSize(200, 50);
		this.setLayout(new GridLayout(1, 4, 0, 0));
		
		
		JButton planta1 = new JButton("Planta 1");
		planta1.setIcon(new ImageIcon(plantasDisponibles.get(0).getSkinBoton()));
		this.add(planta1,1);
		
		JButton  planta2 = new JButton("Planta 2");
		planta2.setIcon(new ImageIcon(plantasDisponibles.get(1).getSkinBoton()));
		this.add(planta2,1);
		
		JButton planta3 = new JButton("Planta3");
		planta2.setIcon(new ImageIcon(plantasDisponibles.get(2).getSkinBoton()));
		this.add(planta3,1);
	
	}
	public void setFondo(String s) {
		ImageIcon img=new ImageIcon(new ImageIcon(s).getImage().getScaledInstance(this.getWidth(),this.getHeight(), Image.SCALE_SMOOTH));
		this.add(new JLabel(img));
	}
	public JLayeredPane getPanel() {
		return this;
	}
}
