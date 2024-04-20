package com.company;

import org.junit.Test;
import static org.junit.Assert.*;

public class StudentTest {

    // Test cases for student name

    @Test
    public void testNameWithValidCharacters() {
        Student student = new Student("Mustafa Osama", "1234567A", 8, 7, 18, 55);
        assertTrue(student.isNameValid("Mustafa Osama"));
    }

    @Test
    public void testNameWithNumericCharacters() {
        Student student = new Student("Mustafa123", "1234567A", 8, 7, 18, 55);
        assertFalse(student.isNameValid("Mustafa123"));
    }

    @Test
    public void testNameWithSpecialCharacters() {
        Student student = new Student("Mustafa@Osama", "1234567A", 8, 7, 18, 55);
        assertFalse(student.isNameValid("Mustafa@Osama"));
    }

    @Test
    public void testNameWithSingleCharacter() {
        Student student = new Student("M", "1234567A", 8, 7, 18, 55);
        assertTrue(student.isNameValid("M"));
    }

    @Test
    public void testNameWithSingleSpace() {
        Student student = new Student(" ", "1234567A", 8, 7, 18, 55);
        assertFalse(student.isNameValid(" "));
    }

    @Test
    public void testNameStartingWithSpace() {
        Student student = new Student(" Mustafa", "1234567A", 8, 7, 18, 55);
        assertFalse(student.isNameValid(" Mustafa"));
    }

    @Test
    public void testEmptyName() {
        Student student = new Student("", "1234567A", 8, 7, 18, 55);
        assertFalse(student.isNameValid(""));
    }

    // Test cases for student code
    @Test
    public void testValidStudentCode() {
        Student student = new Student("Mustafa Osama", "1234567A", 8, 7, 18, 55);
        assertTrue(student.isCodeValid("1234567A"));
    }

    @Test
    public void testValidStudentCodeWithLetterAtEnd() {
        Student student = new Student("Mustafa Osama", "1234567A", 8, 7, 18, 55);
        assertTrue(student.isCodeValid("1234567A"));
    }
    @Test
    public void testInvalidStudentCodeWithMoreThanOneLetterAtEnd() {
        Student student = new Student("Mustafa Osama", "1234AAAA", 8, 7, 18, 55);
        assertFalse(student.isCodeValid("1234AAAA"));
    }

    @Test
    public void testInvalidShortStudentCode() {
        Student student = new Student("Mustafa Osama", "123456A", 8, 7, 18, 55);
        assertFalse(student.isCodeValid("123456A"));
    }

    @Test
    public void testInvalidLongStudentCode() {
        Student student = new Student("Mustafa Osama", "12345678A", 8, 7, 18, 55);
        assertFalse(student.isCodeValid("12345678A"));
    }

    @Test
    public void testInvalidStudentCodeWithSpecialCharacters() {
        Student student = new Student("Mustafa Osama", "1234567@", 8, 7, 18, 55);
        assertFalse(student.isCodeValid("1234567@"));
    }

    @Test
    public void testInvalidStudentCodeStartingWithCharacter() {
        Student student = new Student("Mustafa Osama", "A1234567", 8, 7, 18, 55);
        assertFalse(student.isCodeValid("A1234567"));
    }

    // Test cases for student marks

    @Test
    public void testValidActivitiesMark() {
        Student student = new Student("Mustafa Osama", "1234567A", 10, 7, 18, 55);
        assertTrue(student.isMarkValid(10));
    }

    @Test
    public void testInvalidActivitiesMarkOutOfRange() {
        Student student = new Student("Mustafa Osama", "1234567A", 15, 7, 18, 55);
        assertFalse(student.isMarkValid(15));
    }

    @Test
    public void testValidOralPracticalMark() {
        Student student = new Student("Mustafa Osama", "1234567A", 8, 10, 18, 55);
        assertTrue(student.isMarkValid(10));
    }

    @Test
    public void testInvalidOralPracticalMarkOutOfRange() {
        Student student = new Student("Mustafa Osama", "1234567A", 8, 15, 18, 55);
        assertFalse(student.isMarkValid(15));
    }

    @Test
    public void testValidMidtermMark() {
        Student student = new Student("Mustafa Osama", "1234567A", 8, 7, 20, 55);
        assertTrue(student.isMidMarkValid(20));
    }

    @Test
    public void testInvalidMidtermMarkOutOfRange() {
        Student student = new Student("Mustafa Osama", "1234567A", 8, 7, 25, 55);
        assertFalse(student.isMidMarkValid(25));
    }

