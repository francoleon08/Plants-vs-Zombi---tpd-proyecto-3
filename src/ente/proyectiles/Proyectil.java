package ente.proyectiles;

import ente.Ente;

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
	
	public abstract Proyectil clone(); 
}
