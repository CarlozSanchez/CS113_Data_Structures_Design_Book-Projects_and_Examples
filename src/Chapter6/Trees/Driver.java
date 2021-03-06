package Chapter6.Trees;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Scanner;

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

    public String buildString(File file)
    {

        Scanner inputStream = null;
        StringBuilder sb  = new StringBuilder();

        try
        {
            inputStream = new Scanner(new FileInputStream(file));
        }
        catch(FileNotFoundException e)
        {
            System.out.println(file.getName() + " was not found or could not " +
                "be opened");
            return null;
        }

        while(inputStream.hasNext())
        {
            sb.append(inputStream.nextLine());
        }

        return sb.toString();
    }

    private class CharacterFrequencyList
    {

        private ArrayList<CharacterFrequency> list;

        public void addCharacter(char c)
        {
            for(int i = 0; i < list.size(); i++)
            {
                CharacterFrequency charFreq = list.get(i);
                if(c == charFreq.character)
                {
                    charFreq.incrementFrequency();
                }
                else
                {
                    list.add(new CharacterFrequency(c,1));
                }
            }
        }

        public String toString()
        {
            int buffer = 5;

            StringBuilder sbA = new StringBuilder();
            StringBuilder sbB = new StringBuilder();

            for(CharacterFrequency cf : list)
            {
                sbA.append(String.format("[%" + buffer + "c]",cf.getCharacter()));
                sbB.append(String.format("[%" + buffer + "d]", cf.getFrequency()));
            }

            sbA.append("\n");
            sbA.append(sbB.toString());
            return sbA.toString();
        }
    }

    private class CharacterFrequency
    {
        private char character;
        private int frequency;

        public CharacterFrequency()
        {
            this.character = 0;
            this.frequency = 0;
        }

        public CharacterFrequency(char c,int count)
        {
            this.character = c;
            this.frequency = count;
        }

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

        public void incrementFrequency()
        {
            this.frequency++;
        }
    }


}
