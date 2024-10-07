import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Task3 {
    /*
    Task 3: List of Names Starting with a Substring
    Objective: to enable the user to search for names beginning with a specified substring and display their rankings.
    Features:
    Substring Search: Implement efficient search algorithms to locate names starting with the given substring.
    User Input: Allow the user to input the filename, gender, and substring.
    Display: List the located names along with their rankings, sorted in alphabetical order.
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
                    System.out.println("Invalid Input gender. Try again.");
                    validInput = false;
                }
            }

            //Reset flag for next user input
            validInput = false;
            //While loop for getting correct user string input for the name or beginning of a name
            while (!validInput) {
                validInput = true;
                //User prompted to enter name or substring
                System.out.println("Enter the name or beginning substring (Must begin with capital letter): ");
                name = input.nextLine();
                //If statement to make sure user made any sort of input
                if (name.isEmpty()) {
                    System.out.println("Must input a name or beginning substring! ");
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
            System.out.println("--------------------------------------");
            System.out.println("List for names alphabetically starting with the substring " + name + " in " + year);
            System.out.println("--------------------------------------");
            finder(file, name, gender).forEach((k, v) -> System.out.println("Rank: " + v + " | Name: " + k));
            System.out.println("--------------------------------------");

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
    public static Map finder(File file, String name, String gender) {
        //TreeMap to hold list of names with specified substring in alphabetical order
        Map<String, String> map = new TreeMap<>();
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
                    //If the user entered male and the male index starts with the substring, the rank
                    //and name are added to the TreeMap as key and value
                    if(gender.equals("male")){
                        if(line[1].startsWith(name)){
                            map.put(line[1],line[0]);
                        }
                    } else {
                        //If the user entered female and the female index starts with the substring, the rank
                        //and name are added to the TreeMap as key and value
                        if(line[3].startsWith(name)){
                            map.put(line[3], line[0]);
                        }
                    }
                }
            }
            //Close reader after done reading
            myReader.close();
            //If file not found make the user aware
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found.");
        }
        //If nothing gets added to the map then no names started with the substring
        if (map.isEmpty()) {
            map.put("No name found with that substring in file", "------");
        }
        //Return the map that is in alphabetical order
        return map;
    }
}

