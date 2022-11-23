package generadorNiveles;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class GeneradorNiveles extends JFrame {
	private JPanel panelPrincipal;
	private static String[] ZOMBIS = {"Comun", "Abanderado", "Caracono", "Lector", "Portero", "Bailarin"};
	private static String[] PLANTAS = {"Lanza Guisantes", "Girasol", "Jalapeno", "Seta Desporada", "Seta Solar", "Humoseta", "Nuez"};
	private static String[] MODOS = {"dia", "noche"};
	private int cantPlantas;
    private String ruta;
    private String zombisInsertados;
    private String plantasInsertadas;
    private int nivel;
    
    public GeneradorNiveles() {
        ruta = "assets/niveles/";
        zombisInsertados = "";
        plantasInsertadas = "";
        cantPlantas = 0;
        nivel = 1;                
        
        setTitle("Generador - Plantas vs. Zombis");
        setSize(400,310);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
        
        panelPrincipal = new JPanel();
        panelPrincipal.setBounds(getContentPane().getBounds());
        getContentPane().add(panelPrincipal);
        
        JFormattedTextField textNivel = new JFormattedTextField();
        textNivel.setBounds(84, 67, 39, 31);
        textNivel.setColumns(10);
        textNivel.setValue(nivel);
        
        JLabel labelNivel = new JLabel("Nivel:");
        labelNivel.setBounds(28, 67, 70, 31);
        
        JLabel labelModo = new JLabel("Modo:");
        labelModo.setBounds(235, 67, 70, 31);
        
        JComboBox comboBoxModo = new JComboBox();
        comboBoxModo.setModel(new DefaultComboBoxModel(MODOS));
        comboBoxModo.setBounds(294, 70, 80, 24);
        
        JComboBox comboBoxZombi = new JComboBox();
        comboBoxZombi.setModel(new DefaultComboBoxModel(ZOMBIS));
        comboBoxZombi.setBounds(98, 151, 128, 24);
        
        JLabel lblZombi = new JLabel("Zombi:");
        lblZombi.setBounds(28, 156, 70, 15);
        
        JButton btnAgregarZombi = new JButton("Agregar");
        btnAgregarZombi.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		agregarZombi(comboBoxZombi.getItemAt(comboBoxZombi.getSelectedIndex()).toString());
        	}
        });
        btnAgregarZombi.setBounds(257, 151, 117, 25);
        
        JButton btnGenerar = new JButton("Generar Nivel");
        btnGenerar.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		nivel =(int) textNivel.getValue();
        		if(nivel > 0 && nivel <999) {        			
        			crearNivel(comboBoxModo.getItemAt(comboBoxModo.getSelectedIndex()).toString());
        		}
        	}
        });
        btnGenerar.setBounds(51, 202, 140, 51);
        
        JLabel lblPlanta = new JLabel("Planta:");
        lblPlanta.setBounds(28, 115, 70, 15);
        
        JComboBox comboBoxPlantas = new JComboBox();
        comboBoxPlantas.setBounds(98, 110, 128, 24);
        comboBoxPlantas.setModel(new DefaultComboBoxModel(PLANTAS));
        
        JButton btnAgregarPlanta = new JButton("Agregar");
        btnAgregarPlanta.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if(cantPlantas <= 6) {
        			agregarPlanta(comboBoxPlantas.getItemAt(comboBoxPlantas.getSelectedIndex()).toString());
        			cantPlantas++;
        		}
        		else {
        			generarMensaje("CANTIDAD MAXIMA DE PLANTAS INSERTADAS");
        		}
        	}
        });
        btnAgregarPlanta.setBounds(257, 110, 117, 25);
        
        
        JButton btnReset = new JButton("Reset Campos");
        btnReset.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		nivel = 1;
        		textNivel.setValue(nivel);
        		cantPlantas = 0;
        		plantasInsertadas = "";
        		zombisInsertados = "";  
        		generarMensaje("CAMPOS RESETEADOS");
        	}
        });
        btnReset.setBounds(206, 202, 140, 51);
        
        JLabel lblGeneradorDeNiveles = new JLabel("Generador de niveles");
        lblGeneradorDeNiveles.setHorizontalAlignment(SwingConstants.CENTER);
        lblGeneradorDeNiveles.setFont(new Font("Dialog", Font.BOLD, 15));
        lblGeneradorDeNiveles.setBounds(0, 31, 400, 24);
        
        getContentPane().add(textNivel);
        getContentPane().add(labelNivel);
        getContentPane().add(labelModo);
        getContentPane().add(comboBoxModo);
        getContentPane().add(comboBoxZombi);
        getContentPane().add(lblZombi);
        getContentPane().add(btnAgregarZombi);
        getContentPane().add(btnGenerar);
        getContentPane().add(lblPlanta);
        getContentPane().add(comboBoxPlantas);
        getContentPane().add(btnAgregarPlanta);
        getContentPane().add(btnReset);
        getContentPane().add(lblGeneradorDeNiveles);
        setVisible(true);
    }

    private void agregarPlanta(String planta) {
    	String pos = "";
    	if(cantPlantas == 0)
    		pos += "000";
    	else
    		pos += cantPlantas*100;
    	
    	switch (planta) {
		case "Lanza Guisantes": {
			plantasInsertadas += "L("+pos+",000)\n";
			break;
		}
		case "Girasol": {
			plantasInsertadas += "G("+pos+",000)\n";
			break;
		}
		case "Jalapeno": {
			plantasInsertadas += "J("+pos+",000)\n";
			break;
		}
		case "Seta Desporada": {
			plantasInsertadas += "D("+pos+",000)\n";
			break;
		}
		case "Seta Solar": {
			plantasInsertadas += "S("+pos+",000)\n";
			break;
		}
		case "Humoseta": {
			plantasInsertadas += "H("+pos+",000)\n";
			break;
		}
		case "Nuez": {
			plantasInsertadas += "N("+pos+",000)\n";
			break;
		}
	}
	}

	private void agregarZombi(String zombi) {
    	int posInsert = (int)(Math.random()*5);
    	String pos = "";
    	if(posInsert == 0)
    		pos = "000";
    	else
    		pos += posInsert*100;
    	
		switch (zombi) {
			case "Comun": {
				zombisInsertados += "1(900,"+pos+")\n";	
				break;
			}
			case "Abanderado": {
				zombisInsertados += "2(900,"+pos+")\n";
				break;
			}
			case "Caracono": {
				zombisInsertados += "3(900,"+pos+")\n";
				break;
			}
			case "Lector": {
				zombisInsertados += "4(900,"+pos+")\n";
				break;
			}
			case "Portero": {
				zombisInsertados += "5(900,"+pos+")\n";
				break;
			}
			case "Bailarin": {
				zombisInsertados += "6(900,"+pos+")\n";
				break;
			}
		}
	}

	private void crearNivel(String modo) {
        File nivel = new File(ruta+"nivel-"+this.nivel+"-"+modo+".txt");
        if (nivel.exists()) {
            javax.swing.JOptionPane.showMessageDialog(this, "EL NIVEL YA EXISTE");
        }
        else {
            try {
                FileWriter fw = new FileWriter(nivel);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(plantasInsertadas+"\n"+zombisInsertados);
                bw.close();
                generarMensaje("NIVEL GENERADO CON EXITO");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
	
	private void generarMensaje(String m) {
		javax.swing.JOptionPane.showMessageDialog(this, m);
	}
}
