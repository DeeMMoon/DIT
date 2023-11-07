package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("If you want exit input \'y\', else input something");
            String a = scanner.nextLine();
            if (a.equals("y"))
                break;
            list.add(createPerson(scanner));
        }
        showInfoWithSortByLastname(list);
    }

    private static Person createPerson(Scanner scanner){
        Person person = new Person();
        System.out.println("Input firstname");
        person.setFirstName(scanner.nextLine());
        System.out.println("Input lastname");
        person.setLastName(scanner.nextLine());
        return person;
    }

    private static void showInfoWithSortByLastname(List<Person> people){
        people.stream().sorted(Comparator.comparing(Person::getLastName)).forEach(x -> System.out.println(
                "Person info:" + "\n" +
                        "firstname: " + x.getFirstName() + "\n" +
                        "lastname: " + x.getLastName() + "\n"
        ));
    }
}
