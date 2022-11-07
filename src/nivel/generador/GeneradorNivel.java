package nivel.generador;

import java.awt.Point;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

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
	
	
	public GeneradorNivel(String urlNivel, Nivel nivel) {
		director = new DirectorZombi();
		this.nivel = nivel;
		plantasDisponibles = new LinkedList<Planta>();
		zombisDisponibles = new LinkedList<Zombi>();
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
					x = String.valueOf(level.charAt(i+2)) + String.valueOf(level.charAt(i+3));
					y = String.valueOf(level.charAt(i+5)) + String.valueOf(level.charAt(i+6));
					Planta p = new LanzaGuisantes(new Point(Integer.parseInt(x),Integer.parseInt(y)));
					plantasDisponibles.add(p);
					i=i+6;
				}break;
			
				case 'G':{
					x = String.valueOf(level.charAt(i+2)) + String.valueOf(level.charAt(i+3));
					y = String.valueOf(level.charAt(i+5)) + String.valueOf(level.charAt(i+6));
					Planta p = new Girasol(new Point(Integer.parseInt(x),Integer.parseInt(y)));
					plantasDisponibles.add(p);
					i=i+6;
				}break;
			
				case'P':{
					x = String.valueOf(level.charAt(i+2)) + String.valueOf(level.charAt(i+3));
					y = String.valueOf(level.charAt(i+5)) + String.valueOf(level.charAt(i+6));
					Planta p = new Petacereza(new Point(Integer.parseInt(x),Integer.parseInt(y)));
					plantasDisponibles.add(p);
					i=i+6;
				}break;
			
				case'D':{
					x = String.valueOf(level.charAt(i+2)) + String.valueOf(level.charAt(i+3));
					y = String.valueOf(level.charAt(i+5)) + String.valueOf(level.charAt(i+6));
					Planta p = new SetaDesporada(new Point(Integer.parseInt(x),Integer.parseInt(y)));
					plantasDisponibles.add(p);
					i=i+6;
				}break;
			
				case'S':{
					x = String.valueOf(level.charAt(i+2)) + String.valueOf(level.charAt(i+3));
					y = String.valueOf(level.charAt(i+5)) + String.valueOf(level.charAt(i+6));
					Planta p = new SetaSolar(new Point(Integer.parseInt(x),Integer.parseInt(y)));
					plantasDisponibles.add(p);
					i=i+6;
				}break;
			
				case'H':{
					x = String.valueOf(level.charAt(i+2)) + String.valueOf(level.charAt(i+3));
					y = String.valueOf(level.charAt(i+5)) + String.valueOf(level.charAt(i+6));
					Planta p = new Humoseta(new Point(Integer.parseInt(x),Integer.parseInt(y)));
					plantasDisponibles.add(p);
					i=i+6;
				}break;			
			
				case'1':{
					x = String.valueOf(level.charAt(i+2)) + String.valueOf(level.charAt(i+3));
					y = String.valueOf(level.charAt(i+5)) + String.valueOf(level.charAt(i+6));
					Zombi z = director.zombiComun(new Point(Integer.parseInt(x),Integer.parseInt(y)));
					zombisDisponibles.add(z);
					i=i+6;
				}break;
			
				case'2':{
					x = String.valueOf(level.charAt(i+2)) + String.valueOf(level.charAt(i+3));
					y = String.valueOf(level.charAt(i+5)) + String.valueOf(level.charAt(i+6));
					Zombi z = director.zombiAbanderado(new Point(Integer.parseInt(x),Integer.parseInt(y)));
					zombisDisponibles.add(z);
					i=i+6;
				}break;
				case'3':{
					x = String.valueOf(level.charAt(i+2)) + String.valueOf(level.charAt(i+3));
					y = String.valueOf(level.charAt(i+5)) + String.valueOf(level.charAt(i+6));
					Zombi z = director.zombiCaracono(new Point(Integer.parseInt(x),Integer.parseInt(y)));
					zombisDisponibles.add(z);
					i=i+6;
				}break;
				case'4':{
					x = String.valueOf(level.charAt(i+2)) + String.valueOf(level.charAt(i+3));
					y = String.valueOf(level.charAt(i+5)) + String.valueOf(level.charAt(i+6));
					Zombi z = director.zombiLector(new Point(Integer.parseInt(x),Integer.parseInt(y)));
					zombisDisponibles.add(z);
					i=i+6;
				}break;
				case'5':{
					x = String.valueOf(level.charAt(i+2)) + String.valueOf(level.charAt(i+3));
					y = String.valueOf(level.charAt(i+5)) + String.valueOf(level.charAt(i+6));
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
