package Chapter10.Graphs;

import com.teamdev.jxbrowser.chromium.Browser;
//import com.teamdev.jxbrowser.chromium.BrowserFactory;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import javax.swing.*;
import java.awt.*;

import Chapter10.Graphs.gui.GraphGUI;

import javax.swing.*;

public class GraphDriver
{
    public static void main(String[] args)
    {
        Browser browser = new Browser();
        BrowserView view = new BrowserView(browser);

        JFrame frame = new JFrame();
        frame.add(view, BorderLayout.CENTER);
        frame.setSize(700, 500);
        frame.setVisible(true);

        browser.loadURL("http://www.google.com");


//        Browser browser = BrowserFactory.create();
//        JFrame frame = new JFrame("JxBrowser Google Maps");
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.add(browser.getView().getComponent(), BorderLayout.CENTER);
//        frame.setSize(700, 500);
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//        browser.loadURL("http://maps.google.com");


    }
}
