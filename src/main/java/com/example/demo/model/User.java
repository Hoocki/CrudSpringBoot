package com.example.demo.model;

public class User {

    /**
     * Имя пользователя
     */
    private String name;

    /**
     * Возраст пользователя
     */
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 120 || age < 0) {
            System.out.println("Неверно ввдённый возраст");
            return;
        }
        this.age = age;
    }

}
