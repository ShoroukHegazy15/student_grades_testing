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

    // Test cases for GPA and Grades to be implemented



}
