package nivel.generador;

import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Properties;

import ente.plantas.*;
import ente.plantas.Planta;
import ente.zombi.Zombi;
import ente.zombi.builder.DirectorZombi;
import nivel.Nivel;

public class GeneradorNivel {
	private DirectorZombi director;
	private Nivel nivel;
	private LinkedList<Planta> plantasDisponibles;
	private LinkedList<Zombi> zombisDisponibles;
	private Properties configPlanta;
	
	
	public GeneradorNivel(String urlNivel, Nivel nivel) {
		director = new DirectorZombi();
		this.nivel = nivel;
		plantasDisponibles = new LinkedList<Planta>();
		zombisDisponibles = new LinkedList<Zombi>();		
		configPlanta = new Properties();
		try {
			configPlanta.load(new FileInputStream("assets/configuracion/config_plantas.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		generarNuevoNivel(urlNivel);
	}
	
	public void generarNuevoNivel(String urlNivel) {
		resetListas();
		Path filePath
		= Paths.get(urlNivel);
		String level;
		try {
			level = Files.readString(filePath);
			level = level.replace(" ","");
			level = level.replace("\n", "");
			String x;
			String y;
			
			for(int i = 0; i < level.length(); i++) {
				
				switch(level.charAt(i)){
				
				case 'L':{
					x = String.valueOf(level.charAt(i+2)) + String.valueOf(level.charAt(i+3)) + String.valueOf(level.charAt(i+4));
					y = String.valueOf(level.charAt(i+6)) + String.valueOf(level.charAt(i+7)) + String.valueOf(level.charAt(i+8));
					Planta p = new LanzaGuisantes(new Point(Integer.parseInt(x),Integer.parseInt(y)), configPlanta);
					plantasDisponibles.add(p);
					i=i+6;
				}break;
			
				case 'G':{
					x = String.valueOf(level.charAt(i+2)) + String.valueOf(level.charAt(i+3)) + String.valueOf(level.charAt(i+4));
					y = String.valueOf(level.charAt(i+6)) + String.valueOf(level.charAt(i+7)) + String.valueOf(level.charAt(i+8));
					Planta p = new Girasol(new Point(Integer.parseInt(x),Integer.parseInt(y)), configPlanta);
					plantasDisponibles.add(p);
					i=i+6;
				}break;
			
				case'J':{
					x = String.valueOf(level.charAt(i+2)) + String.valueOf(level.charAt(i+3)) + String.valueOf(level.charAt(i+4));
					y = String.valueOf(level.charAt(i+6)) + String.valueOf(level.charAt(i+7)) + String.valueOf(level.charAt(i+8));
					Planta p = new Jalapeno(new Point(Integer.parseInt(x),Integer.parseInt(y)), configPlanta);
					plantasDisponibles.add(p);
					i=i+6;
				}break;
			
				case'D':{
					x = String.valueOf(level.charAt(i+2)) + String.valueOf(level.charAt(i+3)) + String.valueOf(level.charAt(i+4));
					y = String.valueOf(level.charAt(i+6)) + String.valueOf(level.charAt(i+7)) + String.valueOf(level.charAt(i+8));
					Planta p = new SetaDesporada(new Point(Integer.parseInt(x),Integer.parseInt(y)), configPlanta);
					plantasDisponibles.add(p);
					i=i+6;
				}break;
			
				case'S':{
					x = String.valueOf(level.charAt(i+2)) + String.valueOf(level.charAt(i+3)) + String.valueOf(level.charAt(i+4));
					y = String.valueOf(level.charAt(i+6)) + String.valueOf(level.charAt(i+7)) + String.valueOf(level.charAt(i+8));
					Planta p = new SetaSolar(new Point(Integer.parseInt(x),Integer.parseInt(y)), configPlanta);
					plantasDisponibles.add(p);
					i=i+6;
				}break;
			
				case'H':{
					x = String.valueOf(level.charAt(i+2)) + String.valueOf(level.charAt(i+3)) + String.valueOf(level.charAt(i+4));
					y = String.valueOf(level.charAt(i+6)) + String.valueOf(level.charAt(i+7)) + String.valueOf(level.charAt(i+8));
					Planta p = new Humoseta(new Point(Integer.parseInt(x),Integer.parseInt(y)), configPlanta);
					plantasDisponibles.add(p);
					i=i+6;
				}break;			
			
				case'1':{
					x = String.valueOf(level.charAt(i+2)) + String.valueOf(level.charAt(i+3)) + String.valueOf(level.charAt(i+4));
					y = String.valueOf(level.charAt(i+6)) + String.valueOf(level.charAt(i+7)) + String.valueOf(level.charAt(i+8));					
					Zombi z = director.zombiComun(new Point(Integer.parseInt(x),Integer.parseInt(y)));
					zombisDisponibles.add(z);
					i=i+6;
				}break;
			
				case'2':{
					x = String.valueOf(level.charAt(i+2)) + String.valueOf(level.charAt(i+3)) + String.valueOf(level.charAt(i+4));
					y = String.valueOf(level.charAt(i+6)) + String.valueOf(level.charAt(i+7)) + String.valueOf(level.charAt(i+8));
					Zombi z = director.zombiAbanderado(new Point(Integer.parseInt(x),Integer.parseInt(y)));
					zombisDisponibles.add(z);
					i=i+6;
				}break;
				case'3':{
					x = String.valueOf(level.charAt(i+2)) + String.valueOf(level.charAt(i+3)) + String.valueOf(level.charAt(i+4));
					y = String.valueOf(level.charAt(i+6)) + String.valueOf(level.charAt(i+7)) + String.valueOf(level.charAt(i+8));
					Zombi z = director.zombiCaracono(new Point(Integer.parseInt(x),Integer.parseInt(y)));
					zombisDisponibles.add(z);
					i=i+6;
				}break;
				case'4':{
					x = String.valueOf(level.charAt(i+2)) + String.valueOf(level.charAt(i+3)) + String.valueOf(level.charAt(i+4));
					y = String.valueOf(level.charAt(i+6)) + String.valueOf(level.charAt(i+7)) + String.valueOf(level.charAt(i+8));
					Zombi z = director.zombiLector(new Point(Integer.parseInt(x),Integer.parseInt(y)));
					zombisDisponibles.add(z);
					i=i+6;
				}break;
				case'5':{
					x = String.valueOf(level.charAt(i+2)) + String.valueOf(level.charAt(i+3)) + String.valueOf(level.charAt(i+4));
					y = String.valueOf(level.charAt(i+6)) + String.valueOf(level.charAt(i+7)) + String.valueOf(level.charAt(i+8));
					Zombi z = director.zombiPortero(new Point(Integer.parseInt(x),Integer.parseInt(y)));
					zombisDisponibles.add(z);
					i=i+6;
				}break;
			}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		actaulizarNivel();
	}
	
	private void resetListas() {
		plantasDisponibles.clear();
		zombisDisponibles.clear();
	}
	
	private void actaulizarNivel() {
		nivel.setPlantasDisponibles(plantasDisponibles);
		nivel.setZombisDisponibles(zombisDisponibles);
	}	
}
