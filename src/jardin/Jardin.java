package jardin;

import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

import Sonido.SClip;
import ente.plantas.LanzaGuisantes;
import ente.plantas.Planta;
import ente.proyectiles.Moneda;
import ente.proyectiles.Proyectil;
import ente.zombi.Zombi;
import gui.GUI;
import logica.Logica;
import nivel.Nivel;
import timer.*;

public class Jardin {
	private Logica logica;
	private LinkedList<Planta> plantasDisponibles;
	private LinkedList<Zombi> zombisActivos;
	private LinkedList<Planta> plantasActivas;
	private LinkedList<Proyectil> proyectilesActivos;
	private TimerZombi timerZombis;
	private TimerPlanta timerPlantas;
	private TimerProyectil timerProyectiles;
	private int nivelActual;
	private String modoJuego;
	private Nivel nivel;
	private JardinGrafico jardinGrafico;
	private SClip sonidoZombi;
	
	public Jardin(Logica logica, GUI gui, String modoJuego) {
		sonidoZombi = new SClip("assets/sonidos/hit.wav");
		this.logica = logica;
		nivelActual = 1;
		this.modoJuego = modoJuego;
		zombisActivos = new LinkedList<Zombi>();
		plantasActivas = new LinkedList<Planta>();
		proyectilesActivos = new LinkedList<Proyectil>();		
		nivel = new Nivel("assets/niveles/nivel-"+nivelActual+"-"+this.modoJuego+".txt");		
		plantasDisponibles = nivel.getPlantasDisponibles();
		jardinGrafico = new JardinGrafico(gui, modoJuego);
		
		timerZombis = new TimerZombi(this);
		timerPlantas = new TimerPlanta(this);
		timerProyectiles = new TimerProyectil(this);
	}
	
	public void iniciarJuego() {
		
		Properties configPlanta = new Properties();
		try {
			configPlanta.load(new FileInputStream("assets\\configuracion\\config_plantas.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		LanzaGuisantes insert = new LanzaGuisantes(new Point(100,100), configPlanta);
		jardinGrafico.setEnte(insert.getEnteGrafico());
		plantasActivas.add(insert);
		
		LanzaGuisantes insert2 = new LanzaGuisantes(new Point(300,200), configPlanta);
		jardinGrafico.setEnte(insert2.getEnteGrafico());
		plantasActivas.add(insert2);
		
		LanzaGuisantes insert3 = new LanzaGuisantes(new Point(0,400), configPlanta);
		jardinGrafico.setEnte(insert3.getEnteGrafico());
		plantasActivas.add(insert3);
		
		
		timerZombis.start();
		timerPlantas.start();
		timerProyectiles.start();
	}
	
	public void insertPlanta(int idex, Point position) {
		Planta insert = plantasDisponibles.get(idex);
		if(insert != null && insert.puedeComprar()) {
			insert.setLocation(position);
			jardinGrafico.setEnte(insert.getEnteGrafico());
			plantasActivas.add(insert);
		}
	}
	
	public void generarZombi() {
		Zombi z = nivel.getZombi();
		if(z != null) {
			jardinGrafico.setEnte(z.getEnteGrafico());
			zombisActivos.add(z);
		}
		else {
			if(zombisActivos.size() == 0) {

			}
			else {
				if(checkGameOver()) {
					stopTimers();
					logica.gameOver();
				}					
			}
		}
			
	}

	public boolean addProyectil(Proyectil p) {
		boolean agrego=false;
		if(p != null) {
			jardinGrafico.setEnte(p.getEnteGrafico());
			proyectilesActivos.add(p);
			agrego=true;
		}
		return agrego;
	}
	
	public boolean removeZombi(Zombi z) {
		boolean removio=false;
		if(z != null) {
			zombisActivos.remove(z);
			jardinGrafico.removeEnte(z.getEnteGrafico());
			removio=true;
		}
		return removio;
	}
	
	public boolean removePlanta(Planta p) {
		boolean removio=false;
		if(p != null) {
			plantasActivas.remove(p);
			jardinGrafico.removeEnte(p.getEnteGrafico());
			removio=true;
		}
		return removio;
	}
	
	public boolean removeProyectiles(Proyectil p) {
		boolean removio=false;
		if(p != null) {
			proyectilesActivos.remove(p);
			jardinGrafico.removeEnte(p.getEnteGrafico());
			removio=true;
		}
		return removio;
	}
	
	public void cambiarModoJuego(String modoJuego) {
		nivel.setNivel("nivel-"+nivelActual+"-"+modoJuego);
		plantasDisponibles = nivel.getPlantasDisponibles();
		zombisActivos.clear();
		proyectilesActivos.clear();
		plantasActivas.clear();
	}
	
	public void colision(Zombi z) {
		double fila = z.getY();
		for(Planta p : plantasActivas) {
			if(p.getY() == fila && z.intersects(p)) {				
				p.accept(z);		
				if(!p.estaViva())
					removePlanta(p);
			}
		}
		for(Proyectil p : proyectilesActivos) {
			if(p.getLocation().getX() > 1000)
				proyectilesActivos.remove(p);
			else {
				if(p.getY() == fila && z.intersects(p)) {
					sonidoZombi.play();
					p.accept(z);	
					removeProyectiles(p);
					if(!z.estaVivo()) {
						removeZombi(z);
					}
				}
			}
		}		
	}
	
	public Iterable<Zombi> getZombis() {
		return zombisActivos;
	}
	
	public Iterable<Planta> getPlantas() {
		return plantasActivas;
	}
	
	public Iterable<Proyectil> getProyectiles() {
		return proyectilesActivos;
	}
	
	private boolean checkGameOver() {
		boolean estado = false;
		for(Zombi z : getZombis()) {
			if(z.getLocation().getX() <= 10) {
				estado = true;
				break;
			}
		}
		return estado;
	}
	
	private void stopTimers() {
		timerPlantas.detener();
		timerProyectiles.detener();
		timerZombis.detener();		
	}
	
	public int interaccionMoneda(Point pos) {
		int valor = 0;
		Moneda aux = null;
		for(Proyectil p : proyectilesActivos) {
			if(p.contains(pos)) {
				proyectilesActivos.remove(p);
				jardinGrafico.removeEnte(p.getEnteGrafico());
				if(p.getDanio() == 0) {
					aux = (Moneda) p;
					valor += aux.getValor();					
					break;
				}
			}
		}
		return valor;
	}	
}
