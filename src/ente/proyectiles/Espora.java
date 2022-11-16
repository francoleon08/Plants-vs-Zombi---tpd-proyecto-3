package ente.proyectiles;

import java.awt.Point;
import java.util.Properties;

import ente.grafico.EnteGrafico;
import ente.zombi.visitor.Visitor;

/**
 * The Class Espora.
 */
public class Espora extends Proyectil {
	private int distancia;
	
	/**
	 * Instantiates a new espora.
	 * @param punto the punto
	 * @param p de Properites
	 * @param index 0->rafaga, 1->espora
	 */
	public Espora(Point position, Properties p, int index) {
		this.setLocation(position);
		this.config = p;		
		if(index == 0) {
			this.width = Integer.parseInt(config.getProperty("ancho_rafaga_espora"));
			this.height = Integer.parseInt(config.getProperty("alto_rafaga_espora"));
			this.danio = Integer.parseInt(config.getProperty("danio_rafaga_espora"));
			this.velocidad = Integer.parseInt(config.getProperty("velocidad_rafaga_espora"));
			this.distancia = Integer.parseInt(config.getProperty("distancia_rafaga_espora"));
			this.grafico = new EnteGrafico(position, this.width, this.height, config.getProperty("skin_rafaga_espora"));
		}
		else {
			if(index == 1) {
				this.width = Integer.parseInt(config.getProperty("ancho_espora"));
				this.height = Integer.parseInt(config.getProperty("alto_espora"));
				this.danio = Integer.parseInt(config.getProperty("danio_espora"));
				this.velocidad = Integer.parseInt(config.getProperty("velocidad_espora"));
				this.distancia = Integer.parseInt(config.getProperty("distancia_espora"));
				this.grafico = new EnteGrafico(this.getLocation(), this.width, this.height, config.getProperty("skin_espora"));
			}
		}
	}

	/**
	 * Accept.
	 * @param v the v
	 */
	public void accept(Visitor v) {
		v.visitProyectil(this);
	}

	
	/**
	 * Gets the distancia.
	 * @return Distancia a la que llega la espora.
	 */
	public int getDistancia() {
		return this.distancia;
	}
}
