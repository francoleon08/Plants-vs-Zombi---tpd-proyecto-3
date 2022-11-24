package ente.plantas;

import java.awt.Point;
import java.util.Properties;

import ente.grafico.EnteGrafico;
import ente.proyectiles.Moneda;
import ente.proyectiles.Proyectil;
import ente.zombi.visitor.Visitor;

public class SetaSolar extends Planta {
	private int contador;
	
	
	public SetaSolar(Point position, Properties p) {
		this.setLocation(position);
		this.danio = 0;
		this.config = p;
		this.contador = Integer.parseInt(config.getProperty("contador_setaSolar"));
		this.salud = Integer.parseInt(config.getProperty("salud_setaSolar"));
		this.precio = Integer.parseInt(config.getProperty("precio_setaSolar"));
		this.cooldownAccion = Integer.parseInt(config.getProperty("cooldownaccion_setaSolar"));
		this.cooldownCompra = Integer.parseInt(config.getProperty("cooldowncompra_setaSolar"));	
		this.width = Integer.parseInt(config.getProperty("ancho_setaSolar"));
		this.height = Integer.parseInt(config.getProperty("alto_setaSolar"));
		this.icon = new EnteGrafico(null, 100, 100, config.getProperty("icon_setaSolar_desactivado"));
		this.grafico = new EnteGrafico(position, this.width, this.height, config.getProperty("skin_setaSolar"));
	}
	
	@Override
	public Proyectil disparar() {
		Proyectil disparo =  null;
		Point aux = new Point(0,0);
		if(this.cooldownAccion == 0) {
			aux.setLocation(this.getLocation().getX()+50, this.getLocation().getY());
			disparo = new Moneda(aux, this.config);
			if(contador > 0)
				contador--;
		}
		if (contador == 0)
			crecer();
		return disparo;
	}

	private void crecer() {
		System.out.println("Crecí");
		this.grafico.setSkin(config.getProperty("skin_setaSolar2"));
		this.salud = Integer.parseInt(config.getProperty("salud_setaSolar2"));
	}
	
	public Planta clone() {
		return new SetaSolar(this.getLocation(), config);
	}

	public void accept(Visitor v) {
		v.visitSetaSolar(this);
	}
	
	public void actualizarCompra() {
		if(cooldownCompra > 0)
			cooldownCompra--;
		else {
			this.icon.setSkin(config.getProperty("icon_setaSolar"));		
		}
	}

	public void resetDisparo() {
		this.cooldownAccion = Integer.parseInt(config.getProperty("cooldownaccion_setaSolar"));
	}
	
	public void resetCompra() {
		this.cooldownCompra = Integer.parseInt(config.getProperty("cooldowncompra_setaSolar"));	
		this.icon.setSkin(config.getProperty("icon_setaSolar_desactivado"));	
	}

}
