package ente.proyectiles;

import ente.Ente;


// TODO: Auto-generated Javadoc
/**
 * The Class Proyectil.
 */
public abstract class Proyectil extends Ente {
	
	/** The salud. */
	protected int salud;
	
	/** The danio. */
	protected int danio;
	
	/** The velocidad. */
	protected int velocidad;

	/**
	 * Gets the danio.
	 *
	 * @return the danio
	 */
	public int getDanio() {
		return danio;
	}

	/**
	 * Sets the danio.
	 *
	 * @param danio the new danio
	 */
	public void setDanio(int danio) {
		this.danio = danio;
	}

	/**
	 * Gets the salud.
	 *
	 * @return the salud
	 */
	public int getSalud() {
		return salud;
	}

	/**
	 * Sets the salud.
	 *
	 * @param salud the new salud
	 */
	public void setSalud(int salud) {
		this.salud = salud;
	}
	
	/**
	 * Actualizar.
	 * Suma 1 a su posicion en x.
	 */
	public void actualizar() {
		this.getPosition().translate(1, 0);
	} 
}
