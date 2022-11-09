package timer;

import ente.zombi.Zombi;
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
		System.out.println("");
		while(control) {
			for(Zombi z : jardin.getZombis()) {
				z.actualizar();
				jardin.colision(z);
			}
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
