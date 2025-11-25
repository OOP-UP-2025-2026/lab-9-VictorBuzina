package ua.opnu;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void testRemoveShorterStrings() {
        List<String> list = new ArrayList<>(Arrays.asList("four", "score", "and", "seven", "years", "ago"));
        Task.removeShorterStrings(list);
        assertEquals(Arrays.asList("score", "seven", "years"), list);

        List<String> odd = new ArrayList<>(Arrays.asList("a", "bb", "c"));
        Task.removeShorterStrings(odd);
        assertEquals(Arrays.asList("bb", "c"), odd);
    }

    @Test
    void testStutter() {
        List<String> list = new ArrayList<>(Arrays.asList("how", "are", "you?"));
        Task.stutter(list);
        assertEquals(Arrays.asList("how", "how", "are", "are", "you?", "you?"), list);
    }

    @Test
    void testSwitchPairs() {
        List<String> list = new ArrayList<>(Arrays.asList("four", "score", "and", "seven", "years", "ago"));
        Task.switchPairs(list);
        assertEquals(Arrays.asList("score", "four", "seven", "and", "ago", "years"), list);

        List<String> odd = new ArrayList<>(Arrays.asList("to", "be", "or"));
        Task.switchPairs(odd);
        assertEquals(Arrays.asList("be", "to", "or"), odd);
    }

    @Test
    void testRemoveDuplicates() {
        List<String> list = new ArrayList<>(Arrays.asList("be", "be", "is", "not", "or", "question", "that", "the", "to", "to"));
        Task.removeDuplicates(list);
        assertEquals(Arrays.asList("be", "is", "not", "or", "question", "that", "the", "to"), list);
    }

    @Test
    void testMarkLength4() {
        List<String> list = new ArrayList<>(Arrays.asList("this", "is", "lots", "of", "fun", "for", "every", "Java", "programmer"));
        Task.markLength4(list);
        assertEquals(Arrays.asList("****", "this", "is", "****", "lots", "of", "fun", "for", "every", "****", "Java", "programmer"), list);
    }

    @Test
    void testIsPalindrome() {
        Queue<Integer> q = new LinkedList<>(Arrays.asList(3, 8, 17, 9, 17, 8, 3));
        assertTrue(Task.isPalindrome(q));
        assertEquals(Arrays.asList(3, 8, 17, 9, 17, 8, 3), new ArrayList<>(q)); // Check queue restored

        Queue<Integer> q2 = new LinkedList<>(Arrays.asList(3, 8, 17, 9, 4, 17, 8, 3));
        assertFalse(Task.isPalindrome(q2));
    }

    @Test
    void testReorder() {
        Queue<Integer> q = new LinkedList<>(Arrays.asList(1, 2, -2, 4, -5, 8, -8, 12, -15, 23));
        Task.reorder(q);
        // Очікується: від'ємні, потім додатні (з урахуванням алгоритму сортування стеком)
        assertEquals(Arrays.asList(-15, -8, -5, -2, 1, 2, 4, 8, 12, 23), new ArrayList<>(q));
    }

    @Test
    void testRearrange() {
        Queue<Integer> q = new LinkedList<>(Arrays.asList(3, 5, 4, 17, 6, 83, 1, 84, 16, 37));
        Task.rearrange(q);
        assertEquals(Arrays.asList(4, 6, 84, 16, 3, 5, 17, 83, 1, 37), new ArrayList<>(q));
    }

    @Test
    void testMaxLength() {
        Set<String> set = new HashSet<>(Arrays.asList("one", "two", "three"));
        assertEquals(5, Task.maxLength(set));
        assertEquals(0, Task.maxLength(new HashSet<>()));
    }

    @Test
    void testRemoveEvenLength() {
        Set<String> set = new HashSet<>(Arrays.asList("foo", "buzz", "bar", "fork", "bort", "spoon", "!", "dude"));
        Task.removeEvenLength(set);
        assertTrue(set.contains("foo"));
        assertTrue(set.contains("bar"));
        assertTrue(set.contains("spoon"));
        assertTrue(set.contains("!"));
        assertFalse(set.contains("buzz"));
    }

    @Test
    void testNumInCommon() {
        List<Integer> l1 = Arrays.asList(3, 7, 3, -1, 2, 3, 7, 2, 15, 15);
        List<Integer> l2 = Arrays.asList(-5, 15, 2, -1, 7, 15, 36);
        assertEquals(4, Task.numInCommon(l1, l2));
    }

    @Test
    void testIsUnique() {
        Map<String, String> m1 = new HashMap<>();
        m1.put("Marty", "Stepp"); m1.put("Stuart", "Reges"); m1.put("Jessica", "Miller");
        assertTrue(Task.isUnique(m1));

        Map<String, String> m2 = new HashMap<>();
        m2.put("Kendrick", "Perkins"); m2.put("Hal", "Perkins");
        assertFalse(Task.isUnique(m2));
    }

    @Test
    void testIntersect() {
        Map<String, Integer> m1 = new HashMap<>();
        m1.put("Janet", 87); m1.put("Logan", 62); m1.put("Whitaker", 46);

        Map<String, Integer> m2 = new HashMap<>();
        m2.put("Logan", 62); m2.put("Whitaker", 52); // Value mismatch

        Map<String, Integer> res = Task.intersect(m1, m2);
        assertEquals(1, res.size());
        assertTrue(res.containsKey("Logan"));
        assertEquals(62, res.get("Logan"));
    }

    @Test
    void testReverse() {
        Map<Integer, String> map = new HashMap<>();
        map.put(42, "Marty"); map.put(81, "Sue"); map.put(17, "Ed"); map.put(56, "Ed");

        Map<String, Integer> res = Task.reverse(map);
        assertTrue(res.containsKey("Marty"));
        assertTrue(res.containsKey("Sue"));
        assertTrue(res.containsKey("Ed"));
        assertEquals(42, res.get("Marty")); // Marty unique
        // Ed was 17 and 56. Map keeps one.
    }

    @Test
    void testRarest() {
        Map<String, Integer> m1 = new HashMap<>();
        m1.put("A", 22); m1.put("B", 25); m1.put("C", 25);
        assertEquals(22, Task.rarest(m1));

        Map<String, Integer> m2 = new HashMap<>();
        m2.put("A", 22); m2.put("B", 22); m2.put("C", 20); m2.put("D", 20); m2.put("E", 25);
        // 22: 2 times, 20: 2 times, 25: 1 time.
        assertEquals(25, Task.rarest(m2));
    }

    @Test
    void testMaxOccurrences() {
        List<Integer> list = Arrays.asList(9, 7, 9, -1, 2, 9, 7, 2, 15, 15);
        assertEquals(3, Task.maxOccurrences(list));
        assertEquals(0, Task.maxOccurrences(new ArrayList<>()));
    }
}