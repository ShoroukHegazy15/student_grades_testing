package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

public class OutputFileHandler {

    public void printFile(Vector<Student>students,String filePath){
        try(PrintWriter writer = new PrintWriter(new FileWriter(filePath))){
            //writer.println("Subject name: "+sbj.getName()+"\tSubject code: "+sbj.getFullMark());
            writer.println(String.format("%-40s%-15s","Student name","Student number"));

            for (Student student : students) {
                writer.println(String.format("%-40s%-15s",student.getName() ,student.getCode()));
            };
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