    @Test
    public void testValidFinalExamMark() {
        Student student = new Student("Mustafa Osama", "1234567A", 8, 7, 18, 60);
        assertTrue(student.isFinalMarkValid(60));
    }

    @Test
    public void testInvalidFinalExamMarkOutOfRange() {
        Student student = new Student("Mustafa Osama", "1234567A", 8, 7, 18, 70);
        assertFalse(student.isFinalMarkValid(70));
    }

    // Test cases for GPA and Grades

    @Test
    public void testValidStudentGradeAPlus1() {
        Student student = new Student("Menna Yasser", "6666666A", 8, 7, 18, 70);
        student.setTotalMark(100);
        student.calculateGrade_GPA();
        assertEquals("A+", student.getGrade());
        assertEquals(4.0, student.getGPA(), 0.01);
    }
    @Test
    public void testValidStudentGradeAPlus2() {
        Student student = new Student("Menna Yasser", "6666666A", 8, 7, 18, 70);
        student.setTotalMark(98);
        student.calculateGrade_GPA();
        assertEquals("A+", student.getGrade());
        assertEquals(4.0, student.getGPA(), 0.01);
    }

    @Test
    public void testValidStudentGradeAPlus3() {
        Student student = new Student("Menna Yasser", "6666666A", 8, 7, 18, 70);
        student.setTotalMark(97);
        student.calculateGrade_GPA();
        assertEquals("A+", student.getGrade());
        assertEquals(4.0, student.getGPA(), 0.01);
    }

    @Test
    public void testInvalidStudentGradeAPlus4() {
        Student student = new Student("Menna Yasser", "6666666A", 8, 7, 18, 70);
        student.setTotalMark(96);
        student.calculateGrade_GPA();
        assertNotEquals("A+", student.getGrade());
        assertEquals(4.0, student.getGPA(), 0.01);
    }

    @Test
    public void testInvalidStudentGradeAPlus5() {
        Student student = new Student("Menna Yasser", "6666666A", 8, 7, 18, 70);
        student.setTotalMark(101);
        student.calculateGrade_GPA();
        assertNotEquals("A+", student.getGrade());
        assertNotEquals(4.0, student.getGPA(), 0.01);
    }

    @Test
    public void testValidStudentGradeA1() {
        Student student = new Student("Menna Yasser", "6666666A", 8, 7, 18, 70);
        student.setTotalMark(93);
        student.calculateGrade_GPA();
        assertEquals("A", student.getGrade());
        assertEquals(4.0, student.getGPA(), 0.01);
    }


    @Test
    public void testValidStudentGradeA2() {
        Student student = new Student("Menna Yasser", "6666666A", 8, 7, 18, 70);
        student.setTotalMark(95);
        student.calculateGrade_GPA();
        assertEquals("A", student.getGrade());
        assertEquals(4.0, student.getGPA(), 0.01);
    }

    @Test
    public void testInvalidStudentGradeA3() {
        Student student = new Student("Menna Yasser", "6666666A", 8, 7, 18, 70);
        student.setTotalMark(90);
        student.calculateGrade_GPA();
        assertNotEquals("A", student.getGrade());
        assertNotEquals(4.0, student.getGPA(), 0.01);
    }
    @Test
    public void testInvalidStudentGradeA4() {
        Student student = new Student("Menna Yasser", "6666666A", 8, 7, 18, 70);
        student.setTotalMark(98);
        student.calculateGrade_GPA();
        assertNotEquals("A", student.getGrade());
        assertEquals(4.0, student.getGPA(), 0.01);
    }


    @Test
    public void testValidStudentGradeAMinus1() {
        Student student = new Student("Menna Yasser", "6666666A", 8, 7, 18, 70);
        student.setTotalMark(89);
        student.calculateGrade_GPA();
        assertEquals("A-", student.getGrade());
        assertEquals(3.7, student.getGPA(), 0.01);
    }


    @Test
    public void testValidStudentGradeAMinus2() {
        Student student = new Student("Menna Yasser", "6666666A", 8, 7, 18, 70);
        student.setTotalMark(90);
        student.calculateGrade_GPA();
        assertEquals("A-", student.getGrade());
        assertEquals(3.7, student.getGPA(), 0.01);
    }

    @Test
    public void testInvalidStudentGradeAMinus3() {
        Student student = new Student("Menna Yasser", "6666666A", 8, 7, 18, 70);
        student.setTotalMark(95);
        student.calculateGrade_GPA();
        assertNotEquals("A-", student.getGrade());
        assertNotEquals(3.7, student.getGPA(), 0.01);
    }


