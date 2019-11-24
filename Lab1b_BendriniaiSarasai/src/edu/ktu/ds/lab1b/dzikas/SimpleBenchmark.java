/** @author Eimutis Karčiauskas, KTU IF Programų inžinerijos katedra, 2014 09 23
 *
 * Tai yra darbo su sąrašais greitaveikos tyrimo klasė.
 * Pavyzdyje pateiktas rikiavimo metodų tyrimas.
 * Tyrimo metu pateiktais metodais naudojamasi kaip šablonais,
 * išbandant įvairius rūšiavimo aspektus.
 *  IŠSIAIŠKINKITE metodų sudarymą, jų paskirtį.
 *  SUDARYKITE sąrašo peržiūros antišablono efektyvumo tyrimą.
 *  PASIRINKITE savo objektų klasę ir sudarykite jų generavimo metodą.
 *************************************************************************** */
package edu.ktu.ds.lab1b.dzikas;

import edu.ktu.ds.lab1b.util.Ks;
import edu.ktu.ds.lab1b.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Collection;
import java.util.Locale;
import java.util.Random;
import java.util.AbstractCollection;
import java.util.List;


public class SimpleBenchmark {

    Char[] chars;
    LinkedList<Char> charSeries = new LinkedList<>();
    Random rg = new Random();  // atsitiktinių generatorius
    int[] counts = {2_000, 4_000, 8_000, 16_000};
//    pabandykite, gal Jūsų kompiuteris įveiks šiuos eksperimentus
//    paieškokite ekstremalaus apkrovimo be burbuliuko metodo
//    static int[] counts = {10_000, 20_000, 40_000, 80_000};

    void generateChars(int count) {
        String[][] Names = {
            {"Cavalier", "Guardian", "Sentinel", "Knight", "Sword master"},
            {"Mercenary", "Fencer", "Duelist", "Holy knight", "Crusader", "Templar"},
            {"Inquisitor", "Blackguard", "Hexblade"},
            {"Berserker", "Gladiator", "Viking", "Barbarian"},
            {"Yojimbo", "Kensei", "Myrmidon", "General", "Marshal"},
            {"Outlaw", "Soldier", "Lord", "King", "Fallen knight"},
            {"Witch", "Warlock", "Magicus", "Conjurer", "Sorcerer", "Spy"}
        };
        Char[] chars = new Char[count];
        String[] typ = {"Tank","Mage","Healer","Assassin", "Ranger"};
        Random rnd = new Random();
        rnd.setSeed(System.nanoTime());
        for (int i = 0; i < count; i++) {
            int first = rnd.nextInt(Names.length);
            boolean freshness = rnd.nextBoolean();
            int second = rnd.nextInt(Names[first].length - 1) + 1;
            int third = rnd.nextInt(typ.length);
            
            chars[i] = new Char(Names[first][second], typ[third],
                        (300 + rnd.nextDouble() * 1000),
                        4 + rnd.nextDouble() * 10,
                        500 + rnd.nextInt(4000),
                        freshness);                             
        }
        
        Collections.shuffle(Arrays.asList(chars));
        charSeries.clear();
        for (Char c : chars) {
            charSeries.add(c);
        }
    }

    void generateAndExecute(int elementCount) {
// Paruošiamoji tyrimo dalis
        long t0 = System.nanoTime();
        generateChars(elementCount);
        LinkedList<Char> charSeries2 = charSeries.clone();
        LinkedList<Char> charSeries3 = charSeries.clone();
        LinkedList<Char> charSeries4 = charSeries.clone();
        long t1 = System.nanoTime();
        System.gc();
        System.gc();
        System.gc();
        long t2 = System.nanoTime();
//  Greitaveikos bandymai ir laiko matavimai
        charSeries.sortSystem();
        long t3 = System.nanoTime();
        charSeries2.sortSystem(Char.byPrice);
        long t4 = System.nanoTime();
        charSeries3.sortBuble();
        long t5 = System.nanoTime();
        charSeries4.sortBuble(Char.byPrice);
        long t6 = System.nanoTime();
        Ks.ouf("%7d %7.4f %7.4f %7.4f %7.4f %7.4f %7.4f \n", elementCount,
                (t1 - t0) / 1e9, (t2 - t1) / 1e9, (t3 - t2) / 1e9,
                (t4 - t3) / 1e9, (t5 - t4) / 1e9, (t6 - t5) / 1e9);
    }

    void execute() {
//        long memTotal = Runtime.getRuntime().totalMemory();
//        Ks.oun("memTotal= " + memTotal);      
//        // Pasižiūrime kaip generuoja automobilius (20) vienetų)
//        generateChars(20);
//        for (Char c : charSeries) {
//            Ks.oun(c);
//        }
//        Ks.oun("1 - Pasiruošimas tyrimui - duomenų generavimas");
//        Ks.oun("2 - Pasiruošimas tyrimui - šiukšlių surinkimas");
//        Ks.oun("3 - Rūšiavimas sisteminiu greitu būdu be Comparator");
//        Ks.oun("4 - Rūšiavimas sisteminiu greitu būdu su Comparator");
//        Ks.oun("5 - Rūšiavimas List burbuliuku be Comparator");
//        Ks.oun("6 - Rūšiavimas List burbuliuku su Comparator");
//        Ks.ouf("%6d %7d %7d %7d %7d %7d %7d \n", 0, 1, 2, 3, 4, 5, 6);
//        for (int n : counts) {
//            generateAndExecute(n);
//        }
        
        
        Ks.oun("Math.pow(x, 1.0/3)	<->	Math.cbrt(x)");
     

        
        int x = 100;
        long t7 = System.nanoTime();
       Math.pow(x, 1.0/3);           
        long t8 = System.nanoTime();
       Math.cbrt(x);
        long t9 = System.nanoTime();
        Ks.ouf("    %6f                    %7f \n", (t8-t7) / 1e9, (t9-t8) / 1e9);

        Ks.oun("***********************************************");
        
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        
        long t10 = System.nanoTime();
        long t11 = System.nanoTime();
        long t12 = System.nanoTime();
        
        for (int i = 1; i < 8; i++) {
            arrayList = new ArrayList<Integer>();
            linkedList = new LinkedList<Integer>();
            x = (int)Math.pow(10, i);
            for (int t = 0; t < x; t++) {
                arrayList.add(1);
                linkedList.add(1);
            }
            t10 = System.nanoTime();
            arrayList.containsAll(arrayList);
            t11 = System.nanoTime();
            linkedList.containsAll(linkedList);
            t12 = System.nanoTime();
            Ks.oun("ArrayList<Integer>	<->	LinkedList<Integer>");
            Ks.ouf("    %6f                    %7f - list size: %d\n", (t11-t10) / 1e9, (t12-t11) / 1e9, x);      
        }

        

 
    }

    public static void main(String[] args) {
        // suvienodiname skaičių formatus pagal LT lokalę (10-ainis kablelis)
        Locale.setDefault(new Locale("LT"));
        new SimpleBenchmark().execute();
    }
}
