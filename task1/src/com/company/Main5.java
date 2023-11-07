package com.company;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main5 {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String select;
        while (true){
            System.out.println("Menu:");
            System.out.println("1.Add");
            System.out.println("2.Show");
            System.out.println("3.Show sorted unique");
            System.out.println("4.Exit");
            select = scanner.nextLine();
            switch (select){
                case "1":
                    personList.add(createPerson(scanner));
                    break;
                case "2":
                    showInfo(personList);
                    break;
                case "3":
                    showInfoWithSortByLastnameUnique(personList);
                    break;
                case "4":
                    System.exit(0);
                default:
                    System.out.println("Incorrect input");
            }
        }
    }

    private static Person createPerson(Scanner scanner){
        Person person = new Person();
        System.out.println("Input firstname");
        person.setFirstName(scanner.nextLine());
        System.out.println("Input lastname");
        person.setLastName(scanner.nextLine());
        return person;
    }

    private static void showInfo(List<Person> people){
        people.stream().forEach(x -> System.out.println(
                "Person info:" + "\n" +
                        "firstname: " + x.getFirstName() + "\n" +
                        "lastname: " + x.getLastName() + "\n"
        ));
    }

    public static <T> Predicate<T> distinctByKey(Function<T, Object> function) {
        Set<Object> seen = new HashSet<>();
        return t -> seen.add(function.apply(t));
    }

    private static void showInfoWithSortByLastnameUnique(List<Person> people){
        people.stream()
                .sorted(Comparator.comparing(Person::getLastName))
                .filter(distinctByKey(Person::getLastName))
                .forEach(x -> System.out.println(
                "Person info:" + "\n" +
                        "firstname: " + x.getFirstName() + "\n" +
                        "lastname: " + x.getLastName() + "\n"
        ));
    }
}
