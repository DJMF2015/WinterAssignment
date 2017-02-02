/**
 *  @author:SBJ/2125379
 * 
 * Date: 21 january, 2016
 */
import java.awt.*;
import javax.swing.*;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.*;

public class PhotoPanel extends JPanel{

    // Constructor: only need to set up the size here, so the layout manager knows
    public PhotoPanel() {

        setBackground(Color.orange);                                  // This let us see how big the panel is!
        setPreferredSize(new Dimension(400,400));
        setLayout(new BorderLayout(20, 30));
        setBorder(new SoftBevelBorder(BevelBorder.RAISED));
    }

    // DrawingPanel constructor
    private Image image;                                           // The image on display just now (null initially)
    // Set a new image to be displayed, and refresh the panel on-screen
    public void setImage(Image newImage) {

        image = newImage;
        repaint();

    } // setImage

    // And this actually draws the image, centred in the panel:
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        if (image != null) {                                         // Make sure that there is an image
            int xOffset = (getWidth()-image.getWidth(this))/2;       // x and y offsets to center the image in the panel
            int yOffset = (getHeight()-image.getHeight(this))/2;
            g.drawImage(image, xOffset, yOffset, this);    
            g.setColor(Color.white);
            g.drawString("Source: Munro Scotland: www.munro-scotland.com",10,380);
            g.setColor(Color.white);

            // Note: Coordinates relative to this panel
        }
    } 
    // paintComponent

    
}

