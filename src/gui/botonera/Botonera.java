package gui.botonera;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
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
		panelBotones.setOpaque(false);		
		botones = new LinkedList<JButton>();
			
		gui.addBotonera(panelBotones);		
	}
	
	public void setBotonera(Iterable<EnteGrafico> list) {
		botones.clear();
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
		JLabel fondo = new JLabel(new ImageIcon(gui.getPropiedad("fondo_botonera")));
		fondo.setBounds(panelBotones.getBounds());
		fondo.setSize((int) panelBotones.getSize().getWidth()+20,100);
		fondo.setLocation(-10, 0);
		panelBotones.add(fondo);
		panelBotones.setLayer(fondo, 0);
		gui.addBotonera(panelBotones);
	}
	
	public void actualizarBotonera(Iterable<EnteGrafico> list) {
		Iterator<EnteGrafico> ente = list.iterator();
		for(JButton b : botones) {
			if(ente.hasNext()) {
				b.setIcon(ente.next().getSkin().getIcon());
			}
		}
	}
	
	private void controlBoton(JButton b) {
		int index = (int) b.getLocation().getX() / 100;		
		gui.accionBoton(index);
	}
}
