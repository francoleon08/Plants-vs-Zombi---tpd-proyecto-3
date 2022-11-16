package ente.plantas;

import java.awt.Point;
import java.util.Properties;

import ente.grafico.EnteGrafico;
import ente.proyectiles.Proyectil;
import ente.zombi.visitor.Visitor;

public class Nuez extends Planta {

	public Nuez(Point position, Properties p) {
		this.setLocation(position);
		this.danio = 0;
		this.config = p;
		this.salud = Integer.parseInt(config.getProperty("salud_nuez"));
		this.precio = Integer.parseInt(config.getProperty("precio_nuez"));
		this.cooldownAccion = 4;
		this.cooldownCompra = Integer.parseInt(config.getProperty("cooldowncompra_nuez"));	
		this.width = Integer.parseInt(config.getProperty("ancho_nuez"));
		this.height = Integer.parseInt(config.getProperty("alto_nuez"));
		this.icon = new EnteGrafico(null, 100, 100, config.getProperty("icon_nuez_desactivado"));
		this.grafico = new EnteGrafico(this.getLocation(), this.width, this.height, config.getProperty("skin_nuez"));
	}

	public void actualizarCompra() {
		if(cooldownCompra > 0)
			cooldownCompra--;
		else {
			this.icon.setSkin(config.getProperty("icon_nuez"));			
		}
	}

	public Proyectil disparar() {
		// TODO Auto-generated method stub
		return null;
	}

	public void resetDisparo() {
		this.cooldownAccion = Integer.parseInt(config.getProperty("cooldownaccion_nuez"));
	}

	public void resetCompra() {
		this.cooldownCompra = Integer.parseInt(config.getProperty("cooldowncompra_nuez"));
		this.icon.setSkin(config.getProperty("icon_nuez_desactivado"));
	}

	public Planta clone() {
		return new Nuez(this.getLocation(), config);
	}

	public void accept(Visitor v) {
		v.visitNuez(this);
	}

}
