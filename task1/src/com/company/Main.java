package com.company;

public class Main {

    public static void main(String[] args) {
	    Person person = new Person(args[0], args[1]);
        System.out.println(person.getFirstName() + "\n" + person.getLastName());
    }
}
