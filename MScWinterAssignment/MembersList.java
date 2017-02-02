import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

/**
MembersList class supporting the main Mountain club program

Each instance object of Member class is initialized in the contructor and stored in ArrayList Member. The ArrayList being dynamic enables the list (array)
to be modified accordingly. Each index of the ArrayList identify the elements in ArrayList (such as name, id no, location, phone, record etc).
So each index corrsesponds with the object in arrayList. eg) 'id' corresponds with index 0.

Methods available are:

o readData: The memberslist array stores the data and reads in 
the contents of file 'clublist.txt'. Each line in the
file consists of an integer (id), then a tab, then text(name) and so on, all seperated by a tab.
Each line of text is split up into its text and number components,
and these are added to the ArrayList (by calls of addEntry).

o writeData: The entire current contents of the data store is written
out to file 'clublist.txt', overwriting its previous contents.
The output format precisely mirrors the original data format in
clublist.txt, so that the data can subsequently be read back in by readData.

o addEntry: When called, the method adds a given text/number pair to the memberslist
by adding an item to the end of the existing list. The parameter is the value of element to be added (eg, climb time) and
is placed at the end of the list. In this program addEntry retrieves the input from the user in ClubGUI and adds to list.
index in getMemberIndex( below).

o getMemberIndex: this scans for the required entry (record) to be deleted and returns corresponding index(name) 
and sends to deleteEntry to update and remove from arrayList.

o fillChoice: This fills up the members JComboBox widget with the contents of the members array. 

o lookupNumber: This scans thorough the ArrayList and returns the corresponding instance (id of said climber) from the member class

o lookupMember: This scans thorough the ArrayList and returns the corresponding instance (name climber) from the member class and returns it for display
in the JComboBox 'member' in ClubGUI.

o findMember: initalized to null initially, findMember scans through memberList array and returns the matching 'name' of climber 
regardless whether name is entered by user in lower/upper case or only in form of a partial entry. eg) 'broad' instead of 'Broadbent'.
memberFound is return and called from ClubGui when user performs look-up record for climber.

Given a text, seeks it in the texts array and returns the
corresponding number from the numbers array.

@author 2125379
@Jan 11, 2016, 

 */

class MembersList {

    public static ArrayList<Member> memberList = new ArrayList<Member>();

    // Read data from clublist.txt, split up each line and store the data in the arrays.
    // If there is any ill formatted data, or other file problem is encountered,
    // the reading is abandoned, but storing all correctly read data up to that point.
    public void readData() {

        try {
            BufferedReader input = new BufferedReader(new FileReader("clublist.txt"));
            String dataLine;  // To receive each line from the file

            while ((dataLine = input.readLine()) != null) {  // Get next line from file, or exit loop

                // Split the line into separate fields at occurrences of tab ('\t')
                String[] fields = dataLine.split("\t");

                // Check that there are enough fields, and exit loop if not
                if (fields.length < 6) {
                    System.out.println("Insufficient fields in data line:");
                    System.out.println(dataLine);
                    break;                                 // Bad data: read no further!
                }

                // Extract the text and number fields from the array
                String numberPart = fields[0]; // id no, 12
                String textPart = fields[1]; // Broadbent,Colin
                String phonePart = fields[2]; //0131-545-8748
                String addressPart = fields[3]; //Edinburgh
                String recordPart = fields[4];// Ben Nevis
                String timePart = fields[5];//  5hrs,23mins

                // Convert numberPart to a proper int for storing
                int n = 0;   // To hold the converted number
                try {
                    n = Integer.parseInt(numberPart);      // Convert
                } catch (NumberFormatException ex) {
                    System.out.println("Bad data in number: \"" + numberPart + "\"");
                    break;                                 // Bad data: read no further!
                }

                // We now have the above parts,
                // so store the data obtained as next entry in the arrays
                addEntry(n, textPart, phonePart, addressPart, recordPart, timePart);

            } // end of input loop

            input.close();    
            // File finished, arrays full or bad data, so close file
        } catch (IOException ex) {
            System.out.println("File reading error");    // File handling error: read no further
        }

    } // readData

    // Write the array contents out to clublist.txt, in a compatible format for readData
    public void writeData() {

        try {
            BufferedWriter output = new BufferedWriter(new FileWriter("clublist.txt"));

            // Step through and return number of elements in this list.
            for (int i = 0; i < memberList.size(); i++) {

                // Build a correctly structured string as an output line:
                // the corresponding arraylist items are separated by a tab and retrieved from instances initailised in constructor class via 'getters'.
                Member member = memberList.get(i);
                String outputLine = member.getId() + "\t" + member.getName() + "\t" + member.getPhone() + "\t"
                    + member.getAddress()  + "\t" + member.getRecord()  + "\t" + member.getTime();
                // And output the line to the file, followed by a new line

                output.write(outputLine);

                output.newLine();
            }

            output.close();
            //  output.close();
        } catch (IOException ex) {
            System.out.println("File writing error");    // File management error: write no more
        }

    } // clublist

    // Add another entry to the arraylist.
    public void addEntry(int n, String name, String phone, String address,String record, String time) {
        //add each instance from constructor and add to end of ArrayList
        Member member = new Member(n, name, phone, address,record, time);
        memberList.add(member);

    } //addEntry

    public void deleteEntry(String name) {
        memberList.remove(getMemberIndex(name)); // this will remove the element at the nth index of the arraylist
        // we will not write the data here because that will be done later when saving.
    } 
    //deleteEntry

    // Fill up the given choice list with the contents of the texts array
    public void fillChoice(JComboBox list) {

        // Empty the current entries in the list
        list.removeAllItems();
        // Step through and return number of elements in this list.
        for (int i = 0; i < memberList.size(); i++)
        // Add next text item to the list
            list.addItem(memberList.get(i).getName());

        // Finally if there is at least one entry, select the first one
        if (memberList.size() > 0)
            list.setSelectedIndex(0);

    }
    // fillChoice

    public String lookupNumber(String text) {
        // scan through mountainList and for each corresponding climber name returning corresponding phone number

        for(int i = 0; i < memberList.size(); i++){
            if (memberList.get(i).getName().equals(text)){
                // Found the required entry! Return the corresponding number
                return memberList.get(i).getPhone();
            }
        }
        // Execution will only arrive here if didn't find the required entry
        return "0";
    }

    public Member lookupMember(String text) {
        //scan through mountainList and for each corresponding member/climber name and return corresponding name

        for(int i = 0; i < memberList.size(); i++){
            if (memberList.get(i).getName().equals(text)){
                // Found the required entry! Return the corresponding member
                return memberList.get(i);
            }
        }
        // Execution will only arrive here if didn't find the required entry
        return null;

    } //lookupNumber

    public int getMemberIndex(String text){
        for(int i = 0; i < memberList.size(); i++){
            if (memberList.get(i).getName().equals(text)){
                // Found the required entry! Return the corresponding index
                return i;
            }
        }
        return 0;
    } //getMemberIndex

    public String findMember(String name){
        // initialize memberFound as null initially
        String memberFound = null;
        // scan all entries
        for(int i = 0; i < memberList.size(); i++){
            if (memberList.get(i).getName().toLowerCase().contains(name.toLowerCase())){
                // change memberFound to be true, now that we have found it and break out of loop
                // as we don't need to scan further
                memberFound = memberList.get(i).getName();
                break;
            }
        }

        return memberFound;

    } //findMember

}// class Members 



