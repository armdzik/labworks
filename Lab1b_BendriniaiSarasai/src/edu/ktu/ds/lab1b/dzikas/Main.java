package edu.ktu.ds.lab1b.dzikas;

import edu.ktu.ds.lab1b.util.*;

public class Main {

    LinkedList<Char> list2;
    LinkedList<Char> list;
    Char test = new Char("Knight tank 600 10 2000 false");
    Char test2 = new Char("Soldier ranger 450 20 4000 true");
    Char test3 = new Char("Wizard mage 500 5 5000 true");

    public static void main(String[] args) {
        new Main().execute();
    }

    public void execute() {
        list = new CharList(5);
        list2 = new CharList(6);
        //addingByIndex();
        //removingByIndex();
        //setByIndex();
        //addFirst();
        //addLast();
        //testContains();
        subListTest();
        //
    }

    void addingByIndex() {
        System.out.println("*ADDING BY INDEX*");
        System.out.println("*Before*");
        for (Char p : list) {
            Ks.oun(p);
        }
        list.add(0, test);
        list.add(3, test2);

        System.out.println("*After*");

        for (Char p : list) {
            Ks.oun(p);
        }
        System.out.println("");
    }

    void removingByIndex() {
        System.out.println("*REMOVING BY INDEX*");

        System.out.println("*Before*");
        for (Char p : list) {
            Ks.oun(p);
        }

        list.remove(1);
        list.remove(3);

        System.out.println("*After*");
        for (Char p : list) {
            Ks.oun(p);
        }

        System.out.println("");
    }

    void setByIndex() {
        System.out.println("*SET BY INDEX*");

        System.out.println("*Before*");
        for (Char p : list) {
            Ks.oun(p);
        }

        list.set(2, test3);
        list.set(1, test2);

        System.out.println("*After*");
        for (Char p : list) {
            Ks.oun(p);
        }

        System.out.println("");
    }

    void addFirst() {
        System.out.println("*ADDING FIRST*");

        System.out.println("*Before*");
        for (Char p : list2) {
            Ks.oun(p);
        }

        list2.addFirst(test);

        System.out.println("*After*");
        for (Char p : list2) {
            Ks.oun(p);
        }

        System.out.println("");
    }

    void addLast() {
        System.out.println("*ADDING LAST*");

        System.out.println("*Before*");
        for (Char p : list2) {
            Ks.oun(p);
        }

        list2.addLast(test);

        System.out.println("*After*");
        for (Char p : list2) {
            Ks.oun(p);
        }

        System.out.println("");
    }

    void subListTest() {
        System.out.println("*ADDING LAST*");
        System.out.println("*Before*");
        for (Char p : list) {
            Ks.oun(p);
        }
        List<Char> temp = list.subList(2, 4);

        System.out.println("*After*");
        for (int i = 0; i < temp.size(); i++) {
            Ks.oun(temp.get(i));
        }
        System.out.println("");
    }

    void testContains() {
        System.out.println("*CONTAINS TEST*");
        boolean found = false;
        Char test = new Char("Same1 char1 500 10 2000 false");
        Char test2 = new Char("Same2 char2 600 20 3000 false");
        Char test3 = new Char("Same3 char3 700 30 4000 false");
        list2.add(0, test);
        list2.add(1, test2);
        list2.add(2, test3);
        System.out.println("*Element is - same3 char3 *");
        for (Char p : list2) {
            Ks.oun(p);

        }
        System.out.println(test3);
        System.out.println("CONTAINS : " + list2.contains(test3));
        System.out.println("");
    }

}
