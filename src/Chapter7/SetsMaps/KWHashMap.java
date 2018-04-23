package Chapter7.SetsMaps;

public interface KWHashMap<K,V>
{
    /**
     * INTERFACE : Returns the value associated with the specified key. Returns
     * null if the key is no present.
     * @param key The value to return.
     * @return The value if present otherwise returns null.
     */
    V get(Object key);

    /**
     * INTERFACE: Returns true if this table contains no key-value mapping.
     * @return True if table contains no key-value mapping otherwise true.
     */
    boolean isEmpty();

    /**
     * INTERFACE: Associates the specified value with the specified key.
     * @param key The key.
     * @param value The value to associate with key.
     * @return The previous value associcated with the specified key, or null if
     * there was no mapping for the key.
     */
    V put(K key, V value);

    /**
     * INTERFACE: Removes the mapping from this key from this table if it is
     * present.
     * @param key The key to search for and remove.
     * @return The previous value to associated with the specified key, or null
     * if there was no mapping.
     */
    V  remove(Object key);

    /**
     * INTERFACE: Returns the size of the table.
     * @return Returns integer representing the size of the table.
     */
    int size();
}
