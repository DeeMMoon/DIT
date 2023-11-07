package com.company;

import java.io.*;
import java.util.*;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main8{
    private static final String PATH_TO_FILE = "src/main/resources/file.txt";
    public static void main(String[] args) throws Exception {
        List<Person> personList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu(scanner);
        MenuItem createPerson = new MenuItem("Add", Main8::createPerson);
        MenuItem show = new MenuItem("Show", Main8::showInfo);
        MenuItem showSorted = new MenuItem("Show sorted unique", Main8::showInfoWithSortByLastnameUnique);
        MenuItem saveToFile = new MenuItem("Save to file", Main8::saveToFile);
        MenuItem readFromFile = new MenuItem("Read from file", Main8::readFromFile);
        MenuItem cleanDataInMemory = new MenuItem("Clear data in memory", Main8::cleanDataInMemory);
        menu.addMenuItem(createPerson);
        menu.addMenuItem(show);
        menu.addMenuItem(showSorted);
        menu.addMenuItem(saveToFile);
        menu.addMenuItem(readFromFile);
        menu.addMenuItem(cleanDataInMemory);
        while (true) {
            menu.showMenu();
            menu.action(personList);
        }
    }

    private interface Exec {
        void exec(List<Person> data) throws Exception;
    }

    private static class MenuItem{
        private String name;
        private Exec exec;

        public MenuItem(String name, Exec exec) {
            this.name = name;
            this.exec = exec;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Exec getExec() {
            return exec;
        }

        public void setExec(Exec exec) {
            this.exec = exec;
        }
    }

    private static class Menu {
        private List<MenuItem> items;
        private Scanner scanner;

        public Menu(List<MenuItem> items) {
            this.items = items;
        }

        public Menu(Scanner scanner) {
            items = new ArrayList<>();
            this.scanner = scanner;
        }

        public void showMenu(){
            for (int i = 0; i < items.size(); i++){
                System.out.println((i + 1) + "." + items.get(i).getName());
            }
            System.out.println((items.size() + 1) + ".Exit");
        }

        public void action(List<Person> list){
            int state = scanner.nextInt();
            if(state >= 1 && state < items.size() + 1){
                try {
                    items.get(state - 1).getExec().exec(list);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (state == items.size() + 1)
                System.exit(0);
        }

        public void addMenuItem(MenuItem menuItem){
            items.add(menuItem);
        }

    }

    private static void createPerson(List<Person> people){
        Scanner scanner = new Scanner(System.in);
        Person person = new Person();
        System.out.println("Input firstname");
        person.setFirstName(scanner.nextLine());
        System.out.println("Input lastname");
        person.setLastName(scanner.nextLine());
        people.add(person);
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

    private static void showInfo(List<Person> people){
        people.stream().forEach(x -> System.out.println(
                "Person info:" + "\n" +
                        "firstname: " + x.getFirstName() + "\n" +
                        "lastname: " + x.getLastName() + "\n"
        ));
    }

    private static void saveToFile(List<Person> list){
        try (FileOutputStream fos = new FileOutputStream(new File(PATH_TO_FILE));
             ObjectOutputStream oos = new ObjectOutputStream(fos);){
            oos.writeObject(list);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void readFromFile(List<Person> people){
        try(FileInputStream fis = new FileInputStream(new File(PATH_TO_FILE));
            ObjectInputStream ois = new ObjectInputStream(fis)){
            List <Person> list = (List<Person>) ois.readObject();
            people.addAll(list);
            people.stream().forEach(x -> System.out.println(
                    "Person info:" + "\n" +
                            "firstname: " + x.getFirstName() + "\n" +
                            "lastname: " + x.getLastName() + "\n"
            ));
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private static void cleanDataInMemory(List<Person> list){
        list.clear();
    }

    private static void exit(){
        System.exit(0);
    }
}