    @Test
    public void testValidStudentGradeBPlus1() {
        Student student = new Student("Menna Yasser", "6666666A", 8, 7, 18, 70);
        student.setTotalMark(84);
        student.calculateGrade_GPA();
        assertEquals("B+", student.getGrade());
        assertEquals(3.3, student.getGPA(), 0.01);
    }


    @Test
    public void testValidStudentGradeBPlus2() {
        Student student = new Student("Menna Yasser", "6666666A", 8, 7, 18, 70);
        student.setTotalMark(87);
        student.calculateGrade_GPA();
        assertEquals("B+", student.getGrade());
        assertEquals(3.3, student.getGPA(), 0.01);
    }

    @Test
    public void testInvalidStudentGradeBPlus3() {
        Student student = new Student("Menna Yasser", "6666666A", 8, 7, 18, 70);
        student.setTotalMark(81);
        student.calculateGrade_GPA();
        assertNotEquals("B+", student.getGrade());
        assertNotEquals(3.3, student.getGPA(), 0.01);
    }

    @Test
    public void testValidStudentGradeB1() {
        Student student = new Student("Menna Yasser", "6666666A", 8, 7, 18, 70);
        student.setTotalMark(80);
        student.calculateGrade_GPA();
        assertEquals("B", student.getGrade());
        assertEquals(3.0, student.getGPA(), 0.01);
    }


    @Test
    public void testValidStudentGradeB2() {
        Student student = new Student("Menna Yasser", "6666666A", 8, 7, 18, 70);
        student.setTotalMark(82);
        student.calculateGrade_GPA();
        assertEquals("B", student.getGrade());
        assertEquals(3.0, student.getGPA(), 0.01);
    }

    @Test
    public void testInvalidStudentGradeB3() {
        Student student = new Student("Menna Yasser", "6666666A", 8, 7, 18, 70);
        student.setTotalMark(77);
        student.calculateGrade_GPA();
        assertNotEquals("B", student.getGrade());
        assertNotEquals(3.0, student.getGPA(), 0.01);
    }


    @Test
    public void testValidStudentGradeBMinus1() {
        Student student = new Student("Menna Yasser", "6666666A", 8, 7, 18, 70);
        student.setTotalMark(76);
        student.calculateGrade_GPA();
        assertEquals("B-", student.getGrade());
        assertEquals(2.7, student.getGPA(), 0.01);
    }


    @Test
    public void testValidStudentGradeBMinus2() {
        Student student = new Student("Menna Yasser", "6666666A", 8, 7, 18, 70);
        student.setTotalMark(78);
        student.calculateGrade_GPA();
        assertEquals("B-", student.getGrade());
        assertEquals(2.7, student.getGPA(), 0.01);
    }

    @Test
    public void testInvalidStudentGradeBMinus3() {
        Student student = new Student("Menna Yasser", "6666666A", 8, 7, 18, 70);
        student.setTotalMark(75);
        student.calculateGrade_GPA();
        assertNotEquals("B-", student.getGrade());
        assertNotEquals(2.7, student.getGPA(), 0.01);
    }


    @Test
    public void testValidStudentGradeCPlus1() {
        Student student = new Student("Menna Yasser", "6666666A", 8, 7, 18, 70);
        student.setTotalMark(73);
        student.calculateGrade_GPA();
        assertEquals("C+", student.getGrade());
        assertEquals(2.3, student.getGPA(), 0.01);
    }


    @Test
    public void testValidStudentGradeCPlus2() {
        Student student = new Student("Menna Yasser", "6666666A", 8, 7, 18, 70);
        student.setTotalMark(74);
        student.calculateGrade_GPA();
        assertEquals("C+", student.getGrade());
        assertEquals(2.3, student.getGPA(), 0.01);
    }

    @Test
    public void testInvalidStudentGradeCPlus3() {
        Student student = new Student("Menna Yasser", "6666666A", 8, 7, 18, 70);
        student.setTotalMark(68);
        student.calculateGrade_GPA();
        assertNotEquals("C+", student.getGrade());
        assertNotEquals(2.3, student.getGPA(), 0.01);
    }



    @Test
    public void testValidStudentGradeC1() {
        Student student = new Student("Menna Yasser", "6666666A", 8, 7, 18, 70);
        student.setTotalMark(70);
        student.calculateGrade_GPA();
        assertEquals("C", student.getGrade());
        assertEquals(2.0, student.getGPA(), 0.01);
    }


    @Test
    public void testValidStudentGradeC2() {
        Student student = new Student("Menna Yasser", "6666666A", 8, 7, 18, 70);
        student.setTotalMark(72);
        student.calculateGrade_GPA();
        assertEquals("C", student.getGrade());
        assertEquals(2.0, student.getGPA(), 0.01);
    }

