
/**
 * Constructors & Getters : class : Mountain.
 * 
 * @author (DJMF) 
 * @version (13/01/2016)
Each instance variable created in Member class and initalised in constructor Member.
Provides 'template' for the pattern that is to be copied with each instance variable containing
copy of class template with its own values in memory location.
'Getters' are used to enable the instance object to be returned when initalised in constructor.

 */

public class Member{  

    private String name;  //instance variables declared here
    private int id;
    private String phone;
    private String address;
    private String record;
    private String time;

    // sets-up constuctor for members class with corresponding parameters.
    //'this' refers to object itself
    public Member(int id, String name, String phone, String address, String record, 
    String time){
        this.id = id;     //climber id
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.record = record;  //climber record eg)Ben Macdui
        this.time = time;   //climb time

    }
    // return climber name
    public String getName(){
        return name;
    }

    // return climber phone number
    public String getPhone(){
        return phone;
    }

    // return climber address
    public String getAddress(){
        return address;
    }

    // return climber Id
    public int getId(){
        return id;
    }

    // return climber's record of achievement
    public String getRecord(){
        return record;
    }

    // return climber's recorded climb time
    public String getTime(){
        return time;
    }

} //class member

