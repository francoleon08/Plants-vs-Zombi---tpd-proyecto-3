package ente.plantas;

import java.awt.Point;

import ente.proyectiles.Proyectil;
import ente.zombi.visitor.Visitor;

public class Humoseta extends Planta {
	
	public Humoseta(Point position) {
		this.salud = 80;
		this.precio = 75;
		this.cooldownAccion = 5;
		this.cooldownCompra = 6;
		
		this.setLocation(position);
		this.width = 50; //tamanio de la hitbox
		this.height = 70;
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
