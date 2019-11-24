/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ktu.ds.lab1b.dzikas;

import edu.ktu.ds.lab1b.util.*;
import java.util.Random;


public class CharList extends ParsableList<Char>  {
    public CharList(){
        super();
    }   
    
    public CharList(int count) {
        super();
        String[][] Names = {
            {"Cavalier", "Guardian", "Sentinel", "Knight", "Sword master"},
            {"Mercenary", "Fencer", "Duelist", "Holy knight", "Crusader", "Templar"},
            {"Inquisitor", "Blackguard", "Hexblade"},
            {"Berserker", "Gladiator", "Viking", "Barbarian"},
            {"Yojimbo", "Kensei", "Myrmidon", "General", "Marshal"},
            {"Outlaw", "Soldier", "Lord", "King", "Fallen knight"},
            {"Witch", "Warlock", "Magicus", "Conjurer", "Sorcerer", "Spy"}
        };
        
        String[] typ = {"Tank","Mage","Healer","Assassin", "Ranger"};
        Random rnd = new Random();
        rnd.setSeed(System.nanoTime());
        for (int i = 0; i < count; i++) {
            int first = rnd.nextInt(Names.length);
            boolean freshness = rnd.nextBoolean();
            int second = rnd.nextInt(Names[first].length - 1) + 1;
            int third = rnd.nextInt(typ.length);
            
            add(new Char(Names[first][second], typ[third],
                        (300 + rnd.nextDouble() * 1000),
                        4 + rnd.nextDouble() * 10,
                        500 + rnd.nextInt(4000),
                        freshness));                             
        }
    }
}
