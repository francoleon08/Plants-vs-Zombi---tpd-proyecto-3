package gui.botonera;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;

import ente.grafico.EnteGrafico;
import gui.GUI;

public class Botonera {
	private JLayeredPane panelBotones;
	private GUI gui;
	private LinkedList<JButton> botones;
	
	public Botonera(GUI gui) {
		this.gui = gui;
		panelBotones = new JLayeredPane();
		panelBotones.setBounds(10, 10, 400, 100);
		panelBotones.setLayout(null);
		panelBotones.setOpaque(true);
		botones = new LinkedList<JButton>();
			
		gui.addBotonera(panelBotones);		
	}
	
	public void setBotonera(Iterable<EnteGrafico> list) {
		int cont = 0;
		for(EnteGrafico e : list) {
			JButton insert = new JButton(e.getSkin().getIcon());
			insert.setBounds(cont*100, 0, 100, 100);
			insert.setOpaque(false);
			insert.setContentAreaFilled(false);	
			insert.setBorder(null);
			insert.addActionListener(new ActionListener() {				
				public void actionPerformed(ActionEvent e) {
					controlBoton(insert);
				}
			});
					
			botones.addLast(insert);
			panelBotones.add(insert);
			panelBotones.setLayer(insert, 1);
			cont++;
		}
		panelBotones.setSize((cont)*100, 100);
	}
	
	public void actualizarBotonera(Iterable<EnteGrafico> list) {
		Iterator<EnteGrafico> ente = list.iterator();
		for(JButton b : botones) {
			b.setIcon(ente.next().getSkin().getIcon());
		}
	}
	
	private void controlBoton(JButton b) {
		int index = (int) b.getLocation().getX() / 100;		
		gui.accionBoton(index);
	}
}
