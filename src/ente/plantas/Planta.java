package ente.plantas;

import ente.Ente;
import ente.grafico.EnteGrafico;
import ente.proyectiles.Proyectil;

public abstract class Planta extends Ente {
	protected int salud;
	protected int precio;
	protected int cooldownAccion;
	protected int cooldownCompra;
	protected EnteGrafico icon;
	
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
	
	public EnteGrafico getIcon() {
		return icon;
	}
	
	public abstract void actualizarCompra();
	
	public abstract Proyectil disparar();
	
	public abstract void resetDisparo();
	
	public abstract void resetCompra();
	
	public abstract Planta clone();
}
