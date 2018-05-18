package CS112Final; /***********************************************************************
* COURSE:           CS 112 Intro to CSII 
* HOMEWORK:         CS 112 Final Project
* PROJECT:          Swing Graphing Calculator
* FILE NAME:        GraphPanel.java
* PROGRAMMER:       Carlos Sanchez
* LAST MODIFIED:    12/08/2017             
************************************************************************
*               --- Class Description ---
* This panel holds a Graph representing X and Y Range. A default horizontal
* and vertical line are drawn representing X and Y Zero range. A list of plot
* points representing a function output is then drawn to the graph for display.
************************************************************************
*               --- Required Classes ---
* Point.java - Holds X and Y coordinates for a single plot point.
**********************************************************************/
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;

public class GraphPanel extends JPanel
{
    private static final int BORDER_SIZE = 5;
    private static final int WIDTH = 480;
    private static final int HEIGHT = 480;
    public String test;

    public GraphPanel()
    {
        // Intial setup
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, BORDER_SIZE));
    }

    @Override
    public void paintComponent(Graphics canvas)
    {
        super.paintComponent(canvas);
        drawGraph(canvas);
    }

    // METHOD: Draws an X and Y lines with notches for our graph
    private void drawGraph(Graphics canvas)
    {
        // draw horizontal line
        canvas.drawLine(0, yCenter(), WIDTH, yCenter());
        drawHorizontalNotches(canvas, 6, 10);

        // draw vertical line
        canvas.drawLine(xCenter(), 0, xCenter(), HEIGHT);
        drawVerticalNotches(canvas, 6, 10);
    }

    // METHOD: Draws horizontal notches on canvas graph
    private void drawHorizontalNotches(Graphics canvas, int notchSize, int notchInterval)
    {
        int hNotchCount = WIDTH / notchInterval;
        int xStart, yStart, xFinish, yFinish;       
        for (int i = 0; i < hNotchCount; i++)
        {

            xStart = i * notchInterval;
            yStart = yCenter() - notchSize / 2;

            xFinish = i * notchInterval;
            yFinish = yCenter() + notchSize / 2;

            canvas.drawLine(xStart, yStart, xFinish, yFinish);
        }
    }

    // METHOD: draws vertical notches on canvas graph
    private void drawVerticalNotches(Graphics canvas, int notchSize, int notchInterval)
    {
        int yNotchCount = HEIGHT / notchInterval;
        
        int xStart, yStart, xFinish, yFinish;

        for (int i = 0; i < yNotchCount; i++)
        {
            xStart = xCenter() - notchSize / 2;
            yStart = i * notchInterval;

            xFinish = xCenter() + notchSize / 2;
            yFinish = i * notchInterval;

            canvas.drawLine(xStart, yStart, xFinish, yFinish);
        }
    }
    
    public void drawFunction(ArrayList<Point> points)
    {
        this.getGraphics().clearRect(0 + BORDER_SIZE,0 + BORDER_SIZE, 
                WIDTH - BORDER_SIZE *2, HEIGHT - BORDER_SIZE * 10);
        
        drawFunction(super.getGraphics(), points);
    }

    // METHOD: draws function on screen based on given plot points
    // PRECONDITION:  list is not empty, and list is ordered along range
    private void drawFunction(Graphics canvas, ArrayList<Point> points)
    {
             
        this.drawGraph(canvas);
        
        int xStart, yStart, xFinish, yFinish;
        
        for (int i = 0; i < points.size() - 1; i++)
        {
            xStart = xCenter() + Math.round(points.get(i).getX().floatValue());
            yStart = yCenter() - Math.round(points.get(i).getY().floatValue());
            xFinish = xCenter() + Math.round(points.get(i+1).getX().floatValue());
            yFinish = yCenter() - Math.round(points.get(i+1).getY().floatValue());
            
            canvas.drawLine(xStart, yStart, xFinish, yFinish);
        }
    }
    
    // Calculates the horizontal center
    private int xCenter()
    {
        return WIDTH/2;
    }
    
    // Calculates the veritcal center
    private int yCenter()
    {
        return HEIGHT/2;
    }
    

}
