package DataStructuresAbstractionDesign.chapter3.Stacks;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Class that can evaluate a postfix expression
 */
public class Evaluator
{

    /***************************** CONSTANT VARIABLES *****************************/
    private static final String OPERATORS = "+,-,*/";

    private Stack<Integer> operandStack;

/****************************** CLASS VARIABLES *******************************/

/******************************************************************************/
/******************************* CONSTRUCTORS *********************************/
/******************************************************************************/


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
/****************************************************************************/

    /**
     * CLASS METHOD: Evaluates the current operation.
     * This method pops the two operands off the operand stack and applies the
     * operator.
     *
     * @param operator A character representing the operator.
     * @return The result of applying the operator.
     * @throws java.util.EmptyStackException if pop is attempted on an empty stack
     */
    private int evaluateOperation(char operator)
    {
        int leftOperand, rightOperand;

        rightOperand = operandStack.pop();
        leftOperand = operandStack.pop();

        int result = 0;

        // Evaluate the operator.
        switch (operator)
        {
            case '+':
                result = leftOperand + rightOperand;
                break;

            case '-':
                result = leftOperand - rightOperand;
                break;

            case '/':
                result = leftOperand / rightOperand;
                break;

            case '*':
                result = leftOperand * rightOperand;

                break;

            default:
                System.out.println("Unkown operation!!!");
                break;

        }

        return result;
    }

    /**
     * Determines whether a character is an operator.
     *
     * @param character The character to be tested.
     * @return true if the character is an operator.
     */
    private boolean isOperator(char character)
    {
        return OPERATORS.indexOf(character) != -1;
    }

    /**
     * Evaluates a postfix expression.
     *
     * @param expression The expression to be evaluated.
     * @return The value of the expression.
     * @throws SyntaxErrorException if a syntax error is detected.
     */
    public int evaluate(String expression) throws SyntaxErrorException
    {
        char firstChar = 0;

        // Create an empty stack
        operandStack = new Stack<Integer>();

        // Process each token.
        String[] tokens = expression.split("\\s+");

        try
        {
            for (String nextToken : tokens)
            {
                firstChar = nextToken.charAt(0);

                // Does it start with a digit?
                if (Character.isDigit(firstChar))
                {
                    // get the interger value
                    int value = Integer.parseInt(nextToken);

                    // Push value onto opreand stack.
                    operandStack.push(value);
                }
                // Is it an operator
                else if (isOperator(firstChar))
                {
                    // Evaluate the operator.  !!!!!!!
                    int result = evaluateOperation(firstChar);

                    // !!!! DEBUG !!!!!!!!!
                    System.out.println(result);

                    operandStack.push(result);
                }
                else
                {
                    // invalid character.
                    throw new SyntaxErrorException("DDDDInvalid character " +
                        "encountered: " + firstChar);
                }
            } // end for

            // No more tokens - pop result from operand stack.
            int answer = operandStack.pop();

            //Operand stack should be empty
            if (operandStack.empty())
            {
                return answer;
            }
            else
            {
                // indicate syntax error
                throw new SyntaxErrorException(
                    "NNNNN Syntax Error: Stack should be empty");
            }

        } catch (EmptyStackException e) // Pop() was attempted on an empty stack
        {
            throw new SyntaxErrorException("RRRRInvalid character encountered: "
                + firstChar);
        }
    }


/******************************************************************************/
/****************************** STATIC METHODS ********************************/
/******************************************************************************/


/******************************************************************************/
/****************************** HELPER METHODS ********************************/
/******************************************************************************/



}
