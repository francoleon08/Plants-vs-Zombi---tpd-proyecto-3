package ente.plantas;

import ente.Ente;
import ente.proyectiles.Proyectil;

public abstract class Planta extends Ente {
	protected int salud;
	protected int precio;
	protected int cooldownAccion;
	protected int cooldownCompra;
	
	public boolean disminuirSalud(int danio) {
		salud -= danio;
		return salud <= 0;
	}
	
	public boolean estaViva() {
		return salud > 0;
	}
	
	public boolean puedeComprar() {
		return cooldownCompra == 0;
	}
	
	public boolean puedeDisparar() {
		return cooldownAccion == 0;
	}
	
	public void actualizar() {
		this.cooldownAccion--;
	}
	
	public abstract Proyectil disparar();
	
	public abstract void resetDisparo();
	
	public abstract Planta clone();
}
