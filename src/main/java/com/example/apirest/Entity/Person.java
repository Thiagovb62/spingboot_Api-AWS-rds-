package com.example.apirest.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;
    private String Name;
    private String LastName;
    private int age;

    public Person() {
    }

    public Person(String name, String lastName, int age) {
        Name = name;
        LastName = lastName;
        this.age = age;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", LastName='" + LastName + '\'' +
                ", age=" + age +
                '}';
    }
}
