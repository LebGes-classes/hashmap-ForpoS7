package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HashMapTest {
    private HashMap<String,Integer> map;
    @BeforeEach
    void setUp () {
        map = new HashMap<>();
        map.put("Сережа", 20);
        map.put("Игорь", 19);
        map.put("Bob", 30);
        map.put("NYAAA", 1);
        map.put("Rya", 2);
        map.put("bra", 3);
    }
    @Test
    void put () {
        map.put("Зайон", 30);
        Assertions.assertTrue(map.containsKey("Зайон"));
    }
    @Test
    void containsKey() {
        map.put("Сережа", 21);
        Assertions.assertTrue(map.containsKey("Сережа"));
    }
    @Test
    void containsValue () {
        Assertions.assertTrue(map.containsValue(30));
    }
    @Test
    void remove () {
        Assertions.assertEquals(20, map.remove("Сережа"));
        Assertions.assertFalse(map.containsKey("Сережа"));
    }
    @Test
    void get () {
        Assertions.assertEquals(1, map.get("NYAAA"));
    }
    @Test
    void size () {
        Assertions.assertEquals(6,map.size());
    }
}
