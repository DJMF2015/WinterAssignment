import javax.swing.JOptionPane;
import java.util.ArrayList.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

/**
//Mountain Club database application

This is the GUI component of the application and resposnible for the event-handling of all dynamic methods
corresponding with the MountainList/MembersList class.
ClubGUI sets-up the user interface inform of JTabbedPane embeded within JFrame and within this, the munro
and membersPanels. GUI interface contains various 'buttons' and widgets to perform some function such as
'add member', delete member', 'look-up' etc. A 'save' button for members also writes back to file any modifications
to memberlist array eg)adding,deleting new member. 


 // The buttons notify actionPerformed when clicked
 // Tell the munro Choice to the display,
 // and set it to notify actionPerformed when an item is select
 
NOTE:

There have been some problems that were unresolved at time of deadline. this means that I have had to 'remove' from the program the 'add' and 
'save' functions for the mounatin/munro parton of the GUI. 

This was due to unresolved and unknown error whereby 'bad data' exception occured when adding record even though a search for such bad
data in file did not seem to be visually present. This was same problem as occured when three numbers were present at start of file but could not 
been seen.

 
@authors SBJ/2125379  
modified : 22, January 2016

*/



public class ClubGUI
extends JFrame 
implements ActionListener     // For the JButton handling and item selection in the JComboBox
{

       /**
    Constructor for mountain club application.
    Create mountain club window and set up windown to hold objects.
     */
    public ClubGUI() {

        setTitle("Mountain Club");
        setDefaultCloseOperation(EXIT_ON_CLOSE);   // For main close box click event
        init();                                    // Set up the GUI and data

    }       
   
    // ClubGUI
    /** Mountain and Members/ClimbersList object to store all datas */
    private MembersList theClimber = new MembersList();         // Create a new MembersList object to hold all the data
    private MountainList munro = new MountainList();          // Create a new MountainList object to hold all the data

    // For displaying a 'switchable' JTabbedPane interface with two JPanels, munroPanel and memberspanel
    private JTabbedPane switchable;                // Will contain and manage the switchable panels
    private JPanel munroPanel, membersPanel;   
    
    //set-up the munros/mountain JComboBox:
    private JComboBox textChoice = new JComboBox();

    private JLabel
    members= new JLabel("<html><font color='red'>Members Records</font></html>");

   // The buttons for the members GUI
    private JButton
    AddEntryButton = new JButton (("<html><font color='green'>Add Record : </font></html>")), // Direct movement buttons
    DeleteButton = new JButton(("<html><font color='green'>Delete Record: </font></html>")),// Only one of these will be visible at any time within switchable
    saveButton = new JButton (("<html><font color='green'>Save changes: </font></html>")),
    SearchButton = new JButton (("<html><font color='green'>Find Member: </font></html>"));

    /** membersList labels */
    /** membersList text fields */
    private JLabel ID = new JLabel("<html><font color='blue'>Member ID: </font></html>");
    private JTextField numberField = new JTextField(5); 

    private JLabel TelNo= new JLabel(("<html><font color='blue'>Phone : </font></html>"));
    private JTextField PhoneField = new JTextField(10);

    private JLabel Address= new JLabel(("<html><font color='blue'>Address : </font></html>"));
    private JTextField addressField = new JTextField(10);

    private JLabel memberName= new JLabel(("<html><font color='blue'>Look-Up Name: </font></html>"));
    private JTextField member = new JTextField(10);

    private JLabel ClimberName= new JLabel(("<html><font color='blue'>Climber : </font></html>"));
    private JTextField climb = new JTextField(10);

    private JLabel record = new JLabel(("<html><font color='blue'>Climb Record: </font></html>"));
    private JTextField recordField = new JTextField(10);

    private JLabel time = new JLabel(("<html><font color='blue'>Climb Time : </font></html>"));
    private JTextField timeField = new JTextField(10);

    //set-up the munros/mountain JComboBox:
    private JComboBox mountainChoice = new JComboBox();

    private JLabel
    munros = new JLabel(("<html><font color='blue'>Munro Record : </font></html>"));
    
    // The buttons for the munro GUI
    private JButton
    Delete = new JButton(("<html><font color='green'>Delete: </font></html>"));// Only one of these will be visible at any time within switchable

    /** munroList labels */
    /** munroList text fields */
    private JLabel  Feet = new JLabel(("<html><font color='blue'>Height(ft) </font></html>"));
    private JTextField HeightField = new JTextField(5);  // For diplaying the height of mountain list associated with the selected text item

    private JLabel Translation = new JLabel(("<html><font color='blue'>Translation: </font></html>"));
    private JTextField TranslationField = new JTextField(5);

    private JLabel munroNo = new JLabel(("<html><font color='blue'>Munro No. </font></html>"));
    private JTextField munroField = new JTextField(15);

    private JLabel GridRef = new JLabel(("<html><font color='blue'Grid Reference </font></html>"));
    private JTextField gridRefField = new JTextField(10);

    private JButton openButton, saveFile;
    private JFileChooser fileChooser;
    private JTextField file;
    
    // Read the clublist file into the object theClimber, and set up the GUI
    public void init() {

        // Instruct the DataStore object theClimber to read in the data file
        theClimber.readData(); //read in members list from file
        munro.readData(); //read in munro list from file

        //Set up the GUI for mountain club
        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout());

        /**Set up the GUI for members class.
         */
        
        membersPanel = new JPanel();
        new GridLayout();// Create the members panel in GridLayout
        membersPanel.setLayout(new GridLayout(10,2, 20,20));
        membersPanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED)); //set-up borders
        membersPanel.setBackground(Color.LIGHT_GRAY);
        membersPanel.add(members);                    

        // fill-up member JCombobox with names of climbers 
        theClimber.fillChoice(textChoice);
        membersPanel.add(textChoice);        
        textChoice.addActionListener(this);
        textChoice.setEditable(true);

        // add member id Jtextfield to panel
        membersPanel.add(ID);
        membersPanel.add(numberField);
        numberField.setEditable(false);
 
         // add member phone no. JTextfield to panel
        membersPanel.add(TelNo);
        membersPanel.add(PhoneField );
        PhoneField.setEditable(false);

         // add member address JTextfield to panel
        membersPanel.add(Address);
        membersPanel.add(addressField);
        addressField.setEditable(false);
        
         // add member climbtime JTextfield to panel
        membersPanel.add(time); 
        membersPanel.add(timeField);
        timeField.setEditable(false);

        // add member climb record JTextfield to panel
        membersPanel.add(record); 
        membersPanel.add(recordField);
        recordField.setEditable(false);

        /*Add JButtons to members panel GUI:
         * DeleteButton
         * SearchButton
         * SaveButton      */
         
        membersPanel.add(DeleteButton);                     
        DeleteButton.addActionListener(this);

        membersPanel.add(AddEntryButton);
        AddEntryButton.addActionListener(this);

        membersPanel.add(SearchButton);
        SearchButton.addActionListener(this);

        membersPanel.add(saveButton);
        saveButton.addActionListener(this);

        // add climber name and search field to members panel
        membersPanel.add(ClimberName);
        membersPanel.add(climb);
        climb.setEditable(false);

        membersPanel.add(memberName);
        membersPanel.add(member);
        member.setEditable(true);

        /**Set up the GUI for mountains class.
         */
        munros = new JLabel(("<html><font color='red'>Munro Record </font></html>"));
        munroPanel = new JPanel();     
        new GridLayout();// Create the munro panel
        munroPanel.setLayout(new GridLayout(10,2, 20,20));
        munroPanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED)); //set-up BevelBorder
        munroPanel.setBackground(Color.LIGHT_GRAY);
        munroPanel.add(munros); 

        // fill-up member JCombobox with munro names
           
        munro.fillChoice(mountainChoice);
        munroPanel.add(mountainChoice);
        mountainChoice.addActionListener(this);
 
        // add height JTextfield to panel
        munroPanel.add(Feet);
        munroPanel.add(HeightField);
        HeightField.setEditable(false);

        // add gaelic-english translation JTextfield to panel
        munroPanel.add(Translation);
        munroPanel.add(TranslationField);
        TranslationField.setEditable(false);

        // add munro number JTextfield to panel
        munroPanel.add(munroNo);
        munroPanel.add(munroField); 
        munroField.setEditable(false);

        // addgrid reference JTextfield to panel
        munroPanel.add(GridRef); 
        munroPanel.add(gridRefField);
        gridRefField.setEditable(false);
        
        // add two JButtons 'delete' and 'add' to munroPanel
        munroPanel.add(Delete);
        Delete.addActionListener(this);

       //Set up the GUI buttons and widget for file chooser 
    
        openButton = new JButton("Open");
        munroPanel.add(openButton);
        openButton.addActionListener(this);
        
        saveFile = new JButton("Save File");
        munroPanel.add(saveFile);
        saveFile.addActionListener(this);
        
        // textfield for directory display
        file = new JTextField(20);
        munroPanel.add(file);
        
 // The JTabbedPane will only display one panel at a time
 // Will have tabs at the top and the replaceable panels as added components
    switchable = new JTabbedPane();           
    switchable.setPreferredSize(new Dimension(500,400));  // Set a suitable size

    switchable.add("Members panel", membersPanel);       // enable 'switching'
    switchable.add("Munro panel" ,munroPanel);
    contentPane.add(switchable);

    // Finally, make sure that initial display in numberField
    // is consistent with the initially selected text item
    updateNumberField();
}
   
 // Handle button presses and item selection events
   
    public void actionPerformed(ActionEvent e) {
        // variables for JFileChooser
        File selectedFile;
        int reply;
         
        // First find out which text item is selected
        String chosen = (String)textChoice.getSelectedItem();
        String munroChoice = (String)mountainChoice.getSelectedItem();

        // Finally update the number field display
        updateNumberField();
        
         /** Handle an event from the combo box list: it might be an item
          selection, or deselection or some other change to the combo box.
          place the correct integer from the data store in numberField
          - the one associated with the currently selected text,
          or set to blank if nothing is selected
          place the correct integer from the mountain and memberslist in numberField
          - the one associated with the currently selected text,
          or set to blank if nothing is selected
       */ 
        if (e.getSource() == textChoice) {   // select climber combo box for selection
      

            if (textChoice.getSelectedIndex() == -1) {   // -1 indicates "nothing selected"
                // No item is currently selected
                numberField.setText("");
                addressField.setText("");
                PhoneField.setText("");

            }
            else
                updateNumberField();

        }

        if (e.getSource() == mountainChoice) {  // select munro combo box for selection
            // Handle an event from the combo box list: it might be an item

            if (mountainChoice.getSelectedIndex() == -1) {   // -1 indicates "nothing selected"
                // No item is currently selected 
                HeightField.setText("");
                munroField.setText("");
                gridRefField.setText("");
                TranslationField.setText("");
                timeField.setText("");
                recordField.setText("");
            }
            else
                updateNumberField();

        } // actionPerformed

        
        /**
         * Event-Handling for button widgets.
         * Add, delete, search and save functions.
         * Buttons listen out for user input and respond by calling appropraite methods
         */
        
        // Asks user for input details via JOptionDialog and passes to addEntry method in membersList class
        if (e.getSource() == AddEntryButton) {
        
            String newname = JOptionPane.showInputDialog("Enter Name:");
            String id = JOptionPane.showInputDialog("Enter member's ID ");   
            int idNo = Integer.parseInt(id);
            String phone = JOptionPane.showInputDialog("Enter member's Tel No. ");    
            String address = JOptionPane.showInputDialog( "Enter member's address ");  
            String record = JOptionPane.showInputDialog( "Enter record "); 
            String time = JOptionPane.showInputDialog( "Enter time taken "); 
           
            //this creates a member object with the name fetched from the text field and adds it to the list
            theClimber.addEntry(idNo, newname, phone , address, record, time);
            theClimber.fillChoice(textChoice);
            JOptionPane.showMessageDialog(null, "Press 'Save' to update to file");
        }
     
        // Listens out for user input and returns member if found, and 'not found' otherwise.
        if (e.getSource() == SearchButton){

            String name = member.getText();
            String memberFound = theClimber.findMember(name);   // call findMethod in membersList

            if(memberFound == null){
                // this runs if member not found
                JOptionPane.showMessageDialog(ClubGUI.this, "Member not found");
            }
            else
            {
                // this runs if member found and returns values
                Member member = theClimber.lookupMember(memberFound);
                JOptionPane.showMessageDialog(ClubGUI.this, "Member Name: " + member.getName()+ "\n" + "ID: " +  member.getId() + "\n" +  "Address: " 
                    + member.getAddress() + "\n" +  "Phone No. " + member.getPhone());
                numberField.setText(String.valueOf(member.getId()));
                PhoneField.setText(member.getPhone());
                addressField.setText(member.getAddress());

            }
        }
 
     
        if(e.getSource() == saveButton){

            theClimber.writeData();

        }   

        // when activated deleet button responds by feching last index in arrayList and removing from combo box and panel
        // calls deleteEntry method in membersList and removes from file temporarily and completely if 'save' button activated.
        if (e.getSource() == DeleteButton) {
            int index = textChoice.getItemCount()-1;   //remove last index
            String name = (String) textChoice.getItemAt(index);
            textChoice.removeItemAt(index);
            theClimber.deleteEntry(name);        //call deleteEntry and remove name
            JOptionPane.showMessageDialog(null, "Press 'Save' to update to file");
        }
       
        if (e.getSource() == Delete) {

            mountainChoice.removeItemAt(mountainChoice.getItemCount()-1);  //call deleteEntry and remove munro record
        }
        
        /** handles open and save functions for JFileChooser.
         * Enables file to be opened and saved
         */
    if(e.getSource() == openButton){ 
        fileChooser = new JFileChooser();
        reply = fileChooser.showSaveDialog(this);
        if(reply == JFileChooser.APPROVE_OPTION){          //if 'yes' then fetch directory path and open
            selectedFile = fileChooser.getSelectedFile();
            file.setText(selectedFile.getAbsolutePath());
        }
    }
    
    if(e.getSource() == saveFile){
        fileChooser = new JFileChooser();
        reply = fileChooser.showOpenDialog(this);
        if(reply == JFileChooser.APPROVE_OPTION){
            selectedFile = fileChooser.getSelectedFile(); //if 'yes' then fetch directory path and save
            file.setText(selectedFile.getAbsolutePath());
        }
    }
   
} 


  /**
   * method returns selected index value from arraylist and returns for display in GUI ouput
   */
    private void updateNumberField() {
        // First find out which text item is selected
        String chosen = (String)textChoice.getSelectedItem();  // Will be null if nothing selected
        String munroChoice = (String)mountainChoice.getSelectedItem();

        if (chosen == null || munroChoice == null) {
            numberField.setText("");
            HeightField.setText("");
            gridRefField.setText("");
            addressField.setText("");
            PhoneField.setText("");
            TranslationField.setText("");
            munroField.setText("");
            timeField.setText("");
            recordField.setText("");
           
        }

        else {

            // Then ask theClimber to look up the chosen text in its memberslist, and to return the associated member
            Member member = theClimber.lookupMember(chosen);
            // Finally display the corresponding variables in the number field

            numberField.setText(String.valueOf(member.getId()));
            PhoneField.setText(member.getPhone());
            addressField.setText(member.getAddress());
            recordField.setText(member.getRecord());
            timeField.setText(member.getTime());
            climb.setText(member.getName());

            // ask munro to look up the chosen text in its mounatinlist, and to return the associated munro
            Mountain mountain = munro.lookupMountain(munroChoice);
            // Finally display the corresponding variables in the number field
            HeightField.setText(String.valueOf(mountain.getFeet()));
            gridRefField.setText(mountain.getReference());
            munroField.setText(mountain.getNo());
            TranslationField.setText(mountain.getTranslation());

        }
        // updateNumberField
    }
}  //class ClubGUI

            