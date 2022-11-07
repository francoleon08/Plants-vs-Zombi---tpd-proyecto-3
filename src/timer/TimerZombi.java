package timer;

import ente.zombi.Zombi;
import jardin.Jardin;

public class TimerZombi extends Thread {
	private Jardin jardin;
	private int cooldownSpawn;
	
	public TimerZombi(Jardin jardin) {
		this.jardin = jardin;
		cooldownSpawn = 10;
	}
	
	public void run() {
		while(true) {
			for(Zombi z : jardin.getZombis()) {
				z.actualizar();
				jardin.colision(z);
			}
			cooldownSpawn--;
			if(cooldownSpawn == 0) {
				jardin.generarZombi();
				cooldownSpawn = 10;
			}
		}
	}
}
