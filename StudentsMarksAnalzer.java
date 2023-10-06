
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

    }
}
}

