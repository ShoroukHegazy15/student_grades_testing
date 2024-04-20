package com.company;

public class Subject {

    private String name;
    private String code;
    private Integer FullMark;


    public Subject(){
        this.name = "";
        this.code = "";
        this.FullMark = 0;
    }
    public Subject(String name, String code, Integer fullMArk) {
        this.name = name;
        this.code = code;
        this.FullMark = fullMArk;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Integer getFullMark() {
        return FullMark;
    }

    public boolean isValid(){
        return isNameValid(this.name) && isCodeValid(this.code);
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

    public boolean isCodeValid(String code) {
        if (code == null || code.length() < 6 || code.length() > 7) {
            return false;
        }

        // Check first 3 characters are alphabetic
        if (!code.substring(0, 3).matches("^[a-zA-Z]+$")) {
            System.out.println("first 3 characters must be alphabetic");
            return false;
        }

        // Check last 3 characters are numeric (optionally ending with "s")
        String lastPart = code.substring(3);
        if (!lastPart.matches("^[0-9]+$") && !lastPart.matches("^[0-9]+s$")) {
            System.out.println("Subject code is wrong");
            return false;
        }
        return true;
    }


}
