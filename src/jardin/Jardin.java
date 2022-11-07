package jardin;

import java.util.LinkedList;

import ente.plantas.Planta;
import ente.proyectiles.Proyectil;
import ente.zombi.Zombi;
import gui.GUI;

public class Jardin {

	LinkedList<Planta> plantasDisponibles;
	LinkedList<Zombi> zombisActivos;
	LinkedList<Planta> plantasActivas;
	LinkedList<Proyectil> proyectilesActivos;
	int nivel;
	JardinGrafico jardinGrafico;
	
	public Jardin(GUI gui,String modoJuego) {
		//genera el nivel y setea las listas
		
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
		zombisActivos.remove(z);
		
	}
}
