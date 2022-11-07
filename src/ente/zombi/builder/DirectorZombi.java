package ente.zombi.builder;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import ente.zombi.Zombi;

public class DirectorZombi {
	private Properties config;
	
	public DirectorZombi() {
		try {
			config.load(new FileInputStream("assets/configuracion/config_zombi.properties"));
			System.out.print(config.getProperty("zombiComun"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public Zombi zombiComun(Point position) {
		return new Zombi(false, false, false, false, 60, 50, position, "assets/zombi/comun.gif");
	}
	
	public Zombi zombiAbanderado(Point position) {
		return new Zombi(true, false, false, false, 80, 60, position, "assets/zombi/abanderado.gif");
	}
	
	public Zombi zombiCaracono(Point position) {
		return new Zombi(false, true, false, false, 90, 55, position, "assets/zombi/caracono.gif");
	}
	
	public Zombi zombiLector(Point position) {
		return new Zombi(false, false, true, false, 95, 60, position, "assets/zombi/lector.gif");
	}

	public Zombi zombiPortero(Point position) {
		return new Zombi(false, false, false, true, 100, 70, position, "assets/zombi/portero.gif");
	}
}	
