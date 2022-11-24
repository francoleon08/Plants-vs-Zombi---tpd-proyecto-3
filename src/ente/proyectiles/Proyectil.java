package ente.proyectiles;

import java.awt.Point;

import ente.Ente;

/**
 * The Class Proyectil.
 */
public abstract class Proyectil extends Ente {
	protected int danio;
	protected int velocidad;
	protected boolean choqueZombie;

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
	
	//Indica que ya choqué a un zombie, solo puede ser seteado
	public void setChoqueZombie() {
		choqueZombie = true;
	}

	/**
	 * Actualizar.
	 * Suma 1 a su posicion en x.
	 */
	public void actualizar() {
		Point aux = new Point();
		aux.setLocation(this.getLocation().getX()+velocidad, this.getLocation().getY());
		this.setLocation(aux);
		this.grafico.update(this.getLocation());
	}

	public boolean condicionElim(int lim) {
		return this.getLocation().getX() >= lim || choqueZombie;
	} 
}
