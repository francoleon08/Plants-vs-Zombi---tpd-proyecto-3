package ente.proyectiles;

import ente.grafico.EnteGrafico;
import ente.zombi.visitor.Visitor;
import java.awt.Point;
import java.util.Properties;

/**
 * The Class Guizante.
 */
@SuppressWarnings("serial")
public class Guizante extends Proyectil {
	
	public Guizante(Point position, Properties p ) {
		this.config=p;
		this.setLocation(position);
		this.width= Integer.parseInt(config.getProperty("ancho_guizante"));
		this.height=Integer.parseInt(config.getProperty("alto_guizante"));
		this.danio=Integer.parseInt(config.getProperty("danio_guizante"));
		this.velocidad=Integer.parseInt(config.getProperty("velocidad_guizante"));
		this.grafico=new EnteGrafico(position, this.width, this.height, config.getProperty("skin_guizante"));
	}

	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		
	}
}
