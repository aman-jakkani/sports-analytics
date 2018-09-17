package edu.sportanalytics.control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main
{
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
        System.exit(status);
    }
}
