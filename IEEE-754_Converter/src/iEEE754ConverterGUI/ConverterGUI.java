package iEEE754ConverterGUI;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ieee.Conversion_Logic.*;

import javax.swing.*;


public class ConverterGUI implements ActionListener {
	
	DoubleP_Conversion_Logic sixtyFour = new DoubleP_Conversion_Logic();
	SingleP_Conversion_Logic thirtyTwo = new SingleP_Conversion_Logic();
	

	public void main_gui(){
		JFrame f = makeJFrame();
		}
	
	public JFrame makeJFrame(){
		JFrame f = new JFrame("IEEE Converter");
		f.setSize(750, 600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		f.setResizable(false);

		JPanel pan1 = new JPanel();
		pan1.setLayout(new GridLayout(0, 3));
		
		JLabel lab1 = makeLabel("Labe1");
		JTextField f1 = makeBox("0.0");
		
		
		
		pan1.add(f1);
		pan1.add(lab1);
		f.add(pan1);
		return f;
		
		
	}
	
	public JLabel makeLabel(String labName){
		JLabel label = new JLabel();
		label.setText(labName);
		return label;
		
	}
	
	public JTextField makeBox(String displayText){
		JTextField field = new JTextField();
		field.setText(displayText);
		field.setSize(10, 200);
		return field;
	}

	//run main application
	public static void main(String[] args) {
		ConverterGUI g = new ConverterGUI();
		g.main_gui();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}


