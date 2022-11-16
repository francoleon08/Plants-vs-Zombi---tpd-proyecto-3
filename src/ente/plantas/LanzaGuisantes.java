package ente.plantas;

import java.awt.Point;
import java.util.Properties;

import ente.grafico.EnteGrafico;
import ente.proyectiles.Guizante;
import ente.proyectiles.Proyectil;
import ente.zombi.visitor.Visitor;

public class LanzaGuisantes extends Planta {
	
	public LanzaGuisantes(Point position, Properties p) {
		this.setLocation(position);
		this.danio = 0;
		this.config = p;
		this.salud = Integer.parseInt(config.getProperty("salud_lanzaGuisantes"));
		this.precio = Integer.parseInt(config.getProperty("precio_lanzaGuisantes"));
		this.cooldownAccion = Integer.parseInt(config.getProperty("cooldownaccion_lanzaGuisantes"));
		this.cooldownCompra = Integer.parseInt(config.getProperty("cooldowncompra_lanzaGuisantes"));	
		this.width = Integer.parseInt(config.getProperty("ancho_lanzaGuisantes"));
		this.height = Integer.parseInt(config.getProperty("alto_lanzaGuisantes"));
		this.icon = new EnteGrafico(null, 100, 100, config.getProperty("icon_lanzaGuisantes_desactivado"));
		this.grafico = new EnteGrafico(this.getLocation(), this.width, this.height, config.getProperty("skin_lanzaGuisantes"));
	}
	
	public Proyectil disparar() {
		Proyectil disparo =  null;
		Point aux = new Point(0,0);
		if(this.cooldownAccion == 0) {
			aux.setLocation(this.getLocation().getX()+10, this.getLocation().getY()+10);
			disparo = new Guizante(aux, this.config);
		}
		return disparo;
	}

	public Planta clone() {
		return new LanzaGuisantes(this.getLocation(), config);
	}

	public void accept(Visitor v) {
		v.visitLanzaGuisantes(this);
	}
	
	public void actualizarCompra() {
		if(cooldownCompra > 0)
			cooldownCompra--;
		else {
			this.icon.setSkin(config.getProperty("icon_lanzaGuisantes"));			
		}
	}

	public void resetDisparo() {
		this.cooldownAccion = Integer.parseInt(config.getProperty("cooldownaccion_lanzaGuisantes"));	
	}

	public void resetCompra() {
		this.cooldownCompra = Integer.parseInt(config.getProperty("cooldowncompra_lanzaGuisantes"));
		this.icon.setSkin(config.getProperty("icon_lanzaGuisantes_desactivado"));
	}

}
