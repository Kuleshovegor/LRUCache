import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LRUCacheTest {
    private static final String TEST_STRING = "test";

    @Test
    public void creationTest() {
        new LRUCache<Integer, String>(1);
    }

    @Test
    public void creationFailTest() {
        Assertions.assertThrows(AssertionError.class, () -> new LRUCache<Integer, String>(-1));
    }

    @Test
    public void putTest() {
        LRUCache<Integer, String> cache = new LRUCache<>(1);
        cache.put(1, TEST_STRING);
    }

    @Test
    public void putAndGetTest() {
        LRUCache<Integer, String> cache = new LRUCache<>(1);
        cache.put(1, TEST_STRING);
        Assertions.assertEquals(TEST_STRING, cache.get(1));
    }

    @Test
    public void getLastTest() {
        LRUCache<Integer, Integer> cache = new LRUCache<>(5);
        for (int i = 0; i < 5; i++) {
            cache.put(i, i);
        }

        Assertions.assertEquals(4, 4);
    }

    @Test
    public void getMediumTest() {
        LRUCache<Integer, Integer> cache = new LRUCache<>(5);
        for (int i = 0; i < 5; i++) {
            cache.put(i, i);
        }

        Assertions.assertEquals(3, 3);
    }

    @Test
    public void getEmptyTest() {
        LRUCache<Integer, String> cache = new LRUCache<>(2);
        cache.put(1, TEST_STRING);
        Assertions.assertNull(cache.get(2));
    }

    @Test
    public void putAndGetNullTest() {
        LRUCache<Integer, String> cache = new LRUCache<>(1);
        cache.put(1, null);
        Assertions.assertNull(cache.get(1));
    }

    @Test
    public void putNullKeyTest() {
        LRUCache<Integer, String> cache = new LRUCache<>(1);
        Assertions.assertThrows(AssertionError.class, () -> cache.put(null, TEST_STRING));
    }

    @Test
    public void getNullKeyTest() {
        LRUCache<Integer, String> cache = new LRUCache<>(1);
        Assertions.assertThrows(AssertionError.class, () -> cache.get(null));
    }

    @Test
    public void rewriteTest() {
        String newTestString = "newTest";
        LRUCache<Integer, String> cache = new LRUCache<>(1);
        cache.put(1, TEST_STRING);
        Assertions.assertEquals(TEST_STRING, cache.get(1));
        cache.put(1, newTestString);
        Assertions.assertEquals(newTestString, cache.get(1));
    }

    @Test
    public void putLRUTest() {
        LRUCache<Integer, String> cache = new LRUCache<>(5);
        for (int i = 0; i < 7; i++) {
            cache.put(i, TEST_STRING);
        }

        Assertions.assertEquals(cache.size(), 5);
        Assertions.assertNull(cache.get(0));
        Assertions.assertNull(cache.get(1));
        for (int i = 2; i < 7; i++) {
            Assertions.assertEquals(TEST_STRING, cache.get(i));
        }
    }


    @Test
    public void getLRUTest() {
        LRUCache<Integer, String> cache = new LRUCache<>(5);
        for (int i = 0; i < 5; i++) {
            cache.put(i, TEST_STRING);
        }
        cache.get(1);
        cache.put(5, TEST_STRING);
        cache.put(6, TEST_STRING);

        Assertions.assertEquals(cache.size(), 5);
        Assertions.assertNull(cache.get(0));
        Assertions.assertEquals(TEST_STRING, cache.get(1));
        Assertions.assertNull(cache.get(2));
        for (int i = 3; i < 7; i++) {
            Assertions.assertEquals(TEST_STRING, cache.get(i));
        }
    }

    @Test
    public void rewriteLRUTest() {
        String newTestString = "newTest";
        LRUCache<Integer, String> cache = new LRUCache<>(5);
        for (int i = 0; i < 5; i++) {
            cache.put(i, TEST_STRING);
        }
        cache.put(1, newTestString);
        cache.put(5, TEST_STRING);
        cache.put(6, TEST_STRING);

        Assertions.assertEquals(cache.size(), 5);
        Assertions.assertNull(cache.get(0));
        Assertions.assertEquals(newTestString, cache.get(1));
        Assertions.assertNull(cache.get(2));
        for (int i = 3; i < 7; i++) {
            Assertions.assertEquals(TEST_STRING, cache.get(i));
        }
    }
}
