
//javac CalculatorProduct.java
//java CalculatorProduct

//java CalculatorProduct&
//java MainFrame


//javac MainFrame.java
//java MainFrame&
//java CalculatorProduct


//Library for graphing equations : https://mindfusion.eu/blog/the-functionseries-in-charting-for-java-swing/


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.NumberFormatException;
import java.text.DecimalFormat;

public class CalculatorProduct extends JFrame {
	private JTextField totalField;
    private JTextField totalField2;

	private JTextField newTotalField;

	private JButton calcBtn;
	private JButton quitBtn;
    private boolean hasOper;


    

	public static void main(String[] args) { 
    new CalculatorProduct(); 
  }

	public CalculatorProduct() {
        hasOper = false;
        this.setSize(800,800);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Calculator Product");

		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());

		ListenForButton lForButton = new ListenForButton();

		GridBagConstraints gridConstraints = new GridBagConstraints();

		gridConstraints.gridwidth = 1;
		gridConstraints.gridheight = 1;

		// Original total input
		gridConstraints.anchor = GridBagConstraints.WEST;

		totalField = new JTextField("");
		gridConstraints.gridx = 2;
		gridConstraints.gridy = 1;
		totalField.setPreferredSize(new Dimension(400,30));
		panel.add(totalField, gridConstraints);

        // Original total input2
		gridConstraints.anchor = GridBagConstraints.WEST;

		totalField2 = new JTextField("");
		gridConstraints.gridy = 2;
		totalField2.setPreferredSize(new Dimension(400,30));
		panel.add(totalField2, gridConstraints);
		

		// New total label
		newTotalField = new JFormattedTextField();
		newTotalField.setEditable(false);
		newTotalField.setForeground(Color.red);
		gridConstraints.gridy = 3;
		newTotalField.setPreferredSize(new Dimension(400,30));
		panel.add(newTotalField, gridConstraints);

		// Calculate button
		gridConstraints.anchor = GridBagConstraints.CENTER;
		calcBtn = new JButton("Calculate");
		gridConstraints.gridy = 4;
		calcBtn.setPreferredSize(new Dimension(400,30));
		calcBtn.addActionListener(lForButton);
		panel.add(calcBtn, gridConstraints);

		// Quit button
		quitBtn = new JButton("Quit");
		quitBtn.addActionListener(lForButton);
		gridConstraints.gridy = 5;
		quitBtn.setPreferredSize(new Dimension(400,30));
		panel.add(quitBtn, gridConstraints);

		// Labels
		gridConstraints.anchor = GridBagConstraints.EAST;
		gridConstraints.insets = new Insets(5,5,5,30);

		gridConstraints.gridx = 1;
		gridConstraints.gridy = 1;
		JLabel totalLabel = new JLabel("Equation #1: ");
		panel.add(totalLabel, gridConstraints);		


    //Equation #2
    

        gridConstraints.gridy = 2;
  		JLabel percentFieldLabel = new JLabel("Equation #2: ");
  		panel.add(percentFieldLabel, gridConstraints);

		gridConstraints.gridy = 3;
		JLabel newTotalFieldLabel = new JLabel("Derivative: ");
		panel.add(newTotalFieldLabel, gridConstraints);

		this.add(panel);
		this.setVisible(true);

	}

    public String calculate(String eq0){
    String answer = "";
    int startIndex = 0;
    int size = eq0.length();
    int endIndex = findNextOperator(eq0, size);


    for(int i = 0; i < size - 1; i ++){
        String sub = eq0.substring(i, i + 1);
        if(sub.equals("x")){
            //finds symbol
            String symbol = "";
            if(hasOper){
                symbol = eq0.substring(endIndex, endIndex + 1);
            }
            //obtains the current coeffcient and power
            int coeffcient = Integer.parseInt(eq0.substring(startIndex, i));
            int power = Integer.parseInt(eq0.substring(i + 2, endIndex));
            //creates derrative for that singular term
            answer += (coeffcient * power) + "x^" + (power - 1) + symbol;
            //Creates the new indexes for the next term
            startIndex = endIndex + 1;
            if(hasOper){
            endIndex = findNextOperator(eq0.substring(endIndex+1), size);
            if(hasOper){
            endIndex += startIndex;
            }
            }
        }
    }
    String constant = answer.substring(answer.length() -1);
    if(constant.equals("+") || constant.equals("-")){
        answer += 0;
    }
    return answer;
  }


  public int findNextOperator(String equation, int size){
    int operatorIndex = 2000000;
      
    if(equation.indexOf("+") > 0)
    {
        operatorIndex = equation.indexOf("+");
        hasOper = true;
    }
      
    if(equation.indexOf("-") > 0 && equation.indexOf("-") < operatorIndex){
        operatorIndex = equation.indexOf("-");
        hasOper = true;
    }

    if(operatorIndex == 2000000){
        operatorIndex = size;
        hasOper = false;
    }
    return operatorIndex;
  }


   public void CalculateProduct(){
    String eq0 = totalField.getText();
    String eq2 = totalField2.getText();    
    String answer = ("((" + calculate(eq0) + ") * (" + eq2 + ")) + ((" + eq0 + ") * (" +  calculate(eq2) + "))");
    newTotalField.setText(answer);
  }  

    

	private class ListenForButton implements ActionListener { 
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == quitBtn) System.exit(0);
			else CalculateProduct();
      //System.out.println("Test");
      
		}
	}
}
