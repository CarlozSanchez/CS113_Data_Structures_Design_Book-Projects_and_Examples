package CS112Final; /***********************************************************************
* COURSE:           CS 112 Intro to CSII 
* HOMEWORK:         CS 112 Final Project
* PROJECT:          Swing Graphing Calculator
* FILE NAME:        FinalDriver.java
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
* GraphingCalculator.java - Hold main JFrame window for calculator.
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
import java.util.Scanner;
import java.util.ArrayList;

public class FinalDriver
{
   static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args)
    {

        // Instantiate our graphing calculator
        GraphingCalculator graphingCalculator = new GraphingCalculator();
        graphingCalculator.setVisible(true);
        
        // testMathFunction();
        // testEquals();
        // testDataPoints();     
    }
    
    // For testing Math Functionpurposes
    private static void testMathFunction()
    {

        String temp = "";

        System.out.print("Enter Equation F(x): ");

        temp = keyboard.nextLine();
        
        MathFunction equation = null;

        try
        {
            equation = new MathFunction(temp);
            System.out.println("Calculated " + equation.calculate(11));
            System.out.println(equation);
        }
        catch (InvalidEquationCharacterException e)
        {
            System.out.println(e.getMessage());
        }
        catch(DevisionByZeroException e)
        {
            System.out.println(e.getMessage());
        }
//        catch(NumberFormatException e)
//        {
//           // System.out.println("GRRG");
//            System.out.println("unable to parse " + e.getMessage());
//        }
        

       // System.out.println(equation.getEquation());
    }
    
    // Method: used for testing close method and equals
    private static void testEquals()
    {
        MathFunction fA, fB;
        fA = fB = null;
        
        try
        {
             fA= new MathFunction("6X^3 + 4");
             fB = new MathFunction(fA);
             //fA.setEquation("6x + 4");
        }
        catch(Exception e)
        {
            System.out.println("oops");
        }

        System.out.println("Fa euals fB?" + fA.equals(fB));
    }
    
    // Method: used for testing dataPoints Method
    private static void testDataPoints()
    {
        MathFunction funcA = null;
        try
        {
            funcA = new MathFunction("3x^2 - 2x + 25");
        }
        catch(InvalidEquationCharacterException e)
        {
            System.out.println(e.getMessage());
        }
//        catch(DevisionByZeroException e)
//        {
//            System.out.println(e.getMessage());
//        }
        catch (NumberFormatException e)
        {
            // System.out.println("GRRG");
            System.out.println("unable to parse " + e.getMessage());
        }
        
        ArrayList<Point> list = funcA.dataPoints(-480, 480);
        
        System.out.println("---- The list of values --- ");
        for(Point point: list)
        {
            System.out.println(point);
        }
    }

}
