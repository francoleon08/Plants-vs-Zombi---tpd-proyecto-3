package ente.plantas;

import ente.proyectiles.Proyectil;
import ente.zombi.visitor.Visitor;

public class LanzaGuisantes extends Planta {
	
	public LanzaGuisantes() {
		this.salud = 80;
		this.precio = 100;
		this.cooldownAccion = 5;
		this.cooldownCompra = 5;
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
