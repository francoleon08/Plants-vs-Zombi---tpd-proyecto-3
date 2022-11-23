package ente.plantas;

import java.awt.Point;
import java.util.Properties;

import ente.grafico.EnteGrafico;
import ente.proyectiles.Espora;
import ente.proyectiles.Proyectil;
import ente.proyectiles.RafagaEspora;
import ente.zombi.visitor.Visitor;

public class Humoseta extends Planta {
	
	public Humoseta(Point position, Properties p) {		
		this.setLocation(position);
		this.danio = 0;
		this.config = p;
		this.salud = Integer.parseInt(config.getProperty("salud_humoseta"));
		this.precio = Integer.parseInt(config.getProperty("precio_humoseta"));
		this.cooldownAccion = Integer.parseInt(config.getProperty("cooldownaccion_humoseta"));
		this.cooldownCompra = Integer.parseInt(config.getProperty("cooldowncompra_humoseta"));	
		this.width = Integer.parseInt(config.getProperty("ancho_humoseta"));
		this.height = Integer.parseInt(config.getProperty("alto_humoseta"));
		this.icon = new EnteGrafico(null, 100, 100, config.getProperty("icon_humoseta_desactivado"));
		this.grafico = new EnteGrafico(this.getLocation(), this.width, this.height, config.getProperty("skin_humoseta"));
	}

	@Override
	public Proyectil disparar() {
		Proyectil disparo =  null;
		Point aux = new Point(0,0);
		if(this.cooldownAccion == 0) {
			aux.setLocation(this.getLocation().getX()+30, this.getLocation().getY()+30);
			disparo = new RafagaEspora(aux, this.config);
		}
		return disparo;
	}
	
	public void actualizarCompra() {
		if(cooldownCompra > 0)
			cooldownCompra--;
		else {
			this.icon.setSkin(config.getProperty("icon_humoseta"));			
		}
	}

	public Planta clone() {
		return new Humoseta(this.getLocation(), config);
	}

	public void accept(Visitor v) {
		v.visitHumoseta(this);
	}

	public void resetDisparo() {
		this.cooldownAccion = Integer.parseInt(config.getProperty("cooldownaccion_humoseta"));
	}
	
	public void resetCompra() {
		this.cooldownCompra = Integer.parseInt(config.getProperty("cooldowncompra_humoseta"));
		this.icon.setSkin(config.getProperty("icon_humoseta_desactivado"));	
	}

}
