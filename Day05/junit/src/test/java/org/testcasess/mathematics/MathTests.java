package org.testcasess.mathematics;

import org.example.Maths;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MathTests {
private Maths math;
@BeforeEach
    void set() {
    math = new Maths(2,5);
}
@Test
@DisplayName("TestCase:Addition")
    void testAddition(){
    Assertions.assertEquals(7,math.add());
}

}
