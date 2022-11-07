package ente.proyectiles;

import ente.grafico.EnteGrafico;
import ente.zombi.visitor.Visitor;

public class Guizante extends Proyectil {
	public Guizante(int x, int y, int width, int height, EnteGrafico grafico) {
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.grafico=grafico;
	}

	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		
	}
	
}
