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

    public Vector<Student> GetData() {

        Subject subj=new Subject();
        Vector<Student> list = new Vector<Student>();
        BufferedReader reader;
        boolean isFirstLine = true;
        try
        {
            reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            while (line  != null) {
                if(!isFirstLine) {
                    String[] info = line.split(",");
                    Student st= new Student(info[0],info[1],Integer.parseInt(info[2]),Integer.parseInt(info[3]),Integer.parseInt(info[4]),Integer.parseInt(info[5]));
                    if(st.isValid()){
                        //Student st= new Student(info[0],info[1],Integer.parseInt(info[2]),Integer.parseInt(info[3]),Integer.parseInt(info[4]),Integer.parseInt(info[5]));
                        list.add(st);
                    }
                }
                else {
                    String[] info = line.split(",");
                    subj= new Subject(info[0],info[1],Integer.parseInt(info[2]));
                    if(subj.isValid()){
                        System.out.println("Subject name: "+ subj.getName());
                        System.out.println("Subject code: "+ subj.getCode());
                    }
                    isFirstLine = false;
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return list;
    }
}