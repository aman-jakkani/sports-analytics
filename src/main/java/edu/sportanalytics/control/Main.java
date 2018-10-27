package edu.sportanalytics.control;

import edu.sportanalytics.database.DBAccess;
import edu.sportanalytics.database.DatabaseProperties;
import edu.sportanalytics.guiinterface.RestServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;

class Main
{
    public final static Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args)
    {
        JFrame serverFrame = new JFrame("Sportanalytics Server");
        serverFrame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                super.windowClosing(e);
                onExit(0);
            }
        });
        serverFrame.setSize(800,600);
        serverFrame.setResizable(false);

        serverFrame.setContentPane(buildMainFrame());

        serverFrame.setVisible(true);
        log.info("Main window initialized");

        RestServer.getInstance(); //only for starting
        DBAccess.getInstance().establishConnection();

        log.info("Finished initialization");
    }

    private static Container buildMainFrame()
    {

        Container contentPane = new Container();
        contentPane.setLayout(new BorderLayout());

        JScrollPane sp = new JScrollPane(Logging.getLoggingArea());

        contentPane.add(sp, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());

        final DBAccess connection = DBAccess.getInstance();
        JButton serverPropertiesButton = new JButton("DatabaseProperties");
        serverPropertiesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DatabaseProperties(connection);
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onExit(0);
            }
        });

        buttonPanel.add(serverPropertiesButton);
        buttonPanel.add(exitButton);

        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        return contentPane;
    }

    private static void onExit(int status)
    {
        try
        {
            RestServer.getInstance().close();
            DBAccess.getInstance().resetConnection();
        }
        catch (Exception e)
        {
            log.severe("Fatal Error: " +e.getMessage());
        }
        finally
        {
            log.info("Exiting with status: " + status);
            System.exit(status);
        }
    }
}
