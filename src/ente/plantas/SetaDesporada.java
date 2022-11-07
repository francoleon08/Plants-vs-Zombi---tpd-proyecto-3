package ente.plantas;

import ente.proyectiles.Proyectil;
import ente.zombi.visitor.Visitor;

public class SetaDesporada extends Planta {
	
	public SetaDesporada() {
		this.salud = 50;
		this.precio = 0;
		this.cooldownAccion = 4;
		this.cooldownCompra = 3;
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
