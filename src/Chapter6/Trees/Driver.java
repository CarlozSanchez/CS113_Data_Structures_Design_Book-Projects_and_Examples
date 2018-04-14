package Chapter6.Trees;

import java.util.PriorityQueue;
import java.util.ArrayList;

public class Driver
{
    public static void main(String[] args)
    {
        testQue();
    }

    public static void testQue()
    {
        int start = 65 ;
        int finish = start + 26;
        PriorityQueue<Character> pQue = new PriorityQueue<>();

        for (int i = start; i < finish ; i++) {
            pQue.add((char)i);
        }

        System.out.println(pQue);
    }

    private class CharacterFrequencyList
    {
        private ArrayList<CharacterFrequency> list;
    }

    private class CharacterFrequency
    {
        private char character;
        private int frequency;

        public char getCharacter()
        {
            return character;
        }

        public void setCharacter(char character)
        {
            this.character = character;
        }

        public int getFrequency()
        {
            return frequency;
        }

        public void setFrequency(int frequency)
        {
            this.frequency = frequency;
        }


    }


}
