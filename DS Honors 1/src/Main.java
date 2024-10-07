import java.util.*;
import java.io.*;

public class Main {
    /*
    Task 1: Baby Name Popularity Ranking
    Objective: Develop an interactive program that allows the user to input the year, gender, and name to retrieve the ranking of the name for that specific year and gender.
            Features:
    User Input: Prompt the user to enter the year, gender, and name.
    Validation: Ensure the inputs are valid (e.g., year within the range, valid gender, non-empty name).
    Data Retrieval: Extract data from the corresponding file and locate the name's ranking.
    User Interaction: Allow the user to perform multiple inquiries or exit the program through user input.
    Error Handling: Gracefully handle any errors or exceptions, providing meaningful feedback to the user.
     */
    public static void main(String[] args) {
        //Scanner for taking user input
        Scanner input = new Scanner(System.in);
        //Flag used for outer while loop
        boolean mainFlag = true;
        //While loop that allows the user to run entire program again
        while (mainFlag) {
            //variables for user input
            int year = 0;
            String gender = "";
            String name = "";
            //Flag used for user input while loops
            boolean validInput = false;

            //While loop for getting correct user input for year
            while (!validInput) {
                try {
                    validInput = true;
                    //User is prompted to enter year between 2001 and 2010
                    System.out.println("Enter a year between 2001 and 2010: ");
                    year = input.nextInt();
                    input.nextLine();
                    //If statement to test that the input is within accepted range
                    //If not in range loops again
                    if (year < 2001 || year > 2010) {
                        System.out.println("Year not in range. Try again.");
                        validInput = false;
                    }
                }
                catch(InputMismatchException e) {
                    System.out.println("Numerical input only!");
                    validInput = false;
                    input.nextLine();
                }
            }

            //Reset flag for next user input
            validInput = false;
            //While loop for getting correct user string input for gender
            while (!validInput) {
                validInput = true;
                //User is prompted to enter gender
                //Only exactly "male" or "female" is accepted
                System.out.println("Enter the gender of the baby (type \"male\" or \"female\"): ");
                gender = input.nextLine();
                //If statement to test that the input is correctly typed
                //If not in correct input loops again
                if (!gender.equalsIgnoreCase("male") && !gender.equalsIgnoreCase("female")) {
                    System.out.println("Invalid gender. Try again.");
                    validInput = false;
                }
            }

            //Reset flag for next user input
            validInput = false;
            //While loop for getting correct user string input for the name or beginning of a name
            while (!validInput) {
                validInput = true;
                //User prompted to enter name or substring
                System.out.println("Enter the name (Must begin with capital letter): ");
                name = input.nextLine();
                //If statement to make sure user made any sort of input
                if (name.isEmpty()) {
                    System.out.println("Must input a name!");
                    validInput = false;
                }
            }

            //Creates the file path using the user inputted year
            //String filepath = "/Users/braxtonconley/IdeaProjects/DS Honors 1/src/babynamesranking" + year + ".txt";
            String filepath = "(Delete this and paste here)" + year + ".txt";
            File file = new File(filepath);
            //Run finder method
            //Pass file, name, and gender to method
            //User foreach loop to print map of names and rank starting with substring
            System.out.println("--------------------------------------------");
            System.out.println(finder(file, name, gender, year));
            System.out.println("--------------------------------------------");

            //Flag to for below while loop
            boolean decisionFlag = true;
            //User options for making another query or exiting program
            System.out.println("(1) Another Query:");
            System.out.println("(2) Exit program:");
            System.out.println("Enter the option number you would like to do: ");
            //While loop that runs until user either ends program or wants to make another entry
            while (decisionFlag) {
                try {
                    int option = input.nextInt();
                    input.nextLine();

                    //If statement to test and decide program direction
                    if (option == 1) {
                        decisionFlag = false;
                    } else if (option == 2) {
                        decisionFlag = false;
                        mainFlag = false;
                    } else {
                        System.out.println("Enter a valid number!");
                    }
                }
                catch(InputMismatchException e) {
                    System.out.println("Numerical input only!");
                    input.nextLine();
                }
            }
        }


    }
    public static String finder(File file, String name, String gender, int year){
        //This string will hold the rank of the requested name
        String ranking = "";
        //Array that holds each line of file
        String[] line;

        try {
            //Scanner to read file
            Scanner myReader = new Scanner(file);
            //While loop that reads through each line of the file until none left
            while (myReader.hasNextLine()) {
                //data variable holds each line of file
                String data = myReader.nextLine();
                //If the substring is found on the line, the line is split into an array by spaces
                if(data.contains(name)){
                    line = data.split("\\s+");
                    //If the user entered male for the gender and the male column is equal to
                    //the specified name, the ranking from the rank column is stored
                    if(gender.equals("male")){
                        if(line[1].equals(name)){
                            ranking = line[0];
                        }
                    } else {
                        //If the user entered female for the gender and the female column is equal to
                        //the specified name, the ranking from the rank column is stored
                        if(line[3].equals(name)){
                            ranking = line[0];
                        }
                    }
                }
            }
            //Close the reader
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found.");
        }

        //If a rank was stored and the gender is male the information is returned for males
        if(!ranking.isEmpty()){
            return name + " was ranked #" + ranking + " for " + gender + "s in " + year + ".";
        } else {
            //If rank is empty no name was found
            return "Name not found";
        }
    }
}

