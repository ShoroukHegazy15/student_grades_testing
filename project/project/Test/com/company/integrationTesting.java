package com.company;

import org.junit.Test;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Vector;

import static java.lang.StringTemplate.STR;
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

        //Validate total mark
        student.calculatetotalMark();
        assertEquals(93, student.getTotalMark());

        //validate GPA and grade
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
        assertEquals(student, dataBundle.getStudents().get(0));

        // Test if the student data is valid in the data bundle
        assertTrue(dataBundle.getStudents().get(0).isValid());


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

    @Test
    public void testFileHandlerIntegration() {
        // positive integration testing
        FileHandler fileHandler = new FileHandler();
        String currentDirectory = System.getProperty("user.dir");
        String filePath = STR."\{currentDirectory}/project/sample_input.txt";
        fileHandler.setFilePath(filePath);

        // Call the GetData method to retrieve data from the file
        DataBundle dataBundle = fileHandler.GetData();

        // Perform assertions to validate the integration
        assertNotNull(dataBundle); // Ensure DataBundle is not null
        assertNotNull(dataBundle.getSubject()); // Ensure subject in DataBundle is not null
        assertTrue(dataBundle.getSubject().isValid()); // Ensure that subject is valid
        assertFalse(dataBundle.getStudents().isEmpty()); // Ensure students list in DataBundle is not empty


        // Validate each student's information in the DataBundle
        for (Student student : dataBundle.getStudents()) {
            assertTrue(student.isValid());
        }

        // negative integration testing (empty file)
        String emptyFilePath = STR."\{currentDirectory}/project/empty_file.txt";
        fileHandler.setFilePath(emptyFilePath);

        // Call the GetData method to retrieve data from the empty file
        dataBundle = fileHandler.GetData();

        // Perform assertions to validate the integration
        assertTrue("Students list in DataBundle should be empty", dataBundle.getStudents().isEmpty());
        assertNotNull("DataBundle should not be null", dataBundle);

        // negative integration testing (wrong data)
        String wrongDataFile = STR."\{currentDirectory}/project/wrong_data_sample.txt";
        fileHandler.setFilePath(wrongDataFile);
        dataBundle = fileHandler.GetData();

        assertFalse(dataBundle.getSubject().isValid());
        for (Student student : dataBundle.getStudents()) {
            assertFalse(student.isValid());
        }
    }

    // The next test cases will be on integration GUI with both
    // "FileHandler and OutputFileHandler"


    // Positive testing for putting correct sample data and writing it onto "output.txt"
    @Test
    public void testGuiProcessingSuccess() {
        // Prepare GUI for testing
        FileHandlerGUI fileHandlerGUI = new FileHandlerGUI();

        // Simulate selecting a valid input file
        String currentDirectory = System.getProperty("user.dir");
        System.out.println(currentDirectory);
        String validInputFilePath = STR."\{currentDirectory}/project/sample_input.txt";
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
        outputFile.delete();
    }


    // Negative testing for putting an empty txt file and see if the gui will print the "output.txt" or no
    @Test
    public void testGuiProcessingFailure1() {
        // Prepare GUI for testing
        FileHandlerGUI fileHandlerGUI = new FileHandlerGUI();

        // Simulate selecting a valid input file
        String currentDirectory = System.getProperty("user.dir");
        String validInputFilePath = STR."\{currentDirectory}/project/empty_file.txt";
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


    // Negative testing for putting a wrong data txt file and see if the gui will print the "output.txt" or no
    @Test
    public void testGuiProcessingFailure2() {
        // Prepare GUI for testing
        FileHandlerGUI fileHandlerGUI = new FileHandlerGUI();

        // Simulate selecting a valid input file
        String currentDirectory = System.getProperty("user.dir");
        System.out.println(currentDirectory);
        String validInputFilePath = STR."\{currentDirectory}/project/wrong_data_sample.txt";
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
