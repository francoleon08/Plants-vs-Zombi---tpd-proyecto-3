package timer;

import ente.plantas.Planta;
import jardin.Jardin;

public class TimerPlanta extends Thread {
	private Jardin jardin;
	
	public TimerPlanta(Jardin jardin) {
		this.jardin = jardin;
	}
	
	public void run() {
		while(true) {
			for(Planta p : jardin.getPlantas()) {
				p.actualizar();
				if(p.puedeDisparar()) {
					jardin.addProyectil(p.disparar());
					p.resetDisparo();
				}
			}
		}
	}
}
