
package ua.edu.ucu.autocomplete;

import static org.hamcrest.Matchers.containsInAnyOrder;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import ua.edu.ucu.tries.RWayTrie;
import ua.edu.ucu.tries.Tuple;

/**
 *
 * @author Andrii_Rodionov
 */
public class PrefixMatchesITTest {

    private PrefixMatches pm;

    @Before
    public void init() {
        pm = new PrefixMatches(new RWayTrie());
        pm.load("abc", "abce", "abcd", "abcde", "abcdef");
    }


//  simple tests to test RWayTrie
    @Test
    public void testRWayTrieAdd() {
        RWayTrie tr = new RWayTrie();
        Tuple t = new Tuple("sea", 2);
        tr.add(t);
        boolean actResult = tr.contains("sea");
        assertTrue(actResult);
    }

    @Test
    public void testRWayTrieDelete() {
        RWayTrie tr = new RWayTrie();
        Tuple t = new Tuple("hello", 2);
        tr.add(t);
        tr.delete("hello");
        boolean actResult = tr.contains("hello");
        assertFalse(actResult);
    }

    @Test
    public void testWordsWithPrefix_String() {
        String pref = "ab";

        Iterable<String> result = pm.wordsWithPrefix(pref);

        String[] expResult = {"abc", "abce", "abcd", "abcde", "abcdef"};
        System.out.println();

        assertThat(result, containsInAnyOrder(expResult));
    }

    @Test
    public void testWordsWithPrefix_String_and_K() {
        String pref = "abc";
        int k = 3;

        Iterable<String> result = pm.wordsWithPrefix(pref, k);

        String[] expResult = {"abc", "abce", "abcd", "abcde"};

        assertThat(result, containsInAnyOrder(expResult));
    }

}
