// Importing the InputMismatchException class to handle errors
import java.util.InputMismatchException;
// Importing the Scanner class to get user inputs
import java.util.Scanner;

public class PlaneManagement {   // Main class
    public static int[][] planeSeats = {   // Initializing a 2D array to represent plane seating plan
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    };

    private static Ticket[] all_ticket = new Ticket[52];   // Declaring a static array

    public static void main(String[] args) {   // Main method to execute the program
        Scanner input = new Scanner(System.in);   // Creating a scanner object to read input

        System.out.println("\n______________________________________________________ \n");
        System.out.println("     Welcome to the Plane Management Application");

        int menu = 1;   // Initializing the menu selection variable

        while (menu != 0){   // Displaying the menu options
            System.out.println("\n******************************************************");
            System.out.println("*                    MENU OPTIONS                    *");
            System.out.println("******************************************************");
            System.out.println("     1) Buy a seat");
            System.out.println("     2) Cancel a seat");
            System.out.println("     3) Find first available seat");
            System.out.println("     4) Show seating plan");
            System.out.println("     5) Print tickets information and total sales");
            System.out.println("     6) Search ticket");
            System.out.println("     0) Quit");
            System.out.println("******************************************************");

            while (true){   // Adding a loop to handle invalid input and prompt again
                try{   //Until the user gives a valid input the loop will continue
                    System.out.print("Please select an option: ");
                    menu = input.nextInt();   // Attempting to read the next integer for menu selection
                    input.nextLine();
                    break;   // Exciting the loop(if successful)

                }catch (InputMismatchException e){
                    System.out.println("Invalid option. Please try again! \n");
                    input.nextLine();
                }
            }

            switch(menu){   // Handling the selected menu option
                case 0:
                    System.out.print("\n---------   Thank you for joining with us!   ---------");
                    System.out.print("\n||------------     Have a nice day:)    ------------||");
                    System.out.println("\n______________________________________________________");
                    break;

                case 1:
                    buy_seat(input);   // Calling method to buy a seat
                    break;

                case 2:
                    cancel_seat(input);   // Calling method to cancel a seat
                    break;

                case 3:
                    find_first_available();   // Calling method to find the first available seat
                    break;

                case 4:
                    show_seating_plan();   // Calling method to display the seating plan
                    break;

                case 5:
                    print_ticket_info();   // Calling method to print ticket informaton and total sales
                    break;

                case 6:
                    search_ticket(input);   // Calling method to search a ticket
                    break;

                default:
                    System.out.println("Invalid number. Please try again! \n");
            }
        }
    }

    private static boolean validation(int row_letter, int seat_num){
        if (planeSeats[row_letter][seat_num] == 0){   // Checking whether the seat is available
            return true;   // Seat is available
        }
        else {
            return false;   // Seat is already booked
        }
    }

    // Defining a method to buy a seat reservation according to user input
    private static void buy_seat(Scanner input){
        int price;
        int seat_num = 0;   // Initializing seat number
        int row_letter;   // Initializing row letter(as integer)
        int seatIndex = 0;   // Initializing seat index
        String rowLet;   // Initializing row letter as string

        while (true) {   // Infinite loop for user input
            try {
                System.out.print("Enter a row letter: ");
                rowLet = input.nextLine().toUpperCase();   // Converting the input into uppercase
                row_letter = rowLet.charAt(0) - 'A';   // Converting row letter to index

                if (!(row_letter >= 0 && row_letter <= 4)){   // Checking for correct row input
                    System.out.println("Invalid input. Please try again! \n");
                    continue;   // Restarting the loop
                }

                try{
                    System.out.print("Enter a seat number: ");
                    seat_num = input.nextInt();   // Getting input for seat number
                    input.nextLine();

                }catch (Exception e){
                    System.out.println("Invalid input. Please ry again! \n");
                    input.nextLine();
                    continue;   // Restarting the loop
                }

                seatIndex = seat_num - 1;   // Converting seat number to index

                if (!(seat_num >= 0 && seat_num <= planeSeats[row_letter].length)){   // Checking for correct seat number
                    System.out.println("Invalid input. Please try again! \n");
                }
                else {
                    break;   // Exiting from the loop if input is correct
                }

            }catch(Exception e){
                System.out.println("Invalid input. Please try again!\n");
            }
        }

        if (validation(row_letter, seatIndex)) {
            System.out.print("Enter your Name: ");   // Asking for the Passenger name
            String name = input.nextLine();

            System.out.print("Enter your Surname: ");   // Asking for the passenger surname
            String surname = input.nextLine();

            System.out.print("Enter your Email: ");   // Asking for the passenger email
            String email = input.nextLine();

            Person personclass = new Person(name, surname, email);   // Creating person object

            if (seat_num <= 5){   // Assigning prices according to the seat number
                price = 200;
            } else if (seat_num <= 9) {
                price = 150;
            }else {
                price = 180;
            }

            for(int i = 0; i< all_ticket.length; i++ ){   // Going through all the tickets
                if(all_ticket[i] == null){   // Checking for available seats
                    all_ticket[i]=new Ticket(rowLet,seat_num,price,personclass);   // Creating new ticket object
                    all_ticket[i].save();   // Saving ticket
                    break;   // Exiting loop
                }
            }

            planeSeats[row_letter][seatIndex] = 1;   // Displaying seat as booked
            System.out.println("||---- You have successfully booked seat " + rowLet + seat_num + " ----||");
        }
        else {
            System.out.println("||---- Sorry, seat " + rowLet + seat_num + " is already booked! ----||");

        }
    }

