/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ktu.ds.lab3.Dzikas;
import edu.ktu.ds.lab3.utils.EvaluableMap;
import edu.ktu.ds.lab3.utils.HashMap;
import edu.ktu.ds.lab3.utils.HashType;
import edu.ktu.ds.lab3.utils.Map;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/**
 *
 * @author Arminas
 */
//-----------------------------DM --Dviguba maiša
public class HashMapOa<K, V> implements EvaluableMap<K, V> {

    public static final int DEFAULT_INITIAL_CAPACITY = 16;
    public static final float DEFAULT_LOAD_FACTOR = 0.75f;
    public static final HashType DEFAULT_HASH_TYPE = HashType.DIVISION;

    // Maišos lentelė
    protected Node<K, V>[] table;
    // Lentelėje esančių raktas-reikšmė porų kiekis
    protected int size = 0;
    // Apkrovimo faktorius
    protected float loadFactor;
    // Maišos metodas
    protected HashType ht;
    //--------------------------------------------------------------------------
    //  Maišos lentelės įvertinimo parametrai
    //--------------------------------------------------------------------------
    // Maksimalus suformuotos maišos lentelės grandinėlės ilgis
    protected int maxChainSize = 0;
    // Permaišymų kiekis
    protected int rehashesCounter = 0;
    // Paskutinės patalpintos poros grandinėlės indeksas maišos lentelėje
    protected int lastUpdatedChain = 0;
    // Lentelės grandinėlių skaičius     
    protected int chainsCounter = 0;
    // Einamas poros indeksas maišos lentelėje
    protected int index = 0;

    /* Klasėje sukurti 4 perkloti konstruktoriai, nustatantys atskirus maišos 
     * lentelės parametrus. Jei kuris nors parametras nėra nustatomas - 
     * priskiriama standartinė reikšmė.
     */
    public HashMapOa() {
        this(DEFAULT_HASH_TYPE);
    }

    public HashMapOa(HashType ht) {
        this(DEFAULT_INITIAL_CAPACITY, ht);
    }

