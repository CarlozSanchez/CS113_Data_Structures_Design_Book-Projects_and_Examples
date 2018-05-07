package Chapter10.Graphs;

public class Edge
{
    private int destination;
    private int source;
    private double weight;


    /**************************************************************************/
    /***************************** CONSTRUCTORS *******************************/
    /**************************************************************************/


    /**
     * PARTIAL CONSTRUCTOR: Constructs Edge with source and destination.
     * @param source The source.
     * @param destination The destination.
     */
    public Edge(int source, int destination)
    {
        this.source = source;
        this.destination = destination;
        this.weight = 0;
    }

    /**
     * FULL CONSTRUCTOR: Constructs Edge with source , destination, and weight.
     * @param source The Source.
     * @param destination The destination.
     * @param weight The weight.
     */
    public Edge(int source, int destination, double weight)
    {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    /**************************************************************************/
    /******************************* MUTATORS *********************************/
    /**************************************************************************/

    /**
     * MUTATOR: sets destination to given value.
     * @param destination The destination.
     */
    public void setDestination(int destination)
    {
        this.destination = destination;
    }

    /**
     * MUTATOR: sets source to given value.
     * @param source The source.
     */
    public void setSource(int source)
    {
        this.source = source;
    }

    /**
     * MUTATOR: Sets weight to given vaue.
     * @param weight The weight.
     */
    public void setWeight(double weight)
    {
        this.weight = weight;
    }


    /**************************************************************************/
    /******************************* ACCESSORS ********************************/
    /**************************************************************************/

    /**
     * ACCESSORS: Accesses destination value.
     * @return The destination in integer form.
     */
    public int getDestination()
    {
        return destination;
    }

    /**
     * ACCESSORS: Accesses source value.
     * @return The source in integer form.
     */
    public int getSource()
    {
        return source;
    }

    /**
     * ACCESSORS: Accesses weight value.
     * @return The weight in double form.
     */
    public double getWeight()
    {
        return weight;
    }

    /**************************************************************************/
    /******************************* OVERRIDES ********************************/
    /**************************************************************************/

    @Override
    public boolean equals(Object other)
    {
        if(other == null || other.getClass() != this.getClass())
        {
            return false;
        }

        Edge otherEdge = (Edge)other;
        return this.destination == otherEdge.destination
            && this.source == otherEdge.source
            && this.weight == otherEdge.weight;

    }

    @Override
    public int hashCode()
    {
        return 0;
    }

    @Override
    public String toString()
    {
        return String.format("dest: %d, source: %d, weight: %f",
            this.destination, this.source,this.weight);
    }


}
