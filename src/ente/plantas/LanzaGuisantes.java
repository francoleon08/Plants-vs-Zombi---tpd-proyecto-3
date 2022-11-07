package ente.plantas;

import java.awt.Point;
import java.util.Properties;

import ente.grafico.EnteGrafico;
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
		this.grafico = new EnteGrafico(position, this.width, this.height, config.getProperty("skin_lanzaGuisantes"));
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
