package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

import java.util.ArrayList;

/**
 *
 * @author andrii
 */
public class PrefixMatches {

    private Trie trie;
    private int size;

    public PrefixMatches(Trie trie) {
        this.trie = trie;
        this.size = trie.size();
    }

    public int load(String... strings) {
        int counter = 0;
        for (int i = 0; i < strings.length; i++) {
            String[] arrayOfWords = strings[i].split(" ");
            for (String s: arrayOfWords) {
                if (s.length() > 2) {
                    Tuple tp = new Tuple(s, s.length());
                    trie.add(tp);
                    counter++;
                }
            }
        }
        size += counter;
        return counter;
    }

    public boolean contains(String word) {
        return trie.contains(word);
    }

    public boolean delete(String word) {
        return trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        if (pref.length() >= 2) {
            return trie.wordsWithPrefix(pref);
        }
        throw new IllegalArgumentException();
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        if (pref.length() < 2) {
            throw new IllegalArgumentException();
        }

        int ind;
        if (pref.length() == 2) {
            ind = pref.length() + 1;
        } else {
            ind = k + pref.length();
        }

        Iterable<String> words = trie.wordsWithPrefix(pref);
        ArrayList<String> resultWords = new ArrayList<String>();

        for (String word: words) {
            if (word.length() < ind) {
                resultWords.add(word);
            }
        }
        return resultWords;
    }

    public int size() {
        return size;
    }
}
