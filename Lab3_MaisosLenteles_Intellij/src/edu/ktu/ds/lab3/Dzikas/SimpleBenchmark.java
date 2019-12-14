/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ktu.ds.lab3.Dzikas;

import edu.ktu.ds.lab3.Dzikas.Book;
import edu.ktu.ds.lab3.Dzikas.BooksGenerator;
import edu.ktu.ds.lab3.demo.Timekeeper;
import edu.ktu.ds.lab3.gui.ValidationException;
import edu.ktu.ds.lab3.utils.HashMap;
import edu.ktu.ds.lab3.utils.HashType;
import edu.ktu.ds.lab3.utils.Ks;
import edu.ktu.ds.lab3.utils.ParsableHashMap;
import edu.ktu.ds.lab3.utils.ParsableMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arminas
 */
public class SimpleBenchmark {

    public static final String FINISH_COMMAND = "                               ";
    private static final ResourceBundle MESSAGES = ResourceBundle.getBundle("edu.ktu.ds.lab3.gui.messages");

    private final Timekeeper timekeeper;

    private final String[] BENCHMARK_NAMES = {/*"put", "put",*/ "rem", "rem"};
    private final int[] COUNTS = {10000, 20000, 40000, 80000};

    private final Queue<String> chainsSizes = new LinkedList<>();
    
    
    
    private final HashMap<String, String> vocabulary = new HashMap<String, String>();
    private final java.util.HashMap<String, String> vocabulary2 = new java.util.HashMap<String, String>();
    private final edu.ktu.ds.lab3.utils.HashMap<String,String> vocabulary4 = new edu.ktu.ds.lab3.utils.HashMap<String, String>();
    private final HashMapOa<String, String> vocabulary3 = new HashMapOa<String,String>();
    
    /**
     * For console benchmark
     */
    public SimpleBenchmark() {
        timekeeper = new Timekeeper(COUNTS);
    }

    /**
     * For Gui benchmark
     *
     * @param resultsLogger
     * @param semaphore
     */
    public SimpleBenchmark(BlockingQueue<String> resultsLogger, Semaphore semaphore) {
        semaphore.release();
        timekeeper = new Timekeeper(COUNTS, resultsLogger, semaphore);
    }

    public static void main(String[] args) {
        executeTest();
    }

    public static void executeTest() {
        // suvienodiname skaičių formatus pagal LT lokalę (10-ainis kablelis)
        Locale.setDefault(new Locale("LT"));
        Ks.out("Greitaveikos tyrimas:\n");
        new SimpleBenchmark().startBenchmark();
    }

    public void startBenchmark() {
        try {
            benchmark();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    public void benchmark() throws InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        try {
            chainsSizes.add(MESSAGES.getString("maxChainLength"));
            chainsSizes.add("   kiekis      " + BENCHMARK_NAMES[0] + "   " + BENCHMARK_NAMES[1]);
            for (int k : COUNTS) {
                vocabulary.clear();
                vocabulary2.clear();
                timekeeper.startAfterPause();
                timekeeper.start();
                long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
                read(vocabulary, null, null, 0, k);
                long usedMemory = runtime.totalMemory() - runtime.freeMemory() - usedMemoryBefore;
               // timekeeper.finish(BENCHMARK_NAMES[0]);
                String str = "   " + k + "       " + vocabulary.getMaxChainSize();
                usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
                    read(null, vocabulary2, null, 1, k);
                long usedMemory2 = runtime.totalMemory() - runtime.freeMemory() - usedMemoryBefore;
                //timekeeper.finish(BENCHMARK_NAMES[1]);
                read(null, null, vocabulary3, 2, k);
                str += "   " + "unknown";
                chainsSizes.add(str);
                Object[] set = vocabulary2.keySet().toArray();
                usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
                //Vieta šalinimui----------------------------------------------
                for (int i = 0; i < set.length; i++) {
                    vocabulary4.contains((String)set[i]);
                }
                long usedMemory3 = runtime.totalMemory() - runtime.freeMemory() - usedMemoryBefore;
                timekeeper.finish(BENCHMARK_NAMES[0]);
                usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
                set = vocabulary4.keySet().toArray();
                for (int i = 0; i < set.length; i++) {
                    vocabulary4.contains((String)set[i]);
                }
                long usedMemory4 = runtime.totalMemory() - runtime.freeMemory() - usedMemoryBefore;
                timekeeper.finish(BENCHMARK_NAMES[1]);
                timekeeper.seriesFinish();
                System.out.println("Memory consumption: 1) " /*+ usedMemory + " 2) " 
                    + usedMemory2 + " 3) " */+ usedMemory3 + " 2) " + usedMemory4);
            }

            StringBuilder sb = new StringBuilder();
            chainsSizes.forEach(p -> sb.append(p).append(System.lineSeparator()));
            timekeeper.logResult(sb.toString());
            timekeeper.logResult(FINISH_COMMAND);
        } catch (ValidationException e) {
            timekeeper.logResult(e.getMessage());
        }
    }
    
    public void read(HashMap structure,java.util.HashMap structure2, HashMapOa structure3, int turn, int counter)
    {
        int k = 0;
        try {
            Scanner in = new Scanner(new File("C:\\Users\\Antanas\\Desktop\\labasJava\\Labas3\\Lab3_MaisosLenteles\\data\\zodynas.txt"));
            while(in.hasNext())
            {
                String value = in.nextLine();
                if (counter != k) {
                    if(turn == 0)
                    structure.put(value, value);
                    else if(turn == 1)
                        structure2.put(value, value);
                    else
                        structure3.put(value, value);
                    k++;
                }
            }
            in.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SimpleBenchmark.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

