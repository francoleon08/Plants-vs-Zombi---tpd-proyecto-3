package ente.proyectiles;

import java.awt.Point;

import ente.zombi.visitor.Visitor;

public class Moneda extends Proyectil {
	private int valor;
	protected Boolean generatePlanta;

	public Moneda(Point p) {
		//generar archivo de configuracion
		this.width=100;
		this.height=100;
		this.setPosition(p);
		this.valor=25;
		this.danio=0;
		this.velocidad=0;

	}
	public int getValor() {
		return valor;
	}
	public void accept(Visitor v) {
		v.visitProyectil(this);
	}

}
