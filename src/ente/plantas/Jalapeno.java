package ente.plantas;

import java.awt.Point;
import java.util.Properties;

import ente.grafico.EnteGrafico;
import ente.proyectiles.Explosion;
import ente.proyectiles.Proyectil;
import ente.zombi.visitor.Visitor;

public class Jalapeno extends Planta {
	
	public Jalapeno(Point position, Properties p) {
		this.setLocation(position);
		this.config = p;
		this.salud = Integer.parseInt(config.getProperty("salud_jalapeno"));
		this.danio = Integer.parseInt(config.getProperty("danio_jalapeno"));
		this.precio = Integer.parseInt(config.getProperty("precio_jalapeno"));
		this.cooldownAccion = Integer.parseInt(config.getProperty("cooldownaccion_jalapeno"));
		this.cooldownCompra = Integer.parseInt(config.getProperty("cooldowncompra_jalapeno"));	
		this.width = Integer.parseInt(config.getProperty("ancho_jalapeno"));
		this.height = Integer.parseInt(config.getProperty("alto_jalapeno"));
		this.icon = new EnteGrafico(null, 100, 100, config.getProperty("icon_jalapeno_desactivado"));
		this.grafico = new EnteGrafico(this.getLocation(), this.width, this.height, config.getProperty("skin_jalapeno"));
	}

	public Proyectil disparar() {
		this.salud = 0;
		return new Explosion(this.getLocation(), config);
	}

	public Planta clone() {
		return new Jalapeno(this.getLocation(), config);
	}
	
	public int getDanio() {
		return this.danio;
	}

	public void accept(Visitor v) {
		v.visitJalapeno(this);
	}
	
	public void actualizarCompra() {
		if(cooldownCompra > 0)
			cooldownCompra--;
		else {
			this.icon.setSkin(config.getProperty("icon_jalapeno"));			
		}
	}
	

	public void resetDisparo() {
		this.cooldownAccion = Integer.parseInt(config.getProperty("cooldownaccion_jalapeno"));
	}
	
	public void resetCompra() {
		this.cooldownCompra = Integer.parseInt(config.getProperty("cooldowncompra_jalapeno"));	
		this.icon.setSkin(config.getProperty("icon_jalapeno_desactivado"));		
	}

}
