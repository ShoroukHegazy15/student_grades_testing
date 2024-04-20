package com.company;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileHandlerTest {
    private FileHandler fileHandler;
    private File tempFile;

    @Before
    public void setUp() throws IOException {
        fileHandler = new FileHandler();
        tempFile = File.createTempFile("tempfile", ".txt");
        FileWriter writer = new FileWriter(tempFile);
        writer.write("Mathematics,CSE123s,10\n");
        writer.write("Mennatullah Yasser Mohamed,19000581,8,8,16,50\n");
        writer.write("Shorouk Hegazy,1900058s,9,7,15,55\n");
        writer.write("Mohamed Khaled,19000532,9,9,16,40\n");
        writer.close();
    }

    @After
    public void tearDown() {
        if (tempFile.exists()) {
            tempFile.delete();
        }
    }

    @Test
    public void testGetData() {
        fileHandler.setFilePath(tempFile.getAbsolutePath());
        DataBundle dataBundle = fileHandler.GetData();

        Vector<Student> students = dataBundle.getStudents();
        assertEquals(3, students.size());

        Student student1 = students.get(0);
        assertEquals("Mennatullah Yasser Mohamed", student1.getName());
        assertEquals("19000581", student1.getCode());
        assertEquals(8, (int) student1.getActivitiesMark());
        assertEquals(8, (int) student1.getOralPractMark());
        assertEquals(16, (int) student1.getMidtermMark());
        assertEquals(50, (int) student1.getFinalExamMark());

        Student student2 = students.get(1);
        assertEquals("Shorouk Hegazy", student2.getName());
        assertEquals("1900058s", student2.getCode());
        assertEquals(9, (int) student2.getActivitiesMark());
        assertEquals(7, (int) student2.getOralPractMark());
        assertEquals(15, (int) student2.getMidtermMark());
        assertEquals(55, (int) student2.getFinalExamMark());

        Student student3 = students.get(2);
        assertEquals("Mohamed Khaled", student3.getName());
        assertEquals("19000532", student3.getCode());
        assertEquals(9, (int) student3.getActivitiesMark());
        assertEquals(9, (int) student3.getOralPractMark());
        assertEquals(16, (int) student3.getMidtermMark());
        assertEquals(40, (int) student3.getFinalExamMark());

        Subject subject = dataBundle.getSubject();
        assertEquals("Mathematics", subject.getName());
        assertEquals("CSE123s", subject.getCode());
        assertEquals(10, (int) subject.getFullMark());
    }

    @Test
    public void testGetDataWithInvalidFilePath() {
        fileHandler.setFilePath("invalid/path/to/file.txt");
        DataBundle dataBundle = fileHandler.GetData();

        Vector<Student> students = dataBundle.getStudents();
        assertTrue(students.isEmpty());

        Subject subject = dataBundle.getSubject();
        assertEquals("", subject.getName());
        assertEquals("", subject.getCode());
        assertEquals(100, (int) subject.getFullMark());
    }

    @Test
    public void testGetDataWithEmptyFile() throws IOException {
        File emptyFile = File.createTempFile("emptyfile", ".txt");
        fileHandler.setFilePath(emptyFile.getAbsolutePath());
        DataBundle dataBundle = fileHandler.GetData();

        Vector<Student> students = dataBundle.getStudents();
        assertTrue(students.isEmpty());

        Subject subject = dataBundle.getSubject();
        assertEquals("", subject.getName());
        assertEquals("", subject.getCode());
        assertEquals(100, (int) subject.getFullMark());

        emptyFile.delete();
    }

    @Test
    public void testGetDataWithInvalidStudentData() throws IOException {
        File invalidDataFile = File.createTempFile("invalidfile", ".txt");
        FileWriter writer = new FileWriter(invalidDataFile);
        writer.write("Mathematics,CSE123s,100\n");
        writer.write("mohamed@@##khaled,19000581SSEE,88,88,88,88\n"); //Invalid Student Data
        writer.close();

        fileHandler.setFilePath(invalidDataFile.getAbsolutePath());
        DataBundle dataBundle = fileHandler.GetData();

        Vector<Student> students = dataBundle.getStudents();
        assertTrue(students.isEmpty());

        invalidDataFile.delete();
    }

}
