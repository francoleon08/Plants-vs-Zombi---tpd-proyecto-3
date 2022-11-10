package timer;

import jardin.Jardin;

public class TimerZombi extends Thread {
	private Jardin jardin;
	private boolean control;
	private int cooldownSpawn;
	
	public TimerZombi(Jardin jardin) {
		this.jardin = jardin;
		control = true;
		cooldownSpawn = 50;
	}
	
	public void run() {
		while(control) {		
			jardin.actualizarZombis();
			cooldownSpawn--;
			if(cooldownSpawn == 0) {
				jardin.generarZombi();
				cooldownSpawn = 100;
			}
			try {
				Thread.sleep(50);			
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void detener() {
		control = false;
	}
}
