import java.io.FileWriter;
import java.io.IOException;

public class Ticket {
    // Class fields
    private String row_l;   // Row letter
    private int seatnumber;   // Seat number
    private int t_price;   // Ticket price
    private Person personclass;   //Associated person object

    public Ticket(String row_l, int seatnumber, int t_price, Person personclass){
        this.row_l=row_l;
        this.seatnumber=seatnumber;
        this.t_price=t_price;
        this.personclass=personclass;
    }

    public String getRow_l(){
        return row_l;
    }

    public int seat(){
        return seatnumber;
    }

    public void ticket_info(){   // Displaying ticket information to console
        System.out.println();
        // Writing ticket details to the file
        System.out.println("Row letter: " + row_l);
        System.out.println("Seat no: " + seatnumber);
        System.out.println("Ticket price: £" + t_price);
        System.out.println("Name: " + personclass.name());
        System.out.println("Surname: " + personclass.surname());
        System.out.println("Email: " + personclass.email());
        //System.out.println();
    }
    public int Price(){
        return t_price;
    }

    public void save(){   // Saving ticket information to a file
        try{   // Creating a file with a name according to the row letter and seat number
            // Writing ticket details to the file
            FileWriter ticket_plane = new FileWriter(this.row_l.toUpperCase() + this.seatnumber + ".txt");
            ticket_plane.write("Row letter: "+this.row_l.toUpperCase()+ "\n");
            ticket_plane.write("Seat row: " + this.seatnumber + "\n");
            ticket_plane.write("Ticket price: £" + this.t_price + "\n");
            ticket_plane.write("Name: " + this.personclass.name() + "\n");
            ticket_plane.write("Surname: " + this.personclass.surname() + "\n");
            ticket_plane.write("Email: " + this.personclass.email() + "\n");
            ticket_plane.close();   // Closing the file writer to save resources
        }catch (IOException e){
            e.printStackTrace();   // Printing the stack trace in case of an IOException
        }
    }
}
