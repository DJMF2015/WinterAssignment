 

/*
This is the main launcher class.
Mountain Club database application



SBJ/DF  January 2016
*/

public class Club {

    // Set up the application
    public static void main(String[] args) {

      ClubGUI demo = new ClubGUI();
      demo.setSize(600,650);       // Width, height of window
      demo.setLocation(200,200);   // Where on the screen
      demo.setVisible(true);

      
       DrawPhoto app = new DrawPhoto();
       app.setLocation(200,200);
       app.pack();   // This makes the window exactly the right size for its laid out contents
                      // or could instead explicitly set the size, eg app.setSize(600,500);
        app.setVisible(true);
    } // main

} // class Club
