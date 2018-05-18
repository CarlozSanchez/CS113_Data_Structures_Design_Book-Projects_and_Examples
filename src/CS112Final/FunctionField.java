package CS112Final; /***********************************************************************
* COURSE:           CS 112 Intro to CSII 
* HOMEWORK:         CS 112 Final Project
* PROJECT:          Swing Graphing Calculator
* FILE NAME:        FunctionField.java
* PROGRAMMER:       Carlos Sanchez
* LAST MODIFIED:    12/08/2017             
************************************************************************
*               --- Class Description ---
* this panels holds a tag, text field and button used for inputting a 
* Math function in text form. The button is then used to graph the given
* function on the Graph Panel.
**********************************************************************/
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import javax.swing.BorderFactory;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.FlowLayout;

public class FunctionField extends JPanel implements ActionListener
{
    private final static int WIDTH = 100;
    private final static int HEIGHT = 20;
    private static final int NUMBER_OF_CHAR = 8;
    private static final String BUTTON_LABEL = "Graph";
    
    private char variable;
    private JLabel functionLabel;
    private JTextField inputText;
    private JButton button;
    private GraphingCalculator graphingCalculator;
    
    FunctionField(GraphingCalculator calculator, char variable)
    {
        super();
        this.variable = variable;
        
       // this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
       // this.setBorder(BorderFactory.createBevelBorder(1));
       // this.setLayout(new FlowLayout());
        this.graphingCalculator = calculator;
        
        this.functionLabel = new JLabel(String.format("F(%c) =", this.variable));
        this.add(functionLabel);
        
        this.inputText = new JTextField(NUMBER_OF_CHAR);
        this.add(inputText);
        
        this.button = new JButton(BUTTON_LABEL);
        this.button.addActionListener(this);
        this.add(button);

    }
    
    // MUTATOR; sets reference for graphing calculator
    public void setGraphingCalculator(GraphingCalculator calculator)
    {
        this.graphingCalculator = calculator;
    }
    
    public void actionPerformed(ActionEvent e)
    {
        String actionCommand = e.getActionCommand();
        if(actionCommand == BUTTON_LABEL)
        {
            System.out.println("Want to graph " + inputText.getText());
            if(graphingCalculator != null)
            {
                graphingCalculator.graphFunction(inputText.getText());
            }
            else
            {
                // Debugging
                System.out.println("Reference for graphing calculator not set up");
            }
            
        }
    }
}
