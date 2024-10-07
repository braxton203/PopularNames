import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Task4 {
    /*
    Task 4: Find a Random Name Starting with a Substring
    Objective: Find and display a random name that begins with a specific substring and its ranking from the selected file.
            Features:
    Random Selection: Develop a mechanism to randomly select a name from the list of names starting with the substring.
    User Input: Prompt for filename, gender, and substring.
    Display: Showcase the randomly selected name and its ranking.
    */
    public static void main(String[] args) {
        //Scanner for taking in inputs for program
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
                    System.out.println("Invalid input. Try again.");
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
            //Pass file, name, and gender to method and print the return
            System.out.println("-----------------------------");
            System.out.println(finder(file, name, gender));
            System.out.println("-----------------------------");

            //Flag to for below while loop
            boolean decisionFlag = true;
            //User options for making another query or exiting program
            System.out.println("(1) Another Query:");
            System.out.println("(2) Exit program:");
            System.out.println("Enter the option number you would like to do (1 or 2): ");
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

    public static String finder(File file, String name, String gender) {
        //TreeMap to hold list of names with specified substring
        Map<String, String> map = new TreeMap<>();
        //Array that holds each line of file
        String[] fileLine;

        try {
            //Scanner to read file
            Scanner myReader = new Scanner(file);
            //While loop that reads through each line of the file until none left
            while (myReader.hasNextLine()) {
                //data variable holds each line of file
                String data = myReader.nextLine();
                //If the substring is found on the line, the line is split into an array by spaces
                if (data.contains(name)) {
                    fileLine = data.split("\\s+");
                    //If the user entered male and the male index starts with the substring, the rank
                    //and name are added to the TreeMap as key and value
                    if (gender.equals("male")) {
                        if (fileLine[1].startsWith(name)) {
                            map.put(fileLine[1], fileLine[0]);
                        }
                    } else {
                        //If the user entered female and the female index starts with the substring, the rank
                        //and name are added to the TreeMap as key and value
                        if (fileLine[3].startsWith(name)) {
                            map.put(fileLine[3], fileLine[0]);
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
            return "No names Starting with that substring";
        }

        //MECHANISM FOR RANDOM NAME IN SUBSTRING LIST
        //Two lists holding either the keys or values
        List<String> valuesList = new ArrayList<>(map.values());
        List<String> keyList = new ArrayList<>(map.keySet());
        //Picks a random index to grab from each list
        int randomIndex = new Random().nextInt(valuesList.size());
        //Corresponding pairKey and pairValue are grabbed from the lists
        String pairKey = keyList.get(randomIndex);
        String pairValue = valuesList.get(randomIndex);
        //Return the randomly pulled key and value pair in string form
        return "Rank: " + pairValue + " | Name: " + pairKey;
    }
}
