package iEEE754ConverterGUI;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ieee.Conversion_Logic.*;

import javax.swing.*;


public class ConverterGUI implements ActionListener {
	public static JFrame frame1;
	public static JFrame DBSP;
	public static JTextField field2  = new JTextField();
	public static JTextField field = new JTextField("0.0");
	

	public void main_gui(){
		
		makeInitialJFrame();
		
	}
	
	public JFrame makeInitialJFrame(){
		frame1 = new JFrame("IEEE Converter");
		frame1.setSize(750, 600);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setVisible(true);
		frame1.setLocationRelativeTo(null);
		frame1.setResizable(false);
		Font f1 = new Font(null, Font.BOLD, 22);
		JPanel pan1 = new JPanel();
		pan1.setLayout(new GridLayout(4, 1));
		
		JButton b1 = new JButton();
		b1.setText("Convert from Decimal to Binary (Single Precision)");
		b1.setFont(f1);
		b1.setSize(20, 10);
		b1.addActionListener(this);
		b1.setActionCommand("Decimal to Binary (Single Precision)");
	
		
		JButton b2 = new JButton();
		b2.setText("Convert from Binary to Decimal (Single Precision)");
		b2.setFont(f1);
		b2.setSize(20, 10);
		b2.addActionListener(this);
		b2.setActionCommand("Binary to Decimal (Single Precision)");
		
		JButton b3 = new JButton();
		b3.setText("Convert from Decimal to Binary (Double Precision)");
		b3.setFont(f1);
		b3.setSize(20, 10);
		b3.addActionListener(this);
		b3.setActionCommand("Decimal to Binary (Double Precision)");
		
		JButton b4 = new JButton();
		b4.setText("Convert from Binary to Decimal (Double Precision)");
		b4.setFont(f1);
		b4.setSize(20, 10);
		b4.addActionListener(this);
		b4.setActionCommand("Binary to Decimal (Double Precision)");
		
		pan1.add(b1);
		pan1.add(b2);
		pan1.add(b3);
		pan1.add(b4);
		frame1.add(pan1);
		return frame1;			
	}
	
	public void destroyFrame(JFrame f ){
		f.dispose();
		f.setVisible(false);
	}
	
	public JFrame makeFrameDBSP(){
		DBSP = new JFrame("IEEE Converter");
		DBSP.setSize(750, 600);
		DBSP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DBSP.setVisible(true);
		DBSP.setLocationRelativeTo(null);
		DBSP.setResizable(false);
		Font f1 = new Font(null, Font.BOLD, 22);
		JPanel pan1 = new JPanel();
		pan1.setLayout(new GridLayout(3, 1));
		
		field.setFont(f1);
		field2.setFont(f1);
		
		JButton b1 = new JButton("Convert To Binary (Single Precision)");
		b1.setFont(f1);
		b1.addActionListener(this);
		b1.setActionCommand("Convert To Binary (Single Precision)");
		
		pan1.add(field);
		pan1.add(b1);
		pan1.add(field2);
		DBSP.add(pan1);
		return DBSP;
	}
	
	//run main application
	public static void main(String[] args) {
		ConverterGUI g = new ConverterGUI();
		g.main_gui();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DoubleP_Conversion_Logic sixtyFour = new DoubleP_Conversion_Logic();
		SingleP_Conversion_Logic thirtyTwo = new SingleP_Conversion_Logic();
		//ConverterGUI gui = new ConverterGUI();
	if(e.getActionCommand().equals("Decimal to Binary (Single Precision)")){
		System.out.println(e.getActionCommand());
		destroyFrame(frame1);
		makeFrameDBSP();
		
	}else if(e.getActionCommand().equals("Binary to Decimal (Single Precision)")){
		System.out.println(e.getActionCommand());
		destroyFrame(frame1);
	}else if(e.getActionCommand().equals("Decimal to Binary (Double Precision)")){
		System.out.println(e.getActionCommand());
		destroyFrame(frame1);
	}else if(e.getActionCommand().equals("Binary to Decimal (Double Precision)")){
		System.out.println(e.getActionCommand());
		destroyFrame(frame1);
	}else if(e.getActionCommand().equals("Convert To Binary (Single Precision)")){
		System.out.println(e);
		String t = field.getText();
		String binNum = thirtyTwo.convertToSinglePrecision(t);
		field2.setText(binNum);
		
	}
	
	}

}


