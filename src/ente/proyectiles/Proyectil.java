package ente.proyectiles;

import ente.Ente;

// TODO: Auto-generated Javadoc
/**
 * The Class Proyectil.
 */
public abstract class Proyectil extends Ente {
	
	/** The daño. */
	protected int daño;
	
	/** The distancia. */
	protected int distancia;
	
	/** The Velocidad. */
	protected int Velocidad;
	
	
	/**
	 * Actualizar.
	 * Suma 1 a su posicion en x
	 */
	public void actualizar() {
		this.getPosition().translate(1, 0);
	}
	
	/**
	 * Gets the daño.
	 *
	 * @return the daño
	 */
	public int getDaño() {
		return daño;
	}
	
	/**
	 * Sets the daño.
	 *
	 * @param daño the new daño
	 */
	public void setDaño(int daño) {
		this.daño = daño;
	}
	
	/**
	 * Gets the distancia.
	 *
	 * @return the distancia
	 */
	public int getDistancia() {
		return distancia;
	}
	
	/**
	 * Sets the distancia.
	 *
	 * @param distancia the new distancia
	 */
	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}
	
	/**
	 * Gets the velocidad.
	 *
	 * @return the velocidad
	 */
	public int getVelocidad() {
		return Velocidad;
	}
	
	/**
	 * Sets the velocidad.
	 *
	 * @param velocidad the new velocidad
	 */
	public void setVelocidad(int velocidad) {
		Velocidad = velocidad;
	}
	
	
}
