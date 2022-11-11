package timer;

import jardin.Jardin;

public class TimerProyectil extends Thread {
	private Jardin jardin;
	private boolean control;
	private int cooldownMoneda;
	
	public TimerProyectil(Jardin jardin) {
		this.jardin = jardin;
		control = true;
		cooldownMoneda = 1000;
	}
	
	public void run() {
		while(control) {					
			jardin.actualizarProyectiles();
			cooldownMoneda--;
			if(cooldownMoneda == 0) {
				jardin.generarMoneda();
				cooldownMoneda = 1000;
			}
			try {
				Thread.sleep(10);			
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void detener() {
		control = false;
	}
}
