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
	
	public int getPrecio() {
		return precio;
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
		if(cooldownAccion > 0)
			cooldownAccion--;
	}
	
	public void actualizarCompra() {
		if(cooldownCompra > 0)
			cooldownCompra--;
	}
	
	public abstract Proyectil disparar();
	
	public abstract void resetDisparo();
	
	public abstract void resetCompra();
	
	public abstract Planta clone();
}
