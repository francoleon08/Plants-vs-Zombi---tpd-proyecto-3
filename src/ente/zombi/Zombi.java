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
	private boolean run;
	
	public Zombi(boolean abanderado, boolean caracono, boolean lector, boolean portero, int salud, int danio, Point position,String skin) {
		this.salud = salud;
		this.danio = danio;
		this.velocidad = 1;
		this.abanderado = abanderado;
		this.caracono = caracono;
		this.lector = lector;
		this.portero = portero;
		this.run = true;
		this.setLocation(position);
		this.width = 80; //tamanio de la hitbox
		this.height = 100;
		
		this.grafico = new EnteGrafico(this.getLocation(), this.width, this.height, skin);		
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

	public void actualizar() {
		if(run) {			
			this.getPosition().translate(-velocidad, 0);
			this.grafico.update();
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

	public void visitPetaCereza(Petacereza p) {
		this.run = false;
		this.salud = 0;
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

	@Override
	public void visitProyectil(Proyectil p) {		
		this.salud -= p.getDanio();
		lectorPeriodico();
	}

	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub		
	}
	
	private void lectorPeriodico() {
		if(lector && salud <= 40 && velocidad == 1)
			velocidad *=5;
	}
}
