package jardin;

import java.awt.Point;
import java.util.LinkedList;
import Sonido.SClip;
import animacion.Animacion;
import ente.plantas.Jalapeno;
import ente.plantas.Planta;
import ente.proyectiles.Moneda;
import ente.proyectiles.Proyectil;
import ente.zombi.Zombi;

public class FilaJardin {
	private Jardin jardin;
	private LinkedList<Zombi> zombisActivos;
	private LinkedList<Planta> plantasActivas;
	private LinkedList<Proyectil> proyectilesActivos;
	private SClip sonidoZombi;
	private SClip sonidoMoneda;
	private int zombis;
	private boolean gameOver;
	
	public FilaJardin(Jardin jardin) {
		this.jardin = jardin;
		zombisActivos = new LinkedList<Zombi>();
		plantasActivas = new LinkedList<Planta>();
		proyectilesActivos = new LinkedList<Proyectil>();
		sonidoZombi = new SClip("assets/sonidos/proyectil.wav");
		sonidoMoneda = new SClip("assets/sonidos/sonidoMoneda.wav");
		zombis = 0;
		gameOver = false;
	}
	
	public void actualizarZombis() {
		try {
			for(Zombi z : zombisActivos) {
				z.actualizar();
				colision(z);
				if(!z.estaVivo())
					removeZombi(z);
				if(z.getPosition().getX() <= 10)
					gameOver = true;
			}
		} catch(Exception e) {}
	}
	
	public void actualizarPlantas() {
		Proyectil insert = null;
		try { 
			for(Planta p : plantasActivas) {
				p.actualizar();
				if(p.puedeDisparar()) {
					insert = p.disparar();					
					if(insert != null) {
						insertProyectil(insert);
						p.resetDisparo();
					}						
					else {
						if(p.getDanio() > 0)
							removePlanta(p);
					}
				}
			}
		} catch(Exception e) {}
	}
	
	public void actualizarProyectiles() {
		try {
			for(Proyectil p : proyectilesActivos) {
				p.actualizar();
				if(p.getLocation().getX() >= 899) 
					removeProyectiles(p);
			}
		} catch(Exception e) {}
	}
	
	public void colision(Zombi z) {		
		for(Planta p : plantasActivas) {
			if(z.intersects(p)) {				
				p.accept(z);		
				if(!p.estaViva())
					removePlanta(p);
			}
		}
		for(Proyectil p : proyectilesActivos) {
			if(z.intersects(p) && p.getDanio() > 0) {
				sonidoZombi.play();
				removeProyectiles(p);
				p.accept(z);					
				if(!z.estaVivo()) {
					removeZombi(z);
					jardin.generarAnimacionColision(p.getLocation(), "assets\\imagenes\\zombis\\explocion.gif");
				}
				else {
					jardin.generarAnimacionColision(p.getLocation(), "assets\\imagenes\\zombis\\impacto.gif");
				}
			}
		}
	}
	
	public int interaccionMoneda(Point pos) {
		int valor = 0;
		Moneda aux = null;
		for(Proyectil p : proyectilesActivos) {
			if(p.getDanio() == 0 && p.contains(pos)) {
				sonidoMoneda.play();
				proyectilesActivos.remove(p);
				jardin.removerEnteJardinGrafico(p.getEnteGrafico());
				aux = (Moneda) p;
				valor += aux.getValor();				
			}
		}
		return valor;
	}
	
	public boolean hayZombisActivos() {
		return zombis != 0;
	}
	
	public boolean hayZombiAlFinal() {
		return gameOver;
	}
	
	public boolean hayPlantaEnPos(Point posInsert) {
		boolean estado = false;
		for(Planta p : plantasActivas) {
			if(p.getLocation().equals(posInsert)) {
				estado = true;
				break;
			}				
		}
		return estado;
	}
	
	public void insertPlanta(Planta p) {
		plantasActivas.add(p);
		jardin.insertEnteJardinGrafico(p.getEnteGrafico());
	}
	
	public void insertZombi(Zombi z) {
		zombisActivos.add(z);
		jardin.insertEnteJardinGrafico(z.getEnteGrafico());
		zombis++;
	}
	
	public void insertProyectil(Proyectil p) {
		proyectilesActivos.add(p);
		jardin.insertEnteJardinGrafico(p.getEnteGrafico());
	}
	
	public void limpiarListas() {
		zombisActivos.clear();
		plantasActivas.clear();
		proyectilesActivos.clear();
		zombis = 0;
		gameOver = false;
	}
	
	private boolean removeZombi(Zombi z) {
		boolean removio=false;
		if(z != null) {
			zombisActivos.remove(z);
			jardin.removerEnteJardinGrafico(z.getEnteGrafico());
			zombis--;
			removio=true;
		}
		return removio;
	}
	
	private boolean removePlanta(Planta p) {
		boolean removio=false;
		if(p != null) {
			plantasActivas.remove(p);
			jardin.removerEnteJardinGrafico(p.getEnteGrafico());
			removio=true;
		}
		return removio;
	}
	
	private boolean removeProyectiles(Proyectil p) {
		boolean removio=false;
		if(p != null) {
			proyectilesActivos.remove(p);
			jardin.removerEnteJardinGrafico(p.getEnteGrafico());
			removio=true;
		}
		return removio;
	}
}
