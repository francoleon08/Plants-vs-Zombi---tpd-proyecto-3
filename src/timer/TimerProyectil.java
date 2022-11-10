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
		while(control) {					
			try {
				for(Proyectil p : jardin.getProyectiles()) {
					p.actualizar();
				}
			}catch(Exception e) {
			}
			try {
				Thread.sleep(5);			
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void detener() {
		control = false;
	}
}
