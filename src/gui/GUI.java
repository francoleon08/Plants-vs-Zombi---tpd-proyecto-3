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

public class GUI extends JFrame {
	private Logica logica;
	private Properties guiConfig;
	private Botonera botoneraGrafica;
	private JLayeredPane panelGrafico;
	private JLayeredPane botonera;	
	private JTextPane textDinero;
	private JButton buttonModoDia;
	private JButton buttonModoNoche;
	private JButton pala;
	private JButton buttonMusic;
	private ImageIcon playMusic;
	private ImageIcon stopMusic;
	private JLabel logo;
	private JLabel fondo;
	private int indexPlanta;
	private boolean runJuego;
	private boolean accionPala;
	private JLayeredPane panelFondo;
	
	public GUI(Logica logica) {
		cargarConfiguracion();		
		this.logica = logica;
		this.indexPlanta = -1;
		this.runJuego = false;
		this.accionPala = false;
		playMusic = new ImageIcon(guiConfig.getProperty("playMusic"));
		stopMusic = new ImageIcon(guiConfig.getProperty("stopMusic"));
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(1100, 750));
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		getContentPane().setLayout(null);
		
		panelFondo  = new JLayeredPane();
		panelFondo.setSize(1100, 750);
		panelFondo.setOpaque(false);
		getContentPane().add(panelFondo);
		
		//getContentPane().setBackground(new Color(154, 228, 146));			
		
		Toolkit tk = Toolkit.getDefaultToolkit();		
		Image cursorImage = tk.getImage(guiConfig.getProperty("cursor"));
		Cursor cursor = tk.createCustomCursor(cursorImage, new Point(0,0), "Custom Cursor");	
		getContentPane().setCursor(cursor);
		
		logo = new JLabel(new ImageIcon(guiConfig.getProperty("logo")));
		logo.setBounds(200, 116, 700, 418);
		
		textDinero = new JTextPane();
		textDinero.setEditable(false);
		textDinero.setFont(new Font("Arial", Font.BOLD, 17));
		textDinero.setBackground(new Color(154, 228, 146));
		textDinero.setText("DINERO: "+logica.getDinero());
		textDinero.setBounds(750, 53, 150, 20);
		textDinero.setVisible(false);
		
		buttonModoDia = new JButton(new ImageIcon(guiConfig.getProperty("modoDia")));
		buttonModoNoche = new JButton(new ImageIcon(guiConfig.getProperty("modoNoche")));		
		buttonModoDia.setBounds(200, 578, 283, 70);
		buttonModoNoche.setBounds(617, 578, 283, 70);		
		buttonModoDia.setContentAreaFilled(false);		
		buttonModoNoche.setContentAreaFilled(false);
		buttonModoDia.setBorder(null);
		buttonModoNoche.setBorder(null);
		
		pala = new JButton(new ImageIcon(guiConfig.getProperty("pala")));
		pala.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			}
		});
		pala.setBounds(910,15,90,90);
		pala.setContentAreaFilled(false);
		pala.setBorder(null);
		pala.setVisible(false);
		
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
		
		buttonMusic = new JButton(playMusic);
		buttonMusic.setBounds(515, 578, 70, 70);
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
		
		fondo = new JLabel();
		fondo.setBounds(0, 0, 1100, 750);
		fondo.setVisible(false);
		
		getContentPane().add(logo);
	
		getContentPane().add(buttonModoDia);
		getContentPane().add(buttonModoNoche);
		panelFondo.add(textDinero,1);
		
		
		panelFondo.add(buttonMusic,0);
		panelFondo.add(fondo,1);
		accionMouse();				
	}

	private void initGame() {
		logo.setVisible(false);
		buttonModoNoche.setVisible(false);
		buttonModoDia.setVisible(false);
		pala.setVisible(true);
		panelFondo.add(pala,0);
		buttonMusic.setLocation(1020, 25);
		logica.initGame();
		botoneraGrafica = new Botonera(this);
		botoneraGrafica.setBotonera(logica.getPlantasDisponibles());
		textDinero.setVisible(true);
		if(logica.getModoJuego() == "dia") {
			fondo.setIcon(new ImageIcon(guiConfig.getProperty("fondo_dia2")));
		}
		else {
			fondo.setIcon(new ImageIcon(guiConfig.getProperty("fondo_noche")));
		}
		fondo.setVisible(true);
		runJuego = true;
	}
	//Panel de juego
	public void addJPanel(JLayeredPane panel) {
		panelGrafico = panel;
		panelFondo.add(panelGrafico,-1);
		
	}
	//Botonera
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
	
	public void setVisible() {
		this.setVisible(true);
	}
	public String getPropiedad(String key) {
		return guiConfig.getProperty(key);
	}
	
	private void accionMouse() {
		getContentPane().addMouseListener(new MouseAdapter() {			
			public void mouseClicked(MouseEvent e) {								
				if(runJuego) {
					Point insert = e.getPoint();
					insert.setLocation(insert.getX()-110, insert.getY()-116);
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
