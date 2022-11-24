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
import generadorNiveles.GeneradorNiveles;
import gui.botonera.Botonera;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GUI extends JFrame {
	private Logica logica;
	private Properties guiConfig;
	private Botonera botoneraGrafica;
	private JLayeredPane panelGrafico;
	private JLayeredPane botonera;	
	private JLayeredPane panelFondo;
	private JTextPane textDinero;
	private JButton buttonModoDia;
	private JButton buttonModoNoche;
	private JButton pala;
	private JButton buttonMusic;
	private JButton btnCrearNiveles;
	private ImageIcon playMusic;
	private ImageIcon stopMusic;
	private JLabel logo;
	private JLabel fondo;
	private JPanel botonExit;
	private int indexPlanta;
	private boolean runJuego;
	private boolean accionPala;
	
	public GUI() {
		cargarConfiguracion();		
		this.logica = new Logica(this);
		this.indexPlanta = -1;
		this.runJuego = false;
		this.accionPala = false;						
		playMusic = new ImageIcon(guiConfig.getProperty("playMusic"));
		stopMusic = new ImageIcon(guiConfig.getProperty("stopMusic"));

		configGui();		
		configAssets();					
		configListener();
		
		getContentPane().add(panelFondo);
		getContentPane().add(logo);	
		getContentPane().add(buttonModoDia);
		getContentPane().add(buttonModoNoche);	
		getContentPane().add(btnCrearNiveles);
				
		panelFondo.add(buttonMusic, 2);
		panelFondo.add(fondo, 1);
		panelFondo.add(botonExit, 0);
		accionMouse();				
		setVisible(true);
	}

	private void initGame() {
		logo.setVisible(false);
		buttonModoNoche.setVisible(false);
		buttonModoDia.setVisible(false);
		pala.setVisible(true);
		panelFondo.add(textDinero,0);
		panelFondo.add(pala,0);
		buttonMusic.setLocation(1020, 25);
		logica.initGame();
		botoneraGrafica = new Botonera(this);
		botoneraGrafica.setBotonera(logica.getPlantasDisponibles());	
		textDinero.setText("DINERO: "+logica.getDinero());
		textDinero.setVisible(true);
		btnCrearNiveles.setVisible(false);
		if(logica.getModoJuego() == "dia") {
			fondo.setIcon(new ImageIcon(guiConfig.getProperty("fondo_dia")));
		}
		else {
			fondo.setIcon(new ImageIcon(guiConfig.getProperty("fondo_noche")));
		}
		fondo.setVisible(true);
		runJuego = true;
	}
	
	private void configGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(1100, 750));
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(154, 228, 146));			
		
		Toolkit tk = Toolkit.getDefaultToolkit();		
		Image cursorImage = tk.getImage(guiConfig.getProperty("cursor"));
		Cursor cursor = tk.createCustomCursor(cursorImage, new Point(0,0), "Custom Cursor");
		getContentPane().setCursor(cursor);
		
		panelFondo  = new JLayeredPane();
		panelFondo.setSize(1100, 750);
		panelFondo.setOpaque(false);
	}
	
	private void configAssets() {
		logo = new JLabel(new ImageIcon(guiConfig.getProperty("logo")));
		logo.setBounds(200, 116, 700, 418);			
		
		textDinero = new JTextPane();
		textDinero.setEditable(false);
		textDinero.setFont(new Font("Arial", Font.BOLD, 20));
		textDinero.setBounds(710, 50, 180, 25);
		textDinero.setOpaque(false);
		textDinero.setVisible(false);	
		textDinero.setForeground(new Color(255,255,255));
		
		
		buttonModoDia = new JButton(new ImageIcon(guiConfig.getProperty("modoDia")));
		buttonModoNoche = new JButton(new ImageIcon(guiConfig.getProperty("modoNoche")));		
		buttonModoDia.setBounds(200, 578, 283, 70);
		buttonModoNoche.setBounds(617, 578, 283, 70);		
		buttonModoDia.setContentAreaFilled(false);		
		buttonModoNoche.setContentAreaFilled(false);
		buttonModoDia.setBorder(null);
		buttonModoNoche.setBorder(null);
		
		pala = new JButton(new ImageIcon(guiConfig.getProperty("pala")));				
		pala.setBounds(910,15,90,90);
		pala.setContentAreaFilled(false);
		pala.setBorder(null);
		pala.setVisible(false);

		buttonMusic = new JButton(playMusic);
		buttonMusic.setBounds(515, 578, 70, 70);
		buttonMusic.setOpaque(false);
		buttonMusic.setContentAreaFilled(false);	
		buttonMusic.setBorder(null);
				
		fondo = new JLabel();
		fondo.setBounds(0, 0, 1100, 750);
		fondo.setVisible(false);			

		botonExit = new JPanel();
		botonExit.setOpaque(false);
		botonExit.setBounds(1073, 2, 25, 25);
		JLabel boton=new JLabel(new ImageIcon(guiConfig.getProperty("boton_exit")));
		boton.setSize(25,25);
		botonExit.add(boton);
		botonExit.setLayout(null);
		
		btnCrearNiveles = new JButton("Crear Niveles");
		btnCrearNiveles.setBounds(930, 2, 130, 25);
		btnCrearNiveles.setOpaque(false);
		btnCrearNiveles.setContentAreaFilled(false);
	}
	
	private void configListener() {
		pala.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			}
		});
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
		pala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accionPala = true;
			}
		});
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
		botonExit.addMouseListener(new MouseAdapter() {			
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		btnCrearNiveles.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new GeneradorNiveles();
			}
		});
	}
	
	public void addJPanel(JLayeredPane panel) {
		panelGrafico = panel;
		panelFondo.add(panelGrafico,-1);
	}
	
	public void addBotonera(JLayeredPane panel) {
		botonera = panel;
		panelFondo.add(botonera, 2);
	}
	
	public void accionBoton(int index) {
		indexPlanta = index;
	}
	
	public void repintar() {
		panelGrafico.repaint();		
	}
	
	public String getPropiedad(String key) {
		return guiConfig.getProperty(key);
	}
	
	private void accionMouse() {
		getContentPane().addMouseListener(new MouseAdapter() {			
			public void mouseClicked(MouseEvent e) {								
				if(runJuego) {
					Point insert = e.getPoint();
					insert.setLocation(insert.getX()-110, insert.getY()-97);
					if(indexPlanta >= 0) {
						crearPlanta(insert);
						accionPala = false;						
					}						
					else {
						if(accionPala) {							
							logica.removerPlanta(insert);
							accionPala = false;
						}
						else {
							logica.interactuarMoneda(insert);
							accionPala = false;
						}
					}
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
		javax.swing.JOptionPane.showMessageDialog(this, "GAME OVER");
		logo.setVisible(true);
		botonera.setVisible(false);
		panelGrafico.setVisible(false);
		buttonModoDia.setVisible(true);
		buttonModoNoche.setVisible(true);
		pala.setVisible(false);
		fondo.setVisible(false);
		btnCrearNiveles.setVisible(true);
		buttonMusic.setLocation(515, 578);
		runJuego = false;
	}
	
	public void cambiarNivel() {	
		runJuego = false;
		javax.swing.JOptionPane.showMessageDialog(this, "SUIGUIENTE NIVEL");
		getContentPane().remove(botonera);
		getContentPane().repaint();
		botoneraGrafica = new Botonera(this);
		botoneraGrafica.setBotonera(logica.getPlantasDisponibles());
		runJuego = true;
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
