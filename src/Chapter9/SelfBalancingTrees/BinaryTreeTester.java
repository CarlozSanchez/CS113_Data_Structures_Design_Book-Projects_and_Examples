package Chapter9.SelfBalancingTrees;

import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import static junit.framework.TestCase.assertEquals;

public class BinaryTreeTester
{
    private static final int START = 65;

    private static final String STRING_TREE_A = " A\n  B\n   null\n   null\n" +
        "  C\n   null\n   null\n";

    private BinaryTree<Character> tree;
    private ArrayList<BinaryTree> treeList;

    @Before
    public void setUp()
    {
        treeList = new ArrayList<BinaryTree>();
    }


    public void generateCharacterTrees(int num)
    {

//        models.BinaryTree<Character> bt = new models.BinaryTree<Character>(
//          new models.BinaryTree.Node('c'));
//
//        System.out.println(bt);

        for (int i = START; i < START + num; i++) {
            char c = (char)i;

            treeList.add(new BinaryTree<Character>(new BinaryTree.Node(c)));
        }
    }

    @Test
    public void testDefault()
    {
        BinaryTree tree = new BinaryTree();

        System.out.println(tree);
    }

    @Test
    public void testPartialConstructor()
    {
        BinaryTree tree = new BinaryTree(new BinaryTree.Node('A'));

        System.out.println(tree);
    }

    @Test
    public void testFullConstructor()
    {
        BinaryTree leftTree = new BinaryTree(new BinaryTree.Node('B'));
        BinaryTree rightTree = new BinaryTree(new BinaryTree.Node('C'));
        BinaryTree root = new BinaryTree('A', leftTree, rightTree);

        assertEquals("Tree should match toString()", STRING_TREE_A, root.toString());
    }

    @Test
    public void testReadTree()
    {
        Scanner scan = new Scanner(STRING_TREE_A);

        BinaryTree tree = BinaryTree.readBinaryTree(scan);

        assertEquals("tree toString should match " + STRING_TREE_A,
                STRING_TREE_A, tree.toString());
    }

    @Test
    public void testReadFile()
    {
        //Create object(stream) for input using the FileInputStream class
        Scanner inputStream = null;

        try
        {
            inputStream = new Scanner(
                    new FileInputStream("doc/TreeData.txt"));
        }

        catch(FileNotFoundException e)
        {
            System.out.println("File readFile.txt was not found");
            System.out.println("or could not be opened");
            System.exit(0);
        }

        // !!!! left of here
       StringBuilder sb = new StringBuilder();

        BinaryTree  tree = BinaryTree.readBinaryTree(inputStream);

        assertEquals("Tree should have ben created",
                STRING_TREE_A, tree.toString());

    }

    @Test
    public void testReadAlphabet()
    {
        //Create object(stream) for input using the FileInputStream class
        Scanner inputStream = null;

        try
        {
            inputStream = new Scanner(
                new FileInputStream("doc/AlphabetTree.txt"));
        }

        catch(FileNotFoundException e)
        {
            System.out.println("File readFile.txt was not found");
            System.out.println("or could not be opened");
            System.exit(0);
        }

        // !!!! left of here
        StringBuilder sb = new StringBuilder();

        BinaryTree  tree = BinaryTree.readBinaryTree(inputStream);

        System.out.println(tree);
    }

    @Test
    public void testReadMorseCodeData()
    {
        //Create object(stream) for input using the FileInputStream class
        Scanner inputStream = null;

        try
        {
            inputStream = new Scanner(
                new FileInputStream("doc/AlphabetTree.txt"));
        }

        catch(FileNotFoundException e)
        {
            System.out.println("File readFile.txt was not found");
            System.out.println("or could not be opened");
            System.exit(0);
        }

        // !!!! left of here
        StringBuilder sb = new StringBuilder();

        BinaryTree  tree = BinaryTree.readBinaryTree(inputStream);

        System.out.println(tree);
    }

    @Test
    public void testWriteFile()
    {
        //Create object(stream) for output using the FileOutputStream class
        PrintWriter outputStream = null;

        try
        {
            outputStream = new PrintWriter(
                    new FileOutputStream("doc/TreeData.txt"));
        }

        //Check to see if file can be created or exists. If not, end program
        catch(FileNotFoundException e)
        {
            System.out.println("Error opening the file dataFile.");
            System.exit(0);
        }


        //Tell user you are writing out to file
        System.out.println("Writing to file.");

        // Create a tree
        BinaryTree<String> tree = BinaryTree.readBinaryTree(
                new Scanner(STRING_TREE_A));

        //Write out to file
        outputStream.print(tree.toString());

        //Close output file
        outputStream.close();

        System.out.println("End of program.");
    }


    public void playingWithTrees()
    {
        generateCharacterTrees(24);

//        for(models.BinaryTree bt : treeList)
//        {
//            System.out.println(bt);
//        }

        BinaryTree<Character> test = new BinaryTree(treeList.get(0).root.data,
                treeList.get(1), treeList.get(2));

        BinaryTree<Character> test2 = new BinaryTree(treeList.get(3).root.data,
                treeList.get(4), treeList.get(5));

        BinaryTree<Character> test3 = new BinaryTree('X', test, test2);

        System.out.println(test3);

    }

}

