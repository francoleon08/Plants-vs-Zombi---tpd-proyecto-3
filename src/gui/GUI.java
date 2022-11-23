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
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.FlowLayout;

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
	private JButton pala;
	private JButton buttonMusic;
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
				
		configGui();
		
		playMusic = new ImageIcon(guiConfig.getProperty("playMusic"));
		stopMusic = new ImageIcon(guiConfig.getProperty("stopMusic"));
		logo = new JLabel(new ImageIcon(guiConfig.getProperty("logo")));
		logo.setBounds(200, 116, 700, 418);
		
		textDinero = new JTextPane();
		textDinero.setEditable(false);
		textDinero.setFont(new Font("Arial", Font.BOLD, 17));
		textDinero.setOpaque(false);
		textDinero.setBounds(750, 53, 200, 20);
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
		
		botonExit = new JPanel();
		botonExit.addMouseListener(new MouseAdapter() {			
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		botonExit.setBackground(new Color(224, 27, 36));
		botonExit.setBounds(1075, 0, 25, 25);
		botonExit.setLayout(null);
	
		getContentPane().add(botonExit);
		getContentPane().add(logo);
		getContentPane().add(textDinero);
		getContentPane().add(buttonModoDia);
		getContentPane().add(buttonModoNoche);
		getContentPane().add(pala);
		getContentPane().add(buttonMusic);
		getContentPane().add(fondo);
		
		accionMouse();			
		
		setVisible(true);
	}

	private void initGame() {
		logo.setVisible(false);
		buttonModoNoche.setVisible(false);
		buttonModoDia.setVisible(false);
		pala.setVisible(true);
		buttonMusic.setLocation(1020, 25);
		logica.initGame();
		botoneraGrafica = new Botonera(this);
		botoneraGrafica.setBotonera(logica.getPlantasDisponibles());		
		textDinero.setText("DINERO: "+logica.getDinero());
		textDinero.setVisible(true);
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
					insert.setLocation(insert.getX()-66, insert.getY()-116);
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
