//
//javac Calculator.java

//java CalculatorProduct&
//java MainFrame


//javac MainFrame.java
//java Calculator&


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.NumberFormatException;
import java.text.DecimalFormat;

public class Calculator extends JFrame {
	private JTextField totalField;
	//private JTextField percentField;
	private JTextField newTotalField;

	private JButton calcBtn;
	private JButton quitBtn;
    private boolean hasOper;


    

	public static void main(String[] args) { new Calculator(); }

	public Calculator() {
        hasOper = false;
        this.setSize(600,600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Calculator");

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
		totalField.setPreferredSize(new Dimension(300,30));
		panel.add(totalField, gridConstraints);
		
		// Tip Percent input
		// percentField = new JTextField("");
		// gridConstraints.gridy = 2;
		// percentField.setPreferredSize(new Dimension(100,30));
		// panel.add(percentField, gridConstraints);

		// New total label
		newTotalField = new JFormattedTextField();
		newTotalField.setEditable(false);
		newTotalField.setForeground(Color.red);
		gridConstraints.gridy = 3;
		newTotalField.setPreferredSize(new Dimension(300,30));
		panel.add(newTotalField, gridConstraints);

		// Calculate button
		gridConstraints.anchor = GridBagConstraints.CENTER;
		calcBtn = new JButton("Calculate");
		gridConstraints.gridy = 4;
		calcBtn.setPreferredSize(new Dimension(300,30));
		calcBtn.addActionListener(lForButton);
		panel.add(calcBtn, gridConstraints);

		// Quit button
		quitBtn = new JButton("Quit");
		quitBtn.addActionListener(lForButton);
		gridConstraints.gridy = 5;
		quitBtn.setPreferredSize(new Dimension(300,30));
		panel.add(quitBtn, gridConstraints);

		// Labels
		gridConstraints.anchor = GridBagConstraints.EAST;
		gridConstraints.insets = new Insets(5,5,5,30);

		gridConstraints.gridx = 1;
		gridConstraints.gridy = 1;
		JLabel totalLabel = new JLabel("Equation: ");
		panel.add(totalLabel, gridConstraints);

		// gridConstraints.gridy = 2;
		// JLabel percentFieldLabel = new JLabel("tip (%):");
		// panel.add(percentFieldLabel, gridConstraints);

		gridConstraints.gridy = 3;
		JLabel newTotalFieldLabel = new JLabel("Derivative: ");
		panel.add(newTotalFieldLabel, gridConstraints);

		this.add(panel);
		this.setVisible(true);

	}

    private void doCalculate(){
    String eq = totalField.getText();
    String p = "";

        
        
    String answer = "";
    int startIndex = 0;
    int endIndex = findNextOperator(eq, eq);
    for(int i = 0; i < eq.length() - 1; i ++){
        String sub = eq.substring(i, i + 1);
        if(sub.equals("x")){
            //finds symbol
            String symbol = "";
            if(hasOper){
                symbol = eq.substring(endIndex, endIndex + 1);
            }
            //obtains the current coeffcient and power
            int coeffcient = Integer.parseInt(eq.substring(startIndex, i));
            int power = Integer.parseInt(eq.substring(i + 2, endIndex));
            //creates derrative for that singular term
            answer += (coeffcient * power) + "x^" + (power - 1) + symbol;
            //Creates the new indexes for the next term
            startIndex = endIndex + 1;
            if(hasOper){
            endIndex = findNextOperator(eq.substring(endIndex+1), eq);
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

    //String out = df.format(t+t-t*(100-p)*Math.pow(10,-2));
	newTotalField.setText(answer);
    //return answer;
    }


    public int findNextOperator(String equation, String eq){
    int operatorIndex = 100000;
      
    if(equation.indexOf("+") > 0)
    {
        operatorIndex = equation.indexOf("+");
        hasOper = true;
    }
      
    if(equation.indexOf("-") > 0 && equation.indexOf("-") < operatorIndex){
        operatorIndex = equation.indexOf("-");
        hasOper = true;
    }

    if(operatorIndex == 100000){
        operatorIndex = eq.length();
        hasOper = false;
    }
    return operatorIndex;
  }

    

	private class ListenForButton implements ActionListener { 
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == quitBtn) System.exit(0);
			else doCalculate();
      //System.out.println("Test");
      
		}
	}
}
