package ente.plantas;

import ente.proyectiles.Proyectil;
import ente.zombi.visitor.Visitor;

public class SetaSolar extends Planta {

	public SetaSolar() {
		this.salud = 70;
		this.precio = 25;
		this.cooldownAccion = 7;
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
