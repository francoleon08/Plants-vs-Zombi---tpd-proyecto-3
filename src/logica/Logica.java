package logica;

import java.awt.Point;
import java.util.LinkedList;

import Sonido.SClip;
import ente.grafico.EnteGrafico;
import gui.GUI;
import jardin.Jardin;

public class Logica {
	private Jardin jardin;
	private GUI gui;
	private int dinero;
	private String modo;
	private SClip reproductorfondo;
	
	public Logica() {
		dinero = 40000;
		gui = new GUI(this);		
		gui.setVisible();
		reproductorfondo= new SClip("assets\\sonidos\\music.wav");
		reproductorfondo.loop();
	}
	
	public void setModoJuego(String modo) {
		this.modo = modo;
	}
	
	public void initGame() {
		jardin = new Jardin(this, gui, modo);		
		jardin.iniciarJuego();
	}
	
	public Iterable<EnteGrafico> getPlantasDisponibles() {
		return jardin.getPlantasDisponibles();
	}
	
	public void interactuarMoneda(Point p) {
		dinero += jardin.interaccionMoneda(p);
	}
	
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
