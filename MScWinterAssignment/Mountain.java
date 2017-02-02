
/**
 * Constructors & Getters : class : Mountain.
 * 
 * @author (DJMF) 
 * @version (13/01/2016)
Each instance variable created in Mountain class and initalised in constructor Mountain.
Provides 'template' for the pattern that is to be copied with each instance variable containing
copy of class template with its own values in memory location.
'Getters' are used to enable the instance object to be returned when initalised in constructor.

 */

public class Mountain{

    private String munroName;   //instance variables declared here
    private int feet;
    private String reference;
    private String No;
    private String translation;

    // sets-up constuctor for members class with corresponding parameters.
    //'this' refers to object itself
    public Mountain(int feet, String munroName, String translation,  
    String reference, String No){
        this.feet = feet;  //height
        this.munroName = munroName;
        this.translation = translation;  //Gaelic to English translaton
        this.reference = reference;  //grid reference
        this.No = No; //munro no eg)MUN222

    }

    // return climber name
    public String getName(){
        return munroName;
    }

    // return munro grid reference
    public String getReference(){
        return reference;
    }

    //return munro number
    public String getNo(){
        return No;
    }

    // return height of munro(feet)
    public int getFeet(){
        return feet;
    }

    // return gaelic-english translation
    public String getTranslation(){
        return translation;

    }

} //class mountain