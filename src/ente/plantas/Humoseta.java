package ente.plantas;

import java.awt.Point;
import java.util.Properties;

import ente.grafico.EnteGrafico;
import ente.proyectiles.Proyectil;
import ente.zombi.visitor.Visitor;

public class Humoseta extends Planta {
	
	public Humoseta(Point position, Properties p) {		
		this.setLocation(position);
		this.config = p;
		this.salud = Integer.parseInt(config.getProperty("salud_humoseta"));
		this.precio = Integer.parseInt(config.getProperty("precio_humoseta"));
		this.cooldownAccion = Integer.parseInt(config.getProperty("cooldownaccion_humoseta"));
		this.cooldownCompra = Integer.parseInt(config.getProperty("cooldowncompra_humoseta"));	
		this.width = Integer.parseInt(config.getProperty("ancho_humoseta"));
		this.height = Integer.parseInt(config.getProperty("alto_humoseta"));
		this.grafico = new EnteGrafico(position, this.width, this.height, config.getProperty("skin_humoseta"));
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
