package CS112Final; /***********************************************************************
* COURSE:           CS 112 Intro to CSII 
* HOMEWORK:         CS 112 Final Project
* PROJECT:          Swing Graphing Calculator
* FILE NAME:        MathFunction.java
* PROGRAMMER:       Carlos Sanchez
* LAST MODIFIED:    12/11/2017             
************************************************************************
*               --- Class Description ---
* Used to hold and parse a Math function in String form. Also generates
* a list of plot points based on validated Function.
************************************************************************
*               --- Required Classes ---
* Point.java - Holds X and Y coordinates for a single plot point.
* InvalidEquationCharacterException.java - used for exception handling
*   in Math Function.
*************************************************************************/
import java.util.ArrayList;

public class MathFunction 
{
    // constants
    private final static String NUMBERS = "0123456789";
    private final static String VALID_CHARACTERS = " .xX^*/+-0123456789";
    private final static char DECIMAL_MARKER = '.';
    private final static char EXPONENT_OPERATOR = '^';    
    private final static char MULTIPLY_OPERATOR = '*';
    private final static char DEVISION_OPERATOR = '/';
    private final static char ADDITION_OPERATOR = '+';
    private final static char SUBTRACTION_OPERATOR = '-';
    private final static char EXPONENT_MARKER = 'E';
    private final static char VARIABLE = 'x';

    // class variable
    private String equation;

    /************* CONSTRUCTOR **************/

    // CONSTRUCTOR: default constructor
    public MathFunction()
    {
        this.equation = "";
    }

    // CONSTRUCTOR: full constructor
    public MathFunction(String equation) 
            throws InvalidEquationCharacterException
    {
        this.setEquation(equation);
    }

    // CONSTRUCTOR: copy constructor
    public MathFunction(MathFunction original) 
            throws InvalidEquationCharacterException
    {
        this.setEquation(original.getEquation());
    }

    /************** MUTATORS ************/
    public void setEquation(String equation) 
            throws InvalidEquationCharacterException
    {
        if (MathFunction.validateFunction(equation))
        {
            this.equation = equation;
        }
        else
        {
            throw new InvalidEquationCharacterException();
        }
    }

    /************** ACCESSORS ***********/
    public String getEquation()
    {
        return this.equation;
    }

    /************* OVERRIDE ******************/

    @Override
    // METHOD: compares given object to this one for equality
    // RETURN: a boolean representing equality
    public boolean equals(Object other)
    {
        if (other == null || other.getClass() != this.getClass())
        {
            return false;
        }
        else
        {
            MathFunction otherFunction = (MathFunction) other;
            return otherFunction.getEquation().equals(this.equation);
        }
    }

    @Override
    // METHOD: returns the equation in this class in string form
    // RETURN: a string representing math function
    public String toString()
    {
        return this.equation;
    }

    /************** CLASS METHODS ******************/

    // METHOD: calculates the class equation with given x values
    public <T extends Number> double  calculate(T x) throws DevisionByZeroException
    {
        return calculate(this.equation, x);
    }

    /************** STATIC METHODS ******************/

    // METHOD: executes the needed process for calculating the equation
    // formats string, replaces X with variable, then parses equation
    // RETURN: a double value representing the equation result
    public static <T extends Number> double  calculate(String equation, T value)
        throws DevisionByZeroException
    {
        // !!!!!! might need to change this to something else !!!!!!
        if(equation.isEmpty())
        {
            return 0;
        }
        // add any needed operators to equation
        equation = formatEquation(equation);
        System.out.println(equation);

        // replaced variable with given number
        equation = replaceX(equation, value);
        System.out.println(equation);

        return parseEquation(equation);
    }

    // METHOD: formats the equation by adding proper operators where needed
    // PRECONDITION: given string is not empty or null
    // RETURN: returns a string with needed operators to properly parse
    // equation string
    public static String formatEquation(String equation)
    {
        String temp = "";

        // remove empty spaces
        equation = equation.replaceAll(" ", "");

        // add multiply operator if variable detected after number
        for (int i = 0; i < equation.length() - 1; i++)
        {
            temp += equation.charAt(i);

            // if a number is followed by Variable x
            if (canAddMultiplyOperator(equation, i))
            {
                // add multiply operator in between
                temp += MULTIPLY_OPERATOR;
            }
        }

        // Add the last character in equation to temp
        temp += equation.charAt(equation.length() - 1);
        
        temp = replaceDoubleSubtractionOperator(temp);
        temp = replaceDoubleAdditionOperator(temp);

        return temp;
    }
    
