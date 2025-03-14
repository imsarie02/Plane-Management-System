public class Person {   // Defining class for a person
    private String passengerName;
    private String passengerSurName;
    private String passengerEmail;

    // Initializing a constructor
    Person (String passengerName, String passengerSurName, String passengerEmail){
        this.passengerName= passengerName;
        this.passengerSurName= passengerSurName;
        this.passengerEmail= passengerEmail;
    }

    public String name(){   // Adding a method to get the passenger's name
        return passengerName;
    }

    public String surname(){   // Adding a method to get the passenger's surname
        return passengerSurName;
    }

    public String email(){   // Adding a method to get passenger's email
        return passengerEmail;
    }
}
