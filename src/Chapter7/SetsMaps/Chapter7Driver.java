package Chapter7.SetsMaps;

import java.util.*;

public class Chapter7Driver <E>
{

    public static void main (String[] args)
    {

        hashSet();
    }

    public static void hashSet()
    {
        String[] things = {"Carlos", "car", "Carlos", "Bill", "Alex"};
        List<String> list = Arrays.asList(things);

        Set<String> set = new HashSet<String>(list);

        print(things);
        print(set);

    }

    public static <T> void print(T[] things)
    {
        for(T t : things)
        {
            System.out.print(t.toString() + ", ");
        }
        System.out.println();
    }

    public static<E>  void print(Set<E> list)
    {
        for(E t : list)
        {
            System.out.print(t.toString() + ", ");
        }
    }

}
