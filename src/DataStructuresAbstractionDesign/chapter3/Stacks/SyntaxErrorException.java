package DataStructuresAbstractionDesign.chapter3.Stacks;

public class SyntaxErrorException extends Exception
{
    SyntaxErrorException()
    {
        super("Syntax Error Exception!!!");
    }

    SyntaxErrorException(String message)
    {
        super(message);
    }
}


