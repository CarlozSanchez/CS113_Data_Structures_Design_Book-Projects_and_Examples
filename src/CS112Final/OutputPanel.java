package CS112Final; /***********************************************************************
* COURSE:           CS 112 Intro to CSII 
* HOMEWORK:         CS 112 Final Project
* PROJECT:          Swing Graphing Calculator
* FILE NAME:        OutputPanel.java
* PROGRAMMER:       Carlos Sanchez
* LAST MODIFIED:    12/08/2017             
************************************************************************
*               --- Class Description ---
* This panel holds a text box for updating user with errors and Function output.
**********************************************************************/
import java.awt.Color;
import java.awt.Dimension;


import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class OutputPanel extends JPanel
{
    private static final int WIDTH = 210;
    private static final int HEIGHT = 400;

    
    private JTextArea textArea;
    private JScrollPane scrollPane;
    
    public OutputPanel()
    {        
        this.setBorder(BorderFactory.createBevelBorder(1));
    
        textArea = new JTextArea();
        
        scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        //scrollPane.add(textArea);
        
        this.add(scrollPane);
    }
    
    // MUTATOR:
    public void setText(String text)
    {
        this.textArea.setText(text);
    }
}
