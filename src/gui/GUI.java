package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputFilter.Config;
import java.util.Properties;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import jardin.JardinGrafico;
import logica.Logica;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextPane;

import gui.botonera.Botonera;

import java.awt.Font;
import java.awt.Button;
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
	private Button button;
	private Button button_1;
	private int indexPlanta;
	
	public GUI(Logica logica) {
		this.logica = logica;
		indexPlanta = -1;
		getContentPane().setBackground(new Color(255, 255, 170));
		guiConfig= new Properties();
		
		try {
			FileReader reader=new FileReader("assets/configuracion/config_gui.properties");  
			guiConfig.load(reader);			
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(1000, 719));
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		getContentPane().setLayout(null);
		
		textDinero = new JTextPane();
		textDinero.setEditable(false);
		textDinero.setFont(new Font("Arial", Font.BOLD, 17));
		textDinero.setBackground(new Color(255,255,170));
		textDinero.setText("DINERO: "+logica.getDinero());
		textDinero.setBounds(700, 55, 154, 40);
		getContentPane().add(textDinero);
		
		button = new Button("MODO DIA");
		button_1 = new Button("MODO NOCHE");
		button.setBounds(118, 147, 103, 22);
		button_1.setBounds(118, 208, 103, 22);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logica.setModoJuego("dia");
				initGame();
			}
		});		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logica.setModoJuego("noche");
				initGame();
			}
		});
		
		getContentPane().add(button);
		getContentPane().add(button_1);
		accionMouse();		
	}
	
	private void initGame() {
		button_1.setVisible(false);
		button.setVisible(false);
		logica.initGame();
		botoneraGrafica = new Botonera(this);
		botoneraGrafica.setBotonera(logica.getPlantasDisponibles());
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
				Point insert = e.getPoint();
				insert.setLocation(insert.getX()-10, insert.getY()-110);
				if(indexPlanta >= 0)
					crearPlanta(insert);
				else
					logica.interactuarMoneda(insert);
				textDinero.setText("DINERO: "+logica.getDinero());
			}
		});
	}	
	
	private void crearPlanta(Point position) {
		logica.crearPlanta(indexPlanta, position);
		indexPlanta = -1;			
	}
}
