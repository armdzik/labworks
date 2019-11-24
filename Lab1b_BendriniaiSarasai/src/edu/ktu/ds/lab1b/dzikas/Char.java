package edu.ktu.ds.lab1b.dzikas;

import edu.ktu.ds.lab1b.util.*;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class Char implements Parsable <Char>{

        private String name;
        private String type;
        private double price;
        private double size; // metres
        private int mana; 
        private boolean freshness;

        public Char(String data){
            parse(data);
        }
        

        public Char(String name, String type, double price, double size, int mana, boolean freshness) {
            this.name = name;
            this.type = type;
            this.price = price;
            this.size = size;
            this.mana = mana;
            this.freshness = freshness;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getSize() {
            return size;
        }

        public void setSize(double size) {
            this.size = size;
        }

        public int getMana() {
            return mana;
        }

        public void setMana(int mana) {
            this.mana = mana;
        }

        public boolean isFreshness() {
            return freshness;
        }

        public void setFreshness(boolean freshness) {
            this.freshness = freshness;
        }
        
        
        @Override
        public void parse(String dataString) {
            try {   // ed - tai elementarūs duomenys, atskirti tarpais
                Scanner ed = new Scanner(dataString);
                name = ed.next();
                type = ed.next();
                price = ed.nextDouble();
                size = ed.nextDouble();
                mana = ed.nextInt();
                freshness = ed.nextBoolean();
            } catch (InputMismatchException e) {
                Ks.ern("Blogas duomenų formatas apie auto -> " + dataString);
            } catch (NoSuchElementException e) {
                Ks.ern("Trūksta duomenų apie auto -> " + dataString);
            }
        }

        @Override
        public int compareTo(Char charr) {
            double otherPrice = charr.getPrice();
            if (price < otherPrice) {
                return -1;
            }
            if (price > otherPrice) {
                return +1;
            }
            return 0;
        }

        public final static Comparator<Char> byTypeAndName
                = new Comparator<Char>() {
            @Override
            public int compare(Char charr1, Char charr2) {
                // 
                int cmp = charr1.getType().compareTo(charr2.getType());
                if (cmp != 0) {
                    return cmp;
                }
                return charr1.getName().compareTo(charr2.getName());
            }
        };
        public final static Comparator byPrice = new Comparator() {
            // sarankiškai priderinkite prie generic interfeiso ir Lambda funkcijų
            @Override
            public int compare(Object obj1, Object obj2) {
                double price1 = ((Char) obj1).getPrice();
                double price2 = ((Char) obj2).getPrice();
                // didėjanti tvarka, pradedant nuo mažiausios
                if (price1 < price2) {
                    return -1;
                }
                if (price1 > price2) {
                    return 1;
                }
                return 0;
            }
        };
        public final static Comparator bySizeAndPrice = new Comparator() {
            // sarankiškai priderinkite prie generic interfeiso ir Lambda funkcijų
            @Override
            public int compare(Object obj1, Object obj2) {
                Char c1 = (Char) obj1;
                Char c2 = (Char) obj2;
                // 
                if (c1.getSize() < c2.getSize()) {
                    return 1;
                }
                if (c1.getSize() > c2.getSize()) {
                    return -1;
                }
                if (c1.getPrice() < c2.getPrice()) {
                    return 1;
                }
                if (c1.getPrice() > c2.getPrice()) {
                    return -1;
                }
                return 0;
            }
        };
        
        @Override
        public String toString() {  // surenkama visa reikalinga informacija
            String isFreshness = freshness ? "yes" : "cancel";
            return String.format("%-20s %-12s %8.1f %8.1f %8d %s",
                    name, type, price, size, mana, isFreshness);
        }

}
