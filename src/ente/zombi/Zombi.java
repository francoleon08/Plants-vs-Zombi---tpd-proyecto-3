package ente.zombi;

import ente.Ente;
import ente.plantas.Girasol;
import ente.plantas.Humoseta;
import ente.plantas.LanzaGuisantes;
import ente.plantas.Petacereza;
import ente.plantas.SetaDesporada;
import ente.plantas.SetaSolar;
import ente.proyectiles.Proyectil;
import ente.zombi.visitor.Visitor;

public class Zombi extends Ente implements Visitor {
	private int salud;
	private int danio;
	private boolean abanderado;
	private boolean caracono;
	private boolean lector;
	private boolean portero;
	private boolean run;
	
	public Zombi(boolean abanderado, boolean caracono, boolean lector, boolean portero, int salud, int danio,String skin) {
		this.salud = salud;
		this.danio = danio;
		this.abanderado = abanderado;
		this.caracono = caracono;
		this.lector = lector;
		this.portero = portero;
		this.run = true;
	}
	
	
	
	

	@Override
	public void visitLanzaGuisantes(LanzaGuisantes p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitGirasol(Girasol p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitPetaCereza(Petacereza p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitSetaDesporada(SetaDesporada p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitSetaSolar(SetaSolar p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitHumoseta(Humoseta p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitProyectil(Proyectil p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizar() {
		// TODO Auto-generated method stub
		
	}

}
