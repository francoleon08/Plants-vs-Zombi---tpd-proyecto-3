package ente.proyectiles;

import ente.Ente;

// TODO: Auto-generated Javadoc
/**
 * The Class Proyectil.
 */
public abstract class Proyectil extends Ente {
	protected int salud;
	protected int danio;
	protected int velocidad;

	public int getDanio() {
		return danio;
	}

	public void setDanio(int danio) {
		this.danio = danio;
	}

	public int getSalud() {
		return salud;
	}

	public void setSalud(int salud) {
		this.salud = salud;
	}
	
	/**
	 * Actualizar.
	 * Suma 1 a su posicion en x
	 */
	public void actualizar() {
		this.getPosition().translate(1, 0);
	} 
}