    // METHOD: replaces any variable 'x' found in equation string
    // with given value
    // RETURN: a string which now only contains number values and operators
    public static  <T extends Number> String replaceX(String equation, T variable)
    {
        String temp = "";

        for (int i = 0; i < equation.length(); i++)
        {
            if (Character.toLowerCase(equation.charAt(i)) == VARIABLE)
            {
                temp += variable;
            }
            else
            {
                temp += equation.charAt(i);
            }
        }
        return temp;
    }

    // METHOD: recursive method for calculating the result of equation.
    // PRECONDITION: blank spaces removed, X variable replaced with a value, 
    //               multiplication operator has been added in correct spot
    // RETURN: a double representing the final results of calculate equation
    public static<T extends Number> double parseEquation(String equation)
        throws DevisionByZeroException
    {
        T a,b,c;
        String temp = "";
        
        // Clean up any double operators "--" or "++"
        equation = replaceDoubleSubtractionOperator(equation);
        equation = replaceDoubleAdditionOperator(equation);
        
        // Clean up single "+" operator 
        //  exampled: + 45 - 4 changed to 45 - 4
        equation = removeAdditionOperatorIfFirst(equation);
             
        // if no operators are found and only a number remains
        // then base case reached, return the result
        //equation = formatEquation(equation); 
        if(numberRemains(equation))
        {        
            return parseValue(equation).doubleValue();
        }
              
        // debug
        System.out.println("Current Status: " + equation);
        
        // iterate through equation to checking for Exponent ^
        for(int i = 0; i < equation.length(); i++)
        {
            // if exponent operator found
            if(equation.charAt(i) == EXPONENT_OPERATOR)
            {
                // pull value from left side
                a = parseLeftSideValue(equation, i);

                // pull value from right side
                b = parseRightSideValue(equation, i);
              
                // raise first value to power of second value
                c = (T)(Double)(Math.pow(a.doubleValue(), b.doubleValue()));
                
                // debug
                System.out.println("\t" + a + "^" + b + " = " + c);           
                
                // replace  (left value, operator and right value) in equation 
                // with the result of powerFunction
                temp = "" + a + EXPONENT_OPERATOR + b;
                equation = equation.replace(temp, "" + c);
                            
                // Recursive call
                return parseEquation(equation);                 
            }
        }
          
        // iterate through equation checking for Multiply *
        for(int i = 0; i < equation.length(); i++)
        {
        // if multiplication operator found
            if(equation.charAt(i) == MULTIPLY_OPERATOR)
            {
                // parse values beside operator
                a = parseLeftSideValue(equation, i);
                b = parseRightSideValue(equation, i);
               
                // perform operation
                c =(T) (Double) (a.doubleValue() *  b.doubleValue());
                
                // debug
                System.out.println("\t" + a + " x "  + b + " = " + c);
                                  
                // replace the value operator value in equation with result
                temp = "" + a + MULTIPLY_OPERATOR + b;
                if (a.doubleValue() < 0 && b.doubleValue() < 0)
                {
                    equation = equation.replace(temp, "+" + c);
                }
                else
                {
                    equation = equation.replace(temp, "" + c);
                }
  
                // recursive call
                return parseEquation(equation);
                
            }
        }
        
        // iterate through equation checking for Devision /
        for(int i = 0; i < equation.length(); i++)
        {
            // if devision operator found
            if(equation.charAt(i) == DEVISION_OPERATOR)
            {
                // parse values beside operator
                a = parseLeftSideValue(equation,i);
                b = parseRightSideValue(equation,i);
                               
                
                // Check for devision by zero
                if(b.doubleValue() == 0.0)
                {
                    throw new DevisionByZeroException();
                }
                
                // perform devision operation
                c = (T) (Double)(a.doubleValue() / b.doubleValue());
                
                // debug
                System.out.println("\t" + a + " / " + b + " = " + c);
                
                temp = "" + a + DEVISION_OPERATOR + b;
                if (a.doubleValue() < 0 && b.doubleValue() < 0)
                {
                    equation = equation.replace(temp, "+" + c);
                }
                else
                {
                    equation = equation.replace(temp, "" + c);
                }
              
                // recursive call
                return parseEquation(equation);
            }
        }
        
       
        // iterator through equation checking for Addition +
        //for (int i = 0; i < equation.length(); i++)
        for (int i = equation.length() - 1; i > -1; i--)
        {
            // if addition operator found
            if (equation.charAt(i) == ADDITION_OPERATOR)
            {
                // parse values beside operator
                a = parseLeftSideValue(equation, i);
                b = parseRightSideValue(equation, i);

                // perform addition operation
                c = (T) (Double) (a.doubleValue() + b.doubleValue());

                // debug
                System.out.println("\t" + a + "+" + b + " = " + c);

                temp = "" + a + ADDITION_OPERATOR + b;
                equation = equation.replace(temp, "+" + c);

                // recursive call
                return parseEquation(equation);
            }
        }
        
        
        // iterate through equation checking for Subtraction -
        //for(int i = 0; i < equation.length(); i++)
        for(int i = equation.length() -1; i > -1; i--)
        {            
         // if subtraction operator found
            if(equation.charAt(i) == SUBTRACTION_OPERATOR)
            {
                // parse values beside operator
                a = parseLeftSideValue(equation, i);
                b = parseRightSideValue(equation, i);
                
                // perform subtraction operation
                c = (T)(Double)(a.doubleValue() - b.doubleValue());
                
                System.out.println("\t" + a + " - " + b + " = " + c);
                
                // replace operation from equation with result
                temp = "" + a + SUBTRACTION_OPERATOR + b;
                equation = equation.replace(temp,  "" + c);
         
                // recursive call
                return parseEquation(equation);
            }
        }
           
        return 0;
    }
    
