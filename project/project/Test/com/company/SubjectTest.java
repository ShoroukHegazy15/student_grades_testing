package com.company;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SubjectTest {

    private Subject subject;

    @Before
    public void setUp() {
        subject = new Subject();
    }

    // Test cases for subject name

    @Test
    public void testValidName() {
        subject = new Subject("Math", "MAT101", 100);
        assertTrue(subject.isNameValid("Math"));
    }

    @Test
    public void testMultiWordName() {
        subject = new Subject("Biology Lab", "BIO101", 100);
        assertTrue(subject.isNameValid("Biology Lab"));
    }

    @Test
    public void testNullName() {
        subject = new Subject(null, "BIO101", 100);
        assertNull(subject.getName());
    }

    @Test
    public void testEmptyName() {
        subject = new Subject("", "MAT101", 100);
        assertFalse(subject.isNameValid(""));
    }

    @Test
    public void testSpaceAtBeginning() {
        subject = new Subject(" English", "MAT101", 100);
        assertFalse(subject.isNameValid(" English"));
    }

    @Test
    public void testNumericCharacters() {
        subject = new Subject("Math123", "MAT101", 100);
        assertFalse(subject.isNameValid("Math123"));
    }

    @Test
    public void testSpecialCharacters() {
        subject = new Subject("Math@", "MAT101", 100);
        assertFalse(subject.isNameValid("Math@"));
    }

    @Test
    public void testSingleLetter() {
        subject = new Subject("M", "MAT101", 100);
        assertTrue(subject.isNameValid("M"));
    }

    @Test
    public void testSingleSpace() {
        subject = new Subject(" ", "MAT101", 100);
        assertFalse(subject.isNameValid(" "));
    }

    // Test cases for subject code

    @Test
    public void testValidCode() {
        subject = new Subject("Mathematics", "MAT101", 100);
        assertTrue(subject.isCodeValid("MAT101"));
    }

    @Test
    public void testInvalidCodeLengthShort() {
        subject = new Subject("Physics", "PHY1", 100);
        assertFalse(subject.isCodeValid("PHY1"));
    }

    @Test
    public void testInvalidCodeLengthLong() {
        subject = new Subject("Chemistry", "CHEM1234", 100);
        assertFalse(subject.isCodeValid("CHEM1234"));
    }

    @Test
    public void testInvalidCodeFormatFirstThreeNonAlphabetic() {
        subject = new Subject("Biology", "123BIO", 100);
        assertFalse(subject.isCodeValid("123BIO"));
    }

    @Test
    public void testInvalidCodeFormatLastThreeNonNumeric() {
        subject = new Subject("History", "HISabc", 100);
        assertFalse(subject.isCodeValid("HISabc"));
    }

    @Test
    public void testInvalidCodeFormatLastCharacterNotS() {
        subject = new Subject("Geography", "GEO123e", 100);
        assertFalse(subject.isCodeValid("GEO123e"));
    }

    @Test
    public void testValidCodeLastCharacterNotS() {
        subject = new Subject("Geography", "GEO123", 100);
        assertTrue(subject.isCodeValid("GEO123"));
    }

    @Test
    public void testValidCodeLastCharacterWithSmallS() {
        subject = new Subject("Geography", "GEO123s", 100);
        assertTrue(subject.isCodeValid("GEO123s"));
    }

    @Test
    public void testInvalidCodeLastCharacterWithCapitalS() {
        subject = new Subject("Geography", "GEO123S", 100);
        assertFalse(subject.isCodeValid("GEO123S"));
    }

    @Test
    public void testNullCode() {
        // A null subject code should fail validation
        subject = new Subject("English", null, 100);
        assertFalse(subject.isCodeValid(null));
    }

    // Test cases for subject full mark
    @Test
    public void testDefaultFullMark() { //bug found here
        Subject subject = new Subject();
        assertEquals(100, subject.getFullMark());
    }
}
