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
	private boolean reproductorActivo;
	
	public Logica() {
		dinero = 40000;
		reproductorActivo = true;
		gui = new GUI(this);		
		gui.setVisible();
		reproductorfondo= new SClip("assets\\sonidos\\music.wav");
		reproductorfondo.loop();
	}
	
	public void initGame() {
		jardin = new Jardin(this, gui, modo);		
		jardin.iniciarJuego();
	}
	
	public void setModoJuego(String modo) {
		this.modo = modo;
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
	
	public boolean playPausaMusica() {
		if(reproductorActivo) {
			reproductorfondo.stop();
			reproductorActivo = false;
		}
		else {
			reproductorfondo.loop();
			reproductorActivo = true;
		}
		return reproductorActivo;	
	}
	
	public int getDinero() {
		return dinero;
	}
	
	public void gameOver() {
		reproductorfondo.stop();
		System.exit(0);
	}
}
