package timer;

import ente.proyectiles.Proyectil;
import jardin.Jardin;

public class TimerProyectil extends Thread {
	private Jardin jardin;
	
	public TimerProyectil(Jardin jardin) {
		this.jardin = jardin;
	}
	
	public void run() {
		while(true) {
			for(Proyectil p : jardin.getProyectiles()) {
				p.actualizar();
			}
		}
	}
}
