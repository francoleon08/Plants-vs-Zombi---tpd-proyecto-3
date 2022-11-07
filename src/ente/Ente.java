package ente;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.FileInputStream;
import java.util.Properties;

import ente.grafico.EnteGrafico;
import ente.zombi.visitor.Visitor;

public abstract class Ente extends Rectangle {
	protected EnteGrafico grafico;
	protected Properties config;
	
	public Point getPosition() {
		return this.getLocation();
	}

	public void setPosition(Point position) {
		this.setLocation(position);
	}
	
	public abstract void accept(Visitor v);
	
	public abstract void actualizar();
	

}
