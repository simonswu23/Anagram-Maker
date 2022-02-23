//LetterInventory
//Constructs the object LetterInventory which keeps the count of the 
//letters (not case-sensitive) in a given String.

import java.util.*;

public class LetterInventory {
    private int[] letterCount; //an array containing the count of each letter
    private int size;   //the sum of total letters in inventory
    
    public static final int ALPHABET = 26;

    //Constructs an empty Letter Inventory  
    public LetterInventory() {
        this("");
    }
    
    //Constructs a letter inventory from a given String data
    public LetterInventory(String data) {
        letterCount = new int[ALPHABET];
        data = data.toLowerCase();
        for (int i = 0; i < data.length(); i++) {
            int ascii = (int) data.charAt(i);
            if (ascii >= (int) 'a' && ascii <= (int) 'a' + ALPHABET) {
                letterCount[ascii - (int) 'a']++; 
                size += 1;  
            }
        }        
    }

    //Checks if a char is a letter
    //Makes the letter lower case if it's capital
    //Returns the int ASCII of the letter
    //Throws IllegalArgumentException if the value isn't a letter
    private int inventoryCheck(char letter) {
         if ((int) letter < (int) 'A' || (int) letter > (int) 'Z' 
                && (int) letter < (int) 'a' || (int) letter > (int) 'z') {
             throw new IllegalArgumentException("Value must be a letter"); 
         } else if ((int) letter < (int) 'a') {
             return (int) letter - (int) 'A';   
         } else {
             return (int) letter - (int) 'a';
         }
    }
    
    //Checks how many times a char letter appears in the strings
    //Returns the int number of times it appears
    //throws IllegalArgumentException if the char letter isn't a letter
    public int get(char letter) {
        return letterCount[inventoryCheck(letter)];
    }
    
    //Changes the number of appearances of a letter in the LetterInventory
    //Sets the char letter to a specific int value
    //Throws IllegalArgumentException if the int value is negative
    //or if char letter isn't a letter
    public void set(char letter, int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Integer cannot be less than zero");
        } else {
            int difference = value - letterCount[inventoryCheck(letter)];
            size += difference;
            letterCount[inventoryCheck(letter)] = value;

        }
    }
    
    //Gets and returns the total int sum of the values in the LetterInventory
    public int size() {
        return size;
    }
    
    //Returns true if the LetterInventory is empty
    //and false if otherwise
    public boolean isEmpty() {
        return this.size() == 0;
    }
    
    //Sorts and prints out a String of all the letters and duplicates in the LetterInventory
    public String toString() {
        String sorted = "[";
        for (int i = 0; i < ALPHABET; i++) {
            for (int j = 0; j < letterCount[i]; j++) {
                sorted += (char) (i + (int) 'a');
            }
        }
        sorted += "]";
        return sorted;
    }
    
    //Constructs and returns a new LetterInventory and fills it in
    //with the sum of this LetterInventory and a passed LetterInventory other
    //also updates and sets the size of the new LetterInventory
    public LetterInventory add(LetterInventory other) {
        LetterInventory sum = new LetterInventory();
        for (int i = 0; i < ALPHABET; i++) {
            sum.set((char) (i + (int) 'a'), this.letterCount[i] + other.letterCount[i]);
        }
        return sum;
    }

    //Constructs and returns a new LetterInventory and fills it in
    //with the difference of this LetterInventory and a passed LetterInventory other
    //also updates and sets the size of the new LetterInventory
    //returns null if the new LetterInventory has any value less than zero
    public LetterInventory subtract(LetterInventory other) {
        LetterInventory subtraction = new LetterInventory();
        for (int i = 0; i < ALPHABET; i++) {
            int difference = this.letterCount[i] - other.letterCount[i];
            if (difference < 0) {
                return null;
            } else
                subtraction.set((char) (i + (int) 'a'), difference);         
            }
        return subtraction;
    }     
}
