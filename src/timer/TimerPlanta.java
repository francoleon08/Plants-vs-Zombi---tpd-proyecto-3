package timer;

import jardin.Jardin;

public class TimerPlanta extends Thread {
	private Jardin jardin;
	private boolean control;
	
	public TimerPlanta(Jardin jardin) {
		this.jardin = jardin;
		control = true;
	}
	
	public void run() {
		while(control) {			
			jardin.actualizarPlantas();
			try {
				Thread.sleep(500);			
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void detener() {
		control = false;
	}
}
