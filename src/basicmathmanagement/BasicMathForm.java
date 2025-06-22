package basicmathmanagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class BasicMathForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField numFirstText;
	private JTextField numSecondText;
	private JTextField resultText;
	private JLabel numFirstError;
	private JLabel numSecondError;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BasicMathForm frame = new BasicMathForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static boolean isInput(String s) {
		if (s.trim().isEmpty()) {
			return false;
		}
		return true;
	}
	
	public static boolean isNumeric(String s) {
		char[] charList = s.trim().toCharArray();
		int count = 0;
		for (int i = 0; i < charList.length; ++i) {
			if (charList[i] == '.') {
				++count;
				continue;
			}
			if (!Character.isDigit(charList[i])) {
				return false;
			}
		}
		if (count > 1) {
			return false;
		}
		return true;
	}
	
	private String formatResult(double numFirst, double numSecond, double result, boolean isDivision) {
	    boolean isFirstInteger = (numFirst % 1 == 0);
	    boolean isSecondInteger = (numSecond % 1 == 0);
	    int decimalPlacesFirst = isFirstInteger ? 0 : String.valueOf(numFirst).split("\\.")[1].length();
	    int decimalPlacesSecond = isSecondInteger ? 0 : String.valueOf(numSecond).split("\\.")[1].length();
	    int maxDecimalPlaces = Math.max(decimalPlacesFirst, decimalPlacesSecond);

		if (isDivision) {
	    	boolean isResultInteger = (result % 1 == 0);
	    	if (isResultInteger) {
	    		return String.valueOf((long) result);
	    	}
	        return String.valueOf(result);
	    }
	    if (isFirstInteger && isSecondInteger) {
	        return String.valueOf((long) result);
	    }
	    return String.format("%." + maxDecimalPlaces + "f", result);
	}
	
	/**
	 * Create the frame.
	 */
	public BasicMathForm() {
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 450, 300);
	    setResizable(false);
	    setExtendedState(JFrame.NORMAL);

	    contentPane = new JPanel();
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setContentPane(contentPane);
	    contentPane.setLayout(null);
		
		JLabel numFirstLable = new JLabel("Number First = ");
		numFirstLable.setBounds(32, 60, 109, 33);
		contentPane.add(numFirstLable);
		
		numFirstText = new JTextField();
		numFirstText.setBounds(137, 60, 130, 26);
		contentPane.add(numFirstText);
		numFirstText.setColumns(10);
		
		JLabel numSecondLable = new JLabel("Number Second = ");
		numSecondLable.setBounds(16, 100, 125, 33);
		contentPane.add(numSecondLable);
		
		numSecondText = new JTextField();
		numSecondText.setColumns(10);
		numSecondText.setBounds(137, 100, 130, 26);
		contentPane.add(numSecondText);
		
	    numFirstError = new JLabel();
	    numFirstError.setForeground(Color.RED);
	    numFirstError.setBounds(280, 60, 150, 26);
	    contentPane.add(numFirstError);

	    numSecondError = new JLabel();
	    numSecondError.setForeground(Color.RED);
	    numSecondError.setBounds(280, 100, 150, 26);
	    contentPane.add(numSecondError);
		
		JButton sumButton = new JButton("Sum");
		sumButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String numFirstString = numFirstText.getText();
				String numSecondString = numSecondText.getText();
	            boolean isFirstInput = isInput(numFirstString);
	            boolean isSecondInput = isInput(numSecondString);
	            boolean isFirstNumeric = isFirstInput && isNumeric(numFirstString);
	            boolean isSecondNumeric = isSecondInput && isNumeric(numSecondString);

	            numFirstError.setText(isFirstInput ? (isFirstNumeric ? "" : "INVALID") : "REQUIRED");
	            numSecondError.setText(isSecondInput ? (isSecondNumeric ? "" : "INVALID") : "REQUIRED");

	            if (isFirstNumeric && isSecondNumeric) {
	                double numFirst = Double.parseDouble(numFirstString);
	                double numSecond = Double.parseDouble(numSecondString);
	                double sum =  numFirst + numSecond;
	                resultText.setText(formatResult(numFirst, numSecond, sum, false));
	            } else {
	                resultText.setText("");
	            }
			}
		});
		sumButton.setBounds(82, 140, 117, 29);
		contentPane.add(sumButton);
		
		JButton productButton = new JButton("Product");
		productButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String numFirstString = numFirstText.getText();
				String numSecondString = numSecondText.getText();
			    boolean isFirstInput = isInput(numFirstString);
			    boolean isSecondInput = isInput(numSecondString);
			    boolean isFirstNumeric = isFirstInput && isNumeric(numFirstString);
			    boolean isSecondNumeric = isSecondInput && isNumeric(numSecondString);
	            numFirstError.setText(isFirstInput ? (isFirstNumeric ? "" : "INVALID") : "REQUIRED");
	            numSecondError.setText(isSecondInput ? (isSecondNumeric ? "" : "INVALID") : "REQUIRED");

	            if (isFirstNumeric && isSecondNumeric) {
					double numFirst = Double.parseDouble(numFirstString);
					double numSecond = Double.parseDouble(numSecondString);
					double product = numFirst * numSecond;
					resultText.setText(formatResult(numFirst, numSecond, product, false));
			    }
			}
		});
		productButton.setBounds(242, 140, 117, 29);
		contentPane.add(productButton);
		
		JButton quotientButton = new JButton("Quotient");
		quotientButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String numFirstString = numFirstText.getText();
				String numSecondString = numSecondText.getText();
			    boolean isFirstInput = isInput(numFirstString);
			    boolean isSecondInput = isInput(numSecondString);
			    boolean isFirstNumeric = isFirstInput && isNumeric(numFirstString);
			    boolean isSecondNumeric = isSecondInput && isNumeric(numSecondString);
	            numFirstError.setText(isFirstInput ? (isFirstNumeric ? "" : "INVALID") : "REQUIRED");
	            numSecondError.setText(isSecondInput ? (isSecondNumeric ? "" : "INVALID") : "REQUIRED");

	            if (isFirstNumeric && isSecondNumeric) {
					double numFirst = Double.parseDouble(numFirstString);
					double numSecond = Double.parseDouble(numSecondString);
					if (numSecond != 0) {
						double quotient = numFirst / numSecond;
						resultText.setText(formatResult(numFirst, numSecond,quotient, true));
					}
					else {
						resultText.setText("DIV BY ZERO");
					}
			    }
			}
		});
		quotientButton.setBounds(242, 190, 117, 29);
		contentPane.add(quotientButton);
		
		JButton differenceButton = new JButton("Difference");
		differenceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String numFirstString = numFirstText.getText();
				String numSecondString = numSecondText.getText();
			    boolean isFirstInput = isInput(numFirstString);
			    boolean isSecondInput = isInput(numSecondString);
			    boolean isFirstNumeric = isFirstInput && isNumeric(numFirstString);
			    boolean isSecondNumeric = isSecondInput && isNumeric(numSecondString);
	            numFirstError.setText(isFirstInput ? (isFirstNumeric ? "" : "INVALID") : "REQUIRED");
	            numSecondError.setText(isSecondInput ? (isSecondNumeric ? "" : "INVALID") : "REQUIRED");

	            if (isFirstNumeric && isSecondNumeric) {
					double numFirst = Double.parseDouble(numFirstString);
					double numSecond = Double.parseDouble(numSecondString);
					double difference = numFirst - numSecond;
					resultText.setText(formatResult(numFirst, numSecond,difference, false));
			    }
			}
		});
		differenceButton.setBounds(82, 190, 117, 29);
		contentPane.add(differenceButton);
		
		JLabel titleLable = new JLabel("BASIC MATH FORM");
		titleLable.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		titleLable.setForeground(new Color(68, 137, 255));
		titleLable.setBounds(137, 5, 190, 47);
		contentPane.add(titleLable);
		
		resultText = new JTextField();
		resultText.setBounds(82, 230, 277, 26);
		contentPane.add(resultText);
		resultText.setColumns(10);
	}
}
