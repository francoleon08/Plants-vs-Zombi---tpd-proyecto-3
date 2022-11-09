package gui;

import java.awt.Dimension;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputFilter.Config;
import java.util.Properties;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jardin.JardinGrafico;

@SuppressWarnings("serial")
public class GUI extends JFrame {
	private JPanel panelGrafico;
	private Properties guiConfig;
	//private JLabel fondoJardin;
	private JardinGrafico jardin;
	private JPanel botoneraPlantas;
	private JPanel panel_1;
	
	public GUI() {
		guiConfig= new Properties();
		try {
			FileReader reader=new FileReader("assets/configuracion/config_gui.properties");  
			guiConfig.load(reader);
			
		} catch (IOException e) {
			System.out.println("NO cargo la wea");
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(1000, 719));
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		getContentPane().setLayout(null);
		
		botoneraPlantas = new JPanel();
		botoneraPlantas.setBounds(84, 37, 393, 64);
		ImageIcon fondoBotonera= imagenEscalada(guiConfig.getProperty("fondo_botonera"), botoneraPlantas.getWidth(),botoneraPlantas.getHeight());
		JLabel fondoBot=new JLabel(fondoBotonera);
		botoneraPlantas.add(fondoBot);
		getContentPane().add(botoneraPlantas);
		
	    //Panel_1 es jardin grafico.		
		panel_1 = new JPanel();
		panel_1.setBounds(22, 69, 900, 600);
		panel_1.setOpaque(false);
		ImageIcon img2= imagenEscalada(guiConfig.getProperty("fondo_dia"), panel_1.getWidth(), panel_1.getHeight());
		JLabel fondoLabel=new JLabel(img2); 
		panel_1.add(fondoLabel);
		
		
		getContentPane().add(panel_1);
		
		
		
		//jardin=new JardinGrafico();
	}
	private ImageIcon imagenEscalada(String ruta, int ancho, int largo) {
		ImageIcon img=new ImageIcon(new ImageIcon(ruta).getImage().getScaledInstance(ancho,largo, Image.SCALE_SMOOTH));
		return img; 
	}
	public void addJPanel(JPanel panel) {
		panelGrafico = panel;
		getContentPane().add(panelGrafico);
	}
	
	public void setVisible() {
		this.setVisible(true);
	}
}