    @Test
    public void testInvalidStudentGradeC3() {
        Student student = new Student("Menna Yasser", "6666666A", 8, 7, 18, 70);
        student.setTotalMark(68);
        student.calculateGrade_GPA();
        assertNotEquals("C", student.getGrade());
        assertNotEquals(2.0, student.getGPA(), 0.01);
    }


    @Test
    public void testValidStudentGradeCMinus1() {
        Student student = new Student("Menna Yasser", "6666666A", 8, 7, 18, 70);
        student.setTotalMark(67);
        student.calculateGrade_GPA();
        assertEquals("C-", student.getGrade());
        assertEquals(1.7, student.getGPA(), 0.01);
    }


    @Test
    public void testValidStudentGradeCMinus2() {
        Student student = new Student("Menna Yasser", "6666666A", 8, 7, 18, 70);
        student.setTotalMark(69);
        student.calculateGrade_GPA();
        assertEquals("C-", student.getGrade());
        assertEquals(1.7, student.getGPA(), 0.01);
    }

    @Test
    public void testInvalidStudentGradeCMinus3() {
        Student student = new Student("Menna Yasser", "6666666A", 8, 7, 18, 70);
        student.setTotalMark(65);
        student.calculateGrade_GPA();
        assertNotEquals("C-", student.getGrade());
        assertNotEquals(1.7, student.getGPA(), 0.01);
    }


    @Test
    public void testValidStudentGradeDPlus1() {
        Student student = new Student("Menna Yasser", "6666666A", 8, 7, 18, 70);
        student.setTotalMark(64);
        student.calculateGrade_GPA();
        assertEquals("D+", student.getGrade());
        assertEquals(1.3, student.getGPA(), 0.01);
    }


    @Test
    public void testValidStudentGradeDPlus2() {
        Student student = new Student("Menna Yasser", "6666666A", 8, 7, 18, 70);
        student.setTotalMark(65);
        student.calculateGrade_GPA();
        assertEquals("D+", student.getGrade());
        assertEquals(1.3, student.getGPA(), 0.01);
    }

    @Test
    public void testInvalidStudentGradeDPlus3() {
        Student student = new Student("Menna Yasser", "6666666A", 8, 7, 18, 70);
        student.setTotalMark(63);
        student.calculateGrade_GPA();
        assertNotEquals("D+", student.getGrade());
        assertNotEquals(1.3, student.getGPA(), 0.01);
    }



    @Test
    public void testValidStudentGradeD1() {
        Student student = new Student("Menna Yasser", "6666666A", 8, 7, 18, 70);
        student.setTotalMark(60);
        student.calculateGrade_GPA();
        assertEquals("D", student.getGrade());
        assertEquals(1.0, student.getGPA(), 0.01);
    }


    @Test
    public void testValidStudentGradeD2() {
        Student student = new Student("Menna Yasser", "6666666A", 8, 7, 18, 70);
        student.setTotalMark(63);
        student.calculateGrade_GPA();
        assertEquals("D", student.getGrade());
        assertEquals(1.0, student.getGPA(), 0.01);
    }

    @Test
    public void testInvalidStudentGradeD3() {
        Student student = new Student("Menna Yasser", "6666666A", 8, 7, 18, 70);
        student.setTotalMark(55);
        student.calculateGrade_GPA();
        assertNotEquals("D", student.getGrade());
        assertNotEquals(1.0, student.getGPA(), 0.01);
    }



    @Test
    public void testInvalidStudentGradeF1() {
        Student student = new Student("Menna Yasser", "6666666A", 8, 7, 18, 70);
        student.setTotalMark(60);
        student.calculateGrade_GPA();
        assertNotEquals("F", student.getGrade());
        assertNotEquals(0.0, student.getGPA(), 0.01);
    }

    @Test
    public void testValidStudentGradeF2() {
        Student student = new Student("Menna Yasser", "6666666A", 8, 7, 18, 70);
        student.setTotalMark(50);
        student.calculateGrade_GPA();
        assertEquals("F", student.getGrade());
        assertEquals(0.0, student.getGPA(), 0.01);
    }

    @Test
    public void testValidStudentGradeF3() {
        Student student = new Student("Menna Yasser", "6666666A", 8, 7, 18, 70);
        student.setTotalMark(15);
        student.calculateGrade_GPA();
        assertEquals("F", student.getGrade());
        assertEquals(0.0, student.getGPA(), 0.01);
    }



}
