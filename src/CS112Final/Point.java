package CS112Final;

/***********************************************************************
* COURSE:           CS 112 Intro to CSII 
* HOMEWORK:         CS 112 Final Project
* PROJECT:          Swing Graphing Calculator
* FILE NAME:        Point.java
* PROGRAMMER:       Carlos Sanchez
* LAST MODIFIED:    12/08/2017             
************************************************************************
*               --- Class Description ---
* Holds an X and Y coordinate used for representing a data point for both
* graphing Panel and MathFunction.
**********************************************************************/
public class Point<T extends Number>
{
    // Local Variables
    private T x;
    private T y;
    
    // Default Constructor
    public Point()
    {
        //Integer n = 0;
        this.x = null;
        this.y = null;
        //this.x = (T) n;
        //this.y = (T) n;
    }
    
    // Full Constructor
    public Point(T x, T y)
    {
        this.x = x;
        this.y = y;
    }
    
    // Copy Constructor
    public Point(Point other)
    {
        this.x = (T) other.x;
        this.y = (T) other.y;
    }
    
    // Sets the X vector
    public void setX(T x)
    {
        this.x = x;
    }
    
    // Sets the Y vector
    public void setY(T y)
    {
        this.y = y;
    }
    
    // Returns integer representing X vector
    public T getX()
    {
        return this.x;
    }
    
    // Returns integer representing Y vector
    public T getY()
    {
        return this.y;
    }
    
    // Equals method
    public boolean equals(Object other)
    {
        if(other == null || other.getClass() != this.getClass())
            return false;
        else
        {
            Point otherPoint = (Point)other;
            return this.x == otherPoint.getX() && this.y == otherPoint.getY();
        }

    }
    
    // toString Method 
    // Returns String represnting coordinates
    public String toString()
    {
        String temp = "";
        temp += String.format("(%.2f, %.2f)", this.x.doubleValue(), this.y.doubleValue());
        return temp;
    }   
}
