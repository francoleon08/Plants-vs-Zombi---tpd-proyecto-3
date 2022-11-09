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
		this.config = p;
		this.salud = Integer.parseInt(config.getProperty("salud_lanzaGuisantes"));
		this.precio = Integer.parseInt(config.getProperty("precio_lanzaGuisantes"));
		this.cooldownAccion = Integer.parseInt(config.getProperty("cooldownaccion_lanzaGuisantes"));
		this.cooldownCompra = Integer.parseInt(config.getProperty("cooldowncompra_lanzaGuisantes"));	
		this.width = Integer.parseInt(config.getProperty("ancho_lanzaGuisantes"));
		this.height = Integer.parseInt(config.getProperty("alto_lanzaGuisantes"));
		this.grafico = new EnteGrafico(this.getLocation(), this.width, this.height, config.getProperty("skin_lanzaGuisantes"));
	}

	@Override
	public Proyectil disparar() {
		Proyectil disparo =  null;
		Point aux = new Point(0,0);
		if(this.cooldownAccion == 0) {
			aux.setLocation(this.getLocation().getX()+10, this.getLocation().getY());
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

	public void resetDisparo() {
		this.cooldownAccion = Integer.parseInt(config.getProperty("cooldownaccion_lanzaGuisantes"));
	}

	public String getSkinBoton() {
		return config.getProperty("skin_boton_lanzaGuizantes");
	}

}
