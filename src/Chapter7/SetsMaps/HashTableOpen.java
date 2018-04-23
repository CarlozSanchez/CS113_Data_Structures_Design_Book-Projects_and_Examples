package Chapter7.SetsMaps;

import java.util.HashMap;
import java.util.Map;

public class HashTableOpen <K,V> implements KWHashMap<K,V>
{
    private static final int START_CAPACITY = 101;
    private final Entry<K, V> DELETED = new Entry<K,V>(null,null);

    private Entry<K, V>[] table;
    private double LOAD_THRESHOLD = .75;
    private int numKeys;
    private int numDeletes;

    /**
     * DEFAULT CONSTRUCTOR:
     */
    public HashTableOpen()
    {
        table = new Entry[START_CAPACITY];
    }

    /**************************************************************************/
    /******************************* INTERFACE ********************************/
    /**************************************************************************/


    /**
     * INTERFACE: Method get for classh HashtableOpen.
     * @param key The value to return.
     * @return The value associated with this key if found, otherwise, null.
     */
    @Override
    public V get(Object key)
    {
        // Find the first table element that is empty
        // or the table element that contains the key.
        int index = find(key);

        // If the search is successful, return the value.
        if(table[index] != null)
        {
            return table[index].value;
        }
        else
        {
            return null; // key not found
        }
    }

    @Override
    public boolean isEmpty()
    {
        return numKeys == 0;
    }

    /**
     * INTERFACE: Method put for class HashtableOpen
     * POSCONDITION: This key-value pair is inserted in the table and numKeys
     * is incremeneted.  If the key is already in the table, its value is
     * changed to the argument value and numkeys is not changed. If the
     * LOAD_THRESHOLD is exceeded, the table is expanded.
     * @param key The key of item being inserted.
     * @param value The value to associate with key.
     * @return Old value accosiated with this key if found, otherwise , null.
     */
    @Override
    public V put(K key, V value)
    {
        // Find the first table element that is empty
        // or the table element that contains the key.
        int index = find(key);

        // If an empty element was found, insert new entry.
        if(table[index] == null)
        {
            table[index]  = new Entry<K,V>(key, value);
            numKeys++;

            // Check whether rehash is needed.

            double loadFactor = (double) (numKeys + numDeletes) / table.length;

            if(loadFactor > LOAD_THRESHOLD)
            {
                rehash();
            }

            return null;
        }

        // assert: table element that contains the key was found.
        // Replaced value for this key.
        V oldValue = table[index].value;
        table[index].value = value;
        return oldValue;
    }

    @Override
    public V remove(Object key)
    {
        return null;
    }

    @Override
    public int size()
    {

        return numKeys;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("[");

        for (int i = 0; i < table.length; i++)
        {
            if(table[i] != null & table[i] != DELETED)
            {
                sb.append( table[i].value.toString() );

            }
        }
        sb.append("]");

        return sb.toString();
    }

    /**************************************************************************/
    /**************************** CLASS METHODS *******************************/
    /**************************************************************************/

    /**
     * METHOD: Finds either the target key or the first empty slot in the search
     * chain using linear probing.
     * PRECONDITION: The table is not full.
     * @param key The key of the target object.
     * @return The position of the target or the first empty slot if the
     * target is not in the table.
     */
    private int find(Object key)
    {
        int index = key.hashCode() % table.length;

        if(index < 0)
        {
            index += table.length; // make it positive
        }

        while ((table[index] != null) && (!key.equals(table[index].key)))
        {
            index++;

            // Check for wraparound
            if(index >= table.length)
            {
                index = 0; // Wrap around
            }
        }
        return index;
    }

    /**
     * METHOD: Expands table size when loadFactor exceeds LOAD_THRESHOLD
     * P0STCONDITION: The size of the table is doubled and is an odd interger.
     * Each nondeleted entry from the original table is reinserted into the
     * expanded table. The value of numKeys is reset to the number of items
     * actually inserted; numDeletes is reset to 0.
     */
    private void rehash()
    {
        // Save a reference to oldTable.
        Entry<K,V>[] oldTable = this.table;

        // Double capacity of this table.
        table = new Entry[2 * oldTable.length + 1];

        // Reinsert al items in oldTable into expanded table.
        this.numKeys = this.numDeletes = 0;

        for(int i = 0; i < oldTable.length; i++)
        {
            if((oldTable[i] != null) && (oldTable[i] != DELETED))
            {
                // Insert entry in expanded table
                put(oldTable[i].key, oldTable[i].value);
            }
        }

    }



    /**************************************************************************/
    /****************************** INNER CLASS *******************************/
    /**************************************************************************/

    private static class Entry<K,V>
    {
        // Contains key-value pairs
        private K key;
        private V value;


        /**
         * CONSTRUCTOR: Creates a new key-value pair.
         * @param key Key The key.
         * @param value The Value.
         */
        public Entry(K key, V value)
        {
            this.key = key;
            this.value = value;
        }

        /**
         * ACCESSORS: Retrievers the key.
         * @return The key.
         */
        public K getKey()
        {
            return this.key;
        }

        /**
         * ACCESSOR: Retrievers the value.
         * @return The value.
         */
        public V getValue()
        {
            return this.value;
        }


        /**
         * MUTATOR: Sets the value.
         * @param value The new Value.
         * @return The old value.
         */
        public V setValue(V value)
        {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }


    }

}
