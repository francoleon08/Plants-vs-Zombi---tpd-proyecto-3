package jardin;

import java.util.LinkedList;

import ente.plantas.Planta;
import ente.proyectiles.Proyectil;
import ente.zombi.Zombi;
import gui.GUI;
import nivel.Nivel;

public class Jardin {

	private LinkedList<Planta> plantasDisponibles;
	private LinkedList<Zombi> zombisActivos;
	private LinkedList<Planta> plantasActivas;
	private LinkedList<Proyectil> proyectilesActivos;
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
		
		nivel = new Nivel("nivel-"+nivelActual+"-"+modoJuego);		
		plantasDisponibles = nivel.getPlantasDisponibles();
	}
	
	public boolean addPlanta(Planta p) {
		boolean agrego=false;
		if(p!=null) {
			plantasActivas.add(p);
			agrego=true;
		}
		return agrego;
	}
	
	public boolean addZombi(Zombi z) {
		boolean agrego=false;
		if(z!=null) {
			zombisActivos.add(z);
			agrego=true;
		}
		return agrego;
	}
	
	public boolean addProyectil(Proyectil p) {
		boolean agrego=false;
		if(p!=null) {
			proyectilesActivos.add(p);
			agrego=true;
		}
		return agrego;
	}
	
	public boolean removeZombi(Zombi z) {
		boolean removio=false;
		if(z!=null) {
			zombisActivos.remove(z);
			jardinGrafico.removeEnte(z.getEnteGrafico());
			removio=true;
		}
		return removio;
	}
	
	public boolean removePlanta(Planta p) {
		boolean removio=false;
		if(p!=null) {
			plantasActivas.remove(p);
			jardinGrafico.removeEnte(p.getEnteGrafico());
			removio=true;
		}
		return removio;
	}
	
	public boolean removeProyectiles(Proyectil p) {
		boolean removio=false;
		if(p!=null) {
			plantasActivas.remove(p);
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
			if(p.getY() == fila && z.intersects(p)) {
				p.accept(z);	
				removeProyectiles(p);
				if(!z.estaVivo()) {
					removeZombi(z);
				}
			}
		}		
	}
}
