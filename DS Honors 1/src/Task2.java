import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Task2 {
    /*
    Task 2: Name for Both Genders
    Objective: to identify and display names that are used for both genders in a specified file.
    Features:
    Set Operations: Utilize Set data structures to efficiently find common names between genders.
    User Input: Prompt the user to specify the file (year).
    Display: Present the names used for both genders neatly, with clear labeling.
     */
    public static void main(String[] args) throws Exception {
        //Scanner to read user input
        Scanner input = new Scanner(System.in);
        //flag for user input while loop
        boolean validInput = false;
        //use input variable
        int year = 0;

        //While loop that repeats until user enters correct input
        while(!validInput){
            try {
                validInput = true;
                //User prompted to enter year
                System.out.println("Enter a year (2001-2010): ");
                year = input.nextInt();
                //if statement tests if input is correct
                if (year < 2001 || year > 2010) {
                    System.out.println("Year must be between 2001 and 2010. Enter a year: ");
                    validInput = false;
                }
            }
            catch(InputMismatchException e) {
                System.out.println("Numerical input only!");
                validInput = false;
                input.nextLine();
            }
        }
        //Creates the file path using the user inputted year
        //String filename = "/Users/braxtonconley/IdeaProjects/DS Honors 1/src/babynamesranking" + year + ".txt";
        String filename = "(Delete this and paste here)" + year + ".txt";
        File file = new File(filename);

        //Checks if file exists and runs method if it does
        if (file.exists()) {
            int counter = 0;
            System.out.println("\nNames used for both genders in " + year + "\n------------------------------------");
            //For loop to run a display names neatly
            for (Object name : duplicates(file)) {
                System.out.printf("%-14s", name);
                counter++;
                //Make a new line for every 3 names printed creating 3 columns of names
                if (counter % 3 == 0)
                    System.out.println();
            }
        }
        else {
            System.out.println("File " + filename + " does not exist");
        }
    }

    public static Set duplicates(File file) throws Exception {
        //Set for holding all names in file
        Set<String> allSet = new HashSet<>();
        //Set for holding names used for both genders
        Set<String> doubleSet = new HashSet<>();
        //String array that holds each line of file
        String[] line;

        try {
            //Scanner that reads each line of file
            Scanner myReader = new Scanner(file);
            //While loop that reads through each line of file until none left
            while (myReader.hasNextLine()) {
                //data variable holds each line of file
                String data = myReader.nextLine();
                line = data.split("\\s+");
                //if the unique set already contains the name in the male column
                //add the string to the set of duplicates
                if (allSet.contains(line[1])) {
                    doubleSet.add(line[1]);
                } else {
                    //if the unique set does not contain the male column name already add it to the unique set
                    allSet.add(line[1]);
                }
                //if the unique set already contains the name in the male column
                //add the string to the set of duplicates
                if (allSet.contains(line[3])) {
                    doubleSet.add(line[3]);
                } else {
                    //if the unique set does not contain the male column name already add it to the unique set
                    allSet.add(line[3]);
                }
            }
            //close the reader
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found.");
        }

        //Returns the set of names used for both genders
        return doubleSet;
    }
}

