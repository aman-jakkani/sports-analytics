package edu.sportanalytics.control;

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
    }

    private static Container buildMainFrame()
    {
        Container contentPane = new Container();
        contentPane.setLayout(new BorderLayout());

        contentPane.add(Logging.getLoggingArea(), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onExit(0);
            }
        });

        buttonPanel.add(exitButton);

        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        return contentPane;
    }

    private static void onExit(int status)
    {
        log.info("Exiting with status: " + status);
        System.exit(status);
    }
}
