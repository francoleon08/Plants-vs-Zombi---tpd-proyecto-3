package jardin;

import java.awt.Point;
import java.util.LinkedList;

import ente.plantas.Planta;
import ente.proyectiles.Proyectil;
import ente.zombi.Zombi;
import gui.GUI;
import nivel.Nivel;
import timer.*;

public class Jardin {

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
	
	public Jardin(GUI gui,String modoJuego) {
		nivelActual = 1;
		this.modoJuego = modoJuego;
		zombisActivos = new LinkedList<Zombi>();
		plantasActivas = new LinkedList<Planta>();
		proyectilesActivos = new LinkedList<Proyectil>();		
		nivel = new Nivel("assets/niveles/nivel-"+nivelActual+"-"+this.modoJuego);		
		plantasDisponibles = nivel.getPlantasDisponibles();
		jardinGrafico = new JardinGrafico(gui);
		
		timerZombis = new TimerZombi(this);
		timerPlantas = new TimerPlanta(this);
		timerProyectiles = new TimerProyectil(this);
	}
	
	public void iniciarJuego() {
		timerZombis.start();
		timerPlantas.start();
		timerProyectiles.start();
	}
	
	public void insertPlanta(int idex, Point position) {
		Planta insert = plantasDisponibles.get(idex);
		if(insert != null && insert.puedeComprar()) {
			insert.setLocation(position);
			jardinGrafico.setEnte(insert.getEnteGrafico().getSkin());
			plantasActivas.add(insert);
		}
	}
	
	public void generarZombi() {
		Zombi z = nivel.getZombi();
		if(z != null) {
			jardinGrafico.setEnte(z.getEnteGrafico().getSkin());
			zombisActivos.add(z);
		}
		else {
			if(zombisActivos.size() == 0) {
				//cambio de nivel
			}
			//espero a matar todos los zombis
		}
			
	}
	
	public boolean addProyectil(Proyectil p) {
		boolean agrego=false;
		if(p != null) {
			jardinGrafico.setEnte(p.getEnteGrafico().getSkin());
			proyectilesActivos.add(p);
			agrego=true;
		}
		return agrego;
	}
	
	public boolean removeZombi(Zombi z) {
		boolean removio=false;
		if(z != null) {
			zombisActivos.remove(z);
			jardinGrafico.removeEnte(z.getEnteGrafico().getSkin());
			removio=true;
		}
		return removio;
	}
	
	public boolean removePlanta(Planta p) {
		boolean removio=false;
		if(p != null) {
			plantasActivas.remove(p);
			jardinGrafico.removeEnte(p.getEnteGrafico().getSkin());
			removio=true;
		}
		return removio;
	}
	
	public boolean removeProyectiles(Proyectil p) {
		boolean removio=false;
		if(p != null) {
			plantasActivas.remove(p);
			jardinGrafico.removeEnte(p.getEnteGrafico().getSkin());
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
			if(p.getY() == fila && z.intersects(p)) {
				p.accept(z);	
				removeProyectiles(p);
				if(!z.estaVivo()) {
					removeZombi(z);
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
}
