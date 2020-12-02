package ru.itis.aleynik.task2.student;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Student {
    private String name;
//    private int nameLength; //bytes
    private char gender;
    private Date birthDate;
    private String group;
    private int studentLength; //bytes

    public Student() {
    }

    public Student(String name) {
        setName(name);
    }

    public Student(String name, Date date) {
        setName(name);
        setBirthDate(date);
    }

    public Student(String name, char gender, String birthDate, String group) {
        setName(name);
        setGender(gender);
//        setBirthDate(birth);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = formatter.parse(birthDate);
            System.out.println(formatter.format(date));
            setBirthDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        setGroup(group);
    }

    public Student(String name, char gender, Date birthDate, String group) {
        setName(name);
        setGender(gender);
        setBirthDate(birthDate);
        setGroup(group);
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {

        return birthDate;
    }

    public String getStringBirthDate() {
        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(birthDate);
    }


    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return name + ", " + gender + ", " + getStringBirthDate() + ", " + group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return gender == student.gender &&
                birthDate == student.birthDate &&
                Objects.equals(name, student.name) &&
                Objects.equals(group, student.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gender, birthDate, group);
    }
}
