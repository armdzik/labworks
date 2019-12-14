/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ktu.ds.lab3.Dzikas;

import edu.ktu.ds.lab3.utils.Ks;
import edu.ktu.ds.lab3.utils.Parsable;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Arminas
 */
public class Book implements Parsable<Book> {
    private static final int minYear = 1994;
    private static final int currentYear = LocalDate.now().getYear();
    private static final double minPrice = 10.0;
    private static final double maxPrice = 1200.0;
    private static final String idCode = "PS";   //  ***** nauja
    private static int serNr = 100;               //  ***** nauja

    
    
    private String writer = "";
    private String bookName = "";
    private int year = -1;
    private int edition = -1;
    private double price = -1.0;
    public Book(){
    }
    public Book(String writer, String bookName, int year, int edition, double price){
        this.writer = writer;
        this.bookName = bookName;
        this.year = year;
        this.edition = edition;
        this.price = price;
        validate();
    }
       
       public Book(String dataString) {
        this.parse(dataString);
        validate();
    }
    public Book(Builder builder) {
        this.writer = builder.writer;
        this.bookName = builder.bookName;
        this.year = builder.year;
        this.edition = builder.edition;
        this.price = builder.price;
        validate();
    }
    
    private void validate() {
        String errorType = "";
        if (year < minYear || year > currentYear) {
            errorType = "Netinkami leidimo metai, turi būti ["
                    + minYear + ":" + currentYear + "]";
        }
        if (price < minPrice || price > maxPrice) {
            errorType += " Kaina už leistinų ribų [" + minPrice
                    + ":" + maxPrice + "]";
        }
        if (!errorType.isEmpty()) {
            Ks.ern("Knyga yra blogai sugeneruota: " + errorType);
        }
    }
    
    @Override
    public final void parse(String data)
    {
        try {//ed-tai elementarūs duomenys atskirti tarpais
            Scanner ed = new Scanner(data);
            writer = ed.next();
            bookName = ed.next();
            year = ed.nextInt();
            edition = ed.nextInt();
            setPrice(ed.nextDouble());
        }
        catch (InputMismatchException e) {
            Ks.ern("Blogas duomenų formatas -> " + data);
        } catch (NoSuchElementException e) {
            Ks.ern("Trūksta duomenų apie leidinį -> " + data);
        }
    }
    
     @Override
    public String toString() {
        return writer + "_" + bookName + ":" + year + " " + getEdition() + " "
                + String.format("%4.1f", price);
    }
    
    public void setWriter(String writer) {
        this.writer = writer;
    }
    
    public String getWriter(){
        return writer;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
        @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.writer);
        hash = 29 * hash + Objects.hashCode(this.bookName);
        hash = 29 * hash + this.year;
        hash = 29 * hash + this.edition;
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        return hash;
    }
    
     @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (this.year != other.year) {
            return false;
        }
        if (this.edition != other.edition) {
            return false;
        }
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (!Objects.equals(this.writer, other.writer)) {
            return false;
        }
        if (!Objects.equals(this.bookName, other.bookName)) {
            return false;
        }
        return true;
    }
    
    public static class Builder {

        private final static Random RANDOM = new Random(1949);  // Atsitiktinių generatorius
        private final static String[][] BOOKS = { // galimų knygų masyvas
                {"Hr", "ImanuelKant", "323", "Critique", "13"},
                {"Nietche", "NichomakoEtika", "Metafizika", "51", "NichomakoEtika", "96"},
                {"Aristotelis", "Valstybė", "Zaratrusta"}
        };

          private String writer = "";
          private String bookName = "";
          private int year = -1;
          private int edition = -1;
          private double price = -1.0;

        public Book build() {
            return new Book(this);
        }

        public Book buildRandom() {
            int ma = RANDOM.nextInt(BOOKS.length);        // rašytojo indeksas  0..
            int mo = RANDOM.nextInt(BOOKS[ma].length - 1) + 1;// knygos indeksas 1..
            return new Book(BOOKS[ma][0],
                    BOOKS[ma][mo],
                    1995 + RANDOM.nextInt(15),// metai
                    60 + RANDOM.nextInt(22),// leidimas
                    800 + RANDOM.nextDouble() * 0);// kaina
        }

        public Builder year(int year) {
            this.year = year;
            return this;
        }

        public Builder writer(String writer) {
            this.writer = writer;
            return this;
        }

        public Builder bookName(String bookName) {
            this.bookName = bookName;
            return this;
        }

        public Builder edition(int edition) {
            this.edition = edition;
            return this;
        }

        public Builder price(double price) {
            this.price = price;
            return this;
        }
    }
    
}
