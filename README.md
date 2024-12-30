Sorting Program

A simple Java console application that reads student data from a CSV file (named sortstudent.csv) and allows you to:

Sort by ID
Sort by First Name
Sort by Last Name
Search for a Student by First Name
The program then displays the sorted list or informs you if a name was not found.

1. Prerequisites

Java Development Kit (JDK) 8 or higher.
A CSV file named sortstudent.csv in the same directory as the Java source code, with the correct data format.
2. Project Structure

your_project_directory
├── Sorting.java
├── Text.java
└── sortstudent.csv
Text.java: A simple data container class (id, first, last).
Sorting.java: The main class containing the main method.
sortstudent.csv: Contains the student data to be read.
3. Compilation and Execution

Compile the code:
javac Sorting.java
(This will also compile Text.java automatically.)
Run the program:
java Sorting
4. Usage

After running the application, you will see:

Choose -n for sort by ID
Choose -f for sort by First name
Choose -l for sort by Last name
Choose -s for Search Student
Choose function:
Options
-n: Sorts the students by ID.
-f: Sorts the students by First Name.
-l: Sorts the students by Last Name.
-s: Prompts you for a first name and attempts to find that name in the original (unsorted) list.
5. CSV File Format

This program skips the first 7 lines of the CSV file as header/placeholder lines. Actual student data should begin after these 7 lines. Each line should have at least 4 comma-separated values, for example:

Row,ID,FirstName,LastName
Placeholder 1
Placeholder 2
Placeholder 3
Placeholder 4
Placeholder 5
Placeholder 6
1,622115040,SUCHANUN,SIRIJANYA
2,662115007,CHONCHANUN,KHACHONPHURITHANAKUL
3,662115016,NATTHAPHUM,CHAIKHAN
... etc.
In the provided data snippet, each record looks like:

1	622115040	SUCHANUN 	SIRIJANYA
2	662115007	CHONCHANUN 	KHACHONPHURITHANAKUL
3	662115016	NATTHAPHUM 	CHAIKHAN
4	662115039	MANAPAT 	KAEWLAI
...
35	672115049	ANAWAT 	JANDEE
(These should be converted into CSV lines with commas, skipping the first 7 lines as needed.)

6. Sample Output

Below is a sample interaction showing how the program might behave once you have your sortstudent.csv in place.

6.1 Sorting by ID (-n)
Command / Input:

java Sorting
Choose -n for sort by ID
Choose -f for sort by First name
Choose -l for sort by Last name
Choose -s for Search Student
Choose function: -n
Sample Output (showing sorted by ID in ascending order):

622115040 SUCHANUN SIRIJANYA
662115007 CHONCHANUN KHACHONPHURITHANAKUL
662115016 NATTHAPHUM CHAIKHAN
662115039 MANAPAT KAEWLAI
672115001 KATIKA KANTHASON
672115002 KRITTAMETH TANSUWAN
672115004 JITTIPAT KAEWIJIT
...
(All entries sorted numerically by their ID.)

6.2 Sorting by First Name (-f)
Command / Input:

java Sorting
Choose -n for sort by ID
Choose -f for sort by First name
Choose -l for sort by Last name
Choose -s for Search Student
Choose function: -f
Sample Output (showing sorted by First Name alphabetically):

ANAWAT JANDEE
CHANLACHAT PANYOYAI
CHANCHAKORN JULLAPECH
CHANYA BUNRUEANG
CHETSADA KANKARN
CHIDNUCHA POBKEEREE
CHONCHANUN KHACHONPHURITHANAKUL
...
6.3 Sorting by Last Name (-l)
Command / Input:

java Sorting
Choose function: -l
Sample Output (showing sorted by Last Name alphabetically):

AEIPHINGCHAI
AEINJANG
ABHICHARTTIBUTRA
BUNRUEANG
BURGER
CHAIKHAN
CHAMPATHIP
...
6.4 Searching for a Student (-s)
Command / Input:

java Sorting
Choose function: -s
Enter name that you want to search: NATTHAPHUM
Sample Output:

Found at original index: 3
(If “NATTHAPHUM” was the 3rd entry in the original unsorted list. If not found, it would print “Not found.”)

7. Full Source Code

Below is the complete Java code (Sorting.java and Text.java) for reference.
(Make sure these classes are in separate files named Sorting.java and Text.java respectively.)

// Text.java
public class Text {
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
// Sorting.java
import java.util.*;
import java.io.*;

public class Sorting {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner file = new Scanner(new File("sortstudent.csv"));
        Scanner input = new Scanner(System.in);
        List<Text> students = new ArrayList<>();
        List<Text> originalList = new ArrayList<>();

        // Skip header lines (7 lines in the CSV)
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
                // Sort by ID
                students.sort(Comparator.comparing(Text::getId));
                break;
            case "-f":
                // Sort by first name
                students.sort(Comparator.comparing(Text::getFirst));
                break;
            case "-l":
                // Sort by last name
                students.sort(Comparator.comparing(Text::getLast));
                break;
            case "-s":
                // Search for a student by first name in the original list
                System.out.print("Enter name that you want to search: ");
                String name = input.nextLine().toUpperCase();
                int index = findOriginalIndex(originalList, name);
                if (index != -1) {
                    // Print 1-based index
                    System.out.println("Found at original index: " + (index + 1));
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
Thank You
