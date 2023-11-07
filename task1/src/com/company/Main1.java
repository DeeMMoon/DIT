package com.company;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Person person = createPerson(scanner);
        System.out.println("firstname:" + person.getFirstName() + "\n" + "lastname:" +  person.getLastName());
    }

    private static Person createPerson(Scanner scanner){
        Person person = new Person();
        System.out.println("Input firstname");
        person.setFirstName(scanner.nextLine());
        System.out.println("Input lastname");
        person.setLastName(scanner.nextLine());
        return person;
    }
}
