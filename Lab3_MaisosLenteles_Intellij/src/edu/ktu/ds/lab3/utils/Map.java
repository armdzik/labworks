package edu.ktu.ds.lab3.utils;

import java.util.Set;

/**
 * Interfeisu aprašomas Atvaizdžio ADT.
 *
 * @param <K> Atvaizdžio poros raktas
 * @param <V> Atvaizdžio poros reikšmė
 */
public interface Map<K, V> {

    /**
     * Patikrinama ar atvaizdis yra tuščias.
     *
     * @return true, jei tuščias
     */
    boolean isEmpty();

    /**
     * Grąžinamas atvaizdyje esančių porų kiekis.
     *
     * @return Grąžinamas atvaizdyje esančių porų kiekis.
     */
    int size();
    
    /**
     * Apskaičiuojama laisva vieta.
     * @return laisva vieta.
     */
    int numberOfEmpties();

    /**
     * Išvalomas atvaizdis.
     *
     */
    void clear();

    /**
     * Grąžinamas porų masyvas.
     *
     * @return Grąžinamas porų masyvas.
     */
    String[][] toArray();
    
    /**
     * Pakeičia visų atvaizdžių porų reikšmes nauja reikšme, jei senoji reikšmė yra tokia kaip nurodyta argumente.
     * @param oldValue sena reikšmė.
     * @param newValue nauja reikšmė.
     */
    void replaceAll(V oldValue, V newValue);

    /**
     * Atvaizdis papildomas nauja pora.
     *
     * @param key raktas,
     * @param value reikšmė.
     * @return Grąžinama atvaizdžio poros reikšmė.
     */
    V put(K key, V value);

    /**
     * Grąžinama atvaizdžio poros reikšmė.
     *
     * @param key raktas.
     * @return Grąžinama atvaizdžio poros reikšmė.
     */
    V get(K key);

    /**
     * Iš atvaizdžio pašalinama pora.
     *
     * @param key raktas.
     * @return Grąžinama pašalinta atvaizdžio poros reikšmė.
     */
    V remove(K key);
    
    
    /**
     * Atvaizdis papildomas nauja pora arba jau tuo atveju jei turi porą.
     * @param key raktas.
     * @param value reikšmė.
     * @return Atvaizdis papildomas nauja pora arba jau tuo atveju jei turi porą.
     */
    V putIfAbsent(K key, V value);

    /**
     * Patikrinama ar atvaizdyje egzistuoja pora su raktu key.
     *
     * @param key raktas.
     * @return true, jei atvaizdyje egzistuoja pora su raktu key, kitu atveju -
     * false
     */
    boolean contains(K key);
    
    /**
     * Patikrinama ar vaizdinyje egzistuoja objektas(-ai).
     * @param value reikšmė.
     * @return jei atvaizdyje egzistuoja objektai - true, kitu atveju - false.
     */
    boolean containsValue(Object value);
    
    /**
     * Grąžinami visi esantys reikšmių raktai.
     * @return Grąžinami visi esantys reikšmių raktai.
     */
    Set<K> keySet();
    
}
