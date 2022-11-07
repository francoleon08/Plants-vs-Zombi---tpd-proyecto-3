package ente.proyectiles;

import java.awt.Point;
import java.util.Properties;

import ente.grafico.EnteGrafico;
import ente.zombi.visitor.Visitor;

// TODO: Auto-generated Javadoc
/**
 * The Class Espora.
 */

public class Espora extends Proyectil {
	
	/** The distancia. */
	private int distancia;
	
	/**
	 * Instantiates a new espora.
	 *
	 * @param punto the punto
	 * @param grafico the grafico
	 * @param velocidad the velocidad
	 * @param distancia the distancia
	 * @param danio the danio
	 */
	public Espora(Point punto,EnteGrafico grafico,int velocidad, int distancia, int danio, Properties p ) {
		//Generear archivo de configureacion.
		this.config=p;
		this.width= Integer.parseInt(config.getProperty("ancho_espora"));
		this.height=Integer.parseInt(config.getProperty("alto_espora"));
		this.setLocation(punto);
		this.grafico=grafico;
		this.danio=danio;
		this.velocidad=velocidad;
		this.distancia=distancia;
		
	}
	
	/**
	 * Accept.
	 *
	 * @param v the v
	 */
	public void accept(Visitor v) {
		v.visitProyectil(this);
	}
	
	/**
	 * Gets the distancia.
	 *
	 * @return Distancia a la que llega la espora.
	 */
	public int getDistancia() {
		return this.distancia;}	
	
}
