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
import ente.plantas.Planta;
import ente.proyectiles.Moneda;
import ente.zombi.Zombi;
import gui.GUI;
import logica.Logica;
import nivel.Nivel;
import timer.*;

public class Jardin {
	private Logica logica;
	private LinkedList<Planta> plantasDisponibles;
	private LinkedList<Moneda> monedasGeneradas;
	private SClip sonidoMoneda;
	private Properties configPlanta;
	private TimerZombi timerZombis;
	private TimerPlanta timerPlantas;
	private TimerProyectil timerProyectiles;
	private int nivelActual;
	private String modoJuego;
	private Nivel nivel;
	private JardinGrafico jardinGrafico;
	private FilaJardin[] filas;
	
	public Jardin(Logica logica, GUI gui, String modoJuego) {
		this.logica = logica;
		nivelActual = 1;
		this.modoJuego = modoJuego;
		
		nivel = new Nivel("assets/niveles/nivel-"+nivelActual+"-"+this.modoJuego+".txt");	
		
		filas = new FilaJardin[6];
		for(int i=0; i<filas.length; i++) {
			filas[i] = new FilaJardin(this);
		}	
		
		jardinGrafico = new JardinGrafico(gui, modoJuego);
		plantasDisponibles = nivel.getPlantasDisponibles();		
		monedasGeneradas = new LinkedList<Moneda>();
		cargarConfiguracionMoneda();
		sonidoMoneda = new SClip("assets/sonidos/sonidoMoneda.wav");
		
		
		timerZombis = new TimerZombi(this);
		timerPlantas = new TimerPlanta(this);
		timerProyectiles = new TimerProyectil(this);		
	}

	public void iniciarJuego() {				
		timerZombis.start();
		timerPlantas.start();
		timerProyectiles.start();
	}
	
	public int insertPlanta(int index, Point position) {		
		Planta insert = plantasDisponibles.get(index).clone();
		int precio = 0;
		Point posInsert = refactorPoint(position);		
		boolean celdaVacia = true;
		
		if(filas[(int) (posInsert.getY())/100].hayPlantaEnPos(posInsert)) {
			celdaVacia = false;
		}
		if(plantasDisponibles.get(index).puedeComprar() && logica.getDinero() >= insert.getPrecio() 
				&& celdaVacia && insert != null && posInsert != null) {						
			insert.setPosition(posInsert);
			filas[(int) (posInsert.getY())/100].insertPlanta(insert);
			plantasDisponibles.get(index).resetCompra();
			precio += insert.getPrecio();			
		}
		return precio;
	}
	
	public int interaccionMoneda(Point pos) {
		int fila = (int) (pos.getY()/100)*100;
		int valor = filas[fila/100].interaccionMoneda(pos);
		if(valor == 0) {
			for(Moneda m : monedasGeneradas) {
				if(m.contains(pos)) {
					sonidoMoneda.play();
					valor = m.getValor();
					monedasGeneradas.remove(m);
					jardinGrafico.removeEnte(m.getEnteGrafico());
					break;
				}
			}
		}
		return valor;
	}
	
	public void generarZombi() {
		Zombi z = nivel.getZombi();
		int fila;
		if(z != null) {
			jardinGrafico.setEnte(z.getEnteGrafico());
			fila = (int) (z.getLocation().getY()/100)*100;			
			filas[fila/100].insertZombi(z);
		}
		if(checkGameOver()) {
			stopTimers();
			logica.gameOver();
		}
	}
	
	public void generarMoneda() {
		int x = (int)(Math.random()*850);				
		Moneda insert = new Moneda(new Point(x,-50), configPlanta);
		monedasGeneradas.add(insert);
		jardinGrafico.setEnte(insert.getEnteGrafico());		
	}
	
	public void colision(Zombi z) {
		int fila = (int) (z.getLocation().getY()/100)*100;
		filas[fila/100].colision(z);
	}
	
	public void cambiarModoJuego(String modoJuego) {
		stopTimers();
		for(int i=0; i<filas.length; i++) {
			filas[i].limpiarListas();
		}
		nivel.setNivel("nivel-"+nivelActual+"-"+modoJuego);
		plantasDisponibles.clear();
		plantasDisponibles = nivel.getPlantasDisponibles();
	}
	
	public void actualizarZombis() {
		for(int i=0; i<filas.length; i++) {
			filas[i].actualizarZombis();
		}
	}
	
	public void actualizarPlantas() {
		try {			
			for(Planta p : plantasDisponibles) {
				p.actualizarCompra();
			}
			for(int i=0; i<filas.length; i++) {
				filas[i].actualizarPlantas();
			}
		}catch(Exception e) {
		}
	}
	
	public void actualizarProyectiles() {
		for(int i=0; i<filas.length; i++) {
			filas[i].actualizarProyectiles();
		}
		for(Moneda m : monedasGeneradas) {
			m.actualizarCaida();
		}
	}
	
	public void removerEnteJardinGrafico(EnteGrafico e) {
		jardinGrafico.removeEnte(e);
	}
	
	public void insertEnteJardinGrafico(EnteGrafico e) {
		jardinGrafico.setEnte(e);
	}
	
	public void generarAnimacionColision(Point p, String url) {
		Animacion animacion = new Animacion(this.jardinGrafico, p, url);
		animacion.start();
		animacion = null;
	}
	
	public Iterable<EnteGrafico> getPlantasDisponibles() {
		LinkedList<EnteGrafico> list = new LinkedList<EnteGrafico>();
		for(Planta p : plantasDisponibles) {
			list.add(p.getEnteGrafico());
		}
		return list;
	}
	
	private boolean checkGameOver() {
		boolean estado = false;
		for(int i=0; i<filas.length; i++) {
			if(filas[i].hayZombiAlFinal()) {
				estado = true;
				break;
			}
		}
		return estado;
	}	
	
	private void stopTimers() {
		timerPlantas.detener();
		timerProyectiles.detener();
		timerZombis.detener();
	}
	
	private Point refactorPoint(Point p) {
		Point toReturn = null;		
		if(p != null) {
			toReturn = new Point((int)(p.getX()/100)*100, (int)(p.getY()/100)*100);
		}
		return toReturn;
	}	
	
	private void cargarConfiguracionMoneda() {
		configPlanta = new Properties();
		try {
			configPlanta.load(new FileInputStream("assets/configuracion/config_plantas.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
