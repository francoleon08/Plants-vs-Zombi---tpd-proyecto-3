package ente.plantas;

import java.awt.Point;
import java.util.Properties;

import ente.grafico.EnteGrafico;
import ente.proyectiles.Guizante;
import ente.proyectiles.Proyectil;
import ente.zombi.visitor.Visitor;

public class Jalapeno extends Planta {
	
	public Jalapeno(Point position, Properties p) {
		this.setLocation(position);
		this.config = p;
		this.salud = Integer.parseInt(config.getProperty("salud_jalapeno"));
		this.precio = Integer.parseInt(config.getProperty("precio_jalapeno"));
		this.cooldownAccion = Integer.parseInt(config.getProperty("cooldownaccion_jalapeno"));
		this.cooldownCompra = Integer.parseInt(config.getProperty("cooldowncompra_jalapeno"));	
		this.width = Integer.parseInt(config.getProperty("ancho_jalapeno"));
		this.height = Integer.parseInt(config.getProperty("alto_jalapeno"));
		this.grafico = new EnteGrafico(this.getLocation(), this.width, this.height, config.getProperty("skin_jalapeno"));
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
		return new Jalapeno(this.getLocation(), config);
	}

	public void accept(Visitor v) {
		v.visitJalapeno(this);
	}

	public void resetDisparo() {
		this.cooldownAccion = Integer.parseInt(config.getProperty("cooldownaccion_petaCereza"));
	}

	public String getSkinBoton() {
		return config.getProperty("skin_boton_jalapeno");
	}

}
