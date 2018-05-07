package Chapter10.Graphs;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * CLASS: Abstract base class for graphs. A graph is a set of vertices and a set
 * of edges. Vertices are represented by integers from 0 to n-1. Edges are
 * ordered pairs of vertices.
 */
public abstract class AbstractGraph implements Graph
{

    /****************************** DATA FIELDS *******************************/
    // The number of vertices
    private int numV;

    //Flag to indicate whether this is a directed graph
    private boolean directed;


    /**************************************************************************/
    /***************************** CONSTRUCTORS *******************************/
    /**************************************************************************/

    /**
     * Construct a graph with the specified number of vertices and the directed
     * flag. If the directed flag is true, this is a directed graph.
     *
     * @param numV     The number of vertices
     * @param directed The directed flag
     */
    public AbstractGraph(int numV, boolean directed)
    {
        this.numV = numV;
        this.directed = directed;
    }

    /**************************************************************************/
    /******************************* ACCESSORS ********************************/
    /**************************************************************************/


    /**
     * Return the number of vertices.
     *
     * @return The number of vertices
     */
    public int getNumV()
    {
        return numV;
    }

    /**
     * Return whether this is a directed graph.
     *
     * @return true if this is a directed graph
     */
    public boolean isDirected()
    {
        return directed;
    }

    /**************************************************************************/
    /**************************** CLASS METHODS *******************************/
    /**************************************************************************/

    /**
     * Load the edges of a graph from the data in an input file. The file
     * should contain a series of lines, each line with two or
     * three data values. The first is the source, the second is
     * the destination, and the optional third is the weight.
     *
     * @param scan The Scanner connected to the data file
     */

    public void loadEdgesFromFile(Scanner scan)
    {

    }


    /**
     * Factory method to create a graph and load the data from an input
     * file. The first line of the input file should contain the number
     * of vertices. The remaining lines should contain the edge data as
     * described under loadEdgesFromFile.
     *
     * @param scan       The Scanner connected to the data file
     * @param isDirected true if this is a directed graph,
     *                   false otherwise
     * @param type       The string "Matrix" if an adjacency matrix is to be
     *                   created, and the string "List" if an adjacency list
     *                   is to be created
     * @throws IllegalArgumentException if type is neither "Matrix"
     *                                  nor "List"
     */
    public static Graph createGraph(Scanner scan, boolean isDirected,
                                    String type)
    {
        int numV = scan.nextInt();
        AbstractGraph returnValue;
        type = type.toLowerCase();
        switch (type)
        {
            case "matrix":
                // returnValue = new MatrixGraph(numV, isDirected);
                break;
            case "list":
                // returnValue = new ListGraph(numV, isDirected);
                break;
            default:
                throw new IllegalArgumentException();
        }
        //returnValue.loadEdgesFromFile(scan);
        //return returnValue;
        return null;
    }

    public String toString()
    {
       // BreadthFirstSearch search = new BreadthFirstSearch();

        return String.format("numV: %d, directed: %s", this.numV, this.directed);

    }

//    /**
//     * Class to implement the breadth‐first search algorithm.
//     */
//    public class BreadthFirstSearch
//    {
//        /**
//         * Perform a breadth‐first search of a graph.
//         *
//         * @param graph The graph to be searched
//         * @param start The start vertex
//         * @return The array of parents
//         * @post The array parent will contain the predecessor
//         * of each vertex in the breadth‐first search tree.
//         */
//        public int[] breadthFirstSearch(Graph graph, int start)
//        {
//            Queue<Integer> theQueue = new LinkedList<Integer>();
//
//            // Declare array parent and initialize its elements to –1.
//            int[] parent = new int[graph.getNumV()];
//
//            for (int i = 0; i < graph.getNumV(); i++)
//            {
//                parent[i] = -1;
//            }
//
//            // Declare array identified and
//            // initialize its elements to false.
//            boolean[] identified = new boolean[graph.getNumV()];
//
//            /* Mark the start vertex as identified and insert it into the queue */
//            identified[start] = true;
//            theQueue.offer(start);
//
//            /* Perform breadth‐first search until done */
//            while (!theQueue.isEmpty())
//            {
//                /* Take a vertex, current, out of the queue. (Begin visiting current). */
//                int current = theQueue.remove();
//
//                /* Examine each vertex, neighbor, adjacent to current. */
//                Iterator<Edge> itr = graph.edgeIterator(current);
//
//                while (itr.hasNext())
//                {
//                    Edge edge = itr.next();
//                    int neighbor = edge.getDestination();
//
//                    // If neighbor has not been identified
//                    if (!identified[neighbor])
//                    {
//                        // Mark it identified.
//                        identified[neighbor] = true;
//
//                        // Place it into the queue.
//                        theQueue.offer(neighbor);
//                        /* Insert the edge (current, neighbor) into the tree. */
//                        parent[neighbor] = current;
//                    }
//                }
//                // Finished visiting current.
//            }
//            return parent;
//        }
//    }
}
