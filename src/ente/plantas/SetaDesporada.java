package ente.plantas;

import java.awt.Point;
import java.util.Properties;

import ente.grafico.EnteGrafico;
import ente.proyectiles.Espora;
import ente.proyectiles.Proyectil;
import ente.zombi.visitor.Visitor;

public class SetaDesporada extends Planta {
	
	public SetaDesporada(Point position, Properties p) {
		this.setLocation(position);
		this.config = p;
		this.salud = Integer.parseInt(config.getProperty("salud_setaDesporada"));
		this.precio = Integer.parseInt(config.getProperty("precio_setaDesporada"));
		this.cooldownAccion = Integer.parseInt(config.getProperty("cooldownaccion_setaDesporada"));
		this.cooldownCompra = Integer.parseInt(config.getProperty("cooldowncompra_setaDesporada"));	
		this.width = Integer.parseInt(config.getProperty("ancho_setaDesporada"));
		this.height = Integer.parseInt(config.getProperty("alto_setaDesporada"));
		this.icon = new EnteGrafico(null, 100, 100, config.getProperty("icon_setaDesporada"));
		this.grafico = new EnteGrafico(position, this.width, this.height, config.getProperty("skin_setaDesporada"));
	}

	public Proyectil disparar() {
		Proyectil disparo =  null;
		Point aux = new Point(0,0);
		if(this.cooldownAccion == 0) {
			aux.setLocation(this.getLocation().getX()+10, this.getLocation().getY()+30);
			disparo = new Espora(aux, this.config);
		}
		return disparo;
	}

	@Override
	public Planta clone() {
		return new SetaDesporada(this.getLocation(), config);
	}

	@Override
	public void accept(Visitor v) {
		v.visitSetaDesporada(this);
	}
	
	public void actualizarCompra() {
		if(cooldownCompra > 0)
			cooldownCompra--;
		else {
			//ICONO ACTUIVADO			
		}
	}

	public void resetDisparo() {
		this.cooldownAccion = Integer.parseInt(config.getProperty("cooldownaccion_setaDesporada"));
	}

	public void resetCompra() {
		this.cooldownCompra = Integer.parseInt(config.getProperty("cooldowncompra_setaDesporada"));
		//ICONO DESACTIVADO
	}

}
