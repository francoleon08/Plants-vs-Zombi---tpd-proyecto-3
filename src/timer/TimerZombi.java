package timer;

import java.util.Collections;
import java.util.List;

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
		while(control) {		
			List<Zombi> list = Collections.synchronizedList((List<Zombi>) jardin.getZombis());
			try {
				for(Zombi z : list) {
					z.actualizar();
					jardin.colision(z);
				}
			}catch(Exception e) {
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
