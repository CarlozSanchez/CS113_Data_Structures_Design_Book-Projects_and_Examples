package Chapter3.Stacks;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Translates an infix expression to a postFix expression.
 */
public class InfixToPostFix
{

    /***************************** CONSTANT VARIABLES *****************************/
    private static final String OPERATORS = "+-*/()";
    private static final int[] PRECEDENCE = {1, 1, 2, 2};

    /****************************** CLASS VARIABLES *******************************/
    private Stack<Character> operatorStack;
    private StringBuilder postfix;

/******************************************************************************/
/******************************* CONSTRUCTORS *********************************/
/******************************************************************************/

    /**
     * DEFAULT CONTSTRUCTOR:
     */
    public InfixToPostFix()
    {
        setAll(new Stack<Character>(), new StringBuilder());
    }

    private void setAll(Stack<Character> stack, StringBuilder strBuilder)
    {
        this.operatorStack = stack;
        this.postfix = strBuilder;
    }

/******************************************************************************/
/********************************* MUTATORS ***********************************/
/******************************************************************************/


/******************************************************************************/
/********************************* ACCESSORS **********************************/
/******************************************************************************/


/******************************************************************************/
/********************************* OVERRIDES **********************************/
/******************************************************************************/


/******************************************************************************/
/********************************* INTERFACE **********************************/
/******************************************************************************/


/******************************************************************************/
/****************************** CLASS METHODS *********************************/
    /******************************************************************************/
    public String convert(String infix) throws SyntaxErrorException
    {
        // Declare
        String[] tokens;
        char firstChar, operator;


        // Initialize Class Variables
        operatorStack = new Stack<Character>();
        postfix = new StringBuilder();

        // Initialize local Variables
        tokens = infix.split("\\s+");

        try
        {
            // Process each token in the infix String
            for (String nextToken : tokens)
            {
                firstChar = nextToken.charAt(0);

                // Is it an operand?
                if (Character.isJavaIdentifierStart(firstChar)
                    || Character.isDigit(firstChar))
                {
                    postfix.append(nextToken);
                    postfix.append(' ');
                }
                // Is it an operator?
                else if (isOperator(firstChar))
                {
                    processOperator(firstChar);
                }
                else
                {
                    throw new SyntaxErrorException
                        ("Unexpected Character Encountered: " + firstChar);
                }
            } // end loop

            // Pop any remaining operators and append them to postFix
            while (!operatorStack.empty())
            {
                operator = operatorStack.pop();
                postfix.append(operator);
                postfix.append(' ');
            }
            // assert: Stack is empty, return result.
            return postfix.toString();
        } catch (EmptyStackException e)
        {
            throw new SyntaxErrorException
                ("Syntax Error: The stack is empty");
        }
    }


/******************************************************************************/
/****************************** STATIC METHODS ********************************/
/******************************************************************************/


/******************************************************************************/
/****************************** HELPER METHODS ********************************/
/******************************************************************************/

    /**
     * Method to process operators.
     *
     * @param operator the operator.
     * @throws EmptyStackException
     */
    private void processOperator(char operator)
    {
        char topOperator;
        if (operatorStack.empty())
        {
            operatorStack.push(operator);
        }
        else
        {
            // Peek the operator stack and let the topOperator be top operator.
            topOperator = operatorStack.peek();
            if (precedence(operator) > precedence(topOperator))
            {
                operatorStack.push(operator);
            }
            else
            {
                //Pop all stacked operators with equal
                // or higher precedence than operator
                while (!operatorStack.empty() && precedence(operator)
                    <= precedence(topOperator))
                {
                    operatorStack.pop();
                    postfix.append(topOperator);
                    postfix.append(' ');
                    if (!operatorStack.empty())
                    {
                        // Reset topOperator;
                        topOperator = operatorStack.peek();
                    }
                }
                // assert: Operator stack is empty or current operator
                // precedence > top of stack oeperator precedence.
                operatorStack.push(operator);
            }
        }
    }

    /**
     * Determine whether a character is an operator.
     *
     * @param character The character to be tested
     * @return true if character is an operator
     */
    private boolean isOperator(char character)
    {
        return OPERATORS.indexOf(character) != -1;
    }

    /**
     * Determine the precedence of an operator.
     *
     * @param operator the operator precedence to check.
     * @return the precedence.
     */
    private int precedence(char operator)
    {
        return PRECEDENCE[OPERATORS.indexOf(operator)];
    }


}
