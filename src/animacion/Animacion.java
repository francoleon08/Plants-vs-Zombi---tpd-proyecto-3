package animacion;

import java.awt.Point;
import ente.grafico.EnteGrafico;
import jardin.JardinGrafico;

public class Animacion extends Thread {
	private EnteGrafico animacion;
	private JardinGrafico jardin;
	
	public Animacion(JardinGrafico jardin, Point position, String urlAnimacion) {
		position.setLocation(position.getX()+10, position.getY());
		animacion = new EnteGrafico(position, 50, 50, urlAnimacion);
		this.jardin = jardin;
	}

	public void run() {
		try {
			jardin.setEnte(animacion);				
			Thread.sleep(500);
			jardin.removeEnte(animacion);
			//Thread.interrupted();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}