import java.util.Scanner;
import java.io.*;
public class main{
    public static entry array[] = new entry[31];
    public static void main(String args[]){
        System.out.println("Welcome to the progress tracker!");
        System.out.println("Please make sure to have the latest version and have the latest csv file!");
        try{
            Scanner file = new Scanner(new File("userdata.csv"));
            int index = 0; //index to keep track of array elements
            while(file.hasNextLine()){
                String line = file.nextLine();
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(",");
                entry en = new entry(lineScanner.nextInt(), lineScanner.nextInt(), lineScanner.nextInt(), lineScanner.nextInt());
                array[index] = en;
                index++;
                System.out.println("Data inputted");
            }
        }
        catch(Exception e){
            System.out.println("File error incurred.");
        }
        //code below
        System.out.println("All data has been inputted. What would you like to do next?");
        int choice = 0;
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.println("[0] Exit");
            System.out.println("[1] Compare BMIs");
            System.out.println("[2] Analyze target BMI");
            choice = input.nextInt();
            if(choice == 0){
                System.out.println("You have choosen to exit. Goodbye!");
                System.exit(0);
            }
            if(choice == 1){
                System.out.println("Which day would you like to previously compare to your current date?");
                int temp1 = input.nextInt();
                System.out.println("What is the next date?");
                int temp2 = input.nextInt();
                getBMIDifferences(temp1, temp2);
            }
            if(choice == 2){
                System.out.println("What date would you like to analyze? ");
                int temp = input.nextInt();
                analyzeTargetBMI(temp);
            }
            else{
                System.out.println("That was an invalid choice. Please try again!");
            }
        } //end of while TRUE

    } //end of main


public static void analyzeTargetBMI(int target){
    boolean found = false;
    int place = 0;
    for(int i=0; i< array.length; i++){
        if(array[i] != null && array[i].getDays() == target){
            found = true;
            place = i;
        }
    }
    if(!found){
        System.out.println("Couldn't find target. Goodbye!");
        System.exit(0);
    }
    else{
        if(array[place].getAge() < 18){
            System.out.println("Your BMI predictions may be inaccurate because you are a minor.");
        }
        System.out.println("Your BMI is: " + array[place].getBMI());
        System.out.println("All analyzations are based on the CDC website.");
        System.out.println("https://www.cdc.gov/healthyweight/assessing/index.html#:~:text=If%20your%20BMI%20is%20less,falls%20within%20the%20obese%20range.");
        if(array[place].getBMI() < 18.5){
            System.out.println("You are classified as underweight.");
        }
        else if(array[place].getBMI() >= 18.5 && (array[place].getBMI() <= 24.9)){
            System.out.println("You are classified within the healthy weight range!");
        }
        else if((array[place].getBMI() >=25) && (array[place].getBMI() <=29.9)){
            System.out.println("You are classified as overweight.");
        }
        else if(array[place].getBMI() >=30){
            System.out.println("You are classified as obese.");
        }
    }    
}


    //start of getBMI differences
    /*
     * @params Requires passthrough of integer values that are positive and within date range on csv
     */
    public static void getBMIDifferences(int x, int y){
        boolean found1 = false;
        System.out.println("Calculating BMI differences...");
        int place1 = 0;
        for(int i=0;i<array.length; i++){
            if(array[i] != null && array[i].getDays() == x){
                place1 = i;
                found1 = true;
                break; // No need to continue the loop once found
            }
        }
        boolean found2 = false;
        int place2 = 0;
        for(int i = 0; i<array.length; i++){
            if(array[i] != null && array[i].getDays() == y){
                found2 = true;
                place2 = i;
                break; // No need to continue the loop once found
            }
        }
        if(!(found1 || found2)){
            System.out.println("Error: Targets not found. Exiting system.");
            System.exit(0);
        }
        System.out.println("Aquired BMI targets.");
        double difference = array[place1].getBMI() - array[place2].getBMI();
        System.out.println("You had a BMI difference of " + Math.abs(difference) + " over days " + x + " and " + y + " in the same month");
        if(difference < 0){
            if(array[place1].getDays() > array[place2].getDays()){
                System.out.println("You lost BMI over those days.");
            }
            else{
                System.out.println("You gained BMI over those days");
            }
        }
        else if(difference > 0){
            if(array[place1].getDays() < array[place2].getDays()){
                System.out.println("You lost BMI over those days!");
            }
            else{
                System.out.println("You gained BMI over those days");
            }
        }
    }
}
