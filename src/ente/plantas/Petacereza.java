package ente.plantas;

import java.awt.Point;
import java.util.Properties;

import ente.grafico.EnteGrafico;
import ente.proyectiles.Proyectil;
import ente.zombi.visitor.Visitor;

public class Petacereza extends Planta {
	
	public Petacereza(Point position, Properties p) {
		this.setLocation(position);
		this.config = p;
		this.salud = Integer.parseInt(config.getProperty("salud_petaCereza"));
		this.precio = Integer.parseInt(config.getProperty("precio_petaCereza"));
		this.cooldownAccion = Integer.parseInt(config.getProperty("cooldownaccion_petaCereza"));
		this.cooldownCompra = Integer.parseInt(config.getProperty("cooldowncompra_petaCereza"));	
		this.width = Integer.parseInt(config.getProperty("ancho_petaCereza"));
		this.height = Integer.parseInt(config.getProperty("alto_petaCereza"));
		this.grafico = new EnteGrafico(position, this.width, this.height, config.getProperty("skin_petaCereza"));
	}

	@Override
	public Proyectil disparar() {
		Proyectil disparo =  null;
		if(this.cooldownAccion == 0) {
			//
		}
		return disparo;
	}

	public Planta clone() {
		return new Petacereza(this.getLocation(), config);
	}

	public void accept(Visitor v) {
		v.visitPetaCereza(this);
	}

	public void resetDisparo() {
		this.cooldownAccion = Integer.parseInt(config.getProperty("cooldownaccion_petaCereza"));
	}

}