    // METHOD: generates a list of data points based on given domain
    public ArrayList<Point> dataPoints(int minRange, int maxRange)
    {
        ArrayList<Point> pointList = new ArrayList<Point>();
        Point tempPoint = null;
        Double y = null;

        int domain = 0;

        for (int x = minRange; x < maxRange + 1; x++)
        {
            try
            {
                y = this.calculate(x);
            }
            catch(DevisionByZeroException e)
            {
                y = null;
            }
            
            //System.out.println(x + " | " + y + "\n");
            
            tempPoint = new Point(x,y);
            pointList.add(tempPoint);
        }

        return pointList;

    }
    
    
    /****************************** HELPER METHODS **********************************/
    // HELPER: iterates through equation string and replaces "--" with "+"
    // RETURN: a new string with double subtraction operator replaced with
    //         an addition operator
    private static String replaceDoubleSubtractionOperator(String str)
    {
        String doubleNegative ;
        String additionOperator;
        
        for(int i = 0; i < str.length() -1; i++)
        {
            if(str.charAt(i) == SUBTRACTION_OPERATOR
                    && str.charAt(i +1) == SUBTRACTION_OPERATOR)
            {
                doubleNegative = "" + SUBTRACTION_OPERATOR + SUBTRACTION_OPERATOR;
                additionOperator = Character.toString(ADDITION_OPERATOR);
                
                return str.replace(doubleNegative, additionOperator);
            }
        }   
        return str;
    }
    
    // HELPER: iterates through equation string and replaces "++" with "+"
    // RETURN: a new string with double addition operator replaced with single
    //         addition operator
    private static String replaceDoubleAdditionOperator(String str)
    {
        String doubleAddition;
        String additionOperator;
        
        for(int i = 0; i < str.length() -1; i++)
        {
            if(str.charAt(i) == ADDITION_OPERATOR
                && str.charAt(i +1) == ADDITION_OPERATOR)
            {
                doubleAddition = "" + ADDITION_OPERATOR + ADDITION_OPERATOR;
                additionOperator = Character.toString(ADDITION_OPERATOR);
                
                return str.replace(doubleAddition, additionOperator);
            }
        }
        return str;
    }
    
    private static String removeAdditionOperatorIfFirst(String str)
    {
        String temp = "";
        
        if(!str.isEmpty())
        {
            if(str.charAt(0) == ADDITION_OPERATOR)
            {
                temp = str.substring(1, str.length());
                return temp;
            }
            
        }
            return str;     
    }
    // HELPER: returns the value on the left side of given operator position
    private static <T extends Number> T parseLeftSideValue(String equation, int index)
    {
        int startPosition = 0;
        String extracted = "";
        

        // Iterate through equation backwards starting from left of operator position
        for (int i = index - 1; i > -1; i--)
        {
            // if the index is not a number
            if (!isNumber(equation.charAt(i)) && equation.charAt(i) != DECIMAL_MARKER)
                   // && equation.charAt(i) != EXPONENT_MARKER)
            {
                startPosition =  i +1;
                break;
            }       
        }
        
        // Check for negative number
        if(startPosition > 0)
        {
            if(equation.charAt(startPosition -1) == SUBTRACTION_OPERATOR)
            {
                startPosition--;
            }
        }
        
        //startPosition = getLeftStartPosition(equation, index);
        extracted = equation.substring(startPosition, index);   
        return parseValue(extracted);
    }
    
