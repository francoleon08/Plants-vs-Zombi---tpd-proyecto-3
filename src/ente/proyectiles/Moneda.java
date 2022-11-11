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
		this.valor = Integer.parseInt(config.getProperty("valor_moneda"));
		this.width = Integer.parseInt(config.getProperty("ancho_moneda"));
		this.height = Integer.parseInt(config.getProperty("alto_moneda"));
		this.danio = Integer.parseInt(config.getProperty("danio_moneda"));
		this.velocidad = Integer.parseInt(config.getProperty("valocidad_moneda"));
		this.grafico = new EnteGrafico(position, this.width, this.height, config.getProperty("skin_moneda"));
	}
	
	public int getValor() {
		return valor;
	}
	
	public void actualizar() {
		cooldown--;
	} 
	
	public void actualizarCaida() {
		if(this.getLocation().getY() < Integer.parseInt(config.getProperty("minPosicionMoneda"))) {
			Point aux = new Point();
			aux.setLocation(this.getX(), this.getY()+1);
			this.setPosition(aux);
		}
	}
	
	public void accept(Visitor v) {
		v.visitProyectil(this);
	}
}
