/* Подумать над структурой класса Ноутбук для магазина техники - выделить поля и методы. Реализовать в java.
Создать множество ноутбуков.
Написать метод, который будет запрашивать у пользователя критерий (или критерии) фильтрации и выведет ноутбуки, отвечающие фильтру. 
Критерии фильтрации можно хранить в Map. Например: “Введите цифру, соответствующую необходимому критерию:
1 - ОЗУ
2 - Объем ЖД
3 - Операционная система
4 - Цвет …
Далее нужно запросить минимальные значения для указанных критериев - сохранить параметры фильтрации можно также в Map.
Отфильтровать ноутбуки из первоначального множества и вывести проходящие по условиям. */

import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class hw1 {
    public static void main(String[] args) {
        Map<String, String> propertisMap = new HashMap<>();
        Set<Laptop> set = new HashSet<>();

        goMainMenu(set, propertisMap);
    }

    static void goMainMenu(Set<Laptop> set, Map<String, String> propMap) {
        Scanner iScan = new Scanner(System.in);
        boolean flag = true;

        while (flag) {
            System.out.println("\nВыберите операцию:" +
                    "\nF - Найти ноутбук по параметрам" +
                    "\nQ - Выйти из программы\n");

            System.out.print("Введите символ требуемой операции: ");
            String value = iScan.nextLine();

            switch (value) {
                case "F":
                    listOfLaptops(set);
                    defineSearchProperties(propMap, set);
                    break;
                case "Q":
                    flag = false;
                    System.exit(0);
                    break;
                default:
                    System.out.println("Cимвол операции введен неверно. Повторите ввод.");
            }
        }
    }

    static void listOfLaptops(Set<Laptop> set) {
        Laptop type1 = new Laptop("i7", 16, 512, "Mac_OS", "blue");
        Laptop type2 = new Laptop("i8", 64, 256, "Windows_10", "gray");
        Laptop type3 = new Laptop("i9", 32, 512, "Mac_OS", "gray");
        Laptop type4 = new Laptop("i8", 16, 256, "Windows_11", "black");
        Laptop type5 = new Laptop("i8", 32, 256, "Windows_11", "blue");
        Laptop type6 = new Laptop("i7", 16, 128, "Windows_10", "black");
        Laptop type7 = new Laptop("i7", 32, 256, "Mac_OS", "black");
        Laptop type8 = new Laptop("i9", 64, 512, "Mac_OS", "gray");
        Laptop type9 = new Laptop("i8", 16, 256, "Windows_11", "black");

        set.add(type1);
        set.add(type2);
        set.add(type3);
        set.add(type4);
        set.add(type5);
        set.add(type6);
        set.add(type7);
        set.add(type8);
        set.add(type9);
    }

    static void defineSearchProperties(Map<String, String> propMap, Set<Laptop> set) {
        List<String> list1 = new ArrayList<>();
        Scanner iScan = new Scanner(System.in);
        boolean flag = true;

        while (flag) {
            System.out.println("\nВыберите параметр:" +
                    "\n1 - Процессор" +
                    "\n2 - Оперативная память" +
                    "\n3 - Объем жесткого диска" +
                    "\n4 - Операционная система" +
                    "\n5 - Цвет" +
                    "\nS - Показать ноутбуки" +
                    "\nQ - Выйти в основное меню");

            System.out.print("\nВведите символ требуемой операции: ");
            String choice = iScan.next();
            String parameter;

            switch (choice) {
                case "1":
                    parameter = "processor";
                    createListOfCriterias(propMap, parameter);
                    System.out.println("Введен требуемый тип процессора.");
                    break;
                case "2":
                    parameter = "ram";
                    createListOfCriterias(propMap, parameter);
                    System.out.println("Введен минимальный объем ОЗУ.");
                    break;
                case "3":
                    parameter = "hdd";
                    createListOfCriterias(propMap, parameter);
                    System.out.println("Введен минимальный объем диска.");
                    break;
                case "4":
                    parameter = "os";
                    createListOfCriterias(propMap, parameter);
                    System.out.println("Введен тип ОС.");
                    break;
                case "5":
                    parameter = "color";
                    createListOfCriterias(propMap, parameter);
                    System.out.println("Введен цвет.");
                    break;
                case "S":
                    System.out.println("\nПодходящие варианты:");
                    list1 = findLaptops(propMap, set);
                    System.out.println(String.join("\n", list1));
                    break;
                case "Q":
                    flag = false;
                    goMainMenu(set, propMap);
                    break;
                default:
                    System.out.println("Cимвол операции введен неверно. Повторите ввод.");
                    break;
            }
        }
    }

    static void createListOfCriterias(Map<String, String> propMap, String parameter) {
        System.out.print("\nВведите требуемое значение для параметра " + parameter + ": ");
        Scanner iScan = new Scanner(System.in);
        String value = iScan.nextLine();

        if (propMap.containsKey(parameter)) {
            propMap.put(parameter, value);
            System.out.println("Значение перезаписано\n");
        } else {
            propMap.put(parameter, value);
            System.out.println("Значение принято\n");
        }

        System.out.println(propMap);
    }

    static List<String> findLaptops(Map<String, String> propMap, Set<Laptop> set) {
        int i = 1;
        List<String> list = new ArrayList<>();
        for (Laptop notebook : set) {
            boolean validate = true;
            for (Map.Entry<String, String> entry : propMap.entrySet()) {
                if (entry.getKey().equals("processor")) {
                    if (!notebook.getProcessor().toLowerCase().contains(entry.getValue().toLowerCase())) {
                        validate = false;
                    }
                }
                if (entry.getKey().equals("ram")) {
                    if (!(notebook.getRam() >= Integer.parseInt(entry.getValue()))) {
                        validate = false;
                    }
                }
                if (entry.getKey().equals("hdd")) {
                    if (!(notebook.getHdd() >= Integer.parseInt(entry.getValue()))) {
                        validate = false;
                    }
                }
                if (entry.getKey().equals("os")) {
                    if (!(notebook.getOs().toLowerCase().contains(entry.getValue().toLowerCase()))) {
                        validate = false;
                    }
                }
                if (entry.getKey().equals("color")) {
                    if (!notebook.getColor().toLowerCase().contains(entry.getValue().toLowerCase())) {
                        validate = false;
                    }
                }
            }

            if (validate) {
                list.add("Вариант " + i + " : " + notebook);
                i++;
            }
        }
        return list;
    }
}