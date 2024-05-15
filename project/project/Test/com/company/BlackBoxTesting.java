package com.company;

import org.junit.Test;
import static org.junit.Assert.*;


public class BlackBoxTesting {
    // Boundary Value Analysis (BVA) on Midterm Marks
//    @Test
//    public void testBVA137() {         // 0 (min)
//        Student student = new Student("Mohammed Farouk", "1234567S", 8, 7, 17, 55);
//        assertTrue(student.isMidMarkValid(0));
//    }
//    @Test
//    public void testBVA138() {         // 1 (min+)
//        Student student = new Student("Mohammed Farouk", "1234567S", 8, 7, 17, 55);
//        assertTrue(student.isMidMarkValid(1));
//    }
//    @Test
//    public void testBVA139() {         // 10 (nom)
//        Student student = new Student("Mohammed Farouk", "1234567S", 8, 7, 17, 55);
//        assertTrue(student.isMidMarkValid(10));
//    }
//    @Test
//    public void testBVA140() {         // 19 (max-)
//        Student student = new Student("Mohammed Farouk", "1234567S", 8, 7, 17, 55);
//        assertTrue(student.isMidMarkValid(19));
//    }
//    @Test
//    public void testBVA141() {         // 20 (max)
//        Student student = new Student("Mohammed Farouk", "1234567S", 8, 7, 17, 55);
//        assertTrue(student.isMidMarkValid(20));
//    }
//    @Test
//    public void testBVA142() {         // -1 (invalid) negative testing
//        Student student = new Student("Mohammed Farouk", "1234567S", 8, 7, 17, 55);
//        assertFalse(student.isMidMarkValid(-1));
//    }
//    @Test
//    public void testBVA144() {         // 21 (invalid) negative testing
//        Student student = new Student("Mohammed Farouk", "1234567S", 8, 7, 17, 55);
//        assertFalse(student.isMidMarkValid(21));
//    }
//
//
//    // Equivalence Class Partitioning (ECP) on Final Exam Marks
//    @Test
//    public void testECP150() {         // 28 (valid Partition 2)
//        Student student = new Student("Mohammed Farouk", "1234567S", 8, 7, 17, 55);
//        assertTrue(student.isFinalMarkValid(28));
//    }
//    @Test
//    public void testECP152() {         // -6 (invalid Partition 1 (smaller than 0))
//        Student student = new Student("Mohammed Farouk", "1234567S", 8, 7, 17, 55);
//        assertFalse(student.isFinalMarkValid(-6));
//    }
//    @Test
//    public void testECP154() {         // 74 (invalid Partition 3 (2-digit number greater than 60))
//        Student student = new Student("Mohammed Farouk", "1234567S", 8, 7, 17, 55);
//        assertFalse(student.isFinalMarkValid(74));
//    }
//    @Test
//    public void testECP159() {         // 120 (invalid Partition 4 (+3-digit number greater than 60))
//        Student student = new Student("Mohammed Farouk", "1234567S", 8, 7, 17, 55);
//        assertFalse(student.isFinalMarkValid(120));
//    }


    // State Transition Diagram (STD) on Student Number
//    @Test
//    public void testSTD110() {         // "190" (invalid short one)
//        Student student = new Student("Mohammed Farouk", "1234567S", 8, 7, 17, 55);
//        assertFalse(student.isCodeValid("190"));
//    }
//    @Test
//    public void testSTD111() {         // "19014728378" (invalid long one)
//        Student student = new Student("Mohammed Farouk", "1234567S", 8, 7, 17, 55);
//        assertFalse(student.isCodeValid("19014728378"));
//    }
//    @Test
//    public void testSTD107() {         // "123!4567" (invalid special symbol than needed pattern)
//        Student student = new Student("Mohammed Farouk", "1234567S", 8, 7, 17, 55);
//        assertFalse(student.isCodeValid("123!4567"));
//    }
//    @Test
//    public void testSTD91() {          // 1901472s (valid one)
//        Student student = new Student("Mohammed Farouk", "1234567S", 8, 7, 17, 55);
//        assertTrue(student.isCodeValid("1901472s"));
//    }


    // Decision Table Based (DTB) on Student Number
    @Test
    public void testDTBCase110() {         // "190" (student number length is less than 8)
        Student student = new Student("Mohammed Farouk", "1234567S", 8, 7, 17, 55);
        assertFalse(student.isCodeValid("190"));
    }
    @Test
    public void testDTBCase111() {         // "19014728378" (student number length is greater than 8)
        Student student = new Student("Mohammed Farouk", "1234567S", 8, 7, 17, 55);
        assertFalse(student.isCodeValid("19014728378"));
    }
    @Test
    public void testDTBCase114() {         // "1234567$" (student number length is 8 and the first 7 letters are digits, but the last one is a special symbol)
        Student student = new Student("Mohammed Farouk", "1234567S", 8, 7, 17, 55);
        assertFalse(student.isCodeValid("1234567$"));
    }
    @Test
    public void testDTBCase103() {          // X1234567 (student number length is 8 and the first 7 letters contain a letter)
        Student student = new Student("Mohammed Farouk", "1234567S", 8, 7, 17, 55);
        assertFalse(student.isCodeValid("X1234567"));
    }
    @Test
    public void testDTBCase91() {          // X1234567 (student number length is 8 and the first 7 letters are digits, and the last letter is a lower-case Latin letter)
        Student student = new Student("Mohammed Farouk", "1234567S", 8, 7, 17, 55);
        assertTrue(student.isCodeValid("1901472s"));
    }



}
