package DataStructuresAbstractionDesign.chapter3.Stacks;

import javax.swing.*;
import java.util.Stack;

public class StacksDriver
{
    public static void main(String[] args)
    {
        //parenthesesCheckerDemo();
        //stackPractice();
        //evaluatorTester();
       // testInfixToPostFix();
       // testInfixToPostfixV2();
        //testSplit();
        testMethod();
    }

    private static void parenthesesCheckerDemo()
    {
        String expression = JOptionPane.showInputDialog(
            "Enter an expression containing parentheses");

        if (ParenthesesChecker.isBalanced((expression)))
        {
            JOptionPane.showMessageDialog(null, expression
                + " is balanced");
        }
        else
        {
            JOptionPane.showMessageDialog(null, expression
                + "is not balanced");
        }
        System.exit(0);
    }

    private static void stackPractice()
    {
        Stack<Integer> stack = new Stack<Integer>();

        stack.push(5);
        stack.push(4);
        stack.push(3);

        while(!stack.empty())
        {
            System.out.println("pop: " + stack.pop());
        }
    }

    private static void evaluatorTester()
    {
        String expression;
        int result = 0;

        Evaluator evaluator = new Evaluator();

        expression = "1 100 22 10 2  + - / *";

        try
        {
            result =  evaluator.evaluate(expression);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        System.out.println("Answer: " + result);

    }

    private int overloadTest()
    {
        return 0;
    }

//    private double overloadTest()
//    {
//        return 1.2;
//    }

    private static void testInfixToPostFix()
    {
        String theExpression, postFixExpression;
        InfixToPostfixParentheses converter = new InfixToPostfixParentheses();

        theExpression = " (5 - 4) * 5 ";

        postFixExpression = "";

        System.out.println("infix: " + theExpression);

        try
        {
            postFixExpression = converter.convertToPostfix(theExpression);
            System.out.println("postfix: " + postFixExpression);
        }
        catch(SyntaxErrorException e)
        {
            System.out.println(e.getMessage());
            System.out.println("Unable to convert");
        }

        int result = 0;
        Evaluator evaluator = new Evaluator();


        try
        {
            result =  evaluator.evaluate(postFixExpression);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        System.out.println("Answer: " + result);

    }

    private static void testInfixToPostfixV2()
    {
          final String[] VALID_EXPRESSIONS
            = {"", "0", "9", "5 + 6", "10 - 5", "10 / 2", "10 * 20", "2 ^ 3"};

          InfixToPostfixParentheses converter = new InfixToPostfixParentheses();
        String postFixExpression;

        System.out.println("Infix   :   Postfix");

        for (int i = 0; i < VALID_EXPRESSIONS.length; i++)
        {
            try
            {
                postFixExpression = converter.convertToPostfix
                    (VALID_EXPRESSIONS[i]);

                System.out.println(VALID_EXPRESSIONS[i] + " : " + postFixExpression);
            }
            catch(SyntaxErrorException e)
            {

                System.out.println( "testWithValidExpression Failed "
                    + e.getMessage());
            }

        }
    }

    private static void testMethod()
    {
        InfixToPostfixParentheses converter = new InfixToPostfixParentheses();
        String result = "";
        try
        {
             result = converter.convertToPostfix("2 + 3");
        }
        catch (SyntaxErrorException e)
        {
            System.out.println("Error");
        }

        System.out.println(result);

    }

    private static void testSplit()
    {
      //  String

    }


}
