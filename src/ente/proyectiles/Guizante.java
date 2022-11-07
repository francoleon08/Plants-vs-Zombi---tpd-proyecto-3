package ente.proyectiles;

import java.awt.Point;

import ente.grafico.EnteGrafico;
import ente.zombi.visitor.Visitor;

/**
 * The Class Guizante.
 */
@SuppressWarnings("serial")
public class Guizante extends Proyectil {
	
	public Guizante(Point punto,EnteGrafico grafico,int velocidad, int danio ) {
		//Generear archivo de configureacion.
		this.width=100;
		this.height=100;
		this.setLocation(punto);
		this.grafico=grafico;
		this.danio=danio;
		this.velocidad=velocidad;
	}
	
	public void accept(Visitor v) {
		v.visitProyectil(this);
	}
}
