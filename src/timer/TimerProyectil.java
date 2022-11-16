package timer;

import jardin.Jardin;

public class TimerProyectil extends Thread {
	private Jardin jardin;
	private boolean control;
	private static int COOLDOWN_MONEDA = 2000;
	private int cooldownMoneda;
	
	public TimerProyectil(Jardin jardin) {
		this.jardin = jardin;
		control = true;
		cooldownMoneda = COOLDOWN_MONEDA;
	}
	
	public void run() {
		while(true) {
			System.out.print("");
			if(control) {
				jardin.actualizarProyectiles();
				cooldownMoneda--;
				if(cooldownMoneda == 0) {
					jardin.generarMoneda();
					cooldownMoneda = COOLDOWN_MONEDA;
				}
				try {
					Thread.sleep(5);			
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void detener() {
		control = false;
	}

	public void iniciar() {
		cooldownMoneda = COOLDOWN_MONEDA;
		control = true;
	}
}
