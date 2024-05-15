package com.company;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.*;
import java.util.Vector;

public class FileHandlerTest {

    @Test
    public void testSetFilePath() {
        FileHandler fileHandler = new FileHandler();
        String filePath = "sample.txt";
        fileHandler.setFilePath(filePath);
        assertEquals(filePath, fileHandler.filePath);
    }

    @Test
    public void testGetDataValidFile() throws IOException {
        // Create a temporary valid input file
        File tempFile = File.createTempFile("valid_input", ".txt");
        try (PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {
            writer.println("Mathematics,CSE123s,100");
            writer.println("Mohamed Khaled,1234567S,8,9,15,55");
            writer.println("Shorouk Hegazy,7654321S,7,8,16,50");
            writer.println("Renal Tarek,9876543S,9,7,14,60");
        }

        FileHandler fileHandler = new FileHandler();
        fileHandler.setFilePath(tempFile.getAbsolutePath());
        DataBundle dataBundle = fileHandler.GetData();

        assertNotNull(dataBundle);
        assertNotNull(dataBundle.getSubject());
        assertTrue(dataBundle.getSubject().isValid());
        assertEquals(3, dataBundle.getStudents().size());

        for (Student student : dataBundle.getStudents()) {
            assertTrue(student.isValid());
        }

        // Delete the temporary file
        assertTrue(tempFile.delete());
    }

    @Test
    public void testGetDataEmptyFile() throws IOException {
        // Create a temporary empty input file
        File tempFile = File.createTempFile("empty_file", ".txt");

        FileHandler fileHandler = new FileHandler();
        fileHandler.setFilePath(tempFile.getAbsolutePath());
        DataBundle dataBundle = fileHandler.GetData();

        assertNotNull(dataBundle);
        assertNotNull(dataBundle.getSubject());
        assertTrue(dataBundle.getStudents().isEmpty());

        // Delete the temporary file
        assertTrue(tempFile.delete());
    }

    @Test
    public void testGetDataInvalidSubject() throws IOException {
        // Create a temporary file with an invalid subject header
        File tempFile = File.createTempFile("invalid_subject", ".txt");
        try (PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {
            writer.println("Mathematics,CSE123SSs,110");
            writer.println("Mohamed Khaled,1234567S,8,9,15,55");
        }

        FileHandler fileHandler = new FileHandler();
        fileHandler.setFilePath(tempFile.getAbsolutePath());

        Exception exception = assertThrows(IllegalArgumentException.class, fileHandler::GetData);

        String expectedMessagePart = "Invalid subject header format:";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessagePart));

        // Delete the temporary file
        assertTrue(tempFile.delete());
    }

    @Test
    public void testGetDataInvalidStudent() throws IOException {
        // Create a temporary file with an invalid student line
        File tempFile = File.createTempFile("invalid_student", ".txt");
        try (PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {
            writer.println("Mathematics,CSE123s,100");
            writer.println("Mohamed Khaled,1234567S,8,9,15,55");
            writer.println("Shorouk Hegazy,7654321S,7,8,16,invalid_score");
        }

        FileHandler fileHandler = new FileHandler();
        fileHandler.setFilePath(tempFile.getAbsolutePath());

        Exception exception = assertThrows(IllegalArgumentException.class, fileHandler::GetData);

        String expectedMessagePart = "Invalid student data format:";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessagePart));

        // Delete the temporary file
        assertTrue(tempFile.delete());
    }

    @Test
    public void testGetDataFileNotFound() {
        FileHandler fileHandler = new FileHandler();
        fileHandler.setFilePath("non_existent_file.txt");

        DataBundle dataBundle = fileHandler.GetData();

        assertNull(dataBundle);
    }

    @Test
    public void testParseStudent() {
        FileHandler fileHandler = new FileHandler();
        String studentLine = "Mohamed Khaled,1234567S,8,9,15,55";

        Student student = fileHandler.parseStudent(studentLine);

        assertNotNull(student);
        assertEquals("Mohamed Khaled", student.getName());
        assertEquals("1234567S", student.getCode());
        assertEquals(8, student.getActivitiesMark());
        assertEquals(9, student.getOralPractMark());
        assertEquals(15, student.getMidtermMark());
        assertEquals(55, student.getFinalExamMark());
    }

    @Test
    public void testParseStudentInvalid() {
        FileHandler fileHandler = new FileHandler();
        String studentLine = "Invalid,Student,Data";

        Student student = fileHandler.parseStudent(studentLine);

        assertNull(student);
    }

    @Test
    public void testParseSubject() {
        FileHandler fileHandler = new FileHandler();
        String subjectLine = "Mathematics,CSE123s,100";

        Subject subject = fileHandler.parseSubject(subjectLine);

        assertNotNull(subject);
        assertEquals("Mathematics", subject.getName());
        assertEquals("CSE123s", subject.getCode());
        assertEquals(100, subject.getFullMark());
    }

    @Test
    public void testParseSubjectInvalid() {
        FileHandler fileHandler = new FileHandler();
        String subjectLine = "Invalid,Subject,Data";

        Subject subject = fileHandler.parseSubject(subjectLine);

        assertNull(subject);
    }
}
