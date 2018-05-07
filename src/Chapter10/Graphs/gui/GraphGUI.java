package Chapter10.Graphs.gui;

import javax.print.DocFlavor;
import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class GraphGUI extends JFrame
{
    private static final int WIDTH = 960, HEIGHT = 720;

    public GraphGUI()
    {
        super();
        setSize(WIDTH, HEIGHT);
        this.setTitle("Demo");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setVisible(true);
        GMap();
    }

    private void GMap()
    {
        try
        {
            String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?center=40.714728,-73.998672&zoom=11&size=612x612&scale=2&maptype=roadmap";
            String destinationFile = "imgage.jpg";
            String str = destinationFile;
            URL url = new URL(imageUrl);

            InputStream inputStream = url.openStream();
            OutputStream outputStream = new FileOutputStream((destinationFile));

            byte[] b = new byte[2048];
            int length;

            while((length = inputStream.read(b))!= -1)
            {
                outputStream.write(b,0, length);
            }
        }

        catch(IOException e)
        {
            e.printStackTrace();
            System.out.println("Wopss");
            System.exit(1);
        }

        this.add(new JLabel(new ImageIcon((new ImageIcon("image.jpg")).getImage().getScaledInstance(630,600, Image.SCALE_SMOOTH))));

        this.setVisible(true);
        this.pack();

    }

}
