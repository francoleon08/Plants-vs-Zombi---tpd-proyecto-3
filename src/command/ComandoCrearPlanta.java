package command;

import java.awt.Point;

import logica.Logica;

public final class ComandoCrearPlanta implements command {
	private int planta;
	private Logica logica;
	private Point p;
	public ComandoCrearPlanta(int planta, Logica l, Point p) {
		 this.planta=planta;
		 this.logica=l;
		 this.p=p;
		
	}
		
	@Override
	public void execute() {
		logica.crearPlanta(planta, p);

	}

}
