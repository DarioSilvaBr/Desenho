import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DrawFrame extends JFrame{
	private JPanel options = new JPanel();
	private String[] nomesCores = {"Preto", "Amarelo", "Azul", "Azul Turqueza", "Branco", "Cinza", "Cinza Claro",
			"Cinza Escuro", "Laranja", "Rosa", "Violeta", "Verde", "Vermelho"};
	private Color[] cores = {new Color(0,0,0), new Color(255,255,0), new Color(0,0,255), new Color(0,255,255)
			,new Color(255,255,255),new Color(128,128,128), new Color(192,192,192),new Color(64,64,64)
			,new Color(255,200,0),new Color(255,175,175),new Color(255,0,255),new Color(0,255,0),new Color(255,0,0)};
	
	
	private String[] nomesFormas = {"Desenho Livre","Linha","Elipse", "Retângulo"};
	JLabel barraDeStatus = new JLabel(); 
	
	public DrawFrame() {
		super("Desenhos By Dário Silva");
		DrawPanel panel = new DrawPanel(barraDeStatus);
		
		JButton desfazer = new JButton("Desfazer");
		desfazer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if(panel.getShapeType() != 0) {	
					panel.decrementShapeCount();
				} else {
					panel.removerPoint();
				}
				repaint();
			}
		});
		options.add(desfazer);
		
		JButton limpar = new JButton("Limpar");
		limpar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if(panel.getShapeType()!= 0) {
					panel.clearShapeCount();
				}else {
					panel.clearPoints();
				}
				repaint();
			}
		});
		options.add(limpar);
		
		
		JComboBox<String> coresJComboBox = new JComboBox<String>(nomesCores);
		coresJComboBox.setMaximumRowCount(3);
		coresJComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED)
					panel.setCurrentColor(cores[coresJComboBox.getSelectedIndex()]);
			}
		});
		options.add(coresJComboBox);
		
		JComboBox<String> formasJComboBox = new JComboBox<String>(nomesFormas);
		formasJComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED)
					panel.setShapeType(formasJComboBox.getSelectedIndex());
				}
		});
		options.add(formasJComboBox);
		
		JCheckBox filledCheckBox = new JCheckBox("Preenchido");
		filledCheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if(filledCheckBox.isSelected())
					panel.setFilledShape(true);
				else
					panel.setFilledShape(false);
			}
		});
		options.add(filledCheckBox);
		
		this.add(options, BorderLayout.NORTH);
		this.add(panel, BorderLayout.CENTER);
		this.add(barraDeStatus, BorderLayout.SOUTH);
	}
}