    public HashMapOa(int initialCapacity, HashType ht) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR, ht);
    }

    public HashMapOa(float loadFactor, HashType ht) {
        this(DEFAULT_INITIAL_CAPACITY, loadFactor, ht);
    }

    public HashMapOa(int initialCapacity, float loadFactor, HashType ht) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
        }

        if ((loadFactor <= 0.0) || (loadFactor > 1.0)) {
            throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
        }

        this.table = new Node[initialCapacity];
        this.loadFactor = loadFactor;
        this.ht = ht;
    }

    /**
     * Patikrinama ar atvaizdis yra tuščias.
     *
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Grąžinamas atvaizdyje esančių porų kiekis.
     *
     * @return Grąžinamas atvaizdyje esančių porų kiekis.
     */
    @Override
    public int size() {
        return size;
    }
    
    private int findPosition(K key)//----------------------------------------------------------------- Antroji Hash funkcija-----------||
    {
        int index = hash(key, ht);
        int index0 = index;
        for (int i = 0; i < table.length; i++) {
            if (table[index] == null || table[index].key.equals(key)) {
                return index;
            }
            index = (index0 + i * hash2(key))% table.length;
        }
        return -1;
    }
    private int hash2(K key){
        return 7 - Math.abs(key.hashCode() % 7);
    }

    /**
     * Išvalomas atvaizdis.
     */
    @Override
    public void clear() {
        Arrays.fill(table, null);
        size = 0;
        index = 0;
        lastUpdatedChain = 0;
        maxChainSize = 0;
        rehashesCounter = 0;
        chainsCounter = 0;
    }

    /**
     * Patikrinama ar pora egzistuoja atvaizdyje.
     *
     * @param key raktas.
     * @return Patikrinama ar pora egzistuoja atvaizdyje.
     */
    @Override
    public boolean contains(K key) {
        return get(key) != null;
    }
    
     /**
     * Patikrinama ar vaizdinyje egzistuoja objektas(-ai).
     * @param value reikšmė.
     * @return jei atvaizdyje egzistuoja objektai - true, kitu atveju - false.
     */
    public boolean containsValue(Object value) //----------------------------------------------Patikrinama ar vaizdinyje egzistuoja objektas(-ai).-----------------||
    {
        if (!(value instanceof Book)) {
            return false;
        }
          Book temp = (Book)value;
          for (Node<K, V> node : table) {
            if (node != null) {
                for (Node<K, V> n = node; n != null; n = n.next) {
                    if (temp.equals((Book)n.value)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    /**
     * Atvaizdis papildomas nauja pora arba jau tuo atveju jei turi porą.
     * @param key raktas.
     * @param value reikšmė.
     * @return Atvaizdis papildomas nauja pora arba jau tuo atveju jei turi porą.
     */
    public V putIfAbsent(K key, V value)//----------------------------------------------Atvaizdis papildomas nauja pora arba jau tuo atveju jei turi porą.
    {
        if (!contains(key)) {
            put(key, value);
            return value;
        }
        return value;
    } 
    
    /**
     * Apskaičiuojama laisva vieta.
     * @return laisva vieta.
     */
    public int numberOfEmpties()//----------------------------------------------Patikrinama ar vaizdinyje egzistuoja objektas(-ai).-----------------||
    {
        int counter = 0;
        for (Node<K, V> node : table) {
            if (node == null) {
                counter++;
            }
        }
        return counter;
    }
    
    /**
     * Grąžinami visi esantys reikšmių raktai.
     * @return Grąžinami visi esantys reikšmių raktai.
     */
    public Set<K> keySet()//----------------------------------------------Grąžinami visi esantys reikšmių raktai.-----------------||
    {
        Set<K> key = new HashSet<K>();
        for (Node<K, V> node : table) {
            if(node != null)
            key.add(node.key);
        }
        return key;
    }
    
    /**
     * Pakeičia visų atvaizdžių porų reikšmes nauja reikšme, jei senoji reikšmė yra tokia kaip nurodyta argumente.
     * @param oldValue sena reikšmė.
     * @param newValue nauja reikšmė.
     */
    public void replaceAll(V oldValue, V newValue)//----------Pakeičia visų atvaizdžių porų reikšmes nauja reikšme, jei senoji reikšmė yra tokia kaip nurodyta argumente.-----------------||
    {
        for (Node<K, V> node : table) {
            
            for (Node<K, V> n = node; n != null; n = n.next) {
                if (n != null && ((Book)oldValue).equals(n.value)) {
                n.value = newValue;
                }
            }
        }
    }
     /**
     * Pakeičia atvaizdyje egzistuojantį raktą atitinkančią reikšmę ir grąžina senąją reikšmę. Jei raktas neegzistuoja atvaizdyje, argumentuose nurodyta raktas-reikšmė pora talpinama atvaizdyje ir gražinama null.
     * @param Key ieškomas raktas.
     * @param Value ieškoma reikšmė.
     */
    
    public V replace(K key, V value) {
           V change = null;
        for (Node<K, V> node : table) {
         
            for (Node<K, V> n = node; n != null; n = n.next) {
                if (n != null && ((Book) key).equals(n.key)) {
                    change = n.value;
                    n.value = value;
                    return change;
                }

                if (n != null && ((Book) key).equals(n.key) == false) {
                    n.key = key;
                    n.value = value;
                    return null;
                }
            }

        }
        return change;

    }

    
    /**
     * Gražiną kolizijų skaičių
     */
    int getNumberOfCollisions() {
        int collisions = 0;
//HashMap<K,V> map = new HashMap<K,V>();
// Insert all elements into buckets based on their hash value
        for (Node<K, V> node : table) {

            for (Node<K, V> n = node; n != null; n = n.next) {
                if (n != null && n.key.equals(n.next.key)) {

                    collisions++;
                    return collisions;
                }
            }

        }
        return collisions;
    }

    
    /**
     * Atvaizdis papildomas nauja pora.
     *
     * @param key raktas,
     * @param value reikšmė.
     * @return Atvaizdis papildomas nauja pora.
     */
    @Override
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key or value is null in put(Key key, Value value)");
        }
        index = findPosition(key);
        if (table[index] == null) {
            chainsCounter++;
        }

        Node<K, V> node = getInChain(key, table[index]);
        if (node == null) {
            table[index] = new Node<>(key, value, table[index]);
            size++;

            if (size > table.length * loadFactor) {
                rehash(table[index]);
            } else {
                lastUpdatedChain = index;
            }
        } else {
            node.value = value;
            lastUpdatedChain = index;
        }

        return value;
    }

    /**
     * Grąžinama atvaizdžio poros reikšmė.
     *
     * @return Grąžinama atvaizdžio poros reikšmė.
     *
     * @param key raktas.
     */
    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null in get(Key key)");
        }

        index = findPosition(key);
        Node<K, V> node = getInChain(key, table[index]);
        return (node != null) ? node.value : null;
    }

    /**
     * Pora pašalinama iš atvaizdžio.
     *
     * @param key Pora pašalinama iš atvaizdžio.
     * @return key raktas.
     */
    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null in remove(Key key)");
        }

        index = findPosition(key);
        Node<K, V> previous = null;
        for (Node<K, V> n = table[index]; n != null; n = n.next) {
            if ((n.key).equals(key)) {
                if (previous == null) {
                    table[index] = n.next;
                } else {
                    previous.next = n.next;
                }
                size--;

                if (table[index] == null) {
                    chainsCounter--;
                }
                return n.value;
            }
            previous = n;
        }
        return null;
    }

    /**
     * Permaišymas
     *
     * @param node
     */
    private void rehash(Node<K, V> node) {
        HashMapOa<K, V> newMap = new HashMapOa<>(table.length * 2, loadFactor, ht);
        for (int i = 0; i < table.length; i++) {
            while (table[i] != null) {
                if (table[i].equals(node)) {
                    lastUpdatedChain = i;
                }
                newMap.put(table[i].key, table[i].value);
                table[i] = table[i].next;
            }
        }
        table = newMap.table;
        maxChainSize = newMap.maxChainSize;
        chainsCounter = newMap.chainsCounter;
        rehashesCounter++;
    }

    /**
     * Maišos funkcijos skaičiavimas: pagal rakto maišos kodą apskaičiuojamas
     * atvaizdžio poros indeksas maišos lentelės masyve
     *
     * @param key
     * @param hashType
     * @return
     */
    private int hash(K key, HashType hashType) {
        int h = key.hashCode();
        switch (hashType) {
            case DIVISION:
                return Math.abs(h) % table.length;
            case MULTIPLICATION:
                double k = (Math.sqrt(5) - 1) / 2;
                return (int) (((k * Math.abs(h)) % 1) * table.length);
            case JCF7:
                h ^= (h >>> 20) ^ (h >>> 12);
                h = h ^ (h >>> 7) ^ (h >>> 4);
                return h & (table.length - 1);
            case JCF8:
                h = h ^ (h >>> 16);
                return h & (table.length - 1);
            default:
                return Math.abs(h) % table.length;
        }
    }

    /**
     * Paieška vienoje grandinėlėje
     *
     * @param key
     * @param node
     * @return
     */
    private Node<K, V> getInChain(K key, Node<K, V> node) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null in getInChain(Key key, Node node)");
        }
        int chainSize = 0;
        for (Node<K, V> n = node; n != null; n = n.next) {
            chainSize++;
            if ((n.key).equals(key)) {
                return n;
            }
        }
        maxChainSize = Math.max(maxChainSize, chainSize + 1);
        return null;
    }

    @Override
    public String[][] toArray() {
        String[][] result = new String[table.length][];
        int count = 0;
        for (Node<K, V> n : table) {
            String[] list = new String[getMaxChainSize()];
            int countLocal = 0;
            while (n != null) {
                list[countLocal++] = n.toString();
                n = n.next;
            }
            result[count] = list;
            count++;
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Node<K, V> node : table) {
            if (node != null) {
                for (Node<K, V> n = node; n != null; n = n.next) {
                    result.append(n.toString()).append(System.lineSeparator());
                }
            }
        }
        return result.toString();
    }

    /**
     * Grąžina maksimalų grandinėlės ilgį.
     *
     * @return Maksimalus grandinėlės ilgis.
     */
    @Override
    public int getMaxChainSize() {
        return maxChainSize;
    }

    /**
     * Grąžina formuojant maišos lentelę įvykusių permaišymų kiekį.
     *
     * @return Permaišymų kiekis.
     */
    @Override
    public int getRehashesCounter() {
        return rehashesCounter;
    }

    /**
     * Grąžina maišos lentelės talpą.
     *
     * @return Maišos lentelės talpa.
     */
    @Override
    public int getTableCapacity() {
        return table.length;
    }

    /**
     * Grąžina paskutinės papildytos grandinėlės indeksą.
     *
     * @return Paskutinės papildytos grandinėlės indeksas.
     */
    @Override
    public int getLastUpdatedChain() {
        return lastUpdatedChain;
    }

    /**
     * Grąžina grandinėlių kiekį.
     *
     * @return Grandinėlių kiekis.
     */
    @Override
    public int getChainsCounter() {
        return chainsCounter;
    }

    protected static class Node<K, V> {

        // Raktas        
        protected K key;
        // Reikšmė
        protected V value;
        // Rodyklė į sekantį grandinėlės mazgą
        protected Node<K, V> next;

        protected Node() {
        }

        protected Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }
}
