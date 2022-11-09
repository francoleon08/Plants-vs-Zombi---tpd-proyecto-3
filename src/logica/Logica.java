package logica;

import java.awt.Point;

import gui.GUI;
import jardin.Jardin;

public class Logica {
	private Jardin jardin;
	private GUI gui;
	private int dinero;
	
	public Logica() {
		dinero = 0;
		gui = new GUI();
		gui.setVisible();
		jardin = new Jardin(this, gui, "dia");
		jardin.iniciarJuego();
	}
	
	public void interactuarMoneda(Point p) {
		dinero += jardin.interaccionMoneda(p);
	}
	
	public void setModoJuego(String modoJuego) {
		jardin.cambiarModoJuego(modoJuego);
	}
	
	public void crearPlanta(int index, Point p) {
		jardin.insertPlanta(index, p);
	}
	
	public void gameOver() {
		System.exit(0);
	}
}
