package c01_classes_objects;

// A class is a blueprint; objects are the houses built from it.
public class HousePlan {
    
     HousePlan(String customer, String colour) {
        this.customer = customer;                     // "this" = the object being built
        this.colour = colour;
        housesBuilt++;
    }
    
    HousePlan(String customer) {
        this(customer, "white");                   // constructor chaining: must be the first line
    }
    // fields (state)
    private final String construction = "bhairvnath construction"; 
    private  String customer;   
    private String colour;
    private static int housesBuilt = 0;    
    
    public static int getHousesBuilt() { return housesBuilt; }   // no "this" inside static methods
    
    public String toString() { return colour + " " + customer + " @ " + construction; }  
    
      }  // static: ONE copy shared by all houses