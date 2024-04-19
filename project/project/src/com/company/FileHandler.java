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
//    public boolean isValid(String[] info){
//      boolean isValid=isNameValid(info[0]) && isCodeValid(info[1]) && isMarkValid(info[2]) && isMarkValid(info[3]) && isMidMarkValid(info[4]) && isFinalMarkValid(info[5]);
//      if(!isValid){
//          System.err.println("In student:"+info[0]+"'s"+"info" );
//          return false;
//      }
//      else
//          return true;
//    }
//
//    public boolean isNameValid(String name){
//        if (!name.matches("^[a-zA-Z ]+$")) {
//            System.err.println("Invalid character in word: " + name);
//            return false;
//        } else if (name.startsWith(" ")) {
//            System.err.println("Word starts with a space: " + name);
//            return false;
//        } else {
//            return true;
//        }
//    }
//    public boolean isCodeValid(String code){
//        if(code.length()!=8){
//            System.err.println("Student code is short: " + code);
//            return false;
//        }
//        else if (!code.matches("^\\d{7}[\\dA-Za-z]$")) {
//            System.err.println("Invalid Student code: " + code);
//            return false;
//        }else{
//            return true;
//        }
//    }
//    public boolean isMarkValid(String mark){
//        int num = Integer.parseInt(mark);
//        if(mark.contains(".")){
//            System.err.println("Mark must be integer: " + mark);
//            return false;
//        }
//        else if (!(num>=0 && num<=10)) {
//            System.err.println("Mark is outside the range: " + mark);
//            return false;
//        }else{
//            return true;
//        }
//    }
//
//    public boolean isMidMarkValid(String mark){
//        int num = Integer.parseInt(mark);
//        if(mark.contains(".")){
//            System.err.println("Mark must be integer: " + mark);
//            return false;
//        }
//        else if (!(num>=0 && num<=20)) {
//            System.err.println("Mark is outside the range: " + mark);
//            return false;
//        }else{
//            return true;
//        }
//    }
//    public boolean isFinalMarkValid(String mark){
//        int num = Integer.parseInt(mark);
//        if(mark.contains(".")){
//            System.err.println("Mark must be integer: " + mark);
//            return false;
//        }
//        else if (!(num>=0 && num<=60)) {
//            System.err.println("Mark is outside the range: " + mark);
//            return false;
//        }else{
//            return true;
//        }
//    }


    public static void main(String[] args) {
        String filePath = "D:/senior 2 semester 2/project/grades.txt";
        FileHandler fileHandler = new FileHandler();
        fileHandler.setFilePath(filePath);
        Vector<Student> lines = fileHandler.GetData();

        // Print the integers read from the file
        System.out.println("Students info from file:");
        for (Student st : lines) {
            st.printStudent();
        }
        String outputFilePath = "D:/senior 2 semester 2/project/output.txt";
        OutputFileHandler output= new OutputFileHandler();
        output.printFile(lines,outputFilePath);
    }
}
