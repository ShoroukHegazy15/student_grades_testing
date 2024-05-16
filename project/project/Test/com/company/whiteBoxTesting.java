package com.company;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class whiteBoxTesting {

@Test
    public void testIsValid1(){
        Student student = new Student("Mustafa Osama", "1234567A", 8, 7, 18, 55);
        assertTrue(student.isValid());
    }

    @Test
    public void testIsValid2(){
        Student student = new Student("Mustafa@Osama", "1234567A", 8, 7, 18, 55);
        assertFalse(student.isValid());
    }
    @Test
    public void testIsValid3(){
        Student student = new Student("Mustafa Osama", "1234A67A", 8, 7, 18, 55);
        assertFalse(student.isValid());
    }
    @Test
    public void testIsValid4(){
        Student student = new Student("Mustafa Osama", "1234567A", 11, 7, 18, 55);
        assertFalse(student.isValid());
    }
    @Test
    public void testIsValid5(){
        Student student = new Student("Mustafa Osama", "1234567A", 8, 11, 18, 55);
        assertFalse(student.isValid());
    }
    @Test
    public void testIsValid6(){
        Student student = new Student("Mustafa Osama", "1234567A", 8, 7, 22, 55);
        assertFalse(student.isValid());
    }
    @Test
    public void testIsValid7(){
        Student student = new Student("Mustafa Osama", "1234567A", 8, 11, 18, 101);
        assertFalse(student.isValid());
    }

@Test
    public void testValidFilePath() {
        FileHandler fileHandler = new FileHandler();
        String currentDirectory = System.getProperty("user.dir");
        String validInputFilePath = String.format("%s/project/sample_input.txt", currentDirectory);
        fileHandler.setFilePath(validInputFilePath); // Assume this file exists and is correctly formatted
        DataBundle dataBundle = fileHandler.GetData();
        assertNotNull(dataBundle);
        assertEquals("Mathematics", dataBundle.getSubject().getName());
        assertEquals(3, dataBundle.getStudents().size());
        assertEquals("Mohamed Khaled", dataBundle.getStudents().get(0).getName());
    }

    @Test
    public void testInvalidFilePath() {
        FileHandler fileHandler = new FileHandler();
        fileHandler.setFilePath("invalidFilePath.txt"); // Assume this file does not exist
        DataBundle dataBundle = fileHandler.GetData();
        assertNull(dataBundle);
    }

    @Test
    public void testInvalidSubjectHeaderFormat() {
        FileHandler fileHandler = new FileHandler();
        String currentDirectory = System.getProperty("user.dir");
        String validInputFilePath = String.format("%s/project/invalid_subject_header_input.txt", currentDirectory);
        fileHandler.setFilePath(validInputFilePath);
        Exception exception = assertThrows(IllegalArgumentException.class, fileHandler::GetData);
        assertTrue(exception.getMessage().contains("Invalid subject header format"));
    }

    @Test
    public void testInvalidStudentDataFormat() {
        FileHandler fileHandler = new FileHandler();
        String currentDirectory = System.getProperty("user.dir");
        String validInputFilePath = String.format("%s/project/invalid_student_header_input.txt", currentDirectory);
        fileHandler.setFilePath(validInputFilePath);
        Exception exception = assertThrows(IllegalArgumentException.class, fileHandler::GetData);
        assertTrue(exception.getMessage().contains("Invalid student data format"));
    }
}
