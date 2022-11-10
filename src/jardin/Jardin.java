package jardin;

import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

import Sonido.SClip;
import animacion.Animacion;
import ente.grafico.EnteGrafico;
import ente.plantas.LanzaGuisantes;
import ente.plantas.Planta;
import ente.proyectiles.Moneda;
import ente.proyectiles.Proyectil;
import ente.zombi.Zombi;
import gui.GUI;
import gui.botonera.Botonera;
import logica.Logica;
import nivel.Nivel;
import timer.*;

public class Jardin {
	private Logica logica;
	private LinkedList<Planta> plantasDisponibles;
	private LinkedList<Zombi> zombisActivos;
	private LinkedList<Planta> plantasActivas;
	private LinkedList<Proyectil> proyectilesActivos;
	private TimerZombi timerZombis;
	private TimerPlanta timerPlantas;
	private TimerProyectil timerProyectiles;
	private int nivelActual;
	private String modoJuego;
	private Nivel nivel;
	private JardinGrafico jardinGrafico;
	private Botonera botoneraGrafica;
	private SClip sonidoZombi;
	
	public Jardin(Logica logica, GUI gui, String modoJuego) {
		this.logica = logica;
		nivelActual = 1;
		this.modoJuego = modoJuego;
		zombisActivos = new LinkedList<Zombi>();
		plantasActivas = new LinkedList<Planta>();
		proyectilesActivos = new LinkedList<Proyectil>();		
		nivel = new Nivel("assets/niveles/nivel-"+nivelActual+"-"+this.modoJuego+".txt");		
		plantasDisponibles = nivel.getPlantasDisponibles();		
		jardinGrafico = new JardinGrafico(gui, modoJuego);
		botoneraGrafica = new Botonera(gui);
		setBotonera();
		sonidoZombi = new SClip("assets/sonidos/proyectil.wav");		
		
		timerZombis = new TimerZombi(this);
		timerPlantas = new TimerPlanta(this);
		timerProyectiles = new TimerProyectil(this);
		
	}

	public void iniciarJuego() {		
		Properties configPlanta = new Properties();
		try {
			configPlanta.load(new FileInputStream("assets\\configuracion\\config_plantas.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
			
		timerZombis.start();
		timerPlantas.start();
		timerProyectiles.start();
	}
	
	public int insertPlanta(int index, Point position) {		
		Planta insert = plantasDisponibles.get(index).clone();
		int precio = 0;
		Point posInsert = refactorPoint(position);
		boolean celdaVacia = true;
		for(Planta p : plantasActivas) {
			if(p.getLocation().equals(posInsert))
				celdaVacia = false;
		}
		if(celdaVacia && plantasDisponibles.get(index).puedeComprar() && 
				logica.getDinero() >= insert.getPrecio() && insert != null && posInsert != null) {			
			insert.setLocation(posInsert);
			insert.getEnteGrafico().setPosition(posInsert);
			jardinGrafico.setEnte(insert.getEnteGrafico());
			plantasActivas.add(insert);
			plantasDisponibles.get(index).resetCompra();
			precio += insert.getPrecio();
		}
		return precio;
	}
	
	public int interaccionMoneda(Point pos) {
		int valor = 0;
		Moneda aux = null;
		for(Proyectil p : proyectilesActivos) {
			if(p.contains(pos) && p.getDanio() == 0) {
				proyectilesActivos.remove(p);
				jardinGrafico.removeEnte(p.getEnteGrafico());
				aux = (Moneda) p;
				valor += aux.getValor();
				break;
			}
		}
		return valor;
	}
	
	public void generarZombi() {
		Zombi z = nivel.getZombi();
		if(z != null) {
			jardinGrafico.setEnte(z.getEnteGrafico());
			zombisActivos.add(z);
		}
		else {
			if(zombisActivos.size() == 0) {

			}
			else {
				if(checkGameOver()) {
					//stopTimers();
					//logica.gameOver();
				}					
			}
		}
			
	}

	public boolean addProyectil(Proyectil p) {
		boolean agrego=false;
		if(p != null) {
			jardinGrafico.setEnte(p.getEnteGrafico());
			proyectilesActivos.add(p);
			agrego=true;
		}
		return agrego;
	}
	
	public void cambiarModoJuego(String modoJuego) {
		nivel.setNivel("nivel-"+nivelActual+"-"+modoJuego);
		plantasDisponibles = nivel.getPlantasDisponibles();
		zombisActivos.clear();
		proyectilesActivos.clear();
		plantasActivas.clear();
	}
	
	public void colision(Zombi z) {
		double fila = z.getY();
		for(Planta p : plantasActivas) {
			if(p.getY() == fila && z.intersects(p)) {
				p.accept(z);		
				if(!p.estaViva())
					removePlanta(p);
			}
		}
		for(Proyectil p : proyectilesActivos) {
			if(p.getY() == fila && z.intersects(p) && p.getDanio() > 0) {
				sonidoZombi.play();
				removeProyectiles(p);
				p.accept(z);	
				if(p.getLocation().getX() > 900) 
					removeProyectiles(p);
				if(!z.estaVivo()) {
					removeZombi(z);
					Animacion animacion = new Animacion(this.jardinGrafico, p.getLocation(), "assets\\sprites\\explocion.gif");
					animacion.run();
				}
			}
		}		
	}
	
	public Iterable<Zombi> getZombis() {
		return zombisActivos;
	}
	
	public Iterable<Planta> getPlantas() {
		return plantasActivas;
	}
	
	public Iterable<Planta> getPlantasDisponibles() {
		return plantasDisponibles;
	}
	
	public Iterable<Proyectil> getProyectiles() {
		return proyectilesActivos;
	}
	
	private boolean removeZombi(Zombi z) {
		boolean removio=false;
		if(z != null) {
			zombisActivos.remove(z);
			jardinGrafico.removeEnte(z.getEnteGrafico());
			removio=true;
		}
		return removio;
	}
	
	private boolean removePlanta(Planta p) {
		boolean removio=false;
		if(p != null) {
			plantasActivas.remove(p);
			jardinGrafico.removeEnte(p.getEnteGrafico());
			removio=true;
		}
		return removio;
	}
	
	private boolean removeProyectiles(Proyectil p) {
		boolean removio=false;
		if(p != null) {
			proyectilesActivos.remove(p);
			jardinGrafico.removeEnte(p.getEnteGrafico());
			removio=true;
		}
		return removio;
	}
	
	private boolean checkGameOver() {
		boolean estado = false;
		for(Zombi z : getZombis()) {
			if(z.getLocation().getX() <= 10) {
				estado = true;
				break;
			}
		}
		return estado;
	}	
	
	private void setBotonera() {
		LinkedList<EnteGrafico> list = new LinkedList<EnteGrafico>();
		for(Planta p : plantasDisponibles) {
			list.add(p.getEnteGrafico());
		}
		botoneraGrafica.setBotonera(list);
	}
	
	private void stopTimers() {
		timerPlantas.detener();
		timerProyectiles.detener();
		timerZombis.detener();
	}
	
	private Point refactorPoint(Point p) {
		Point toReturn = null;
		if(p.getX()>10 && p.getY()>110) {
			toReturn = new Point((int)(p.getX()/100)*100, (int)(p.getY()/100)*100);
		}
		return toReturn;
	}	
}
