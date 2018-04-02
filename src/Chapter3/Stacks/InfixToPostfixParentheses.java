package Chapter3.Stacks;


import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

public class InfixToPostfixParentheses
{

    /***************************** CONSTANT VARIABLES *****************************/
    private static final String OPERATORS = "+-*/^()";
    private static final int[] PRECEDENCE = {1, 1, 2, 2, 3, -1, -1};

    /****************************** CLASS VARIABLES *******************************/
    private Stack<Character> operatorStack;
    private StringBuilder postfix;

/******************************************************************************/
/******************************* CONSTRUCTORS *********************************/
/******************************************************************************/

    /**
     * DEFAULT CONSTRUCTOR: sets operatorStack and postfix to new instanced
     * of Stack and StringBuilder.
     */
    public InfixToPostfixParentheses()
    {
        this.operatorStack = new Stack<Character>();
        this.postfix = new StringBuilder();
    }



/******************************************************************************/
/********************************* MUTATORS ***********************************/
/******************************************************************************/


/******************************************************************************/
/********************************* ACCESSORS **********************************/
    /******************************************************************************/


    public StringBuilder getStringBuilder()
    {
        return new StringBuilder(this.postfix);
    }


/******************************************************************************/
/********************************* OVERRIDES **********************************/
    /******************************************************************************/

    @Override
    public boolean equals(Object other)
    {
        if(other == null || this.getClass() != other.getClass())
        {
            return false;
        }
        else
        {
            InfixToPostfixParentheses otherInfix
                = (InfixToPostfixParentheses)other;

//            return this.operatorStack.equals(otherInfix.getOperatorStack())
//                && this.postfix.equals(otherInfix.getStringBuilder());

            return this.postfix.equals(otherInfix.getStringBuilder());

        }
    }

    @Override
    public String toString()
    {
        return this.operatorStack.toString()
            + this.postfix.toString();
    }

/******************************************************************************/
/********************************* INTERFACE **********************************/
/******************************************************************************/


/******************************************************************************/
/****************************** CLASS METHODS *********************************/
    /******************************************************************************/
    public String convertToPostfix(String infix) throws SyntaxErrorException
    {
        Scanner scan;
        String nextToken;

        // Initialize Class Variables
        operatorStack = new Stack<Character>();
        postfix = new StringBuilder();

        try
        {
            // Process each token in the infix string
            scan = new Scanner(infix);
            while ((nextToken = scan.findInLine("[\\p{L}\\p{N}]+|[-+/\\*^()]")) != null)
            {
                char firstChar = nextToken.charAt(0);

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
                        ("ITP01: Unexpected Character Encountered: " + firstChar);
                }
            } // End loop

            // Pop any remaining operator and append t hem to postfix
            while (!operatorStack.empty())
            {
                char operator = operatorStack.pop();
                // Any '(' on the stack is not matched.
                if (operator == '(')
                {
                    throw new SyntaxErrorException
                        ("ITP02: Unmatched opening parenthesis");
                }
                postfix.append(operator);
                postfix.append(' ');
            }

            // assert: Stack is empty, return result
            return postfix.toString();
        }
        catch (EmptyStackException e)
        {
            throw new SyntaxErrorException("ITP03: Syntax error: The stack is empty");
        }
    }

    private void processOperator(char operator)
    {
        char topOperator;

        if(operatorStack.empty() || operator == '(')
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
                while(!operatorStack.empty()
                    && precedence(topOperator) <= precedence(topOperator))
                {
                    operatorStack.pop();
                    if(topOperator == '(')
                    {
                        // matching '(' popped - exit loop
                        break;
                    }
                    postfix.append(topOperator);

                    postfix.append(' ');

                    if(!operatorStack.empty())
                    {
                        // Reset topOp.
                        topOperator = operatorStack.peek();
                    }
                }

                // assert: Operator stack is empty or current operator
                // precedence > top of stack oeperator precedence.
                if(operator != ')')
                    operatorStack.push(operator);
            }
        }
    }


/******************************************************************************/
/****************************** STATIC METHODS ********************************/
/******************************************************************************/


/******************************************************************************/
/****************************** HELPER METHODS ********************************/
/******************************************************************************/

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
