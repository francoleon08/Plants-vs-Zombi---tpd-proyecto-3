package ente.proyectiles;

import java.awt.Point;
import java.util.Properties;

import ente.grafico.EnteGrafico;
import ente.zombi.visitor.Visitor;

public class RafagaEspora extends Proyectil{
	private int duracion;
	
	public RafagaEspora(Point position, Properties p) {
		this.setLocation(position);
		this.config = p;		
		this.choqueZombie = false;
		this.width = Integer.parseInt(config.getProperty("ancho_rafaga_espora"));
		this.height = Integer.parseInt(config.getProperty("alto_rafaga_espora"));
		this.danio = Integer.parseInt(config.getProperty("danio_rafaga_espora"));
		this.velocidad = Integer.parseInt(config.getProperty("velocidad_rafaga_espora"));
		this.duracion = 100*Integer.parseInt(config.getProperty("duracion_rafaga_espora"));
		this.grafico = new EnteGrafico(this.getLocation(), this.width, this.height, config.getProperty("skin_rafaga_espora"));
	}

	@Override
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
