package DataStructureProjects.LinkedArrayList;

import DataStructuresAbstractionDesign.chapter3.Stacks.ParenthesesChecker;

public class LinkedArrayList <E>
{


/***************************** CONSTANT VARIABLES *****************************/
    private static final int INITIAL_SIZE = 4;

/****************************** CLASS VARIABLES *******************************/
    private ArrayNode head;
    private int size;

/******************************************************************************/
/******************************* CONSTRUCTORS *********************************/
/******************************************************************************/

    public  LinkedArrayList()
    {
        this.head = new ArrayNode(INITIAL_SIZE);
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

    public boolean add(E data)
    {
        head.dataArray[size] = data;
        size++;
        return true;
    }

/******************************************************************************/
/****************************** STATIC METHODS ********************************/
/******************************************************************************/


/******************************************************************************/
/****************************** HELPER METHODS ********************************/
/******************************************************************************/

/******************************************************************************/
/******************************** INNER CLASS *********************************/
/******************************************************************************/

private static class ArrayNode <E>
{
    private E[] dataArray;
    private ArrayNode next;

    private ArrayNode()
    {
        setAll(null, null);
    }

    private ArrayNode(int arrSize)
    {
        this.setAll((E[]) new Object[arrSize], null);
    }

    private void setAll(E[] dataArray, ArrayNode next)
    {
        this.dataArray = dataArray;
        this.next = next;
    }
}

}