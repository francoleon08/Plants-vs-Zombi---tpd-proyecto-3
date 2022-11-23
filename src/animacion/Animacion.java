package animacion;

import java.awt.Point;
import ente.grafico.EnteGrafico;
import jardin.JardinGrafico;

public class Animacion {
	private static String EXPLOCION = "assets/imagenes/zombis/explocion.gif";
	private static String COLISION = "assets/imagenes/zombis/impacto.gif";	
	private EnteGrafico animacionExplocion;
	private EnteGrafico animacionColision;
	private JardinGrafico jardin;
	
	public Animacion(JardinGrafico jardin) {		
		this.jardin = jardin;
		animacionExplocion = new EnteGrafico(null, 50, 50, EXPLOCION);
		animacionColision = new EnteGrafico(null, 50, 50, COLISION);
	}

	public void playExplocion(Point p) {
		new Thread(() -> {
			try {
				animacionExplocion.setPosition(p);
				jardin.setEnte(animacionExplocion);				
				Thread.sleep(500);
				jardin.removeEnte(animacionExplocion);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}){}.start();
	}
	
	public void playColision(Point p) {
		new Thread(() -> {
			try {
				animacionColision.setPosition(p);
				jardin.setEnte(animacionColision);				
				Thread.sleep(500);
				jardin.removeEnte(animacionColision);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}){}.start();
	}
}