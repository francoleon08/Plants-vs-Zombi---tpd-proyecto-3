package ente.proyectiles;

import java.awt.Point;
import java.util.Properties;

import ente.grafico.EnteGrafico;
import ente.zombi.visitor.Visitor;

/**
 * The Class Guizante.
 */
@SuppressWarnings("serial")
public class Guizante extends Proyectil {
	
	public Guizante(Point punto,EnteGrafico grafico,int velocidad, int danio, Properties p ) {
		this.config=p;
		this.setLocation(punto);
		this.width= Integer.parseInt(config.getProperty("ancho_guizante"));
		this.height=Integer.parseInt(config.getProperty("alto_guizante"));
		this.grafico=new EnteGrafico(punto, this.width, this.height, config.getProperty("skin_guizante"));
		this.danio=Integer.parseInt(config.getProperty("danio_guizante"));
		this.velocidad=Integer.parseInt(config.getProperty("velocidad_guizante"));
	}
	
	public void accept(Visitor v) {
		v.visitProyectil(this);
	}
}
