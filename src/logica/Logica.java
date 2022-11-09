package logica;

import java.awt.Point;

import gui.GUI;
import jardin.Jardin;

public class Logica {
	private Jardin jardin;
	private GUI gui;
	
	public Logica() {
		gui = new GUI();
		jardin = new Jardin(this, gui, "dia");
		jardin.iniciarJuego();
		gui.setVisible();
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
