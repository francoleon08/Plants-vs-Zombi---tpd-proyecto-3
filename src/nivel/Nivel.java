package nivel;

import java.util.LinkedList;

import ente.plantas.Planta;
import ente.zombi.Zombi;
import nivel.generador.GeneradorNivel;

public class Nivel {
	private LinkedList<Planta> plantasDisponibles;
	private LinkedList<Zombi> zombisDisponibles;
	private GeneradorNivel generador;
	
	public Nivel(String urlNivel) {
		generador = new GeneradorNivel(urlNivel, this);
	}

	public LinkedList<Planta> getPlantasDisponibles() {
		return plantasDisponibles;
	}

	public void setPlantasDisponibles(LinkedList<Planta> plantasDisponibles) {
		this.plantasDisponibles = plantasDisponibles;
	}

	public Zombi getZombi() {
		return zombisDisponibles.removeFirst();
	}

	public void setZombisDisponibles(LinkedList<Zombi> zombisDisponibles) {
		this.zombisDisponibles = zombisDisponibles;
	}
	
	public void setNivel(String urlNivel) {
		generador.generarNuevoNivel(urlNivel);
	}
}
