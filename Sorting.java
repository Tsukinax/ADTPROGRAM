/*
 * Auther: Dalanphop Keawsiripong
 * Student ID: 672115018
 */


import java.util.*;
import java.io.*;

class Text {
    private String id;
    private String first;
    private String last;

    public Text(String id, String first, String last) {
        this.id = id;
        this.first = first.strip();
        this.last = last;
    }

    public String getId() {
        return id;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    @Override
    public String toString() {
        return id + " " + first + " " + last;
    }
}

public class Sorting {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner file = new Scanner(new File("sortstudent.csv"));
        Scanner input = new Scanner(System.in);
        List<Text> students = new ArrayList<>();
        List<Text> originalList = new ArrayList<>();

        // Skip header lines
        for (int i = 0; i < 7; i++) {
            file.nextLine();
        }

        // Read student data
        while (file.hasNextLine()) {
            String line = file.nextLine();
            String[] tokens = line.split(",");
            if (tokens.length >= 4) {
                Text student = new Text(tokens[1], tokens[2], tokens[3]);
                students.add(student);
                originalList.add(student); // Retain original order
            }
        }

        // Menu options
        System.out.println("Choose -n for sort by ID");
        System.out.println("Choose -f for sort by First name");
        System.out.println("Choose -l for sort by Last name");
        System.out.println("Choose -s for Search Student");

        System.out.print("Choose function: ");
        String mode = input.nextLine().toLowerCase();

        switch (mode) {
            case "-n":
                students.sort(Comparator.comparing(Text::getId));
                break;
            case "-f":
                students.sort(Comparator.comparing(Text::getFirst));
                break;
            case "-l":
                students.sort(Comparator.comparing(Text::getLast));
                break;
            case "-s":
                System.out.print("Enter name that you want to search: ");
                String name = input.nextLine().toUpperCase();
                int index = findOriginalIndex(originalList, name);
                if (index != -1) {
                    System.out.println("Found at original index: " + (index + 1)); // Adjusted to 1-based index
                } else {
                    System.out.println("Not found");
                }
                return;
            default:
                System.out.println("Invalid option -- Please enter -n, -f, -l, -s only");
                return;
        }

        // Print sorted students
        students.forEach(System.out::println);
    }

    private static int findOriginalIndex(List<Text> students, String name) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getFirst().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }
}


 
 
 
 
 
 
 
 
 

 