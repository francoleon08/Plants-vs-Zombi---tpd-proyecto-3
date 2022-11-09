package timer;

import ente.proyectiles.Proyectil;
import jardin.Jardin;

public class TimerProyectil extends Thread {
	private Jardin jardin;
	private boolean control;
	
	public TimerProyectil(Jardin jardin) {
		this.jardin = jardin;
		control = true;
	}
	
	public void run() {
		System.out.println("");
		while(control) {
			for(Proyectil p : jardin.getProyectiles()) {
				p.actualizar();
			}
			try {
				Thread.sleep(100);			
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void detener() {
		control = false;
	}
}
