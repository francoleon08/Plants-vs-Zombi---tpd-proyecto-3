package ente.proyectiles;


import java.awt.Point;
import java.util.Properties;

import ente.grafico.EnteGrafico;
import ente.zombi.visitor.Visitor;

public class Explosion extends Proyectil{

	int duracion;
	
	public Explosion(Point position, Properties p) {
		this.choqueZombie = false;
		this.config = p;
		this.setLocation(0, (int) position.getY());
		this.width= Integer.parseInt(config.getProperty("ancho_explosion"));
		this.height=Integer.parseInt(config.getProperty("alto_explosion"));
		this.danio=Integer.parseInt(config.getProperty("danio_explosion"));
		this.velocidad=Integer.parseInt(config.getProperty("velocidad_explosion"));
		this.grafico=new EnteGrafico(position, this.width, this.height, config.getProperty("skin_explosion"));
		this.duracion = 100*Integer.parseInt(config.getProperty("duracion_explosion"));
	}
	
	public void accept(Visitor v) {
		v.visitProyectil(this);
	}
	
	public void actualizar() {
		duracion--;
	}
	
	public boolean condicionElim(int lim) {
		return duracion == 0; 
	}

}
