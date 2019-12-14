/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ktu.ds.lab3.Dzikas;

import edu.ktu.ds.lab3.gui.ValidationException;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

/**
 *
 * @author Arminas
 */
public class BooksGenerator {
    private static final String ID_CODE = "TA";      //  ***** Nauja
    private static int serNr = 12;               //  ***** Nauja

    private Book[] books;
    private String[] keys;
    private int currentBookIndex = 0, currentBookIdIndex = 0;
    
    public static Book[] generateShuffleBooks(int size) {
        Book[] books = IntStream.range(0, size)
                .mapToObj(i -> new Book.Builder().buildRandom())
                .toArray(Book[]::new);
        Collections.shuffle(Arrays.asList(books));
        return books;
    }
    
    public static String[] generateShuffleIds(int size) {
        String[] keys = IntStream.range(0, size)
                .mapToObj(i -> ID_CODE + (serNr++))
                .toArray(String[]::new);
        Collections.shuffle(Arrays.asList(keys));
        return keys;
    }
    
    public Book[] generateShuffleBooksAndIds(int setSize,
            int setTakeSize) throws ValidationException {

        if (setTakeSize > setSize) {
            setTakeSize = setSize;
        }
        books = generateShuffleBooks(setSize);
        keys = generateShuffleIds(setSize);
        this.currentBookIndex = setTakeSize;
        return Arrays.copyOf(books, setTakeSize);
    }
    
      // Imamas po vienas elementas iš sugeneruoto masyvo. Kai elementai baigiasi sugeneruojama
    // nuosava situacija ir išmetamas pranešimas.
    public Book getBook() {
        if (books == null) {
            throw new ValidationException("booksNotGenerated");
        }
        if (currentBookIndex < books.length) {
            return books[currentBookIndex++];
        } else {
            throw new ValidationException("allSetStoredToMap");
        }
    }

    public String getBookId() {
        if (keys == null) {
            throw new ValidationException("booksIdsNotGenerated");
        }
        if (currentBookIdIndex < keys.length) {
            return keys[currentBookIdIndex++];
        } else {
            throw new ValidationException("allKeysStoredToMap");
        }
    }
}
