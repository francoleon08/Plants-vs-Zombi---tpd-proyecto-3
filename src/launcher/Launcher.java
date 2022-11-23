package launcher;

import javax.swing.SwingUtilities;

import gui.GUI;
import logica.Logica;

public class Launcher {

        public static void main(String[] args) {   
            SwingUtilities.invokeLater(GUI::new);
        }
    }
