package Chapter10.Graphs.testers;

import Chapter10.Graphs.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.Scanner;

public class ListGraphTester
{

    private ListGraph undirectedListGraph;
    private ListGraph directedListGraph;

    @Before
    public void setUp()
    {
       undirectedListGraph = new ListGraph(5, false);
        directedListGraph = new ListGraph(5,true);

    }

    private void print(int[] result)
    {
        StringBuilder sb = new StringBuilder();

        sb.append("[");
        for (int i = 0; i < result.length; i++)
        {
            sb.append(String.format("%d,",i));
        }
        sb.append("]");

        System.out.println(sb.toString());
    }

    @Test
    public void firstTest()
    {
        undirectedListGraph.insert(new Edge(0,1, 10));
        undirectedListGraph.insert(new Edge(1,2, 30));

        System.out.println(undirectedListGraph + undirectedListGraph.vertContents(true));

        System.out.println();

    }

    @Test
    public void testDirectedListGraph()
    {
        directedListGraph.insert(new Edge(0,1, 10.65));
        directedListGraph.insert(new Edge(1,4, 26.50));
       directedListGraph.insert(new Edge(4,1, 10.65));

        System.out.println(directedListGraph + directedListGraph.vertContents(true));


    }

    @Test
    public void testBreadthFirstSEarch()
    {

    }

    @Test
    public void testTime()
    {
        Time time = new Time(1);

        time.toLocalTime();

        System.out.println(time);
    }
}
