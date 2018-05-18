package CS112Final; /***********************************************************************
* COURSE:           CS 112 Intro to CSII 
* HOMEWORK:         CS 112 Final Project
* PROJECT:          Swing Graphing Calculator
* FILE NAME:        IOpanel.java
* PROGRAMMER:       Carlos Sanchez
* LAST MODIFIED:    12/08/2017             
************************************************************************
*               --- Class Description ---
* this panel holds a functionField used for inputing a function in text
* form as well as and output field for displaying the X and Y coordinates 
* of given function.
************************************************************************
*               --- Required Classes ---
* FunctionField.java - Holds a function input field and Graph Button
**********************************************************************/
import javax.swing.JPanel;
import java.awt.Dimension;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

import javax.swing.BorderFactory;

public class IOpanel extends JPanel
{
    private static final int WIDTH = 240;
    private static final int HEIGHT = 480;
    //private static final String TITLE = "Function Field";
    
    private FunctionField functionField;
    private OutputPanel outputPanel;

    IOpanel(GraphingCalculator calculator)
    {
        // Set up
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBorder(BorderFactory.createBevelBorder(1));
        this.setLayout(new BorderLayout());
        
        this.functionField = new FunctionField(calculator,'x');
        this.add(functionField, BorderLayout.NORTH);
        
        this.outputPanel = new OutputPanel();
        this.add(outputPanel, BorderLayout.CENTER);
    }
    
    // ACCESSOR: 
    public FunctionField getFunctionField()
    {
        return this.functionField;
    }
    
    public OutputPanel getOutputPanel()
    {
        return this.outputPanel;
    }
}
