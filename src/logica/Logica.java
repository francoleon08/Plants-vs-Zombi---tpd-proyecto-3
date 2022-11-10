package logica;

import java.awt.Point;

import Sonido.SClip;
import gui.GUI;
import jardin.Jardin;

public class Logica {
	private Jardin jardin;
	private GUI gui;
	private int dinero;
	private String modo;
	private SClip reproductorfondo;
	
	public Logica() {
		dinero = 400;
		gui = new GUI(this);		
		gui.setVisible();
		reproductorfondo= new SClip("assets\\sonidos\\Music1.wav");
		reproductorfondo.loop();
	}
	
	public void setModoJuego(String modo) {
		this.modo = modo;
		jardin = new Jardin(this, gui, modo);		
		jardin.iniciarJuego();
	}
	
	public void interactuarMoneda(Point p) {
		dinero += jardin.interaccionMoneda(p);
	}
	
	/*
	public void setModoJuego(String modoJuego) {
		jardin.cambiarModoJuego(modoJuego);
	}
	*/
	
	public void crearPlanta(int index, Point p) {
		dinero -= jardin.insertPlanta(index, p);
	}
	
	public int getDinero() {
		return dinero;
	}
	
	public void gameOver() {
		reproductorfondo.stop();
		System.exit(0);
	}
}
