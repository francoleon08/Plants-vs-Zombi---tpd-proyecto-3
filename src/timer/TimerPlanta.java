package timer;

import ente.plantas.Planta;
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
			
			try {
				for(Planta p : jardin.getPlantas()) {
					p.actualizar();
					if(p.puedeDisparar()) {
						jardin.addProyectil(p.disparar());
						p.resetDisparo();
					}
				}
				for(Planta p : jardin.getPlantasDisponibles()) {
					p.actualizarCompra();	
				}
			}catch(Exception e) {
			}
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
