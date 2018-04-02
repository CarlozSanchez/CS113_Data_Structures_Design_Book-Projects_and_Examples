package DataStructuresAbstractionDesign.chapter3.Stacks;

// IMPORTS
import java.util.Stack;
import java.util.EmptyStackException;

public class ParenthesesChecker
{
    /**************************** CONSTANTS CONSTANTS *****************************/
    private static final String OPEN = "([{";
    private static final String CLOSE = ")]}";


/******************************************************************************/
/****************************** CLASS METHODS *********************************/
/******************************************************************************/

    /**
     * Test the input string to see that it contains balanced
     * parentheses. This method tests and input string to see
     * that each type of parenthesis is balanced. '(' is match
     * with ')', '[' is matched with ']' and '{' is matched
     * with '}'.
     *
     * @param expression A String containing the expression to
     *                   be examined
     * @return true if all parentheses match
     */
    public static boolean isBalanced(String expression)
    {
        // Create an empty stack
        Stack<Character> s = new Stack<Character>();
        boolean balanced = true;

        try
        {
            int index = 0;
            while (balanced && index < expression.length())
            {
                char nextCh = expression.charAt(index);
                if (isOpen(nextCh))
                {
                    s.push(nextCh);
                }
                else if (isClose(nextCh))
                {
                    char topCh = s.pop();
                    balanced = OPEN.indexOf(topCh)
                            == CLOSE.indexOf(nextCh);
                }
                index++;
            }
        } catch (EmptyStackException e)
        {
            balanced = false;
        }

        return (balanced && s.empty());
    }

    /** Method to determine whether a character is one of the
     * opening parentheses.
     * @param ch Character to be test
     * @return true if ch is one of the opening parentheses.
     */
    private static boolean isOpen(char ch)
    {
        return OPEN.indexOf(ch) > -1;
    }

    /**
     * Method to determine whether a character is one of the
     * closing parentheses.
     * @param ch Character to be test.
     * @return true if ch is one of the closing parenthesis.
     */
    private static boolean isClose(char ch)
    {
        return CLOSE.indexOf(ch) > -1;
    }


}
