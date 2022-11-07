package ente;

import java.awt.Point;
import java.awt.Rectangle;

import ente.grafico.EnteGrafico;
import ente.zombi.visitor.Visitor;

public abstract class Ente extends Rectangle {
	protected Point position;
	protected EnteGrafico grafico;
	
	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}
	
	public abstract void accept(Visitor v);
	
	public abstract void actualizar();
}
