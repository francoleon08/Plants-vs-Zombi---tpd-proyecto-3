package ente;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Properties;

import ente.grafico.EnteGrafico;
import ente.zombi.visitor.Visitor;

/**
 * The Class Ente.
 */
public abstract class Ente extends Rectangle {
	protected EnteGrafico grafico;
	protected Properties config;
	
	/**
	 * Gets the position.
	 * @return Point position
	 */
	public Point getPosition() {
		return this.getLocation();
	}

	/**
	 * Sets the position.
	 * @param Point the new position
	 */
	public void setPosition(Point position) {
		this.setLocation(position);
	}
	
	/**
	 * Gets the ente grafico.
	 * @return the EnteGrafico grafico
	 */
	public EnteGrafico getEnteGrafico() {
		return grafico;
	}
	
	/**
	 * Accept.
	 * @param v the v
	 */
	public abstract void accept(Visitor v);
	
	/**
	 * Actualizar.
	 */
	public abstract void actualizar();
	
}
