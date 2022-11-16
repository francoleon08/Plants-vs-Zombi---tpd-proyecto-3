package ente.zombi.builder;

import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import ente.zombi.Zombi;

public class DirectorZombi {
	private Properties config;
	
	public DirectorZombi() {
		config = new Properties();
		try {
			config.load(new FileInputStream("assets/configuracion/config_zombi.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Zombi zombiComun(Point position) {
		return new Zombi(false, false, false, false, false, 60, 50, position, config.getProperty("zombiComun"));
	}
	
	public Zombi zombiAbanderado(Point position) {
		return new Zombi(true, false, false, false, false, 80, 60, position, config.getProperty("zombiAbanderado"));
	}
	
	public Zombi zombiCaracono(Point position) {
		return new Zombi(false, true, false, false, false, 90, 55, position, config.getProperty("zombiCaracono"));
	}
	
	public Zombi zombiLector(Point position) {
		return new Zombi(false, false, true, false, false, 95, 60, position, config.getProperty("zombiLector"));
	}

	public Zombi zombiPortero(Point position) {
		return new Zombi(false, false, false, true, false, 100, 70, position, config.getProperty("zombiPortero"));
	}
	
	public Zombi zombiBailarin(Point position) {
		return new Zombi(false, false, false, false, true, 70, 60, position, config.getProperty("zombiBailarin"));
	}
}	
