package com.company;

import org.junit.Test;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.*;

public class integrationTesting {

    @Test
    public void testStudentModule() {
        // Create a student object
        Student student = new Student("Mustafa Osama", "9955661A", 8, 7, 18, 60);

        // Validate student information
        assertEquals("Mustafa Osama", student.getName());
        assertEquals("9955661A", student.getCode());
        assertEquals(8, student.getActivitiesMark());
        assertEquals(7, student.getOralPractMark());
        assertEquals(18, student.getMidtermMark());
        assertEquals(60, student.getFinalExamMark());

        // Validate total mark
        student.calculatetotalMark();
        assertEquals(93, student.getTotalMark());

        // Validate GPA and grade
        student.calculateGrade_GPA();
        assertEquals(4.0, student.getGPA(), 0.001);
        assertEquals("A", student.getGrade());
    }

    @Test
    public void testSubjectModule() {
        // Create a subject for testing
        Subject subject = new Subject("Software Testing", "CSE337s", 100);

        // Test valid subject
        assertTrue(subject.isValid());

        // Test invalid name
        subject = new Subject("Software_22_Testing", "CSE337s", 100);
        assertFalse(subject.isValid());
        assertFalse(subject.isNameValid(subject.getName())); // Additional name validation check

        // Test invalid code
        subject = new Subject("Software Testing", "MAT10", 100);
        assertFalse(subject.isValid());

        // Test invalid full mark
        subject = new Subject("Software Testing", "MAT101", 90);
        assertFalse(subject.isValid());
        assertFalse(subject.isFullMarkValid(subject.getFullMark())); // Additional full mark validation check
    }

    @Test
    public void testDataBundleIntegration() {
        Student student = new Student("Mustafa Osama", "9955661A", 8, 9, 15, 55);
        Subject subject = new Subject("Mathematics", "CSE123s", 100);
        DataBundle dataBundle = new DataBundle(new Vector<>(), subject);

        // Add the student to the data bundle
        dataBundle.getStudents().add(student);

        // Test if the subject in the data bundle is the same as the subject object created
        assertEquals(subject, dataBundle.getSubject());

        // Test if the subject inside the data bundle is valid
        assertTrue(dataBundle.getSubject().isValid());

        // Test if the student in the data bundle is the same as student object created
        assertEquals(1, dataBundle.getStudents().size());
        assertEquals(student, dataBundle.getStudents().firstElement());

        // Test if the student data is valid in the data bundle
        assertTrue(dataBundle.getStudents().firstElement().isValid());

        // Insert another 3 students into the dataBundle object
        Student student1 = new Student("Mohamed Khaled", "1234567S", 8, 9, 15, 55);
        Student student2 = new Student("Shorouk Hegazy", "7654321S", 7, 8, 16, 50);
        Student student3 = new Student("Renal Tarek", "9876543S", 9, 7, 14, 60);

        // Add the students to the data bundle
        dataBundle.getStudents().add(student1);
        dataBundle.getStudents().add(student2);
        dataBundle.getStudents().add(student3);

        // Test if the correct number of students were added to the data bundle
        assertEquals(4, dataBundle.getStudents().size());

        // Test if each student was added successfully
        assertTrue(dataBundle.getStudents().contains(student1));
        assertTrue(dataBundle.getStudents().contains(student2));
        assertTrue(dataBundle.getStudents().contains(student3));

        // Validate each student's information inside dataBundle
        for (Student s : dataBundle.getStudents()) {
            assertTrue(s.isValid());
        }
    }

    // The next test cases will be on integration of FileHandler with lower modules
    @Test
    public void testFileHandlerIntegrationSuccess() {
        FileHandler fileHandler = new FileHandler();
        String currentDirectory = System.getProperty("user.dir");

        // Positive integration testing
        String positiveFilePath = String.format("%s/project/sample_input.txt", currentDirectory);
        fileHandler.setFilePath(positiveFilePath);
        DataBundle dataBundle = fileHandler.GetData();

        // Perform assertions to validate the integration
        assertNotNull(dataBundle);
        assertNotNull(dataBundle.getSubject());
        assertTrue(dataBundle.getSubject().isValid());
        assertFalse(dataBundle.getStudents().isEmpty());

        // Validate each student's information in the DataBundle
        for (Student student : dataBundle.getStudents()) {
            assertTrue(student.isValid());
        }
    }

