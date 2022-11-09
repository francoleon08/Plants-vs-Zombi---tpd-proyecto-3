package ente.plantas;

import java.awt.Point;
import java.util.Properties;

import ente.grafico.EnteGrafico;
import ente.proyectiles.Proyectil;
import ente.zombi.visitor.Visitor;

public class SetaSolar extends Planta {

	public SetaSolar(Point position, Properties p) {
		this.setLocation(position);
		this.config = p;
		this.salud = Integer.parseInt(config.getProperty("salud_setaSolar"));
		this.precio = Integer.parseInt(config.getProperty("precio_setaSolar"));
		this.cooldownAccion = Integer.parseInt(config.getProperty("cooldownaccion_setaSolar"));
		this.cooldownCompra = Integer.parseInt(config.getProperty("cooldowncompra_setaSolar"));	
		this.width = Integer.parseInt(config.getProperty("ancho_setaSolar"));
		this.height = Integer.parseInt(config.getProperty("alto_setaSolar"));
		this.grafico = new EnteGrafico(position, this.width, this.height, config.getProperty("skin_setaSolar"));
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
		return new SetaSolar(this.getLocation(), config);
	}

	public void accept(Visitor v) {
		v.visitSetaSolar(this);
	}

	public void resetDisparo() {
		this.cooldownAccion = Integer.parseInt(config.getProperty("cooldownaccion_setaSolar"));
	}

	public String getSkinBoton() {
		return config.getProperty("skin_boton_setaSolar");
	}

}
