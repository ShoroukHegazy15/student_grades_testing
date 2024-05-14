package com.company;

import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import static org.junit.Assert.*;

public class OutputFileHandlerTest {

    @Test
    public void testPrintFileWithValidData() {
        // Create test data
        Vector<Student> students = new Vector<>();
        students.add(new Student("Mohamed Khaled", "1234567s", 20, 20, 20, 50));
        students.add(new Student("Renal Tarek", "1231237s", 20, 10, 20, 60));
        Subject subject = new Subject("Mathematics", "MATH101", 100);
        String filePath = "output_valid.txt";

        // Call the method to be tested
        OutputFileHandler outputFileHandler = new OutputFileHandler();
        outputFileHandler.printFile(students, subject, filePath);

        // Verify the output file is generated
        File outputFile = new File(filePath);
        assertTrue(outputFile.exists());

        // Clean up
        outputFile.delete();
    }

    @Test
    public void testPrintFileWithEmptyStudentList() {
        // Create test data with an empty student list
        Vector<Student> students = new Vector<>();
        Subject subject = new Subject("Physics", "PHYS101", 100);
        String filePath = "output_empty_students.txt";

        // Call the method to be tested
        OutputFileHandler outputFileHandler = new OutputFileHandler();
        outputFileHandler.printFile(students, subject, filePath);

        // Verify the output file is generated
        File outputFile = new File(filePath);
        assertTrue(outputFile.exists());

        // Clean up
        outputFile.delete();
    }


}
