package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

public class OutputFileHandler {

    public void printFile(Vector<Student>students, Subject subj, String filePath){
        try(PrintWriter writer = new PrintWriter(new FileWriter(filePath))){
            writer.println("Subject Name: " +subj.getName() +"            Max Mark: " +subj.getFullMark() +"\n");

            writer.println(String.format("%-40s%-20s%-10s%-10s","Student name","Student number","GPA","Grade"));


            for (Student student : students) {
                writer.println(String.format("%-40s%-20s%-10s%-10s",student.getName() ,student.getCode(),student.getGPA(),student.getGrade()));
            };
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
