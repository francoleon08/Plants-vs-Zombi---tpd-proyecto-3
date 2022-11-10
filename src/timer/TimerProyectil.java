package timer;

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
			jardin.actualizarProyectiles();
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
