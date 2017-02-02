/**
Displays selected photos from file in an application's window.

Shows a window with wigets that when activated draw selected photo
on drawing panel positioned between two image selector buttons.

SBJ/2125379 January 2016
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DrawPhoto extends JFrame
implements ActionListener {

    // Fetch the six images from given file names
    private Image CoImage = getToolkit().getImage("10.jpg"),
    DoImage = getToolkit().getImage("14.jpg"),
    EoImage = getToolkit().getImage("18.jpg"),
    FoImage = getToolkit().getImage("33.jpg"),
    LoImage = getToolkit().getImage("35.jpg"),
    MoImage = getToolkit().getImage("25.jpg");

    private JButton choice1 = new JButton("Show photos"),       // Image choice buttons
    choice2 = new JButton("Show photos"),
    choice3 = new JButton("Show photos"),
    choice4 = new JButton("Show photos"),
    choice5 = new JButton("Show photos"),
    choice6 = new JButton("Show photos");

    private PhotoPanel thePicture = new PhotoPanel();             // Widget for displaying the image
    // Constructor: lays out the display
    public DrawPhoto() {

        setTitle("Mountain Club Selected Photos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);                      // close box click event

        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout());
        contentPane.add(choice1);
        choice1.addActionListener(this);
        contentPane.add(thePicture);
        contentPane.add(choice2);
        choice2.addActionListener(this);
        contentPane.add(choice2);
        contentPane.add(choice3);
        choice3.addActionListener(this);
        contentPane.add(thePicture);
        contentPane.add(choice4);
        choice4.addActionListener(this);
        contentPane.add(thePicture);
        contentPane.add(choice5);
        choice5.addActionListener(this);
        contentPane.add(thePicture);
        contentPane.add(choice6);
        choice6.addActionListener(this);
        contentPane.add(thePicture);

    } // DrawImage constructor

    // actionPerformed: choose a picture to display and notify the drawing panel (it repaints itself)
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == choice1)
            thePicture.setImage(CoImage);

        if(e.getSource() == choice2)
            thePicture.setImage(EoImage);

        if(e.getSource() == choice3)
            thePicture.setImage(LoImage);

        if(e.getSource() == choice4)
            thePicture.setImage(FoImage);

        if(e.getSource() == choice5)
            thePicture.setImage(MoImage);

        if(e.getSource() == choice6)
            thePicture.setImage(DoImage);

    } // actionPerfomed

} // class DrawImage



