package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import logica.Logica;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextPane;

import ente.grafico.EnteGrafico;
import gui.botonera.Botonera;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class GUI extends JFrame {
	private Logica logica;
	private Properties guiConfig;
	private Botonera botoneraGrafica;
	private JLayeredPane panelGrafico;
	private JLayeredPane botonera;	
	private JTextPane textDinero;
	private JButton buttonModoDia;
	private JButton buttonModoNoche;
	private JButton buttonMusic;
	private ImageIcon playMusic;
	private ImageIcon stopMusic;
	private JLabel logo;
	private int indexPlanta;
	private boolean runJuego;
	
	public GUI(Logica logica) {
		cargarConfiguracion();		
		this.logica = logica;
		this.indexPlanta = -1;
		this.runJuego = false;
		playMusic = new ImageIcon(guiConfig.getProperty("playMusic"));
		stopMusic = new ImageIcon(guiConfig.getProperty("stopMusic"));
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(1100, 720));
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(154, 228, 146));			
		
		Toolkit tk = Toolkit.getDefaultToolkit();		
		Image cursorImage = tk.getImage("assets\\imagenes\\gui\\cursor.png");
		Cursor cursor = tk.createCustomCursor(cursorImage, new Point(0,0), "Custom Cursor");	
		getContentPane().setCursor(cursor);
		
		logo = new JLabel(new ImageIcon(guiConfig.getProperty("logo")));
		logo.setBounds(211, 99, 700, 418);
		
		textDinero = new JTextPane();
		textDinero.setEditable(false);
		textDinero.setFont(new Font("Arial", Font.BOLD, 17));
		textDinero.setBackground(new Color(154, 228, 146));
		textDinero.setText("DINERO: "+logica.getDinero());
		textDinero.setBounds(650, 53, 150, 20);
		textDinero.setVisible(false);
		
		buttonModoDia = new JButton(new ImageIcon(guiConfig.getProperty("modoDia")));
		buttonModoNoche = new JButton(new ImageIcon(guiConfig.getProperty("modoNoche")));		
		buttonModoDia.setBounds(211, 578, 283, 70);
		buttonModoNoche.setBounds(628, 578, 283, 70);		
		buttonModoDia.setContentAreaFilled(false);		
		buttonModoNoche.setContentAreaFilled(false);
		buttonModoDia.setBorder(null);
		buttonModoNoche.setBorder(null);
		
		buttonModoDia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logica.setModoJuego("dia");
				initGame();
			}
		});		
		buttonModoNoche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logica.setModoJuego("noche");
				initGame();
			}
		});
		
		buttonMusic = new JButton(playMusic);
		buttonMusic.setBounds(527, 578, 70, 70);
		buttonMusic.setOpaque(false);
		buttonMusic.setContentAreaFilled(false);	
		buttonMusic.setBorder(null);
		buttonMusic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!logica.playPausaMusica()) {
					buttonMusic.setIcon(stopMusic);
				}
				else {
					buttonMusic.setIcon(playMusic);
				}
			}
		});
		
		getContentPane().add(logo);
		getContentPane().add(textDinero);
		getContentPane().add(buttonModoDia);
		getContentPane().add(buttonModoNoche);
		getContentPane().add(buttonMusic);
		accionMouse();				
	}
	
	private void initGame() {
		logo.setVisible(false);
		buttonModoNoche.setVisible(false);
		buttonModoDia.setVisible(false);
		buttonMusic.setLocation(840, 25);
		logica.initGame();
		botoneraGrafica = new Botonera(this);
		botoneraGrafica.setBotonera(logica.getPlantasDisponibles());
		textDinero.setVisible(true);
		runJuego = true;
	}
	
	public void addJPanel(JLayeredPane panel) {
		panelGrafico = panel;
		getContentPane().add(panelGrafico);
	}
	
	public void addBotonera(JLayeredPane panel) {
		botonera = panel;
		getContentPane().add(botonera);
	}
	
	public void accionBoton(int index) {
		indexPlanta = index;
	}
	
	public void repintar() {
		panelGrafico.repaint();		
	}
	
	public void setVisible() {
		this.setVisible(true);
	}
	
	private void accionMouse() {
		getContentPane().addMouseListener(new MouseAdapter() {			
			public void mouseClicked(MouseEvent e) {								
				if(runJuego) {
					Point insert = e.getPoint();
					insert.setLocation(insert.getX()-10, insert.getY()-110);
					if(indexPlanta >= 0)
						crearPlanta(insert);
					else
						logica.interactuarMoneda(insert);
					textDinero.setText("DINERO: "+logica.getDinero());
				}
			}
		});
	}	
	
	private void crearPlanta(Point position) {
		logica.crearPlanta(indexPlanta, position);
		indexPlanta = -1;			
	}
	
	public void actualizarBotonera(Iterable<EnteGrafico> list) {
		if(botoneraGrafica != null) {
			botoneraGrafica.actualizarBotonera(list);
		}
	}
	
	public void gameOver() {
		logo.setVisible(true);
		botonera.setVisible(false);
		panelGrafico.setVisible(false);
		buttonModoDia.setVisible(true);
		buttonModoNoche.setVisible(true);
		buttonMusic.setLocation(527, 578);
		runJuego = false;
	}
	
	public void cambiarNivel() {
		botoneraGrafica = new Botonera(this);
		botoneraGrafica.setBotonera(logica.getPlantasDisponibles());
	}
	
	private void cargarConfiguracion() {
		try {
			guiConfig= new Properties();
			FileReader reader=new FileReader("assets/configuracion/config_gui.properties");  
			guiConfig.load(reader);			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
