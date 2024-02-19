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
        System.out.println("[1] Compare BMIs");
        int choice = 0;
        Scanner input = new Scanner(System.in);
        choice = input.nextInt();
        while(true){
            input.nextLine();
            if(choice == 1){
                System.out.println("Which day would you like to previously compare to your current date?");
                int temp1 = input.nextInt();
                System.out.println("What is the next date?");
                int temp2 = input.nextInt();
                getBMIDifferences(temp1, temp2);
                break;
            }
            else{

            }
        }




    }

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
            System.out.println("Error: Targets not found");
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
                System.out.println("You gained BMI!");
            }
        }
        else if(difference > 0){
            if(array[place1].getDays() < array[place2].getDays()){
                System.out.println("You lost BMI over those days!");
            }
            else{
                System.out.println("You gained BMI!");
            }
        }
        }
    }

