
/**
 * This class examines a text file containing students' grades for a unit and analyses the data.

 * @author (Ayodeji)
 * @version (1.0)
 */

//Importing the relevant java libraries
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class StudentsMarksAnalzer
{
    // Constants for the indices of the student marks in the split line array
    private static final int A1 = 3; //accessing the element at index 3 in the splitLine array, which corresponds to the first assignment mark//
    private static final int A2 = 4; //accessing the element at index 4 in the splitLine array, which corresponds to the second assignment mark//
    private static final int A3 = 5; //accessing the element at index 5 in the splitLine array, which corresponds to the third assignment mark//

    public static void main(String[] args) {
        String fileName = "";
        
                try {
            // Request the user to input the name of the file that contains the student information
            System.out.println("Enter the file name: ");
            Scanner scanner = new Scanner(System.in);
            fileName = scanner.nextLine();

            // Create a Scanner object for the text file
            Scanner fileScanner = new Scanner(new File(fileName));

            // Read the unit name from the file
            String unitName = fileScanner.nextLine();

            // Skip the column headers line of the document
            fileScanner.nextLine();

            // Creation of an array list to store the students
            List<Student> students = new ArrayList<>();

            // Read the students' marks from the file, ignoring comments
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();

                // Ignore lines enclosed in double quotation marks as they represent comments
                if (line.startsWith("\"") && line.endsWith("\"")) {
                    continue;
                }

                // Split the line into the student's last name, first name, student ID, and marks
                String[] splitLine = line.split(",");

                // Validate the input data
                if (splitLine.length == 6) {
                    try {
                        // Parse the marks as doubles, and handle missing scores by assigning 0
                        double assignment1 = parseMark(splitLine[A1]);
                        double assignment2 = parseMark(splitLine[A2]);
                        double assignment3 = parseMark(splitLine[A3]);

                        // Create a new Student object
                        Student student = new Student(splitLine[0], splitLine[1], splitLine[2], assignment1, assignment2, assignment3);

                        // Add the student to the list
                        students.add(student);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid mark(s) for " + splitLine[0] + ", " + splitLine[1] + ": " + line);
                    }
                } else {
                    System.out.println("Invalid number of fields for " + splitLine[0] + ", " + splitLine[1] + ": " + line);
                }
            }

            // Close the Scanner object
            fileScanner.close();

            // Print the unit name and the students' marks
            System.out.println("Unit name: " + unitName);
            System.out.println("Students:");
            for (Student student : students) {
                System.out.println(student.getLastName() + ", " + student.getFirstName() + " (" + student.getStudentID() + ")");
                System.out.println("A1: " + student.getAssignment1());
                System.out.println("A2: " + student.getAssignment2());
                System.out.println("A3: " + student.getAssignment3());
                System.out.println("Total: " + student.getTotalMark());
                System.out.println("Average: " + student.getAverageMark());
                System.out.println();
            }

        //catch block to address instances where the program tries to open a file, but the specified file is not found
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        }
    }

    // A method to parse marks as doubles and handle missing scores by assigning 0
    private static double parseMark(String mark) {
        if (mark.isEmpty()) {
            return 0.0;
        } else {
            return Double.parseDouble(mark);
        }
    }
}
    
class Student {
    private String lastName; // Instance variables to store students' last name
    private String firstName; // Instance variables to store students' first name
    private String studentID; // Instance variables to store student's ID
    private double assignment1; // Instance variables to store student's mark for assignment 1
    private double assignment2; // Instance variables to store student's mark for assignment 2
    private double assignment3; // Instance variables to store student's mark for assignment 3

    //Constructor to initialize a Student object with their details and marks
    public Student(String lastName, String firstName, String studentID, double assignment1, double assignment2, double assignment3) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.studentID = studentID;
        this.assignment1 = assignment1;
        this.assignment2 = assignment2;
        this.assignment3 = assignment3;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getStudentID() {
        return studentID;
    }

    public double getAssignment1() {
        return assignment1;
    }

    public double getAssignment2() {
        return assignment2;
    }

    public double getAssignment3() {
        return assignment3;
    }

    public double getTotalMark() {
        return assignment1 + assignment2 + assignment3;
    }

    public double getAverageMark() {
        return getTotalMark() / 3.0;
    }
}
