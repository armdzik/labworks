/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ktu.ds.lab3.Dzikas;

import edu.ktu.ds.lab3.utils.HashType;
import edu.ktu.ds.lab3.utils.Ks;
import edu.ktu.ds.lab3.utils.ParsableHashMap;
import edu.ktu.ds.lab3.utils.ParsableMap;
import java.util.Locale;
import java.util.Set;

/**
 *
 * @author Arminas
 */
public class ManualTest {
     public static void main(String[] args) {
        Locale.setDefault(Locale.US); // suvienodiname skaičių formatus
        executeTest();
    }

    public static void executeTest() {

        Book b1 = new Book("JK Hr 1500 35 15");
        Book b2 = new Book("ImanuelKant Critique 1999 55 65");
        Book b3 = new Book("Nietche Zaratrusta 1999 55 65");
        Book b4 = new Book("Aristotelis NichomakoEtika 2001 70 30");
        Book b5 = new Book.Builder().buildRandom();
        Book b6 = new Book("Dekartes Metafizika 1998 950 77");
        Book b7 = new Book("Platonas Valstybė 2007 364 85.3");

        // Raktų masyvas
        String[] booksIds = {"TA156", "TA102", "TA178", "TA171", "TA105", "TA106", "TA107", "TA108"};
        int id = 0;
        ParsableMap<String, Book> booksMap
                = new ParsableHashMap<>(String::new, Book::new, HashType.DIVISION);
       

        // Reikšmių masyvas
        Book[] books = {b1, b2, b3, b4, b5, b6, b7};
        for (Book b : books) {
            booksMap.put(booksIds[id++], b);
        }
        booksMap.println("Porų išsidėstymas atvaizdyje pagal raktus");
        Ks.oun("Ar egzistuoja pora atvaizdyje?");
        Ks.oun(booksMap.contains(booksIds[0]));
        Ks.oun(booksMap.contains(booksIds[7]));
        Ks.oun("Pašalinamos poros iš atvaizdžio:");
        Ks.oun(booksMap.remove(booksIds[4]));
        Ks.oun(booksMap.remove(booksIds[7]));
        Ks.oun(booksMap.remove("TA156"));
        booksMap.println("Porų išsidėstymas atvaizdyje pagal raktus");
        Ks.oun("Atliekame porų paiešką atvaizdyje:");
        Ks.oun(booksMap.get(booksIds[2]));
        Ks.oun(booksMap.get(booksIds[7]));
        Ks.oun("Išspausdiname atvaizdžio poras String eilute:");
        Ks.ounn(booksMap);
        //----------------------------------------
        Ks.ounn("Tikrinama ar tokia reikšmė yra:");
        System.out.println(booksMap.containsValue(""));
        System.out.println(booksMap.containsValue(b7));
        Ks.ounn(booksMap);
        //----------------------------------------
        Ks.ounn("Įrašo vertę su raktu, o jei ją grąžina:");
        Book b10 = new Book("Sokratas Gorgijas 1999 55 65");
        Ks.oun("//---------------");
        Ks.oun(booksMap.putIfAbsent("TA178", b7));
        Ks.oun(booksMap.putIfAbsent("TA78",b10)); //-----Įrašoma šiuo atveju
        Ks.oun("//---------------");
        Ks.ounn(booksMap);
        //----------------------------------------
        Ks.ounn("Laisva vieta:");
        Ks.oun(booksMap.numberOfEmpties());
        ParsableMap<String, Book> booksMap2
           = new ParsableHashMap<>(null, null, HashType.DIVISION);
        Ks.oun(booksMap2.numberOfEmpties());
        //Papildome vienu
        Ks.ounn("Duomenys2:");
        booksMap2.put("TA89", b7);
        Ks.ounn(booksMap2);
        Ks.oun(booksMap2.numberOfEmpties());
        //----------------------------------------
        Ks.ounn("Raktai:");
            Set<String> key2 = (Set<String>)booksMap.keySet();
        for (String string : key2) {
            System.out.println(string);
        }
        Ks.ounn("Raktai pratęsimas pagal duomenys2:");
            Set<String> key = (Set<String>)booksMap2.keySet();
        for (String string : key) {
            System.out.println(string);
        }
        //----------------------------------------
        Ks.ounn("||---Keičiame reikšmes nauja jei yra senoji:---||");
        Ks.ounn("Keičiama reikšmė: " + b10.toString());
        Ks.ounn("Nauja reikšmė: " + b7.toString());
        booksMap.replaceAll(b10, b7);
        Ks.ounn("");
        Ks.ounn(booksMap);
        Ks.ounn("");
        Ks.ounn("Keičiama reikšmė: " + b7.toString());
        Ks.ounn("Nauja reikšmė: " + b10.toString());
        booksMap.replaceAll(b7, b10);
        Ks.ounn("");
        Ks.ounn(booksMap);
        
        ParsableMapOa<String, Book> booksMap4
                = new ParsableMapOa<>(String::new, Book::new, HashType.DIVISION);           
        int id2 = 0;
        for (Book b : books) {
            booksMap4.put(booksIds[id2++], b);
        }
        Ks.ounn("");
        Ks.ounn(booksMap4);
        booksMap4.remove("TA171");
        Ks.ounn("");
        Ks.ounn(booksMap4);
    }
}
