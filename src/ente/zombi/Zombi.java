package ente.zombi;

import java.awt.Point;

import ente.Ente;
import ente.grafico.EnteGrafico;
import ente.plantas.*;
import ente.proyectiles.Proyectil;
import ente.zombi.visitor.Visitor;

public class Zombi extends Ente implements Visitor {
	private int salud;
	private int danio;
	private int velocidad;
	private boolean abanderado;
	private boolean caracono;
	private boolean lector;
	private boolean portero;
	private boolean bailarin;
	private boolean run;
	
	public Zombi(boolean abanderado, boolean caracono, boolean lector, boolean portero, boolean bailarin, int salud, int danio, Point position, String skin) {
		this.salud = salud;
		this.danio = 1;
		this.velocidad = 1;
		this.abanderado = abanderado;
		this.caracono = caracono;
		this.lector = lector;
		this.portero = portero;
		this.bailarin = bailarin;
		this.run = true;
		this.setLocation(position);
		this.width = 64;
		this.height = 100;
		
		init();
		
		this.grafico = new EnteGrafico(this.getLocation(), this.width, this.height, skin);		
	}
	
	private void init() {
		if(bailarin) {
			velocidad *= 2;
		}
	}
	
	public boolean estaVivo() {
		return salud > 0;
	}

	public boolean isAbanderado() {
		return abanderado;
	}

	public void setAbanderado(boolean abanderado) {
		this.abanderado = abanderado;
	}

	public boolean isCaracono() {
		return caracono;
	}

	public void setCaracono(boolean caracono) {
		this.caracono = caracono;
	}

	public boolean isLector() {
		return lector;
	}

	public void setLector(boolean lector) {
		this.lector = lector;
	}

	public boolean isPortero() {
		return portero;
	}
	
	public void setPortero(boolean portero) {
		this.portero = portero;
	}
	
	public boolean isBailarin() {
		return bailarin;
	}
	
	public void setBailarin(boolean bailarin) {
		this.bailarin = bailarin;
	}

	public void actualizar() {
		Point aux = new Point();
		aux.setLocation(this.getLocation().getX()-velocidad, this.getLocation().getY());	
		if(run) {			
			this.setLocation(aux);
			this.grafico.update(this.getLocation());
			if((int) this.getLocation().getX() <= 10) {
				run = false;			
				//cambiar animacion
			}
		}			
	}
	
	public void visitLanzaGuisantes(LanzaGuisantes p) {
		this.run = false;
		if(p.disminuirSalud(this.danio)) {
			this.run = true;
		}
	}

	public void visitGirasol(Girasol p) {		
		this.run = false;
		if(p.disminuirSalud(this.danio)) {
			this.run = true;
		}
	}

	public void visitJalapeno(Jalapeno p) {
		salud -= p.getDanio();
	}

	public void visitSetaDesporada(SetaDesporada p) {
		this.run = false;
		if(p.disminuirSalud(this.danio)) {
			this.run = true;
		}
	}

	public void visitSetaSolar(SetaSolar p) {
		this.run = false;
		if(p.disminuirSalud(this.danio)) {
			this.run = true;
		}
	}

	public void visitHumoseta(Humoseta p) {
		this.run = false;
		if(p.disminuirSalud(this.danio)) {
			this.run = true;
		}
	}
	
	public void visitNuez(Nuez p) {
		this.run = false;
		if(p.disminuirSalud(danio)) {
			this.run = true;
		}
	}

	public void visitProyectil(Proyectil p) {		
		this.salud -= p.getDanio();
		lectorPeriodico();
	}

	public void accept(Visitor v) {
		// TODO Auto-generated method stub		
	}
	
	private void lectorPeriodico() {
		if(lector && salud <= 40 && velocidad == 1) {
			velocidad *=3;
			//CAMIAR A LA SKIN CORRESPONDIENTE
			this.grafico.setSkin("assets\\imagenes\\zombis\\comunZombi.gif");
		}
			
	}
}
