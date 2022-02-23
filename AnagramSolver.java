//AnagramSolver
//Creates class AnagramSolver that generates an in-order list of 
//all possible anagrams of a user-prompted phrase from a dictionary of words

import java.util.*;

public class AnagramSolver {

    private Map<String, LetterInventory> dictionary;
    private List<String> dictOrder;
    
    //considers possible words and their letters,
    //from a non-empty list of Strings dictionary that 
    //has no duplicates, also keeps track of the order of words
    public AnagramSolver(List<String> dictionary) {
        dictOrder = dictionary;
        this.dictionary = new HashMap<>();
        for (String word : dictionary) {
            this.dictionary.put(word, new LetterInventory(word));
        }
    }
    
    
    //prints out an in-order list of anagrams and anagram phrases
    //containing at most int max words that can be created from String text
    //or unlimited words if max words is set to 0
    //throws an IllegalArgumentException if max words is less than zero
    public void print(String text, int max) {
        if (max < 0) {
            throw new IllegalArgumentException("max words cannot be less than zero");
        }
        LetterInventory baseWord = new LetterInventory(text);
        print(max, new LinkedList<>(), baseWord, prune(baseWord)); 
    }
    
    //prints out an in-order List<String> anagrams of available anagram phrases with a maximum of int max
    //words from a LetterInventory remaining of available letters, using a pruned list of words
    private void print(int max, List<String> anagrams, LetterInventory remaining, List<String> pruned) {
        boolean maxCheck = (max == 0 || anagrams.size() <= max);
        if (remaining.isEmpty() && maxCheck) {
            System.out.println(anagrams.toString());
        } else if (maxCheck) {
            for (String word : dictOrder) {
                if (pruned.contains(word)) {
                    LetterInventory subtracted = remaining.subtract(dictionary.get(word));
                    if (subtracted != null) {
                    //choose
                        anagrams.add(word);
                    //explore
                        print(max, anagrams, subtracted, pruned);
                    //unchoose
                        anagrams.remove(anagrams.size() - 1);
                    }
                }
            }
        }
    }
    
    //prunes the given dictionary creating a list of all the words that
    //only contain letters present in LetterInventory remaining and returns it    
    private List<String> prune(LetterInventory remaining) {
        List<String> pruned = new LinkedList<>();
        for (String word: dictOrder) {
            LetterInventory subtracted = remaining.subtract(dictionary.get(word));
            if (subtracted != null) {
                pruned.add(word);
            }
        }
        return pruned;
    }
}
