package org.testcasess.mathematics;

import org.example.AnagramExample;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class AnagramTest {
    private AnagramExample ana;

    @BeforeEach
    void setUp() {
        String[] str1 = {"Apple", "Orange", "Mango", "pAple", "elppA", "roaneg", "Guava", "ongam", "Jackfruit", "elpap", "gamno"};
        ana = new AnagramExample(str1);
    }

    @Test
    @DisplayName("Test Anagram Detection")
    void testAnagramDetection() {
        Map<String, Integer> map = ana.test();

        Assertions.assertEquals(2, map.get("aegnor"));
    }
}