    // Defining a method to cancel a seat
    private static void cancel_seat(Scanner input){
        int seat_num = 0;
        int row_letter;
        int seatIndex = 0;
        String rowLet; // variable to store row letter data


        while (true) {
            try{ // Until user enter a valid row latter loop will continue

                System.out.print("Enter the row letter: ");// Prompt user to enter row letter
                rowLet = input.nextLine().toUpperCase(); // Converting into uppercase
                row_letter = rowLet.charAt(0) - 'A';

                if (!(row_letter >= 0 && row_letter <= 4)){
                    System.out.println("Invalid input. Please try again! \n");
                    continue;
                }

                try{ // Until user enter a valid seat number loop will continue
                    System.out.print("Enter the seat number: ");
                    seat_num = input.nextInt();
                    input.nextLine();

                }catch(ArrayIndexOutOfBoundsException e){
                    System.out.println("Invalid input. Please try again!\n");
                }

                seatIndex = seat_num - 1;

                if (!(seat_num >= 0 && seat_num <= planeSeats[row_letter].length)){
                    System.out.println("Invalid input. Please try again! \n");
                }
                else {
                    break;
                }
            }catch (Exception e){ // Using exception for invalid input
                input.nextLine();
                System.out.println("Invalid input. Please try again!\n");
            }
        }
        // Checking if seat is available or not
        if (validation(row_letter, seatIndex)) {
            System.out.println("Seat is available!");
        } else {
            for(int i = 0; i< all_ticket.length; i++ ){
                if(all_ticket[i] != null){
                    if (all_ticket[i].getRow_l().equals(rowLet) && all_ticket[i].seat() == seat_num){
                        all_ticket[i] = null;
                        break;
                    }
                }
            }
            // Mark the seat as available in the `planeSeats` array.
            planeSeats[row_letter][seatIndex] = 0;
            System.out.println("Seat " + rowLet + seat_num + ", cancelled successfully!!!");
        }
    }
    // Defining method for first available seat
    private static void find_first_available(){
        boolean seatFound = false;
        for (int i = 0; i < planeSeats.length; i++) {
            char row_letter = (char) ('A' + i); // Calculate row letter for index
            for (int j = 0; j < planeSeats[i].length; j++) {
                if (planeSeats[i][j] == 0) { // Check if seat is available (assuming 0 means available)
                    System.out.println("First available seat: " + row_letter + (j + 1));
                    seatFound = true;
                    break; // Exit from the loop
                }
            }
            if (seatFound) {
                break;
            }
        }// Print a message if no available seats were found
        if (!seatFound) {
            System.out.println("Sorry, all the seat are unavailable!");
        }
    }
    // Defining method to show seating plan
    private static void show_seating_plan(){
        // Display available seat 'O' and booked seats for 'X'
        for (int i = 0; i < planeSeats.length; i++) {
            for (int j = 0; j < planeSeats[i].length; j++) {
                if (planeSeats[i][j] == 0) {
                    System.out.print("O "); // If seat is available
                } else {
                    System.out.print("X ");// If seats are not available
                }
            }
            System.out.println();
        }
    }
    // Defining method to print ticket info
    private static void print_ticket_info(){
        int total_price = 0; // Declaring variable for total price
        for (Ticket ticket : all_ticket){
            if (ticket != null){ // Check if the seat is not null
                ticket.ticket_info();
                total_price += ticket.Price();
            }
        }
        System.out.println();
        System.out.println("Total price: £" + total_price); // Printing total price
    }
    // Defining method to search user ticket information
    private static void search_ticket(Scanner input) {
        int seat_num = 0;
        int row_letter;
        String rowLet;
        int seatIndex = 0;

        while (true) {
            try { //Prompt user to ask to enter row letter
                System.out.print("Enter the row letter: ");
                rowLet = input.nextLine().toUpperCase(); // Converting into uppercase
                row_letter = rowLet.charAt(0) - 'A';

                if (!(row_letter >= 0 && row_letter <= 3)) {
                    System.out.print("Invalid input. Please try again!\n");
                    continue;
                }
                //prompt to enter seat number
                System.out.print("Enter the seat number: ");
                seat_num = input.nextInt();
                input.nextLine();
                seatIndex = seat_num - 1;
                // Check if seat number is within valid range for the row
                if (!(seat_num >= 0 && seat_num <= planeSeats[row_letter].length)) {
                    System.out.println("Invalid input. Please try again! \n");
                    continue;
                }

                boolean found = true;
                // Loop through all tickets to find if seat is already taken or not
                for (Ticket ticket : all_ticket) {
                    if (ticket != null) {
                        if (ticket.getRow_l().equals(rowLet) && ticket.seat() == seat_num) {
                            ticket.ticket_info();
                            found = false;
                        }
                    }
                }
                if (found) {
                    System.out.println("Seat is available!\n");
                    break;
                }
                else {
                    break;
                }
            } catch (Exception e) {// Handle input exceptions
                System.out.print("Invalid input. Please try again!\n");

            }
        }
    }
}