package ente.proyectiles;

import java.awt.Point;
import java.io.FileInputStream;
import java.util.Properties;

import ente.grafico.EnteGrafico;
import ente.zombi.visitor.Visitor;

// TODO: Auto-generated Javadoc
/**
 * The Class Espora.
 */
@SuppressWarnings("serial")
public class Espora extends Proyectil {
	
	/** The distancia. */
	private int distancia;
	
	/**
	 * Instantiates a new espora.
	 *
	 * @param punto the punto
	 * @param grafico the grafico
	 * @param velocidad the velocidad
	 * @param distancia the distancia
	 * @param danio the danio
	 */
	public Espora(Point punto,EnteGrafico grafico,int velocidad, int distancia, int danio ) {
		//Generear archivo de configureacion.
		cargarPropiedades("ente/ConfigEnte.propertiesul");
		this.width=100;
		this.height=100;
		this.setLocation(punto);
		this.grafico=grafico;
		this.danio=danio;
		this.velocidad=velocidad;
		this.distancia=distancia;
		
	}
	
	/**
	 * Accept.
	 *
	 * @param v the v
	 */
	public void accept(Visitor v) {
		v.visitProyectil(this);
	}
	
	/**
	 * Gets the distancia.
	 *
	 * @return Distancia a la que llega la espora.
	 */
	public int getDistancia() {
		return this.distancia;}
	
	private void cargarPropiedades(String path) {
		this.config = new Properties();
	
	try{
		this.config.load(new FileInputStream(path));
	} catch(Exception e){
		
	}}
	
}
