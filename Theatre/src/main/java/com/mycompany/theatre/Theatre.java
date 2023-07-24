/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.theatre;

/**
 *
 * @author prasid
 */
import java.util.Scanner; //helps to scan the seatingarea.txt file
import java.io.File;
import java.io.FileWriter; //https://www.geeksforgeeks.org/filewriter-class-in-java/
import java.io.IOException; //https://stackoverflow.com/questions/2397714/java-try-and-catch-ioexception-must-be-caught-or-declared-to-be-thrown

public class Theatre {

    private static final int[][] seatingArea = {
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // Row 1 (12 Seats)
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // Row 2 (16 Seats)
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0} // Row 3 (20 Seats)
    };

    public static void main(String[] args) {
        System.out.println("\n----------------------------");
        System.out.println(" Welcome to the New Theatre");
        System.out.println("----------------------------");

    //task 1 (welcome msg)    
        
        try (Scanner scanner = new Scanner(System.in)) {
            int option;
            
            do {
                System.out.println("\nPlease select an option:");
                System.out.println("1) Buy a ticket");
                System.out.println("2) Print seating area");
                System.out.println("3) Cancel ticket");
                System.out.println("4) List available seats");
                System.out.println("5) Save to file");
                System.out.println("6) Load from file");
                System.out.println("7) Print ticket information and total price");
                System.out.println("8) Sort tickets by price");
                System.out.println("0) Quit");
                 System.out.println("-------------------------------------------");
                System.out.print("Enter option: ");
                
                option = scanner.nextInt();
                
                switch (option) {
                    case 1 -> buy_ticket();
                    case 2 -> print_seating_area();
                    case 3 -> cancel_ticket();
                    case 4 -> show_available();
                    case 5 -> save();
                    case 6 -> load();
                    case 7 -> {
                    }
                    case 8 -> {
                    }
                    case 0 -> System.out.println("Thank you and Goodbye!");
                    default -> System.out.println("Invalid! Please select an option by typing a number between 0 and 8.");
                }
                            } while (option != 0);
        }
    }
    //task 2 (options ..)
    
    private static void buy_ticket() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the row number (1-3): ");
        int row = scanner.nextInt();
        System.out.print("Enter the seat number: ");
        int seat = scanner.nextInt();

        if (row >= 1 && row <= 3 && seat >= 1 && seat <= seatingArea[row - 1].length) {
            if (seatingArea[row - 1][seat - 1] == 0) {
                seatingArea[row - 1][seat - 1] = 1;
                System.out.println("Ticket bought successfully!");
            } else {
                System.out.println("The seat you selected is already occupied.");
            }
        } else {
            System.out.println("Invalid row or seat number. Try again!");
        }
        
    }
    //task 3 buy ticks
    
        private static void print_seating_area() {
        System.out.println("************************");
        System.out.println("*  *  *   STAGE   *  * *");
        System.out.println("************************");
        for (int row = 0; row < seatingArea.length; row++) {
            StringBuilder sb = new StringBuilder();
            for (int col = 0; col < seatingArea[row].length; col++) {
                char seatSymbol = seatingArea[row][col] == 0 ? 'O' : 'X';
                sb.append(seatSymbol);
            }
            String padding = "";
            padding = switch (row) {
                case 0 -> "      ";
                case 1 -> "    ";
                default -> "  ";
            };
            System.out.println(padding + sb.toString());
        }
    }
        //task 4, stage order (organised)

    private static void cancel_ticket() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the row number (1-3): ");
        int row = scanner.nextInt();
        System.out.print("Enter the seat number: ");
        int seat = scanner.nextInt();

        if (row >= 1 && row <= 3 && seat >= 1 && seat <= seatingArea[row - 1].length) {
            if (seatingArea[row - 1][seat - 1] == 1) {
                seatingArea[row - 1][seat - 1] = 0;
                System.out.println("Ticket cancelled successfully!");
            } else {
                System.out.println("The seat is already available from now.");
            }
        } else {
            System.out.println("Invalid row or seat number.");
        }
    }
    
    //task 5 cancel the ticket and make it available after the cancellation

    private static void show_available() {
        for (int row = 0; row < seatingArea.length; row++) {
            System.out.print("Seats available in row " + (row + 1) + ": ");
            for (int col = 0; col < seatingArea[row].length; col++) {
                if (seatingArea[row][col] == 0) {
                    System.out.print((col + 1) + (col == seatingArea[row].length - 1 ? "" : ", "));
                }
            }
            System.out.println();
        }
    }
//t6, available tickets
    
    private static void save() {
        try (FileWriter writer = new FileWriter("seatingarea.txt")) {
            for (int[] row : seatingArea) {
                for (int seat : row) {
                    writer.write(seat + " ");
                }
                writer.write(System.lineSeparator());
            }
            System.out.println("Seating area saved to file.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the seatingarea.txt.");
        }
    }
//t7 save the tickets in txt files 
    
    private static void load() {
        try (Scanner scanner = new Scanner(new File("seatingarea.txt"))) {
            for (int row = 0; row < seatingArea.length && scanner.hasNextLine(); row++) {
                String[] seats = scanner.nextLine().split(" ");
                for (int col = 0; col < seats.length; col++) {
                    seatingArea[row][col] = Integer.parseInt(seats[col]);
                }
            }
            System.out.println("Seating area loaded from file seatingarea.txt.");
        } catch (IOException e) {
            System.out.println("An error occurred while loading the seating area.");
        }
        
        //t8 load the txt file (scanner helps to find the txt file)
    }
}

//references: https://stackoverflow.com/questions/56110612/simple-java-ticketing-system-in-netbeans
//https://condor.depaul.edu/glancast/212class/docs/lectures/lecApr10.html
//http://www.java2s.com/example/java/object-oriented-design/create-person-class-and-extend-person-class-to-create-student-class.html
//https://www.geeksforgeeks.org/understanding-static-in-public-static-void-main-in-java/
//https://stackoverflow.com/questions/3728062/what-is-the-meaning-of-this-in-java
//https://www.javatpoint.com/Scanner-class

