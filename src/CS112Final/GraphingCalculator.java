package CS112Final; /***********************************************************************
* COURSE:           CS 112 Intro to CSII 
* HOMEWORK:         CS 112 Final Project
* PROJECT:          Swing Graphing Calculator
* FILE NAME:        GraphingCalculator.java
* PROGRAMMER:       Carlos Sanchez
* LAST MODIFIED:    12/08/2017             
************************************************************************
*               --- Class Description ---
* this program demonstrates a simple graphing calculator where user inputs
* a function as a string, the program then parses this string into a function
* to be used to plot points within a given range.  The points are then 
* drawn on the graph panels and a list of X,Y coordinates are displayed to 
* the user.
************************************************************************
*               --- Required Classes ---
* GraphPanel.java - Holds the canvas where Function Graph will be drawn.
* IOpanel.java - holds input/output text field for inputting function 
* and for displaying plot points X/Y coordinates.
* FunctionField.java - Holds a function input field
* MathFunction.java - Holds a validated math function which calculates 
*   the plot points used to draw the function on graph.
* Point.java - Holds X and Y coordinates for a single plot point.
* InvalidEquationCharacterException.java - used for exception handling
*   in Math Function.
**********************************************************************/
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.ArrayList;

public class GraphingCalculator extends JFrame
{
    public static final String MESSAGE = ("Enter Function in the form of F(x):\n"
            + "For exampled 5x^2 + 35x - 100");
    private static final int WIDTH = 720;
    private static final int HEIGHT = 480;

    private GraphPanel graph;
    private IOpanel io;
    
    public GraphingCalculator()
    {
        // intial setup
        super("Graphing Calculator");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Create Graph and IO panel
        graph = new GraphPanel();
        io = new IOpanel(this);
        io.getOutputPanel().setText(MESSAGE);
     
        // Add Graph and IO panel
        add(graph, BorderLayout.WEST);
        add(io, BorderLayout.EAST);  
    }
    
    public  void graphFunction(String function)
    {
        System.out.println("!!!!Wanna to graph " + function);
        
        MathFunction mathFunction = null;
        ArrayList<Point> pointList = null;
        
        try
        {
            mathFunction = new MathFunction(function);
            pointList = new ArrayList<>(mathFunction.dataPoints(-480, 480));        
            graph.drawFunction(pointList);
            
            String header = String.format("%5s%15s%5s\n", "X","|", "Y");
            this.io.getOutputPanel().setText(header + listToString(pointList));
        }
       
        catch(InvalidEquationCharacterException e)
        {
            System.out.println(e.getMessage());
            this.io.getOutputPanel().setText(e.getMessage() + "\n" +  MESSAGE);
            
        }
        catch(StringIndexOutOfBoundsException e)
        {
            this.io.getOutputPanel().setText("Error while processing equation\n"
                    + "Try a different function\n " + MESSAGE);
        }
        catch(Exception e)
        {
            this.io.getOutputPanel().setText("Unexpect Error Occured, Try again!");
        }
        
    }
    
    private String listToString(ArrayList<Point> list)
    {
        String temp = "";
        
        for(Point point: list)
        {
           // temp += point.getX().doubleValue() + point.getY().doubleValue() + "\n";
          //  temp += "" + point + "\n";
            temp += String.format("%20s\n", point);
        }
        
        return temp;
    }
}
