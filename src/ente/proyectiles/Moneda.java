package ente.proyectiles;

import java.awt.Point;
import java.util.Properties;

import ente.grafico.EnteGrafico;
import ente.zombi.visitor.Visitor;

public class Moneda extends Proyectil {
	private int valor;
	private int cooldown;
	protected Boolean generatePlanta;

	public Moneda(Point position, Properties p) {
		this.config = p;
		this.setLocation(position);
		this.cooldown = Integer.parseInt(config.getProperty("cooldown_moneda"));
		this.width = Integer.parseInt(config.getProperty("ancho_moneda"));
		this.height = Integer.parseInt(config.getProperty("alto_emoneda"));
		this.danio = Integer.parseInt(config.getProperty("danio_moneda"));
		this.velocidad = Integer.parseInt(config.getProperty("velocidad_moneda"));
		this.grafico = new EnteGrafico(position, this.width, this.height, config.getProperty("skin_moneda"));
	}
	
	public int getValor() {
		return valor;
	}
	
	public void actualizar() {
		cooldown--;
	} 
	
	public void accept(Visitor v) {
		v.visitProyectil(this);
	}
}
