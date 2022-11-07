package ente.proyectiles;

<<<<<<< HEAD
import ente.grafico.EnteGrafico;
import ente.zombi.visitor.Visitor;
=======
import java.awt.Point;
>>>>>>> branch 'main' of https://github.com/tonyy7/tdp-proyecto-3

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

	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		
	}
	
	public void accept(Visitor v) {
		v.visitProyectil(this);
	}
}
