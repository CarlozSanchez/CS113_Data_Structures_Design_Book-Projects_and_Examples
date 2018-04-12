package Chapter6.Trees;

import java.util.PriorityQueue;

public class Driver
{
    public static void main(String[] args)
    {
        testQue();
    }

    public static void testQue()
    {
        int start = 65;
        int finish = start + 26;
        PriorityQueue<Character> pQue = new PriorityQueue<>();

        for (int i = start; i < finish ; i++) {
            pQue.add((char)i);
        }

        System.out.println(pQue);
    }
}
