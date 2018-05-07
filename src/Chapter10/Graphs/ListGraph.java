package Chapter10.Graphs;


import java.sql.Time;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * CLASS: A ListGraph is an extension of the AbstractGraph abstract class that
 * uses an array of lists to represent the edges.
 */
public class ListGraph extends AbstractGraph
{
    /****************************** DATA FIELDS *******************************/

    // An array of lists to contain the edges that originat with each vertex.
    private List<Edge>[] edges;


    /**************************************************************************/
    /***************************** CONSTRUCTORS *******************************/
    /**************************************************************************/

    /**
     * CONSTRUCTOR: Constructrs a graph with the specified number of vertices
     * and directionality.
     * @param numV The number of vertices.
     * @param directed The directionality flag.
     */
    public ListGraph(int numV, boolean directed)
    {
        super(numV, directed);
        edges = new List[numV];
        for (int i = 0; i < numV; i++)
        {
            edges[i] = new LinkedList<Edge>();
        }
    }

    /**************************************************************************/
    /**************************** INTERFACE Graph******************************/
    /**************************************************************************/



    /**
     * METHOD: Determines wheter the edge exists.
     * @param source The source vertex.
     * @param destination The destination verstex.
     * @return true if theere is an edge from source to destination.
     */
    @Override
    public boolean isEdge(int source, int destination)
    {
        return edges[source].contains(new Edge(source, destination));
    }

    /**
     * METHOD: Insert a new edge into the graph.
     * @param edge The new edge to insert.
     */
    public void insert(Edge edge)
    {
        edges[edge.getSource()].add(edge);
        if(!isDirected())
        {
            edges[edge.getDestination()].add(new Edge(edge.getDestination(),
                edge.getSource(), edge.getWeight()));
        }
    }

    /**
     * METHOD: Get Iterator over the desired source.
     * @param source The edge list to get iterrator from.
     * @return An Iterator over the desired source.
     */
    public Iterator<Edge> edgeIterator(int source)
    {
        return edges[source].iterator();
    }

    /**************************************************************************/
    /**************************** CLASS METHODS *******************************/
    /**************************************************************************/

    /**
     * METHOD: Get the edge between two vertices.
     * @param source The source.
     * @param destination The destination.
     * @return The edge between these two vertices or null if an edge does
     * not exits.
     */
    public Edge getEdge(int source, int destination)
    {
        Edge target = new Edge(source, destination, Double.POSITIVE_INFINITY);
        for(Edge edge: edges[source])
        {
            if(edge.equals(target))
            {
                return edge; // Desired edge found, return it
            }
        }

        // Assert: All edges for source checked.
        return null;
    }

    /**************************************************************************/
    /******************************* OVERRIDES ********************************/
    /**************************************************************************/

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString() +"\n");
        return sb.toString();
    }


    /**
     * HELPER: Used by toString() to display the edges in each vertece.
     * @param withWeights if true adds the weight info assosicated with each
     *                    destination description. If false only adds destination.
     * @return  A String containing each Vertice all it's edges if any.
     */
    public String vertContents(boolean withWeights)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < edges.length; i++)
        {
            sb.append(String.format("Vert %2d: ", i));
            for(Edge edge : edges[i])
            {
                if(withWeights)
                {
                    sb.append(String.format("(d:%d, w: %.1f) ",edge.getDestination(),
                        edge.getWeight()));
                }
                else
                {
                    sb.append("(" + edge.getDestination() + ") ");
                }


            }
            sb.append("\n");
        }
        return sb.toString();
    }


}