    @Test
    public void testFileHandlerIntegrationFailure1() {
        FileHandler fileHandler = new FileHandler();
        String currentDirectory = System.getProperty("user.dir");

        // Negative integration testing (empty file)
        String emptyFilePath = String.format("%s/project/empty_file.txt", currentDirectory);
        fileHandler.setFilePath(emptyFilePath);

        DataBundle dataBundle = fileHandler.GetData();

        // Perform assertions to validate the integration
        assertNotNull(dataBundle);
        assertTrue(dataBundle.getStudents().isEmpty());
        assertNotNull(dataBundle.getSubject());
    }

    @Test
    public void testFileHandlerIntegrationFailure2() {
        FileHandler fileHandler = new FileHandler();
        String currentDirectory = System.getProperty("user.dir");

        // Negative integration testing with invalid subject header for the input file
        String wrongDataFilePath = String.format("%s/project/invalid_subject_header_input.txt", currentDirectory);
        fileHandler.setFilePath(wrongDataFilePath);

        Exception exception = assertThrows(IllegalArgumentException.class, fileHandler::GetData);

        // Use a generic part of the expected message
        String expectedMessagePart = "Invalid subject header format:";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessagePart));
    }

    @Test
    public void testFileHandlerIntegrationFailure3() {
        FileHandler fileHandler = new FileHandler();
        String currentDirectory = System.getProperty("user.dir");

        // Negative integration testing with invalid student header for the input file
        String wrongDataFilePath = String.format("%s/project/invalid_student_header_input.txt", currentDirectory);
        fileHandler.setFilePath(wrongDataFilePath);

        Exception exception = assertThrows(IllegalArgumentException.class, fileHandler::GetData);

        // Use a generic part of the expected message
        String expectedMessagePart = "Invalid student data format:";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessagePart));
    }

    // The next test cases will be on integration GUI with both
    // "FileHandler and OutputFileHandler"

    // Positive testing for putting correct sample data and writing it onto "output.txt"
    // and then check if "output.txt" exists
    @Test
    public void testGuiProcessingSuccess1() {
        // Prepare GUI for testing
        FileHandlerGUI fileHandlerGUI = new FileHandlerGUI();

        // Simulate selecting a valid input file
        String currentDirectory = System.getProperty("user.dir");
        String validInputFilePath = String.format("%s/project/sample_input.txt", currentDirectory);
        fileHandlerGUI.filePathField.setText(validInputFilePath);

        // Set the output file path (directory)
        String outputDirectoryPath = "E:/output.txt";
        fileHandlerGUI.outputFilePathField.setText(outputDirectoryPath);

        // Simulate button click to trigger file processing
        fileHandlerGUI.new ProcessButtonListener()
                .actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));

        // Check if the output file is generated
        File outputFile = new File(outputDirectoryPath);
        assertTrue(outputFile.exists());

        // Clean up: delete the output file
        assertTrue(outputFile.delete());
    }

    // Positive testing for putting correct sample data and writing it onto "output.txt"
    // then we check if the data inside the "output.txt" is correct
    @Test
    public void testGuiProcessingSuccess2() {
        // Prepare GUI for testing
        FileHandlerGUI fileHandlerGUI = new FileHandlerGUI();

        // Simulate selecting a valid input file
        String currentDirectory = System.getProperty("user.dir");
        String validInputFilePath = String.format("%s/project/sample_input.txt", currentDirectory);
        fileHandlerGUI.filePathField.setText(validInputFilePath);

        // Set the output file path (directory)
        String outputFilePath = "E:/output.txt";
        fileHandlerGUI.outputFilePathField.setText(outputFilePath);

        // Simulate button click to trigger file processing
        fileHandlerGUI.new ProcessButtonListener()
                .actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));

        // Check if the output file is generated
        File outputFile = new File(outputFilePath);
        assertTrue(outputFile.exists());

        // Validate the content of the output file
        try (BufferedReader reader = new BufferedReader(new FileReader(outputFile))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\\n");
            }

            // Expected content based on the sample_input.txt
            String expectedContent = "Subject Name: Mathematics            Max Mark: 100\\n" +
                    String.format("%-40s%-20s%-10s%-10s\\n", "Student name", "Student number", "GPA", "Grade") +
                    String.format("%-40s%-20s%-10s%-10s\\n", "Mohamed Khaled", "1234567S", "3.3", "B+") +
                    String.format("%-40s%-20s%-10s%-10s\\n", "Shorouk Hegazy", "7654321S", "3.0", "B") +
                    String.format("%-40s%-20s%-10s%-10s\\n", "Renal Tarek", "9876543S", "3.7", "A-");

            assertEquals(expectedContent.trim(), content.toString().trim());
        } catch (IOException e) {
            fail("Error reading the output file: " + e.getMessage());
        }

        // Clean up: delete the output file
        assertTrue(outputFile.delete());
    }

    // Negative testing for putting a text file with invalid path
    // and see if the gui will print the "output.txt" or no
    @Test
    public void testGuiProcessingFailure1() {
        // Prepare GUI for testing
        FileHandlerGUI fileHandlerGUI = new FileHandlerGUI();

        // Set a non-existent input file path
        String nonExistentFilePath = "non_existent_file.txt";
        fileHandlerGUI.filePathField.setText(nonExistentFilePath);

        // Set the output file path
        String outputFilePath = "E:/output.txt";
        fileHandlerGUI.outputFilePathField.setText(outputFilePath);

        // Simulate button click to trigger file processing
        fileHandlerGUI.new ProcessButtonListener()
                .actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));

        // Check if the output file is not generated
        File outputFile = new File(outputFilePath);
        assertFalse(outputFile.exists());
    }

    // Negative testing for putting an empty txt file
    // and see if the gui will print the "output.txt" or no
    @Test
    public void testGuiProcessingFailure2() {
        // Prepare GUI for testing
        FileHandlerGUI fileHandlerGUI = new FileHandlerGUI();

        // Simulate selecting a valid input file
        String currentDirectory = System.getProperty("user.dir");
        String validInputFilePath = String.format("%s/project/empty_file.txt", currentDirectory);
        fileHandlerGUI.filePathField.setText(validInputFilePath);

        // Set the output file path (directory)
        String outputDirectoryPath = "E:/output.txt";
        fileHandlerGUI.outputFilePathField.setText(outputDirectoryPath);

        // Simulate button click to trigger file processing
        fileHandlerGUI.new ProcessButtonListener()
                .actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));

        // Check if the output file is generated
        File outputFile = new File(outputDirectoryPath);
        assertFalse(outputFile.exists());
    }

    // Negative testing for putting a wrong data txt file
    // and see if the gui will print the "output.txt" or no
    @Test
    public void testGuiProcessingFailure3() {
        // Prepare GUI for testing
        FileHandlerGUI fileHandlerGUI = new FileHandlerGUI();

        // Simulate selecting a valid input file
        String currentDirectory = System.getProperty("user.dir");
        String validInputFilePath = String.format("%s/project/wrong_data_sample.txt", currentDirectory);
        fileHandlerGUI.filePathField.setText(validInputFilePath);

        // Set the output file path (directory)
        String outputDirectoryPath = "E:/output.txt";
        fileHandlerGUI.outputFilePathField.setText(outputDirectoryPath);

        // Simulate button click to trigger file processing
        fileHandlerGUI.new ProcessButtonListener()
                .actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));

        // Check if the output file is generated
        File outputFile = new File(outputDirectoryPath);
        assertFalse(outputFile.exists());
    }
}
