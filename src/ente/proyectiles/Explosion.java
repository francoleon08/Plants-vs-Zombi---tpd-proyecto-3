package ente.proyectiles;


import java.awt.Point;
import java.util.Properties;

import ente.grafico.EnteGrafico;
import ente.zombi.visitor.Visitor;

public class Explosion extends Proyectil{

	public Explosion(Point position, Properties p) {
		this.config = p;
		this.setLocation(position);
		this.width= Integer.parseInt(config.getProperty("ancho_explosion"));
		this.height=Integer.parseInt(config.getProperty("alto_explosion"));
		this.danio=Integer.parseInt(config.getProperty("danio_explosion"));
		this.velocidad=Integer.parseInt(config.getProperty("velocidad_explosion"));
		this.grafico=new EnteGrafico(position, this.width, this.height, config.getProperty("skin_explosion"));
	}
	
	public void accept(Visitor v) {
		v.visitProyectil(this);
	}

}
