package CS112Final;

/***********************************************************************
* COURSE:           CS 112 Intro to CSII 
* HOMEWORK:         CS 112 Final Project
* PROJECT:          Swing Graphing Calculator
* FILE NAME:        InvalidEquationCharacterException.java
* PROGRAMMER:       Carlos Sanchez
* LAST MODIFIED:    12/08/2017             
************************************************************************
*               --- Class Description ---
* Used for exception handling in MathFunction class.
**********************************************************************/
public class InvalidEquationCharacterException extends Exception
{
    public InvalidEquationCharacterException()
    {
        super("Invalid Character/s detected");
    }

    public InvalidEquationCharacterException(String message)
    {
        super(message);
    }

    public InvalidEquationCharacterException(char c)
    {
        super("Invalid Character \"" + c + "\" Detected");
    }

}
