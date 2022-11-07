package ente.plantas;

import ente.proyectiles.Proyectil;
import ente.zombi.visitor.Visitor;

public class Humoseta extends Planta {
	
	public Humoseta() {
		this.salud = 80;
		this.precio = 75;
		this.cooldownAccion = 5;
		this.cooldownCompra = 6;
	}

	@Override
	public Proyectil disparar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Planta clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizar() {
		// TODO Auto-generated method stub
		
	}

}
