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
		this.setLocation(punto);
		this.width= Integer.parseInt(config.getProperty("ancho_espora"));
		this.height=Integer.parseInt(config.getProperty("alto_espora"));
		this.grafico=new EnteGrafico(punto, this.width, this.height, config.getProperty("skin_espora"));
		this.danio=Integer.parseInt(config.getProperty("danio_espora"));
		this.velocidad=Integer.parseInt(config.getProperty("velovidad_espora"));
		this.distancia=Integer.parseInt(config.getProperty("distancia_espora"));
		
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
