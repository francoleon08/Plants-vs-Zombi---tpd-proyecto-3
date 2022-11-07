package ente.zombi.builder;

import java.awt.Point;

import ente.zombi.Zombi;

public class DirectorZombi {

	public Zombi zombiComun(Point position) {
		return new Zombi(false, false, false, false, 60, 50, position, "assets/zombi/comun.gif");
	}
	
	public Zombi zombiAbanderado(Point position) {
		return new Zombi(true, false, false, false, 80, 60, position, "assets/zombi/abanderado.gif");
	}
	
	public Zombi zombiCaracono(Point position) {
		return new Zombi(false, true, false, false, 90, 55, position, "assets/zombi/caracono.gif");
	}
	
	public Zombi zombiLector(Point position) {
		return new Zombi(false, false, true, false, 95, 60, position, "assets/zombi/lector.gif");
	}

	public Zombi zombiPortero(Point position) {
		return new Zombi(false, false, false, true, 100, 70, position, "assets/zombi/portero.gif");
	}
}	
