package ente.proyectiles;

import ente.Ente;

/**
 * The Class Proyectil.
 */
public abstract class Proyectil extends Ente {
	protected int danio;
	protected int velocidad;

	/**
	 * Gets the danio.
	 * @return the danio
	 */
	public int getDanio() {
		return danio;
	}

	/**
	 * Sets the danio.
	 * @param danio the new danio
	 */
	public void setDanio(int danio) {
		this.danio = danio;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	/**
	 * Actualizar.
	 * Suma 1 a su posicion en x.
	 */
	public void actualizar() {
		this.getPosition().translate(1, 0);
	} 
}
