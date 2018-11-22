package edu.sportanalytics.database;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.NumberFormatter;

public class DatabaseProperties extends JFrame
{

    private static final Logger log = Logger.getLogger(DatabaseProperties.class.getName());

    private DBAccess connection;

    public DatabaseProperties(DBAccess connection)
    {
        this.connection = connection;
        buildDialog();

        log.info("Created database properties dialog.");
    }

    private void buildDialog()
    {
        this.getContentPane().setLayout(new GridLayout(6,2));

        //server url
        JLabel serverURLL = new JLabel("ServerURL:", JLabel.TRAILING);
        this.getContentPane().add(serverURLL);
        final JTextField serverURLTF = new JTextField();
        serverURLL.setLabelFor(serverURLTF);
        this.getContentPane().add(serverURLTF);

        //portnumber
        JLabel portL = new JLabel("Port:", JLabel.TRAILING);
        this.getContentPane().add(portL);
        //interger only
        NumberFormat format = NumberFormat.getInstance();
        format.setGroupingUsed(false);
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(9999);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);
        final JFormattedTextField portTF = new JFormattedTextField(formatter);
        portL.setLabelFor(portTF);
        this.getContentPane().add(portTF);

        //SID
        JLabel sidL = new JLabel("SID:", JLabel.TRAILING);
        this.getContentPane().add(sidL);
        final JTextField sidTF = new JTextField();
        sidL.setLabelFor(sidTF);
        this.getContentPane().add(sidTF);


        //username
        JLabel usernameL = new JLabel("Username:", JLabel.TRAILING);
        this.getContentPane().add(usernameL);
        final JTextField usernameTF = new JTextField();
        usernameL.setLabelFor(usernameTF);
        this.getContentPane().add(usernameTF);

        //password
        JLabel passwordL = new JLabel("Password:", JLabel.TRAILING);
        this.getContentPane().add(passwordL);
        final JPasswordField passwordTF = new JPasswordField();
        passwordL.setLabelFor(passwordTF);
        this.getContentPane().add(passwordTF);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                connection.setDBParams(
                        serverURLTF.getText(),
                        Integer.valueOf(portTF.getText()).toString(),
                        sidTF.getText(),
                        usernameTF.getText(),
                        passwordTF.getPassword());
                log.info("Database properties changed.");
                exit();
            }
        });
        this.getContentPane().add(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                log.info("Database properties dialog canceled.");
                exit();
            }
        });
        this.getContentPane().add(cancelButton);

        //init
        serverURLTF.setText(connection.getServerURL());
        portTF.setText(connection.getPort());
        sidTF.setText(connection.getSid());
        usernameTF.setText(connection.getUsername());

        this.getRootPane().setDefaultButton(okButton);
        okButton.requestFocus();

        this.pack();
        this.setVisible(true);
        passwordTF.requestFocusInWindow();
    }

    private void exit()
    {
        dispose();
    }
}
