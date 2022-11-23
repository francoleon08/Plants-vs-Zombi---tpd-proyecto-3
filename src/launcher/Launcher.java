package launcher;

import javax.swing.SwingUtilities;
import gui.GUI;
import splashscreen.SplashScreen;

public class Launcher {

        public static void main(String[] args) {
        	new SplashScreen(3, "assets/splashScreen/splashScreen.jpg");
            SwingUtilities.invokeLater(GUI::new);
        }
    }
