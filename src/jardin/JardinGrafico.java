package jardin;

import java.awt.Image;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import ente.grafico.EnteGrafico;
import gui.GUI;

public class JardinGrafico {
	private GUI gui;
	private JLayeredPane panelGrafico;
	private Properties config;
	private LinkedList<JLabel> entes;
	
	public JardinGrafico(GUI gui, String modoJuego) {
		entes = new LinkedList<JLabel>();
		this.gui = gui;
		config = new Properties();
		try {
			FileReader reader=new FileReader("assets/configuracion/panelGrafico.properties");
			config.load(reader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		panelGrafico = new JLayeredPane();
		panelGrafico.setBounds(10, 110, Integer.parseInt(config.getProperty("width")), Integer.parseInt(config.getProperty("heigth")));		
		panelGrafico.setLayout(null);		
		panelGrafico.setOpaque(false);
		ImageIcon img= imagenEscalada(config.getProperty("fondo_"+modoJuego), panelGrafico.getWidth(), panelGrafico.getHeight());
		JLabel fondo = new JLabel(img);
		fondo.setSize(900, 600);
	
		gui.addJPanel(panelGrafico);			
		panelGrafico.add(fondo);
		panelGrafico.setLayer(fondo, 0);		
	}
	
	public void removeEnte(EnteGrafico e) {
		if(entes.remove(e.getSkin())) {
			panelGrafico.remove(e.getSkin());
			rePaint();
		}
	}
	
	public void setEnte(EnteGrafico e) {
		entes.add(e.getSkin());
		panelGrafico.add(e.getSkin());
		panelGrafico.setLayer(e.getSkin(), 1);
		gui.repintar();
	}
	
	public void rePaint() {
		panelGrafico.repaint();
	}
	
	private ImageIcon imagenEscalada(String ruta, int ancho, int largo) {
		ImageIcon img=new ImageIcon(new ImageIcon(ruta).getImage().getScaledInstance(ancho,largo, Image.SCALE_SMOOTH));
		return img; 
	}
}
