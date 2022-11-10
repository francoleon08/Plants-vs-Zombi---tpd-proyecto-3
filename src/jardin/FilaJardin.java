package jardin;

import java.awt.Point;
import java.util.LinkedList;
import Sonido.SClip;
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
	
	public FilaJardin(Jardin jardin) {
		this.jardin = jardin;
		zombisActivos = new LinkedList<Zombi>();
		plantasActivas = new LinkedList<Planta>();
		proyectilesActivos = new LinkedList<Proyectil>();
		sonidoZombi = new SClip("assets/sonidos/proyectil.wav");
	}
	
	public void actualizarZombis() {
		try {
			for(Zombi z : zombisActivos) {
				z.actualizar();
				colision(z);
			}
		} catch(Exception e) {}
	}
	
	public void actualizarPlantas() {
		try { 
			for(Planta p : plantasActivas) {
				p.actualizar();
				if(p.puedeDisparar()) {
					insertProyectil(p.disparar());							
					p.resetDisparo();
				}
			}
		} catch(Exception e) {}
	}
	
	public void actualizarProyectiles() {
		try {
			for(Proyectil p : proyectilesActivos) {
				p.actualizar();
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
				if(p.getLocation().getX() > 900) 
					removeProyectiles(p);
				if(!z.estaVivo()) {
					removeZombi(z);
					jardin.generarAnimacionColision(p.getLocation(), "assets\\sprites\\explocion.gif");
				}
				else {
					jardin.generarAnimacionColision(p.getLocation(), "assets\\sprites\\impacto.gif");
				}
			}
		}
	}
	
	public int interaccionMoneda(Point pos) {
		int valor = 0;
		Moneda aux = null;
		for(Proyectil p : proyectilesActivos) {
			if(p.getDanio() == 0 && p.contains(pos)) {
				proyectilesActivos.remove(p);
				jardin.removerEnteJardinGrafico(p.getEnteGrafico());
				aux = (Moneda) p;
				valor += aux.getValor();				
			}
		}
		return valor;
	}
	
	public boolean hayZombiAlFinal() {
		boolean estado = false;
		for(Zombi z : zombisActivos) {
			if(z.getLocation().getX() == 10) {
				estado = true;
				break;
			}
		}
		return estado;
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
	}
	
	public void insertProyectil(Proyectil p) {
		proyectilesActivos.add(p);
		jardin.insertEnteJardinGrafico(p.getEnteGrafico());
	}
	
	public void limpiarListas() {
		zombisActivos.clear();
		plantasActivas.clear();
		proyectilesActivos.clear();
	}
	
	private boolean removeZombi(Zombi z) {
		boolean removio=false;
		if(z != null) {
			zombisActivos.remove(z);
			jardin.removerEnteJardinGrafico(z.getEnteGrafico());			
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
