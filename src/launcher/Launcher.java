package launcher;

import javax.swing.SwingUtilities;

import logica.Logica;

public class Launcher {

	public static void main(String[] args) {		
		SwingUtilities.invokeLater(Logica::new);
	}
}
