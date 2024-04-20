package com.company;

import java.util.Vector;

public class DataBundle {
    private Vector<Student> students;
    private Subject subject;

    public DataBundle(Vector<Student> students, Subject subject) {
        this.students = students;
        this.subject = subject;
    }

    public Vector<Student> getStudents() {
        return students;
    }

    public Subject getSubject() {
        return subject;
    }
}
