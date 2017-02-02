import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

/**
MountainList class supporting the main Mountain club program

Each instance object of Mountain class is initialized in the contructor and stored in ArrayList Mountain. The ArrayList being dynamic enables the list (array)
to be modified accordingly. Each index of the ArrayList identify the elements in ArrayList (such as munro number, grid ref, height etc).
So each index corrsesponds with the element in arrayList. eg) 'feet' corresponds with index 0.

Methods available are:

o readData: The mountainlist array stores the data and reads in 
the contents of file 'munro - copy.txt'. Each line in the
file consists of an integer (feet), then a tab, then text(munro name) and so on, all seperated by a tab.
Each line of text is split up into its text and number components,
and these are added to the ArrayList (by calls of addEntry).

o writeData: The entire current contents of the data store is written
out to file 'munro- copy.txt', overwriting its previous contents.
The output format precisely mirrors the original data format in
munro-copy.txt, so that the data can subsequently be read back in by readData.

o addEntry: When called, the method adds a given text/number pair to the mountainsList
by addinng an item to the end of the existing list. The parameter is the value of element to be added (eg, munro record) and
is placed at the end of the list. In this program addEntry retrieves the input from the user in ClubGUI and adds to list.

o fillChoice: This fills up the mountain JComboBox widget with the contents of the mountain array. 

o lookupFeet: This scans thorough the ArrayList and returns the corresponding instance (height of munro) from the mountain class constructor

o lookupMountain: This scans thorough the ArrayList and returns the corresponding instance (name of munro) from the mountain class constructor and returns it for display
in the JComboBox mountain in ClubGUI.
.
Given a text, seeks it in the texts array and returns the
corresponding number from the numbers array.

@author 2125379
@Jan 11, 2016.

 */

class MountainList{

    public static ArrayList<Mountain> mountainList = new ArrayList<Mountain>();

    // Read data from munro - copy.txt, split up each line and store the data in the arraylist
    // If there is any ill formatted data, or other file problem is encountered,
    // the reading is abandoned, but storing all correctly read data up to that point.
    public void readData() {

        try {
            BufferedReader input = new BufferedReader(new FileReader("munrosv4 - Copy.txt"));
            String dataLine;  // To receive each line from the file

            while ((dataLine = input.readLine()) != null) {  // Get next line from file, or exit loop

                // Split the line into separate fields at occurrences of tab ('\t')
                String[] fields = dataLine.split("\t");

                // Check that there are enough fields, and exit loop if not
                if (fields.length < 5) {
                    System.out.println("Insufficient fields in data line:");
                    System.out.println(dataLine);
                    break;                                 // Bad data: read no further!
                }

                // Extract the text and number fields from the array
                String feet = fields[0]; // 936
                String munro = fields[1]; // A'Bhuidh...
                String translation = fields [2];
                String MNumber  = fields[3]; // MUN236 
                String ref = fields[4]; // NN661776  NN 231658  

                // Convert feet to a proper int for storing
                int n = 0;   // To hold the converted number
                try {
                    n = Integer.parseInt(feet);      // Convert

                }
                catch (NumberFormatException ex) {
                    System.out.println("Bad data in number: \"" + feet);
                    break;                                 // Bad data: read no further!
                }
                // We now have the text and number parts,
                // so store the data obtained as next entry in the arrays
                addEntry(n, munro, translation,   MNumber, ref );

            } // end of input loop

            input.close();                               // File finished, arrays full or bad data, so close file
        }
        catch (IOException ex) {
            System.out.println("File reading error");    // File handling error: read no further
        }

    } // readData

    // Write the array contents out to data.txt, in a compatible format for readData
    public void writeData() {

        try {
            BufferedWriter output = new BufferedWriter(new FileWriter("munrosv4 - Copy.txt"));

            // Process each stored text/number pair from 0 to top
            for (int i = 0; i <= mountainList.size(); i++) {

                // Build a correctly structured string as an output line:
                // the five corresponding array items are separated by a tab
                Mountain mountain = mountainList.get(i);

                // Build a correctly structured string as an output line:
                // the corresponding arraylist items are separated by a tab and retrieved from instances initailised in constructor class via 'getters'.
                String outputLine = mountain.getName() + "\t" + mountain.getFeet() + "\t"
                    + mountain.getTranslation() + "\t"
                    + mountain.getReference() + "\t" + mountain.getNo();

                // And output the line to the file, followed by a new line
                output.write(outputLine);
                output.newLine();

            }
            output.close();
        }
        catch (IOException ex) {
            System.out.println("File writing error");    // File management error: write no more
        }

    } // writeData

    //add each instance from constructor and add to end of ArrayList
    public void addEntry(int n, String munro, String translation,  String munroNo, String ref ) {
        Mountain mountain = new Mountain(n, munro, translation, munroNo, ref );
        mountainList.add(mountain);

    }    // addEntry

    // Fill up the given Munro list with the contents of the mountain arraylist
    public void fillChoice(JComboBox list) {

        // Empty the current entries in the list
        list.removeAllItems();
        // Step through the occupied part of the arraylist
        for (int i = 0; i < mountainList.size(); i++)
        // Add next text item to the list(munro name)
            list.addItem(mountainList.get(i).getName());

        // Finally if there is at least one entry, select the first one
        if (mountainList.size() > 0)
            list.setSelectedIndex(0);

    } // fillChoice

    // scan through mountainList and for each corresponding munro name return corresponding height
    public int lookupFeet(String text) {//int n??
        for(int i = 0; i < mountainList.size(); i++){
            if (mountainList.get(i).getName().equals(text)){
                // Found the required entry! Return the corresponding height
                return mountainList.get(i).getFeet();
            }
            // class Mountainlist
        }
        return 0;
    }  
    // lookupFeet

    public Mountain lookupMountain(String text) {

        //scan through mountainList and for each corresponding munro name return corresponding name
        for(int i = 0; i < mountainList.size(); i++){
            if (mountainList.get(i).getName().equals(text)){
                // Found the required entry! Return the corresponding member
                return mountainList.get(i);
            }
        }
        // Execution will only arrive here if didn't find the required entry
        return null;

    }
}  // lookupMountain
// class MountainList