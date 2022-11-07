package ente.plantas;

import java.awt.Point;

import ente.proyectiles.Proyectil;
import ente.zombi.visitor.Visitor;

public class Petacereza extends Planta {
	
	public Petacereza(Point position) {
		this.salud = 90;
		this.precio = 150;
		this.cooldownAccion = 7;
		this.cooldownCompra = 5;
		
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
