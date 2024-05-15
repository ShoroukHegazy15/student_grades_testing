package com.company;

public class Student {
    private String name;
    private String code;
    private int activitiesMark;
    private int oralPractMark;
    private int midtermMark;
    private int finalExamMark;
    private String grade;
    private double GPA;
    private int totalMark;
    private boolean valid;

    public Student(String name, String code, int activitiesMark,int oralPractMark, int midtermMark, int finalExamMark) {
        this.name = name;
        this.code = code;
        this.activitiesMark = activitiesMark;
        this.oralPractMark = oralPractMark;
        this.midtermMark = midtermMark;
        this.finalExamMark = finalExamMark;

    }

//    public String printStudent(){
//
//        int totalMark = getTotalMark();
//        String grade = getGrade();
//        double GPA = getGPA();
//
//        StringBuilder stringBuilder = new StringBuilder();
//        System.out.println("name and code : " + this.name+" , "+code);
//        System.out.println("\t Activities Mark: " + this.activitiesMark);
//        System.out.println("\t Oral/practical Mark: "+this.oralPractMark);
//        System.out.println("\t Midterm Mark: "+this.midtermMark);
//        System.out.println("\t Final exam Mark: "+this.finalExamMark);
//        System.out.println("\t Total Mark: "+totalMark);
//        System.out.println("\t Grade: "+grade);
//        System.out.println("\t GPA: "+GPA);
//
//        return stringBuilder.toString();
//    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setActivitiesMark(int activitiesMark) {
        this.activitiesMark = activitiesMark;
    }

    public void setOralPractMark(int oralPractMark) {
        this.oralPractMark = oralPractMark;
    }

    public void setMidtermMark(int midtermMark) {
        this.midtermMark = midtermMark;
    }

    public void setFinalExamMark(int finalExamMark) {
        this.finalExamMark = finalExamMark;
    }

    public void calculatetotalMark()
    {
        totalMark = getActivitiesMark() + getOralPractMark()
                + getFinalExamMark() + getMidtermMark();
    }

    public void setTotalMark(int totalMark) {
        this.totalMark = totalMark;
    }
    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public int getActivitiesMark() {
        return activitiesMark;
    }

    public int getOralPractMark() {
        return oralPractMark;
    }

    public int getMidtermMark() {
        return midtermMark;
    }

    public String getGrade() {
        calculateGrade_GPA();
        return grade;
    }
    public int getTotalMark() {

        calculatetotalMark();
        return totalMark;
    }

    /**
     * Calculates and returns the GPA (Grade Point Average) of the student based on the total mark.
     * @return the GPA of the student
     */

    public double getGPA() {
        calculateGrade_GPA();
        return GPA;
    }


    public void calculateGrade_GPA()
    {
        calculatetotalMark();

        if ((totalMark >= 97) && (totalMark <= 100)) {
            grade = "A+";
            GPA = 4.0;
        }
        else if ((totalMark >= 93) && (totalMark < 97)) {
            grade = "A";
            GPA = 4.0;
        }
        else if ((totalMark >= 89)&& (totalMark < 93)) {
            grade = "A-";
            GPA = 3.7;
        }
        else if ((totalMark >= 84) && (totalMark < 89)) {
            grade = "B+";
            GPA = 3.3;
        }
        else if ((totalMark >= 80) && (totalMark < 84)) {
            grade = "B";
            GPA = 3.0;
        }
        else if ((totalMark >= 76) && (totalMark < 80)) {
            grade = "B-";
            GPA = 2.7;
        }
        else if ((totalMark >= 73) && (totalMark < 76)) {
            grade = "C+";
            GPA = 2.3;
        }
        else if ((totalMark >= 70) && (totalMark < 73)) {
            grade = "C";
            GPA = 2.0;
        }
        else if ((totalMark >= 67) && (totalMark < 70)) {
            grade = "C-";
            GPA = 1.7;
        }
        else if ((totalMark >= 64) && (totalMark < 67)) {
            grade = "D+";
            GPA = 1.3;
        }
        else if ((totalMark >= 60) && (totalMark < 64)) {
            grade = "D";
            GPA = 1.0;
        }
        else if (totalMark < 60) {
            grade = "F";
            GPA = 0.0;
        }
        else {
            System.out.println("out of range !");
        }
    }

    public int getFinalExamMark() { return finalExamMark; }

    public boolean isValid(){
        boolean isValid=isNameValid(this.name) && isCodeValid(this.code) && isMarkValid(this.activitiesMark) && isMarkValid(this.oralPractMark) && isMidMarkValid(this.midtermMark) && isFinalMarkValid(this.finalExamMark);
        if(!isValid){
            System.err.println("In subject:"+this.name+"'s"+"info" );
            return false;
        }
        else
            return true;
    }

    public boolean isNameValid(String name){
        if (!name.matches("^[a-zA-Z ]+$")) {
            System.err.println("Invalid character in word: " + name);
            return false;
        } else if (name.startsWith(" ")) {
            System.err.println("Word starts with a space: " + name);
            return false;
        } else {
            return true;
        }
    }

    public boolean isCodeValid(String code){
        code = code.trim();

        // Remove spaces from the code
        code = code.replaceAll("\\s+", "");

        if(code.length()<8){
            System.err.println("Student code is short: " + code);
            return false;
        }
        if(code.length()>8){
            System.err.println("Student code is long: " + code);
            return false;
        }
         if (!code.matches("^\\d{7}[\\dA-Za-z]$")) {
            System.err.println("Invalid Student code: " + code);
            return false;
        }else{
            return true;
        }
    }
    public boolean isMarkValid(int mark){

        if (!(mark>=0 && mark<=10)) {
            System.err.println("Mark is outside the range: " + mark);
            return false;
        }else{
            return true;
        }
    }

    public boolean isMidMarkValid(int mark){
        if (!(mark>=0 && mark<=20)) {
            System.err.println("Mark is outside the range: " + mark);
            return false;
        }else{
            return true;
        }
    }
    public boolean isFinalMarkValid(int mark){
        if (!(mark>=0 && mark<=60)) {
            System.err.println("Mark is outside the range: " + mark);
            return false;
        }else{
            return true;
        }
    }
    public boolean isTotalMarkValid(int mark){
        if (!(mark>=0 && mark<=100)) {
            System.err.println("Total Mark is outside the range: " + mark);
            return false;
        }else{
            return true;
        }
    }
}
