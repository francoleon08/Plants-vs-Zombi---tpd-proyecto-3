package ente.plantas;

import java.awt.Point;
import java.util.Properties;

import ente.grafico.EnteGrafico;
import ente.proyectiles.Moneda;
import ente.proyectiles.Proyectil;
import ente.zombi.visitor.Visitor;

public class Girasol extends Planta {
	
	public Girasol(Point position, Properties p) {
		this.setLocation(position);
		this.config = p;
		this.salud = Integer.parseInt(config.getProperty("salud_girasol"));
		this.precio = Integer.parseInt(config.getProperty("precio_girasol"));
		this.cooldownAccion = 4;
		this.cooldownCompra = Integer.parseInt(config.getProperty("cooldowncompra_girasol"));	
		this.width = Integer.parseInt(config.getProperty("ancho_girasol"));
		this.height = Integer.parseInt(config.getProperty("alto_girasol"));
		this.icon = new EnteGrafico(null, 100, 100, config.getProperty("icon_girasol_desactivado"));
		this.grafico = new EnteGrafico(this.getLocation(), this.width, this.height, config.getProperty("skin_girasol"));
	}

	@Override
	public Proyectil disparar() {
		Proyectil disparo =  null;
		Point aux = new Point(0,0);
		if(this.cooldownAccion == 0) {
			aux.setLocation(this.getLocation().getX()+50, this.getLocation().getY());
			disparo = new Moneda(aux, this.config);
		}
		return disparo;
	}

	public Planta clone() {
		return new Girasol(this.getLocation(), config);
	}

	public void accept(Visitor v) {
		v.visitGirasol(this);
	}	

	public void resetDisparo() {		
		this.cooldownAccion = Integer.parseInt(config.getProperty("cooldownaccion_girasol"));
	}
	
	public void actualizarCompra() {
		if(cooldownCompra > 0)
			cooldownCompra--;
		else {
			this.icon.setSkin(config.getProperty("icon_girasol"));			
		}
	}

	public void resetCompra() {
		this.cooldownCompra = Integer.parseInt(config.getProperty("cooldowncompra_girasol"));
		this.icon.setSkin(config.getProperty("icon_girasol_desactivado"));
	}
}
