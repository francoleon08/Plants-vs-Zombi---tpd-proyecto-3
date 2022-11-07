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
		return salud >= 0;
	}
	
	public abstract Proyectil disparar();
	
	public abstract Planta clone();
}
