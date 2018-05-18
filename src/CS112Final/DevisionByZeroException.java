package CS112Final;

/***********************************************************************
* COURSE:           CS 112 Intro to CSII 
* HOMEWORK:         CS 112 Final Project
* PROJECT:          Swing Graphing Calculator
* FILE NAME:        DevisionByZeroException.java
* PROGRAMMER:       Carlos Sanchez
* LAST MODIFIED:    12/08/2017             
************************************************************************
*               --- Class Description ---
* Used for exception handling in MathFunction class.
**********************************************************************/
public class DevisionByZeroException extends Exception
{
    public DevisionByZeroException()
    {
        super("Devision by Zero detect, aborting operation");
    }
}
