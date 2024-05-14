package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class FileHandler {
    private String filePath;

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public DataBundle GetData() {
        Subject subj = new Subject();
        Vector<Student> list = new Vector<>();
        BufferedReader reader;
        boolean isFirstLine = true;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            while (line != null) {
                if (!isFirstLine) {
                    Student st = parseStudent(line);
                    if (st != null && st.isValid()) {
                        list.add(st);
                    } else {
                        reader.close();
                        throw new IllegalArgumentException("Invalid student data format: " + line);
                    }
                } else {
                    subj = parseSubject(line);
                    if (subj == null || !subj.isValid()) {
                        reader.close();
                        throw new IllegalArgumentException("Invalid subject header format: " + line);
                    }
                    isFirstLine = false;
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null; // Indicate error in reading file
        }
        return new DataBundle(list, subj);
    }

    private Student parseStudent(String line) {
        try {
            String[] info = line.split(",");
            return new Student(info[0], info[1], Integer.parseInt(info[2]), Integer.parseInt(info[3]), Integer.parseInt(info[4]), Integer.parseInt(info[5]));
        } catch (Exception e) {
            System.err.println("Failed to parse student: " + e.getMessage());
            return null;
        }
    }

    private Subject parseSubject(String line) {
        try {
            String[] info = line.split(",");
            return new Subject(info[0], info[1], Integer.parseInt(info[2]));
        } catch (Exception e) {
            System.err.println("Failed to parse subject: " + e.getMessage());
            return null;
        }
    }
}