    // HELPER: returns the value on the right side of given operator position
    private static <T extends Number> T parseRightSideValue(String equation, int index)
    {
        int startPosition = index + 1;
        int endPosition = equation.length();
        String extracted = "";
        
        // check for negative number
        if(equation.charAt(startPosition) == SUBTRACTION_OPERATOR)
        {
            startPosition++;
        }
        
        
        // Iterate through equation forward starting from right of operator position
        for(int i = startPosition; i < equation.length(); i++)
        {
            // if the index is not a number
            if(!isNumber(equation.charAt(i)) && equation.charAt(i) != DECIMAL_MARKER)
                    //&& equation.charAt(i) != EXPONENT_MARKER)
            {
               endPosition = i;
               break;
            }
        }
        
        //endPosition = getRightFinishPosition(equation, index);
        extracted = equation.substring(index +1, endPosition);  
        return parseValue(extracted);      
    }
    
    // HELPER: parses Integer or Double in given string
    private static <T extends Number> T parseValue(String str)
    {
        //return (T)value;
        try
        {
            Integer result = Integer.parseInt(str);
            return (T)result;
        }
        catch(NumberFormatException e)
        {
            try
            {
                Double result = Double.parseDouble(str);
                return (T)result;
            }
            catch(NumberFormatException f)
            {
                System.out.println("failed to parse" + str);
                throw new NumberFormatException(str);
            }
        }
    }

    // HELPER: checks if a Number precedes variable X. used for adding
    // '*' operator in between number and variable X
    private static boolean canAddMultiplyOperator(String str, int position)
    {

        if (isNumber(str.charAt(position)) 
                && Character.toLowerCase(str.charAt(position + 1)) == VARIABLE)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    // HELPER: checks if given character is a number digit
    // RETURN: true if number, else false
    private static boolean isNumber(char c)
    {
        for (int i = 0; i < NUMBERS.length(); i++)
        {
            if (c == NUMBERS.charAt(i))
            {
                return true;
            }
        }
        return false;
    }

    // HELPER: verifies string is a valid function
    private static boolean validateFunction(String str)
    {
        boolean valid = false;

        // Iterate through string
        for (int i = 0; i < str.length(); i++)
        {
            // Iterate through valid character
            for (int j = 0; j < VALID_CHARACTERS.length(); j++)
            {
                if (str.charAt(i) == VALID_CHARACTERS.charAt(j))
                {
                    valid = true;
                }
            }

            // return false if we find a bad character
            if (!valid)
            {
                return false;
            }

            // reset flag for next pass
            valid = false;

        }
        // everything checks out fine
        return true;
    }
    
    // HELPER: checks if string is a number without any operators other then -
    //         example: -45.566 would return true , -45.67 - 45 returns false
    private static boolean numberRemains(String str)
    {
        int startPosition = 0;

        if (str.charAt(startPosition) == SUBTRACTION_OPERATOR 
                || str.charAt(startPosition) == ADDITION_OPERATOR)
        {
            startPosition = 1;
        }
        for (int i = startPosition; i < str.length(); i++)
        {
            if (!isNumber(str.charAt(i)) && str.charAt(i) != DECIMAL_MARKER)
            // && str.charAt(i) != SUBTRACTION_OPERATOR)
            {
                return false;
            }
        }

        return true;
    }
}


/******************* SCRATCH PAD ******************************************
//    // HELPER: returns the starting position of a number value left of operator position
//    private static int getLeftStartPosition(String equation, int index)
//    {
//        // Iterate through equation backwards starting from left of operator position
//        for (int i = index - 1; i > -1; i--)
//        {
//            // if the index is not a number
//            if (!isNumber(equation.charAt(i)) && equation.charAt(i) != DECIMAL_MARKER)
//            {
//                return i +1;
//                //break;
//            }       
//        }
//        
//        return 0; 
//        
//    }
      
//    // HELPER: returns the end position of a number value right of operator position
//    private static int getRightFinishPosition(String equation, int index)
//    {
//        // Iterate through equation forward starting from right of operator position
//        for(int i = index + 1; i < equation.length(); i++)
//        {
//            // if the index is not a number
//            if(!isNumber(equation.charAt(i)) && equation.charAt(i) != DECIMAL_MARKER)
//            {
//                return  i;
//               // break;
//            }
//        }
//        
//        return equation.length(); 
//        
//    }
 **************************************************************************/
