package Calculator;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextArea;      
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;

public class Calculator extends Frame implements ActionListener {
	
	TextArea display = new TextArea();
	Button btn1 = new KeyButton("1");
	Button btn2 = new KeyButton("2");
	Button btn3 = new KeyButton("3");
	Button btn4 = new KeyButton("4");
	Button btn5 = new KeyButton("5");
	Button btn6 = new KeyButton("6");
	Button btn7 = new KeyButton("7");
	Button btn8 = new KeyButton("8");
	Button btn9 = new KeyButton("9");
	Button btn0 = new KeyButton("0");
	Button btn00 = new KeyButton("00");
	
	Button btnPlus = new OperatorButton("+");
	Button btnMinus = new OperatorButton("-");
	Button btnMul = new OperatorButton("*");
	Button btnDiv = new OperatorButton("/");
	Button btnClear = new OperatorButton("CE");
	Button btnEqual = new OperatorButton("=");
	Button btnMod = new OperatorButton("%");
	Button btnBack = new OperatorButton("←");
	Button btnSquare = new OperatorButton("√");
	
	Double num1, num2, result;
	int add, minus, mul, div, mod;
	
	public Calculator() {		
		
		setLayout(new GridLayout(4,1));
		add(display);
		add(buildButtonPanel_1());
		add(buildButtonPanel_2());
		add(buildButtonPanel_3());
		
		setTitle("Calculator");
		setSize(250,300);
		setVisible(true);
		setLocation(400,200);
		setBackground(Color.lightGray);
	
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	private Panel buildButtonPanel_1() {
		Panel panel = new Panel();
		
		panel.setLayout(new GridLayout(2,5));
		panel.add(new OperatorButton("MC"));
		panel.add(new OperatorButton("MR"));
		panel.add(new OperatorButton("MS"));
		panel.add(new OperatorButton("M+"));
		panel.add(new OperatorButton("M-"));
		
		panel.add(btnBack);
		panel.add(btnClear);
		panel.add(new OperatorButton("C"));
		panel.add(new OperatorButton("±"));
		panel.add(btnSquare);
		
		return panel;
	}
	
	private Panel buildButtonPanel_2() {
		Panel panel = new Panel();
		
		panel.setLayout(new GridLayout(2,5));
		panel.add(btn7);
		panel.add(btn8);
		panel.add(btn9);
		panel.add(btnDiv);
		panel.add(btnMod);
		
		panel.add(btn4);
		panel.add(btn5);
		panel.add(btn6);
		panel.add(btnMul);
		panel.add(new OperatorButton("1/x"));
		
		return panel;
	}
	
	private Panel buildButtonPanel_3() {
		Panel panel = new Panel();

		panel.setLayout(new GridLayout(2,5));
		panel.add(btn1);
		panel.add(btn2);
		panel.add(btn3);
		panel.add(btnMinus);
		panel.add(new OperatorButton("log"));
		
		panel.add(btn0);
		panel.add(btn00);
		panel.add(new KeyButton("."));
		panel.add(btnPlus);
		panel.add(btnEqual);
		
		return panel;
	}
	
	class KeyButton extends Button {
		
		public KeyButton(String label) {
			super(label);
			addActionListener(Calculator.this);
		}
	}

	class OperatorButton extends Button {

		public OperatorButton(String label) {
			super(label);
			addActionListener(Calculator.this);
		}
	}
	
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();
		Button btn = (Button)source;
		
		if(source instanceof KeyButton) {
			display.append(btn.getLabel());
		} else if(source instanceof OperatorButton) {
			
			if(source == btnPlus) {
				num1 = reader();
				display.setText("");
				setOperator();
				add++;
			} else if(source == btnMinus) {
				num1 = reader();
				display.setText("");
				setOperator();
				minus++;
			} else if(source == btnMul) {
				num1 = reader();
				display.setText("");
				setOperator();
				mul++;
			} else if(source == btnDiv) {
				num1 = reader();
				display.setText("");
				setOperator();
				div++;
			}else if(source == btnMod) {
				num1 = reader();
				display.setText("");
				setOperator();
				mod++;
				
			} else if(source == btnClear) {
				display.setText("");
				
			} else if(source == btnEqual) {
				num2 = reader();
				display.setText("");
				if(add>0) {
					result = num1+num2;
				} else if(mul>0) {
					result = num1*num2;
				} else if(minus>0) {
					result = num1-num2;
				} else if(div>0) {
					result = num1/num2;
				} else if(mod>0) {
					result = num1%num2;
				}
				display.setText(Double.toString(result));
				
			} else if(source == btnBack) {
				inputBackspace();

			} else if(source == btnSquare) {
				calculateSquareRoot();
			}
		}
	}

	public void calculateSquareRoot() {
		Double value = Double.valueOf(display.getText());

		BigDecimal tmp = new BigDecimal(Math.sqrt(value));

		Double f = tmp.doubleValue();
		Integer i = tmp.intValue();
		if(f.equals(Double.valueOf(i)))
			display.setText(String.valueOf(i));
		else
			display.setText(String.valueOf(f));
	}
	
	//백스페이스 구현
	public void inputBackspace() {
		String txtValue = display.getText();
		display.setText(txtValue.substring(0, txtValue.length()-1));
		if(display.getText().equals(""))
			display.setText("0");
	}
	
	public void setOperator() {
		add=0; mul=0; minus=0; div=0; mod=0;
	}
	
	//텍스트로 부터 문자열 읽어 들이는 함수
	public Double reader() {
		Double num;
		String str = display.getText();
		num = Double.valueOf(str);
		return num;
	}
}











